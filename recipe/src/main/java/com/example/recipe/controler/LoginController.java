package com.example.recipe.controler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import com.example.recipe.base.BaseController;

public class LoginController extends BaseController {
	
	private final HttpSession session;
	
	public LoginController(HttpServletRequest request, HttpSession session) {
		super(request, session);
		this.session = session;
	}
	
	
}
