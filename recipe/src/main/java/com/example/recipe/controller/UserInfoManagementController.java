package com.example.recipe.controller;

import java.util.ArrayList;
import java.util.Locale;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
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
import com.example.recipe.dto.ServiceIn.UserInfoManagementShowUserInfoIn;
import com.example.recipe.dto.ServiceOut.UserInfoManagementShowUserInfoOut;
import com.example.recipe.dto.view.UserInfoManagementDto;
import com.example.recipe.service.UserInfoManagementService;

@Controller
@RequestMapping("/UserInfoManagement")
public class UserInfoManagementController extends BaseController {
	
	private final HttpSession session;
	
	private final UserInfoManagementService userInfoManagementService;
	
	private final SingleFieldCheck singleFieldCheck;
	
	private final MessageSource messageSource;
	
	public UserInfoManagementController(HttpServletRequest request, HttpSession session, UserInfoManagementService userInfoManagementService, SingleFieldCheck singleFieldCheck, MessageSource messageSource) {
		super(request, session);
		this.session = session;
		this.userInfoManagementService = userInfoManagementService;
		this.singleFieldCheck = singleFieldCheck;
		this.messageSource = messageSource;
	}
	
	/**
	 *ユーザ情報管理画面 初期表示
	 * @return
	 */
	@GetMapping("/initShow")
	public String initShow(Model model, HttpSession session) {
		
		//セッション情報を取得する
		SessionInfoDto sessionInfoDto = (SessionInfoDto) session.getAttribute(CommonConst.KEY_SYSYTEMINFO_DTO);
		
		//F層呼び出しでユーザ情報を取得
		UserInfoManagementShowUserInfoIn inDto = new UserInfoManagementShowUserInfoIn();
		inDto.setUserId(sessionInfoDto.getUserId());
		UserInfoManagementShowUserInfoOut showUserInfoOut = userInfoManagementService.showUserInfo(inDto);
		
		//画面表示DTOにF層から取得した値ををコピー
		UserInfoManagementDto viewDto =  new UserInfoManagementDto();
		BeanUtils.copyProperties(showUserInfoOut, viewDto);
		
		//取得したユーザ情報をmodelに設定
		model.addAttribute(CommonConst.KEY_USERINFOMANAGEMENT_DTO, viewDto);
		
		return CommonConst.SCREENID_USERINFOMANAGEMENT;
	}
	
	/**
	 * ユーザ情報編集
	 * @param userInfoManagementDto
	 * @return
	 */
	@PostMapping("/editUserInfo")
	public String editUserInfo(UserInfoManagementDto viewDto, RedirectAttributes redirectAttributes) {
		//単項目チェック
		checkSingeField(viewDto);
		
		//エラーの場合初期表示にリダイレクトしエラーメッセージを表示
		if(!(viewDto.getErrormessageAreaList().isEmpty())) {
			//エラーメッセージが存在する場合
			redirectAttributes.addFlashAttribute(CommonConst.KEY_USERINFOMANAGEMENT_DTO, viewDto);
			//自画面に遷移
			return CommonConst.REDIRECT_USERREGISTRATION;
		}
		
		//F層呼び出しでDB更新
		
		
		
		return CommonConst.REDIRECT_USERINFOMANAGEMENT;
	}
	
	/**
	 * 単項目チェック
	 * @param userRegistrationFormDto
	 * @param model
	 */
	private void checkSingeField(UserInfoManagementDto userInfoManagementDto) {
		
		//ユーザ名
		//指定文字数以内確認
		if(singleFieldCheck.checkCharLimit(userInfoManagementDto.getUserName(), 30, 1)) {
			String errorMessage = messageSource.getMessage("E114", new Object[] { "ユーザ名", "1" ,"30"}, Locale.JAPAN);
			setErrorMessageList(userInfoManagementDto, errorMessage);
		}
		//禁則文字チェック
		SingleFieldCheckCheckForBiddenCharOut singleFieldCheckCheckForBiddenCharOut1 = singleFieldCheck.checkForbiddenChar(userInfoManagementDto.getUserName());
		if(singleFieldCheckCheckForBiddenCharOut1.isResult()) {
			String errorMessage = messageSource.getMessage("E111", new Object[] {"ユーザ名", singleFieldCheckCheckForBiddenCharOut1.getErrorList() }, Locale.JAPAN);
			setErrorMessageList(userInfoManagementDto, errorMessage);
		}
		//null空文字チェック
		if(NullOrEmptyChecker.isNullOrEmpty(userInfoManagementDto.getUserName())) {
			String errorMessage = messageSource.getMessage("E100", new Object[] {"ユーザ名"}, Locale.JAPAN);
			setErrorMessageList(userInfoManagementDto, errorMessage);
		}
		//メアドは他コントローラのコピペする
		
		//電話番号
		//指定文字数以内確認
		//正規表現チェック
		//半角数字チェック(型チェック)
		//null空文字チェック
		
		//bio
		//指定文字数以内確認
		if(singleFieldCheck.checkMaxCharLimit(userInfoManagementDto.getBio(), 300)) {
			String errorMessage = messageSource.getMessage("E101", new Object[] { "自己紹介", "300"}, Locale.JAPAN);
			setErrorMessageList(userInfoManagementDto, errorMessage);
		}
		//禁則文字チェック
		SingleFieldCheckCheckForBiddenCharOut singleFieldCheckCheckForBiddenCharOut2 = singleFieldCheck.checkForbiddenChar(userInfoManagementDto.getUserName());
		if(singleFieldCheckCheckForBiddenCharOut2.isResult()) {
			String errorMessage = messageSource.getMessage("E111", new Object[] {"自己紹介", singleFieldCheckCheckForBiddenCharOut2.getErrorList() }, Locale.JAPAN);
			setErrorMessageList(userInfoManagementDto, errorMessage);
		}
		
	}
	/**
	 * 画面に表示するエラーメッセージの設定
	 * @param userRegistrationFormDto
	 * @param errorMessage
	 */
	private void setErrorMessageList(UserInfoManagementDto outDto, String errorMessage) {
		ArrayList<ErrorMessageDto> ErrorMessageList = outDto.getErrormessageAreaList();
		ErrorMessageDto errorMessageDto = new ErrorMessageDto();
		errorMessageDto.setErrorMessage(errorMessage);
		ErrorMessageList.add(errorMessageDto);
	}
}
