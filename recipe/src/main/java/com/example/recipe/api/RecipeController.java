package com.example.recipe.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.recipe.base.BaseController;
import com.example.recipe.service.EditRecipeService;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController extends BaseController {
	
	private EditRecipeService editRecipeService;
	
	public RecipeController(HttpServletRequest request, HttpSession session, EditRecipeService editRecipeService) {
		super(request, session);
		this.editRecipeService = editRecipeService; 
	}
	
	@PatchMapping("/{recipeId}/delete")
	public void delete(@PathVariable("recipeId") String recipeId) {
		editRecipeService.delete(recipeId);
		System.out.println("deleted");
	}
}
