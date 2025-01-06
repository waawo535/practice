package com.example.recipe.controler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.recipe.base.BaseController;
import com.example.recipe.dto.UserRegistrationFormDto;
import com.example.recipe.service.UserRegistrationService;

@Controller
@RequestMapping("UserRegistration")
public class UserRegistrationController extends BaseController {
	
	@Autowired
	private final HttpSession session;
	
	@Autowired
	private final UserRegistrationService userRegistrationService;
	
	public UserRegistrationController(HttpServletRequest request, HttpSession session, UserRegistrationService userRegistrationService) {
		super(request, session);
		this.session = session;
		this.userRegistrationService = userRegistrationService;
	}
	
	/*
	 * ユーザ登録画面初期表示
	 */
	@GetMapping("initShow")
	public String initShow(Model model) {
		
		
		return "UserRegistration";
	}
	
	/*
	 * ユーザ登録
	 */
	@PostMapping("register")
	public String register(@ModelAttribute  UserRegistrationFormDto userRegistrationFormDto) {
		
		userRegistrationService.register(userRegistrationFormDto.getUserName());
		
		return "dummy";
	}
}
