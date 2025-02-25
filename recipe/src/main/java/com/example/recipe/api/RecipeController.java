package com.example.recipe.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.recipe.base.BaseController;
import com.example.recipe.common.util.CommonConst;
import com.example.recipe.dto.SessionInfoDto;
import com.example.recipe.dto.ServiceIn.UserRecipeRelationAlterFavIn;
import com.example.recipe.dto.requestBody.FavoriteRequest;
import com.example.recipe.service.EditRecipeService;
import com.example.recipe.service.UserRecipeRelationService;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController extends BaseController {
	
	private EditRecipeService editRecipeService;
	
	private UserRecipeRelationService userRecipeRelationService;
	
	public RecipeController(HttpServletRequest request, HttpSession session, EditRecipeService editRecipeService, UserRecipeRelationService userRecipeRelationService) {
		super(request, session);
		this.editRecipeService = editRecipeService; 
		this.userRecipeRelationService = userRecipeRelationService;
	}
	
	@PatchMapping("/{recipeId}/delete")
	public void delete(@PathVariable("recipeId") String recipeId) {
		editRecipeService.delete(recipeId);
		System.out.println("deleted");
	}
	
	@PutMapping("/favorites")
	public void alterFavorite(@RequestBody FavoriteRequest favoriteRequest) {
		SessionInfoDto sessionInfoDto = (SessionInfoDto)session.getAttribute(CommonConst.KEY_SYSTEMINFO_DTO);
		UserRecipeRelationAlterFavIn inDto = new UserRecipeRelationAlterFavIn();
		inDto.setRecipeId(favoriteRequest.getRecipeId());
		inDto.setUserId(sessionInfoDto.getUserId());
		userRecipeRelationService.alterFavorite(inDto);
		
	}
}
