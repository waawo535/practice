package com.example.recipe.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.recipe.base.BaseController;
import com.example.recipe.common.util.CommonConst;
import com.example.recipe.service.EditRecipeService;

@Controller
@RequestMapping("/editRecipe")
public class EditRecipeController extends BaseController {
	
	EditRecipeService editRecipeService;
	
	public EditRecipeController(HttpServletRequest request, HttpSession session, EditRecipeService editRecipeService) {
		super(request, session);
		this.editRecipeService = editRecipeService;
	}
	
	@PostMapping("/update")
	public String updateRecipe() {
		
		return CommonConst.SCREENID_USERINFOMANAGEMENT;
	}
	
	@PostMapping("/delete")
	public String deleteRecipe() {
		
		return CommonConst.SCREENID_USERINFOMANAGEMENT;
	}
}
