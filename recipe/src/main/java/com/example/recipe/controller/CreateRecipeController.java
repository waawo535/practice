package com.example.recipe.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.recipe.base.BaseController;
import com.example.recipe.common.util.CommonConst;
import com.example.recipe.dto.ErrorMessageDto;
import com.example.recipe.dto.SessionInfoDto;
import com.example.recipe.dto.SystemInfoDto;
import com.example.recipe.dto.ServiceIn.CreateRecipeSaveRecipeIn;
import com.example.recipe.dto.view.CreateRecipeDto;
import com.example.recipe.dto.view.SearchRecipeDto;
import com.example.recipe.service.CreateRecipeService;

@Controller
@RequestMapping("/CreateRecipe")
public class CreateRecipeController extends BaseController {
	
	CreateRecipeService createRecipeService;
	
	private final MessageSource messageSource;
	
	public CreateRecipeController(HttpServletRequest request, HttpSession session, CreateRecipeService createRecipeService, MessageSource messageSource) {
		super(request, session);
		this.createRecipeService = createRecipeService;
		this.messageSource = messageSource;
	}
	
	@GetMapping("/initShow")
	public String initShow(Model model) {

		CreateRecipeDto createRecipeDto = new CreateRecipeDto();
		SearchRecipeDto searchRecipeDto = new SearchRecipeDto();
		SystemInfoDto systemInfoDto = new SystemInfoDto ();
		systemInfoDto.setCurrentScreenId(CommonConst.SCREENID_RCP01);
		model.addAttribute(CommonConst.KEY_SRCH01, searchRecipeDto);
		model.addAttribute(CommonConst.KEY_SYSTEMINFO, systemInfoDto);
		model.addAttribute(CommonConst.KEY_RCP01, createRecipeDto);
		
		return CommonConst.SCREENID_RCP01;
	}
	
	@PostMapping("/saveRecipe")
	public String saveRecipe(CreateRecipeDto createRecipeDto, RedirectAttributes redirectAttributes) {
		try {
		SessionInfoDto sessionInfoDto = (SessionInfoDto)session.getAttribute(CommonConst.KEY_SESSIONINFO);
		//単項目チェック実装する	
		
		//DB保存処理
		CreateRecipeSaveRecipeIn inDto = new CreateRecipeSaveRecipeIn();
		inDto.setUserId(sessionInfoDto.getUserId());
		inDto.setRecipeName(createRecipeDto.getRecipeName());
		inDto.setRecipeCategory(createRecipeDto.getRecipeCategory());
		inDto.setRecipeImg(createRecipeDto.getRecipeImg());
		inDto.setRecipeDiscrip(createRecipeDto.getRecipeDiscrip());
		inDto.setRecipeIngredients(createRecipeDto.getRecipeIngredients());
		inDto.setStepsList(createRecipeDto.getStepsList());
		inDto.setPublishStatus(createRecipeDto.getPublishStatus());
		createRecipeService.saveRecipe(inDto);
		} catch (IOException e) {
			//ファイルアップロードに失敗した場合、エラーメッセージを設定して編集画面にリダイレクトする
			String errorMessage = messageSource.getMessage("E407", new Object[] { }, Locale.JAPAN);
			setErrorMessageList(createRecipeDto, errorMessage);
			redirectAttributes.addAttribute(CommonConst.KEY_RCP01, createRecipeDto);
			return CommonConst.REDIRECT_RCP01;
		}
		
		return CommonConst.REDIRECT_UINF01;
	}
	
	/**
	 * 画面に表示するエラーメッセージの設定
	 * @param createRecipeDto
	 * @param errorMessage
	 */
	private void setErrorMessageList(CreateRecipeDto createRecipeDto, String errorMessage) {
		ArrayList<ErrorMessageDto> ErrorMessageList = createRecipeDto.getErrormessageAreaList();
		ErrorMessageDto errorMessageDto = new ErrorMessageDto();
		errorMessageDto.setErrorMessage(errorMessage);
		ErrorMessageList.add(errorMessageDto);
	}
}
