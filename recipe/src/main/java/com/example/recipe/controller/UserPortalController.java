package com.example.recipe.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.recipe.base.BaseController;
import com.example.recipe.common.util.CommonConst;
import com.example.recipe.dto.SessionInfoDto;
import com.example.recipe.dto.ServiceIn.UserPortalInitShowIn;
import com.example.recipe.dto.view.UserPortalDto;
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
	public String initShow(Model model) {
		SessionInfoDto sessionInfoDto = (SessionInfoDto)session.getAttribute(CommonConst.KEY_SYSTEMINFO_DTO);
		UserPortalInitShowIn inDto = new UserPortalInitShowIn();
		UserPortalDto userPortalDto = new UserPortalDto();
		inDto.setUserId(sessionInfoDto.getUserId());
		inDto.setUserPortalDto(userPortalDto);
		//F層呼び出しで画面に表示する項目を取得
		userPortalService.initShow(inDto);
		
		model.addAttribute(CommonConst.KEY_USERPORTAL_DTO, userPortalDto);
		
		return CommonConst.SCREENID_USERPORTAL;
	}
}
