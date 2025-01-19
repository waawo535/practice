package com.example.recipe.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.recipe.base.BaseController;
import com.example.recipe.common.util.CommonConst;
import com.example.recipe.dto.SessionInfoDto;
import com.example.recipe.dto.ServiceIn.UserInfoManagementShowUserInfoIn;
import com.example.recipe.dto.ServiceOut.UserInfoManagementShowUserInfoOut;
import com.example.recipe.service.UserInfoManagementService;

@Controller
@RequestMapping("/UserInfoManagement")
public class UserInfoManagementController extends BaseController {
	
	UserInfoManagementService userInfoManagementService;
	
	public UserInfoManagementController(HttpServletRequest request, HttpSession session, UserInfoManagementService userInfoManagementService) {
		super(request, session);
		this.userInfoManagementService = userInfoManagementService;
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
		UserInfoManagementShowUserInfoOut outDto = userInfoManagementService.showUserInfo(inDto);
		
		//取得したユーザ情報をmodelに設定
		model.addAttribute(CommonConst.KEY_USERINFOMANAGEMENT_DTO, outDto);
		
		return CommonConst.SCREENID_USERINFOMANAGEMENT;
	}
	
	
	@PostMapping("/editUserInfo")
	public String editUserInfo() {
		
		
		return CommonConst.REDIRECT_USERINFOMANAGEMENT;
	}
}
