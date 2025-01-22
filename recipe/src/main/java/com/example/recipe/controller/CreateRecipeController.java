package com.example.recipe.controller;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.recipe.base.BaseController;
import com.example.recipe.common.util.CommonConst;
import com.example.recipe.dto.ServiceIn.CreateRecipeSaveRecipeIn;
import com.example.recipe.dto.view.CreateRecipeDto;
import com.example.recipe.service.CreateRecipeService;

@Controller
@RequestMapping("/CreateRecipe")
public class CreateRecipeController extends BaseController {
	
	HttpSession session;
	
	CreateRecipeService createRecipeService;
	
	public CreateRecipeController(HttpServletRequest request, HttpSession session, CreateRecipeService createRecipeService) {
		super(request, session);
		this.createRecipeService = createRecipeService;
	}
	
	@GetMapping("/initShow")
	public String initShow(Model model) {
		
		
		CreateRecipeDto createRecipeDto = new CreateRecipeDto();
		model.addAttribute(CommonConst.KEY_CREATERECIPE_DTO, createRecipeDto);
		return CommonConst.SCREENID_CREATERECIPE;
	}
	
	@PostMapping("/saveRecipe")
	public String saveRecipe(CreateRecipeDto createRecipeDto) {
		try {
		//DB保存処理
		CreateRecipeSaveRecipeIn inDto = new CreateRecipeSaveRecipeIn();
		inDto.setRecipeName(createRecipeDto.getRecipeName());
		inDto.setRecipeCategory(createRecipeDto.getRecipeCategory());
		inDto.setRecipeImg(createRecipeDto.getRecipeImg());
		inDto.setRecipeDiscrip(createRecipeDto.getRecipeDiscrip());
		inDto.setRecipeIngredients(createRecipeDto.getRecipeIngredients());
		inDto.setStepsList(createRecipeDto.getStepsList());
		inDto.setPublishStatus(createRecipeDto.getPublishStatus());
		
			createRecipeService.saveRecipe(inDto);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		
		
		return CommonConst.SCREENID_RECIPELIST;
	}
}
