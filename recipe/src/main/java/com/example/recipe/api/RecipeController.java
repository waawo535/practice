package com.example.recipe.api;

import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.example.recipe.dto.ServiceIn.UserRecipeRelationRateIn;
import com.example.recipe.dto.requestBody.FavoriteRequest;
import com.example.recipe.dto.requestBody.RateRequest;
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
	public ResponseEntity<Map<String, Object>> alterFavorite(@RequestBody FavoriteRequest favoriteRequest) {
		Map<String, Object> response = new HashMap<>();
		
		try {
		SessionInfoDto sessionInfoDto = (SessionInfoDto)session.getAttribute(CommonConst.KEY_SESSIONINFO);
		UserRecipeRelationAlterFavIn inDto = new UserRecipeRelationAlterFavIn();
		inDto.setRecipeId(favoriteRequest.getRecipeId());
		inDto.setUserId(sessionInfoDto.getUserId());
		userRecipeRelationService.alterFavorite(inDto);
		
		response.put("success", true);
		return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", "お気に入り処理に失敗しました: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@PutMapping("/{recipeId}/rate")
	public ResponseEntity<Map<String, Object>> rate(@PathVariable("recipeId") String recipeId, @RequestBody RateRequest rateRequest) {
		Map<String, Object> response = new HashMap<>();
//		try {
		SessionInfoDto sessionInfoDto = (SessionInfoDto)session.getAttribute(CommonConst.KEY_SESSIONINFO);
		
		UserRecipeRelationRateIn inDto = new UserRecipeRelationRateIn();
		inDto.setRecipeId(recipeId);
		inDto.setUserId(sessionInfoDto.getUserId());
		inDto.setRating(rateRequest.getRating());
		editRecipeService.rate(inDto);
		
		response.put("success", true);
		return ResponseEntity.ok(response);
//		} catch (Exception e) {
//			response.put("success", false);
//			response.put("message", "処理に失敗しました: " + e.getMessage());
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//		}
	}
}
