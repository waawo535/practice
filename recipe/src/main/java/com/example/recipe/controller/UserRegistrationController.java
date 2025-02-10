/**
 * ユーザ登録画面
 */

package com.example.recipe.controller;

import java.util.ArrayList;
import java.util.Locale;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.recipe.base.BaseController;
import com.example.recipe.common.checker.NullOrEmptyChecker;
import com.example.recipe.common.checker.SingleFieldCheck;
import com.example.recipe.common.util.CommonConst;
import com.example.recipe.dto.ErrorMessageDto;
import com.example.recipe.dto.SessionInfoDto;
import com.example.recipe.dto.SingleFieldCheckCheckForBiddenCharOut;
import com.example.recipe.dto.UserRegistrationFormDto;
import com.example.recipe.dto.ServiceIn.UserRegistrationServiceCheckTokenIn;
import com.example.recipe.dto.ServiceIn.UserRegistrationServiceIn;
import com.example.recipe.dto.ServiceOut.UserRegistrationServiceCheckTokenOut;
import com.example.recipe.dto.ServiceOut.UserRegistrationServiceOut;
import com.example.recipe.dto.view.UserAuthenticationDto;
import com.example.recipe.dto.view.UserRegistrationDto;
import com.example.recipe.service.EmailService;
import com.example.recipe.service.UserRegistrationService;

@Controller
@RequestMapping("/UserRegistration")
public class UserRegistrationController extends BaseController {
	
	private final UserRegistrationService userRegistrationService;
	
	private final SingleFieldCheck singleFieldCheck;
	
    private final MessageSource messageSource;
	
    private final EmailService emailService;
    
	public UserRegistrationController(HttpServletRequest request, HttpSession session, UserRegistrationService userRegistrationService, SingleFieldCheck singleFieldCheck, MessageSource messageSource, EmailService emailService) {
		super(request, session);
		this.userRegistrationService = userRegistrationService;
		this.singleFieldCheck = singleFieldCheck;
		this.messageSource = messageSource;
		this.emailService = emailService;
		}
	
	/*
	 * ユーザ登録画面初期表示
	 */
	@GetMapping("/initShow")
	public String initShow(Model model) {
		if (!model.containsAttribute(CommonConst.KEY_USERREGISTRATION_DTO)) {
	        UserRegistrationFormDto dto = new UserRegistrationFormDto();
	        model.addAttribute(CommonConst.KEY_USERREGISTRATION_DTO, dto);
	    }
		if(!model.containsAttribute(CommonConst.KEY_PREVSCREEN)) {
			model.addAttribute(CommonConst.KEY_PREVSCREEN, null);
		}
		return CommonConst.SCREENID_USERREGISTRATION;
	}
	
