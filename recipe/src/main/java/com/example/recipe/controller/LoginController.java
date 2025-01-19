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
import com.example.recipe.dto.SuccessMessageDto;
import com.example.recipe.dto.ServiceIn.LoginServiceLoginIn;
import com.example.recipe.dto.ServiceOut.LoginServiceLoginOut;
import com.example.recipe.dto.view.LoginDto;
import com.example.recipe.service.LoginService;

@Controller
@RequestMapping("/Login")
public class LoginController extends BaseController {
	
	private final HttpSession session;
	
	private final MessageSource messageSource;
	
	private final SingleFieldCheck singleFieldCheck;
	
	private final LoginService loginService;
	
	public LoginController(HttpServletRequest request, HttpSession session, MessageSource messageSource, SingleFieldCheck singleFieldCheck, LoginService loginService) {
		super(request, session);
		this.session = session;
		this.messageSource = messageSource;
		this.singleFieldCheck = singleFieldCheck;
		this.loginService = loginService;
	}
	
	/**
	 * ログイン画面初期表示
	 * @param model
	 * @return
	 */
	@GetMapping("/initShow")
	public String initShow(Model model) {
		//リダイレクト元にmodelの中身があることを考慮
		if (!model.containsAttribute(CommonConst.KEY_LOGIN_DTO)) {
			LoginDto dto = new LoginDto();
	        model.addAttribute(CommonConst.KEY_LOGIN_DTO, dto);
	    }
		if (!model.containsAttribute(CommonConst.KEY_PREVSCREEN)) {
	        model.addAttribute(CommonConst.KEY_PREVSCREEN, null);
	    }
		if (!model.containsAttribute(CommonConst.KEY_SYSYTEMINFO_DTO)) {
			SessionInfoDto sessionInfoDto = new SessionInfoDto();
	        model.addAttribute(CommonConst.KEY_SYSYTEMINFO_DTO, sessionInfoDto);
	    }
		return CommonConst.SCREENID_LOGIN;
	}
	
