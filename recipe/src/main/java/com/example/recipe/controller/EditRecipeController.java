package com.example.recipe.controller;

import java.io.IOException;

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
import com.example.recipe.dto.SessionInfoDto;
import com.example.recipe.dto.ServiceIn.EditRecipeServiceGetRecipeDetailIn;
import com.example.recipe.dto.ServiceIn.EditRecipeServiceUpdateIn;
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
		EditRecipeDto editRecipeDto = new EditRecipeDto();
		editRecipeDto.setRecipeId(recipeId);
		
		//レシピ詳細を取得する
		EditRecipeServiceGetRecipeDetailIn inDto = new EditRecipeServiceGetRecipeDetailIn();
		inDto.setEditRecipeDto(editRecipeDto);
		editRecipeService.getRecipeDetail(inDto);
		if(!model.containsAttribute(CommonConst.KEY_EDITRECIPE_DTO)) {
			model.addAttribute(CommonConst.KEY_EDITRECIPE_DTO, editRecipeDto);
		}
		
		return CommonConst.SCREENID_EDITRECIPE;
	}
	
	@PostMapping("/update/confirm")
	public String confirmUpdate(EditRecipeDto editRecipeDto) {
		try {
			SessionInfoDto sessionInfoDto = (SessionInfoDto) session.getAttribute(CommonConst.KEY_SYSTEMINFO_DTO);
			EditRecipeServiceUpdateIn editRecipeServiceUpdateIn = new EditRecipeServiceUpdateIn();
			editRecipeServiceUpdateIn.setUserId(sessionInfoDto.getUserId());
			editRecipeServiceUpdateIn.setEditRecipeDto(editRecipeDto);
			editRecipeService.update(editRecipeServiceUpdateIn);
		} catch (IOException e) {
			//エラー時のエラーメッセージあとで実装する
			e.printStackTrace();
		}
		return CommonConst.REDIRECT_USERINFOMANAGEMENT;
	}
	
	//単項目チェックなど実装する
}
