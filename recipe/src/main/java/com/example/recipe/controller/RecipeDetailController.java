package com.example.recipe.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.recipe.base.BaseController;
import com.example.recipe.common.util.CommonConst;
import com.example.recipe.dto.SessionInfoDto;
import com.example.recipe.dto.ServiceIn.RecipeDetailServiceInitShowIn;
import com.example.recipe.dto.view.RecipeDetailDto;
import com.example.recipe.service.RecipeDetailService;

@Controller
@RequestMapping("/recipeDetail")
public class RecipeDetailController extends BaseController {
	
	private RecipeDetailService recipeDetailService;
	
	public RecipeDetailController(HttpServletRequest request, HttpSession session, RecipeDetailService recipeDetailService) {
		super(request, session);
		this.recipeDetailService = recipeDetailService;
	}
	
	@GetMapping("/initShow")
	public String initShow(@RequestParam String recipeId, Model model) {
		SessionInfoDto sessionInfoDto = (SessionInfoDto)session.getAttribute(CommonConst.KEY_SESSIONINFO);
		
		RecipeDetailServiceInitShowIn inDto= new RecipeDetailServiceInitShowIn();
		RecipeDetailDto recipeDetailDto= new RecipeDetailDto();
		inDto.setUserId(sessionInfoDto.getUserId());
		inDto.setRecipeDetailDto(recipeDetailDto);
		inDto.setRecipeId(recipeId);
		recipeDetailService.initShow(inDto);
		
		model.addAttribute(CommonConst.KEY_RCP03, recipeDetailDto);
		
		return CommonConst.SCREENID_RCP03;
	}
}
