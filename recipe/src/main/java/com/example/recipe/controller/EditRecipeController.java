package com.example.recipe.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.recipe.base.BaseController;
import com.example.recipe.common.util.CommonConst;
import com.example.recipe.dto.view.EditRecipeDto;
import com.example.recipe.service.EditRecipeService;

@Controller
@RequestMapping("/editRecipe")
public class EditRecipeController extends BaseController {
	
	EditRecipeService editRecipeService;
	
	public EditRecipeController(HttpServletRequest request, HttpSession session, EditRecipeService editRecipeService) {
		super(request, session);
		this.editRecipeService = editRecipeService;
	}
	
	@GetMapping("/initShow")
	public String updateRecipe(@RequestParam("recipeId") String recipeId, Model model) {
		
		//編集画面にする
		return CommonConst.SCREENID_USERINFOMANAGEMENT;
	}
	
	@PostMapping("/update/confirm")
	public String confirmUpdate(EditRecipeDto editRecipeDto) {
		editRecipeService.update();
		return CommonConst.SCREENID_USERINFOMANAGEMENT;
	}
	

}
