package com.example.recipe.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.recipe.base.BaseController;
import com.example.recipe.common.util.CommonConst;

@Controller
@RequestMapping("/UserPortal")
public class UserPortalController extends BaseController {

	public UserPortalController(HttpServletRequest request, HttpSession session) {
		super(request, session);
	}
	
	@GetMapping("/initShow")
	public String initShow() {
		
		return CommonConst.SCREENID_USERPORTAL;
	}
}
