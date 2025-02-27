package com.example.recipe.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.recipe.base.BaseController;
import com.example.recipe.common.util.CommonConst;
import com.example.recipe.dto.SessionInfoDto;
import com.example.recipe.dto.ServiceIn.SearchRecipeSearchIn;
import com.example.recipe.dto.view.SearchRecipeDto;
import com.example.recipe.service.SearchRecipeService;

@Controller
@RequestMapping("/search")
public class SearchRecipeController extends BaseController {
	
	SearchRecipeService searchRecipeService;
	
	public SearchRecipeController(HttpServletRequest request, HttpSession session, SearchRecipeService searchRecipeService) {
		super(request, session);
		this.searchRecipeService = searchRecipeService;
	}
	
	//ヘッダーからの検索は、全レシピに対して検索を行う？
	//自分が作成したレシピ、お気に入りなどの条件での絞り込みの検索は実装する？
	@GetMapping("/searchRecipe")
	public String search(@RequestParam("screenId") String screenId, SearchRecipeDto searchRecipeDto) {
		SessionInfoDto sessionInfoDto = (SessionInfoDto)session.getAttribute(CommonConst.KEY_SESSIONINFO);
		
		SearchRecipeSearchIn inDto= new SearchRecipeSearchIn();
		inDto.setUserId(sessionInfoDto.getUserId());
		inDto.setScreenId(screenId);
		inDto.setSearchCondition(searchCondition);
		searchRecipeService.search(inDto);
		
		
			
		
		return CommonConst.SCREENID_UPRT01;
	}
}
