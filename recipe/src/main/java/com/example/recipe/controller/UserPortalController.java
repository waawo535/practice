package com.example.recipe.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.recipe.base.BaseController;
import com.example.recipe.common.util.CommonConst;
import com.example.recipe.service.UserPortalService;

@Controller
@RequestMapping("/UserPortal")
public class UserPortalController extends BaseController {
	
	UserPortalService userPortalService;
	
	public UserPortalController(HttpServletRequest request, HttpSession session, UserPortalService userPortalService) {
		super(request, session);
		this.userPortalService = userPortalService;
	}
	
	@GetMapping("/initShow")
	public String initShow() {
		
		//F層呼び出しで画面に表示する項目を取得
		
		
		
		return CommonConst.SCREENID_USERPORTAL;
	}
}