	/*
	 * ユーザ登録
	 */
	@PostMapping("/register")
	public String register(UserRegistrationFormDto userRegistrationFormDto, Model model, RedirectAttributes redirectAttributes) {
		
		//単項目チェック
		checkSingleField(userRegistrationFormDto);
		
		//単項目エラー存在チェック
		if(!(userRegistrationFormDto.getErrormessageAreaList().isEmpty())) {
			//エラーメッセージが存在する場合
			redirectAttributes.addFlashAttribute(CommonConst.KEY_USERREGISTRATION_DTO, userRegistrationFormDto);
			//自画面に遷移
			return CommonConst.REDIRECT_USERREGISTRATION;
		}
		
		//F層呼び出し
		//メアドとパスワードの整合性チェック
		UserRegistrationServiceIn userRegistrationServiceIn = new UserRegistrationServiceIn();
		userRegistrationServiceIn.setEmailAddress(userRegistrationFormDto.getEmailAddress());
		userRegistrationServiceIn.setPassword(userRegistrationFormDto.getPassword());
		userRegistrationServiceIn.setComfirmPassword(userRegistrationFormDto.getConfrimPassword());
		UserRegistrationServiceOut userRegistrationServiceOut = userRegistrationService.register(userRegistrationServiceIn);
		
		//メールアドレスが登録済み又はパスワードとパスワード（確認用）の整合性がない場合
		if(userRegistrationServiceOut.isProvisionallyRegistered()||userRegistrationServiceOut.isDefinitivlyRegistered()||!(userRegistrationServiceOut.isPasswordCheckFlg())) {
			//メールアドレスが仮登録済みの場合
			if(userRegistrationServiceOut.isProvisionallyRegistered()) {
				String errorMessage = messageSource.getMessage("E208", new Object[] {}, Locale.JAPAN);
				setErrorMessageList(userRegistrationFormDto, errorMessage);
				
				//メールアドレスが本登録済みの場合
			}else if(userRegistrationServiceOut.isDefinitivlyRegistered()) {
				String errorMessage = messageSource.getMessage("E402", new Object[] {}, Locale.JAPAN);
				setErrorMessageList(userRegistrationFormDto, errorMessage);
				
				//パスワードとパスワード（確認用）の整合性がない場合
			}else if(!(userRegistrationServiceOut.isPasswordCheckFlg())) {
				String errorMessage = messageSource.getMessage("E406", new Object[] {}, Locale.JAPAN);
				setErrorMessageList(userRegistrationFormDto, errorMessage);
			}
			
			//リダイレクト後にフォームにメアドとパスワードが入力されている状態にしてもいいかも（余裕があれば実装する）
			redirectAttributes.addFlashAttribute(CommonConst.KEY_USERREGISTRATION_DTO, userRegistrationFormDto);
			//自画面に遷移
			return CommonConst.REDIRECT_USERREGISTRATION;
		}
		
		UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
		userRegistrationDto.setUserId(userRegistrationServiceOut.getUserId());
		userRegistrationDto.setEmailAddress(userRegistrationFormDto.getEmailAddress());
		session.setAttribute(CommonConst.KEY_SESSIONINFO_DTO, userRegistrationDto);
		
		sendVerificationEmail(userRegistrationFormDto.getEmailAddress(), userRegistrationServiceOut.getToken());
		
		UserAuthenticationDto userAuthenticationDto = new UserAuthenticationDto();
		userAuthenticationDto.setUserId(null);
		
		model.addAttribute(CommonConst.KEY_USERREGISTRATION_DTO, userRegistrationFormDto);
		model.addAttribute(CommonConst.KEY_USERAUTHENTICATION_DTO, userAuthenticationDto);
		
		//認証画面に遷移
		return CommonConst.SCREENID_AUTHENTICATION;
	}
	
	//ログイン画面から認証画面に戻る用
	@GetMapping("/registerToAuthentication")
	public String redirectLoginToAuthentication(Model model, RedirectAttributes redirectAttributes) {
		UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
		redirectAttributes.addFlashAttribute(CommonConst.KEY_USERREGISTRATION_DTO, userRegistrationDto);
		redirectAttributes.addFlashAttribute(CommonConst.KEY_PREVSCREEN, CommonConst.SCREENID_USERREGISTRATION);
		return CommonConst.REDIRECT_AUTHENTICATION;
	}
	
