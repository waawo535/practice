package com.example.recipe.base;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public abstract class BaseController {
	
	protected final HttpServletRequest request;

	protected final HttpSession session;
	
	public BaseController(HttpServletRequest request, HttpSession session) {
		this.request = request;
		this.session = session;
	}
}
