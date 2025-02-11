package com.example.recipe.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.recipe.base.BaseController;

@Controller
@RequestMapping("recipeList")
public class RecipeController extends BaseController {
	
	public RecipeController(HttpServletRequest request, HttpSession session) {
		super(request, session);
	}
	
	@GetMapping("initShow")
	public String initShow(Model model) {
		
		
		return "list";
	}
}