	/**
	 * メール認証
	 * @return
	 */
	@PostMapping("/authentication")
	public String authentication(UserRegistrationFormDto userRegistrationFormDto, Model model, RedirectAttributes redirectAttributes) {
		
		UserRegistrationDto userDto = (UserRegistrationDto) session.getAttribute(CommonConst.KEY_SESSIONINFO_DTO);
		
		//認証コードを検証する
		UserRegistrationServiceCheckTokenIn userRegistrationServiceCheckTokenIn = new UserRegistrationServiceCheckTokenIn();
		userRegistrationServiceCheckTokenIn.setUserId(userDto.getUserId());
		userRegistrationServiceCheckTokenIn.setEmailAddress(userDto.getEmailAddress());
		userRegistrationServiceCheckTokenIn.setToken(userRegistrationFormDto.getToken());
		UserRegistrationServiceCheckTokenOut userRegistrationServiceCheckTokenOut = userRegistrationService.checkToken(userRegistrationServiceCheckTokenIn);
		
		//分岐開始：検証に失敗した場合は認証画面にリダイレクトする
		if(!userRegistrationServiceCheckTokenOut.isRegistrationStatusFlg()||!userRegistrationServiceCheckTokenOut.isTokenValidateFlg()||!userRegistrationServiceCheckTokenOut.isExpiryDateValidateFlg()) {
			//登録ステータスが仮登録以外の場合
			if(!userRegistrationServiceCheckTokenOut.isRegistrationStatusFlg()) {
				String errorMessage = messageSource.getMessage("E207", new Object[] {}, Locale.JAPAN);
				setErrorMessageList(userRegistrationFormDto, errorMessage);
			
				//認証の有効期限が切れていた場合
			}else if(!userRegistrationServiceCheckTokenOut.isExpiryDateValidateFlg()) {
				String errorMessage = messageSource.getMessage("E206", new Object[] {}, Locale.JAPAN);
				setErrorMessageList(userRegistrationFormDto, errorMessage);
				
				//認証コード不一致の場合
			}else if(!userRegistrationServiceCheckTokenOut.isTokenValidateFlg()) {
				String errorMessage = messageSource.getMessage("E205", new Object[] {}, Locale.JAPAN);
				setErrorMessageList(userRegistrationFormDto, errorMessage);
			}
			
			redirectAttributes.addFlashAttribute(CommonConst.KEY_USERREGISTRATION_DTO, userRegistrationFormDto);
			return CommonConst.REDIRECT_AUTHENTICATION;
		}
		
		//整合性があればユーザ本登録してユーザポータル画面に遷移する
		UserRegistrationServiceIn userRegistrationServiceIn = new UserRegistrationServiceIn();
		userRegistrationServiceIn.setUserId(userDto.getUserId());
		userRegistrationService.definitiveRegistration(userRegistrationServiceIn);
		
		SessionInfoDto sessionInfoDto = new SessionInfoDto();
		sessionInfoDto.setUserId(userDto.getUserId());
		sessionInfoDto.setPrevScreen(CommonConst.SCREENID_AUTHENTICATION);
		session.setAttribute(CommonConst.KEY_SYSTEMINFO_DTO, sessionInfoDto);
		
		return CommonConst.SCREENID_USERPORTAL;
	}
	
	//認証画面初期表示
	@GetMapping("/authentication/initShow")
	public String authenticationInitShow(Model model) {
		
		UserAuthenticationDto userAuthenticationDto = new UserAuthenticationDto();
		model.addAttribute(CommonConst.KEY_USERAUTHENTICATION_DTO, userAuthenticationDto);
		if(!model.containsAttribute(CommonConst.KEY_PREVSCREEN)) {
			model.addAttribute(CommonConst.KEY_PREVSCREEN, null);
		}
		return CommonConst.SCREENID_AUTHENTICATION;
	}
	
	//認証画面から登録画面にリダイレクトする用の
	@GetMapping("/authentecationToRegister")
	public String redirectAuthentecationToRegister(Model model, RedirectAttributes redirectAttributes) {
		//生成した認証コードを引き継ぐように設定
		UserRegistrationFormDto userRegistrationFormDto = new UserRegistrationFormDto();
		redirectAttributes.addFlashAttribute(CommonConst.KEY_USERREGISTRATION_DTO, userRegistrationFormDto);
		//遷移元画面IDに認証画面を設定
		redirectAttributes.addFlashAttribute(CommonConst.KEY_PREVSCREEN, CommonConst.SCREENID_AUTHENTICATION);
		
		return CommonConst.REDIRECT_USERREGISTRATION;
	}
	
