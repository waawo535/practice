package com.example.recipe.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.recipe.base.BaseController;
import com.example.recipe.dto.TestServiceIn;
import com.example.recipe.service.TestService;

@Controller
@RequestMapping("/test")
public class TestController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	/*
	 * セッション情報
	 */
	private final HttpSession session;
	
	/*
	 * サービスクラス
	 */
	private final TestService testService;
	
	public TestController(HttpServletRequest request, HttpSession session, TestService testService) {
		super(request, session);
		this.session = session;
		this.testService = testService;
	}
	
	@GetMapping("/initShow")
	public String initShow(Model model) {
		logger.info("initShow() method invoked!");
		TestServiceIn testServiceIn = new TestServiceIn();
		testServiceIn.setRecipeId(1);
		
		String screenId = "test";
		model.addAttribute("TestServiceDto",testService.getSomeThing(testServiceIn));
		return screenId;
	}
}
