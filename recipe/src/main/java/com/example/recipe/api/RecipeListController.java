package com.example.recipe.api;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.recipe.base.BaseController;
import com.example.recipe.common.util.CommonConst;
import com.example.recipe.dto.SessionInfoDto;
import com.example.recipe.entity.result.SelectUserRecipeListEntity;
import com.example.recipe.service.RecipeListService;

@RestController
@RequestMapping("/api/recipes")
public class RecipeListController extends BaseController {
	
	private RecipeListService recipeListService;
	
	public RecipeListController(HttpServletRequest request, HttpSession session, RecipeListService recipeListService) {
		super(request, session);
		this.recipeListService = recipeListService;
	}
	
	// 無限スクロールで追加読み込み
	@GetMapping("/more")
	public List<SelectUserRecipeListEntity> getMoreRecipes(@RequestParam int offset, @RequestParam String screenId) {
		SessionInfoDto sessionInfoDto = (SessionInfoDto)session.getAttribute(CommonConst.KEY_SESSIONINFO);
		System.out.println("offset: " + offset + ", screenId: " + screenId);
	    return recipeListService.getRecipeList(offset, screenId, sessionInfoDto.getUserId());
	}
}
