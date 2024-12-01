package com.example.controler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.base.BaseController;
import com.example.dto.TestServiceOut;
import com.example.service.TestService;

@Controller
@RequestMapping("/test")
public class TestController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	/*
	 * セッション情報
	 */
	@Autowired
	private final HttpSession session;
	
	/*
	 * サービスクラス
	 */
	@Autowired
	private final TestService testService;
	
	public TestController(HttpServletRequest request, HttpSession session, TestService testService) {
		super(request, session);
		this.session = session;
		this.testService = testService;
	}
	
	@RequestMapping("/initShow")
	public String initShow(Model model) {
		logger.info("initShow() method invoked!");
		
		String screenId = "test";
		
		TestServiceOut testServiceOut = testService.getSomeThing(null);
		
		model.addAttribute("TestServiceDto", testServiceOut);
		
		return screenId;
	}
}
