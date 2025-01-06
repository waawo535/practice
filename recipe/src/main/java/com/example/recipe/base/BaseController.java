package com.example.recipe.base;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseController {
	
	protected final HttpServletRequest request;
	
	@Autowired
	private final HttpSession session;
	
	public BaseController(HttpServletRequest request, HttpSession session) {
		this.request = request;
		this.session = session;
	}
}