	/**
	 * メール送信
	 * @param email
	 * @param code
	 */
	public void sendVerificationEmail(String email, String token) {
	    String subject = "Email Verification Code";
	    String body = "Your verification code is: " + token;
	    emailService.sendEmail(email, subject, body);
	}

	
	/**
	 * 単項目チェック
	 * @param userRegistrationFormDto
	 * @param model
	 */
	private void checkSingleField(UserRegistrationFormDto userRegistrationFormDto) {
		
		//メールアドレス単項目チェック
		//最大桁数チェック
		if(singleFieldCheck.checkMaxCharLimit(userRegistrationFormDto.getEmailAddress(), 255)) {
			String errorMessage = messageSource.getMessage("E101", new Object[] { "メールアドレス", "255" }, Locale.JAPAN);
			setErrorMessageList(userRegistrationFormDto, errorMessage);
		}
		//型チェック
		if(!singleFieldCheck.checkRegEx(userRegistrationFormDto.getEmailAddress(), CommonConst.REGEX_EMAIL)) {
			String errorMessage = messageSource.getMessage("E103", new Object[] { }, Locale.JAPAN);
			setErrorMessageList(userRegistrationFormDto, errorMessage);
		}
		//禁則文字チェック
		SingleFieldCheckCheckForBiddenCharOut singleFieldCheckCheckForBiddenCharOut1 = singleFieldCheck.checkForbiddenChar(userRegistrationFormDto.getEmailAddress());
		if(singleFieldCheckCheckForBiddenCharOut1.isResult()) {
			String errorMessage = messageSource.getMessage("E111", new Object[] { "メールアドレス", singleFieldCheckCheckForBiddenCharOut1.getErrorList()}, Locale.JAPAN);
			setErrorMessageList(userRegistrationFormDto, errorMessage);
		}
		//null又は空文字チェック
		if(NullOrEmptyChecker.isNullOrEmpty(userRegistrationFormDto.getEmailAddress())) {
			String errorMessage = messageSource.getMessage("E100", new Object[] {"メールアドレス" }, Locale.JAPAN);
			setErrorMessageList(userRegistrationFormDto, errorMessage);
		}
		
		//パスワード単項目チェック
		//桁数チェック
		if(singleFieldCheck.checkCharLimit(userRegistrationFormDto.getPassword(), 64, 8)) {
			String errorMessage = messageSource.getMessage("E114", new Object[] { "パスワード", "8", "64" }, Locale.JAPAN);
			setErrorMessageList(userRegistrationFormDto, errorMessage);
		}
		//型チェック
		if(!singleFieldCheck.isHalfWidthAlphanumSymbol(userRegistrationFormDto.getPassword())) {
			String errorMessage = messageSource.getMessage("E112", new Object[] { "パスワード"}, Locale.JAPAN);
			setErrorMessageList(userRegistrationFormDto, errorMessage);
		}
		//禁則文字チェック
		SingleFieldCheckCheckForBiddenCharOut singleFieldCheckCheckForBiddenCharOut2 = singleFieldCheck.checkForbiddenChar(userRegistrationFormDto.getPassword());
		if(singleFieldCheckCheckForBiddenCharOut2.isResult()) {
			String errorMessage = messageSource.getMessage("E111", new Object[] { "パスワード", singleFieldCheckCheckForBiddenCharOut2.getErrorList()}, Locale.JAPAN);
			setErrorMessageList(userRegistrationFormDto, errorMessage);
		}
		//null又は空文字チェック
		if(NullOrEmptyChecker.isNullOrEmpty(userRegistrationFormDto.getPassword())) {
			String errorMessage = messageSource.getMessage("E105", new Object[] {"パスワード" }, Locale.JAPAN);
			setErrorMessageList(userRegistrationFormDto, errorMessage);
		}
	}
	
	/**
	 * 画面に表示するエラーメッセージの設定
	 * @param userRegistrationFormDto
	 * @param errorMessage
	 */
	private void setErrorMessageList(UserRegistrationFormDto userRegistrationFormDto, String errorMessage) {
		ArrayList<ErrorMessageDto> ErrorMessageList = userRegistrationFormDto.getErrormessageAreaList();
		ErrorMessageDto errorMessageDto = new ErrorMessageDto();
		errorMessageDto.setErrorMessage(errorMessage);
		ErrorMessageList.add(errorMessageDto);
	}
}
