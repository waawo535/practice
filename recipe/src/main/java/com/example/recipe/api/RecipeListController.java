package com.example.recipe.api;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.recipe.base.BaseController;
import com.example.recipe.dto.RecipeListDto;
import com.example.recipe.service.RecipeListService;

@RestController
@RequestMapping("/api/recipes")
public class RecipeListController extends BaseController {
	
	private RecipeListService recipeListService;
	
	public RecipeListController(HttpServletRequest request, HttpSession session, RecipeListService recipeListService) {
		super(request, session);
		this.recipeListService = recipeListService;
	}
	
	//初期表示はControllerから渡して追加で読み込む分だけAPIで取得するように修正する。なのでこれいらない
	 // 初期表示用のレシピ一覧
	@GetMapping("/init")
	public List<RecipeListDto> getInitialRecipes(@RequestParam String screenId) {
	    return recipeListService.getRecipeList(0, screenId);
	}
	
	// 無限スクロールで追加読み込み
	@GetMapping("/more")
	public List<RecipeListDto> getMoreRecipes(@RequestParam int offset, @RequestParam String screenId) {
	    return recipeListService.getRecipeList(offset, screenId);
	}
}