	/**
	 * ログイン
	 * @param loginDto
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/login")
	public String login(LoginDto loginDto, Model model, RedirectAttributes redirectAttributes) {
		
		//単項目チェック
		checkSingleField(loginDto);
		
		//F層呼び出しでDB整合性チェックとユーザ情報取得
		LoginServiceLoginIn loginServiceLoginIn = new LoginServiceLoginIn();
		loginServiceLoginIn.setEmailAddress(loginDto.getEmailAddress());
		loginServiceLoginIn.setPassword(loginDto.getPassword());
		LoginServiceLoginOut loginServiceLoginOut = loginService.login(loginServiceLoginIn);
		
		//DB整合性がない場合、エラーメッセージを設定する
		//メールアドレスがDBに存在しない場合
		if(!loginServiceLoginOut.isMailAddressExists()) {
			//エラーメッセージを設定してログイン画面にリダイレクトする
			String errorMessage = messageSource.getMessage("E200", new Object[] { }, Locale.JAPAN);
			setErrorMessageList(loginDto, errorMessage);
			
			redirectAttributes.addFlashAttribute(CommonConst.KEY_LOGIN_DTO, loginDto);
			return CommonConst.REDIRECT_LOGIN;
			
			//メールアドレスがDBに存在する場合
			//本登録ではない又はパスワード整合性がない場合
		}else if(!loginServiceLoginOut.isDefinitivlyRegistered()||!loginServiceLoginOut.isPasswordCheckPassed()){
			//仮登録の場合
			if(loginServiceLoginOut.isProvisionallyRegistered()) {
				String errorMessage = messageSource.getMessage("E208", new Object[] { }, Locale.JAPAN);
				setErrorMessageList(loginDto, errorMessage);
				
				//パスワード整合性がない場合
			}else if(!loginServiceLoginOut.isPasswordCheckPassed()) {
				String errorMessage = messageSource.getMessage("E200", new Object[] { }, Locale.JAPAN);
				setErrorMessageList(loginDto, errorMessage);
			}
			redirectAttributes.addFlashAttribute(CommonConst.KEY_LOGIN_DTO, loginDto);
			return CommonConst.REDIRECT_LOGIN;
		}
		
		//セッションにユーザ情報を設定
		SessionInfoDto sessionInfoDto = new SessionInfoDto();
		sessionInfoDto.setUserId(loginServiceLoginOut.getUserId());
		sessionInfoDto.setUserName(loginServiceLoginOut.getUserName());
		sessionInfoDto.setProfileImgUrl(loginServiceLoginOut.getProfileImgUrl());
		sessionInfoDto.setPrevScreen(CommonConst.SCREENID_LOGIN);
		session.setAttribute(CommonConst.KEY_SESSIONINFO_DTO, sessionInfoDto);
		
		return CommonConst.SCREENID_USERPORTAL;
	}
	
	//ログアウト
	@GetMapping("/logout")
	public String logout(SessionInfoDto sessionInfoDto, RedirectAttributes redirectAttributes) {
		String successMessage = messageSource.getMessage("I103", new Object[] { }, Locale.JAPAN);
		setSuccessMessageList(sessionInfoDto, successMessage);
		redirectAttributes.addFlashAttribute(CommonConst.KEY_SYSYTEMINFO_DTO, sessionInfoDto);
		redirectAttributes.addFlashAttribute(CommonConst.KEY_PREVSCREEN, CommonConst.SCREENID_LOGOUT);
		session.invalidate();
		return CommonConst.REDIRECT_LOGIN;
	}
	/**
	 * 単項目チェック
	 * @param userRegistrationFormDto
	 * @param model
	 */
	private void checkSingleField(LoginDto loginDto) {
		
		//メールアドレス単項目チェック
		//最大桁数チェック
		if(singleFieldCheck.checkMaxCharLimit(loginDto.getEmailAddress(), 255)) {
			String errorMessage = messageSource.getMessage("E101", new Object[] { "メールアドレス", "255" }, Locale.JAPAN);
			setErrorMessageList(loginDto, errorMessage);
		}
		//型チェック
		if(!singleFieldCheck.checkRegEx(loginDto.getEmailAddress(), CommonConst.REGEX_EMAIL)) {
			String errorMessage = messageSource.getMessage("E103", new Object[] { }, Locale.JAPAN);
			setErrorMessageList(loginDto, errorMessage);
		}
		//禁則文字チェック
		SingleFieldCheckCheckForBiddenCharOut singleFieldCheckCheckForBiddenCharOut1 = singleFieldCheck.checkForbiddenChar(loginDto.getEmailAddress());
		if(singleFieldCheckCheckForBiddenCharOut1.isResult()) {
			String errorMessage = messageSource.getMessage("E111", new Object[] { "メールアドレス", singleFieldCheckCheckForBiddenCharOut1.getErrorList()}, Locale.JAPAN);
			setErrorMessageList(loginDto, errorMessage);
		}
		//null又は空文字チェック
		if(NullOrEmptyChecker.isNullOrEmpty(loginDto.getEmailAddress())) {
			String errorMessage = messageSource.getMessage("E100", new Object[] {"メールアドレス" }, Locale.JAPAN);
			setErrorMessageList(loginDto, errorMessage);
		}
		
		//パスワード単項目チェック
		//桁数チェック
		if(singleFieldCheck.checkCharLimit(loginDto.getPassword(), 64, 8)) {
			String errorMessage = messageSource.getMessage("E114", new Object[] { "パスワード", "8", "64" }, Locale.JAPAN);
			setErrorMessageList(loginDto, errorMessage);
		}
		//型チェック
		if(!singleFieldCheck.isHalfWidthAlphanumSymbol(loginDto.getPassword())) {
			String errorMessage = messageSource.getMessage("E112", new Object[] { "パスワード"}, Locale.JAPAN);
			setErrorMessageList(loginDto, errorMessage);
		}
		//禁則文字チェック
		SingleFieldCheckCheckForBiddenCharOut singleFieldCheckCheckForBiddenCharOut2 = singleFieldCheck.checkForbiddenChar(loginDto.getPassword());
		if(singleFieldCheckCheckForBiddenCharOut2.isResult()) {
			String errorMessage = messageSource.getMessage("E111", new Object[] { "パスワード", singleFieldCheckCheckForBiddenCharOut2.getErrorList()}, Locale.JAPAN);
			setErrorMessageList(loginDto, errorMessage);
		}
		//null又は空文字チェック
		if(NullOrEmptyChecker.isNullOrEmpty(loginDto.getPassword())) {
			String errorMessage = messageSource.getMessage("E105", new Object[] {"パスワード" }, Locale.JAPAN);
			setErrorMessageList(loginDto, errorMessage);
		}
	}
	
	/**
	 * 画面に表示するエラーメッセージの設定
	 * @param userRegistrationFormDto
	 * @param errorMessage
	 */
	private void setErrorMessageList(LoginDto loginDto, String errorMessage) {
		ArrayList<ErrorMessageDto> errorMessageList = loginDto.getErrormessageAreaList();
		ErrorMessageDto errorMessageDto = new ErrorMessageDto();
		errorMessageDto.setErrorMessage(errorMessage);
		errorMessageList.add(errorMessageDto);
	}
	
	/**
	 * 画面に表示する成功メッセージの設定
	 * @param userRegistrationFormDto
	 * @param errorMessage
	 */
	private void setSuccessMessageList(SessionInfoDto sessionInfoDto, String successMessage) {
		ArrayList<SuccessMessageDto> successmessageAreaList = sessionInfoDto.getSuccessmessageAreaList();
		SuccessMessageDto successMessageDto = new SuccessMessageDto();
		successMessageDto.setSuccessMessage(successMessage);
		successmessageAreaList.add(successMessageDto);
	}
}
