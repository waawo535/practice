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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.recipe.base.BaseController;
import com.example.recipe.common.util.CommonConst;
import com.example.recipe.dto.ErrorMessageDto;
import com.example.recipe.dto.ServiceIn.CreateRecipeSaveRecipeIn;
import com.example.recipe.dto.view.CreateRecipeDto;
import com.example.recipe.service.CreateRecipeService;

@Controller
@RequestMapping("/CreateRecipe")
public class CreateRecipeController extends BaseController {
	
	HttpSession session;
	
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
		model.addAttribute(CommonConst.KEY_CREATERECIPE_DTO, createRecipeDto);
		return CommonConst.SCREENID_CREATERECIPE;
	}
	
	@PostMapping("/saveRecipe")
	public String saveRecipe(CreateRecipeDto createRecipeDto, RedirectAttributes redirectAttributes, MultipartFile multipartFile) {
		try {
			
		//単項目チェック実装する	
		
		
		//DB保存処理
		CreateRecipeSaveRecipeIn inDto = new CreateRecipeSaveRecipeIn();
		inDto.setRecipeName(createRecipeDto.getRecipeName());
		inDto.setRecipeCategory(createRecipeDto.getRecipeCategory());
		inDto.setRecipeImg(createRecipeDto.getRecipeImg());
		inDto.setRecipeDiscrip(createRecipeDto.getRecipeDiscrip());
		inDto.setRecipeIngredients(createRecipeDto.getRecipeIngredients());
		inDto.setStepsList(createRecipeDto.getStepsList());
		inDto.setPublishStatus(createRecipeDto.getPublishStatus());
		createRecipeService.saveRecipe(inDto, multipartFile);
		
		} catch (IOException e) {
			//ファイルアップロードに失敗した場合、エラーメッセージを設定して編集画面にリダイレクトする
			String errorMessage = messageSource.getMessage("E407", new Object[] { }, Locale.JAPAN);
			setErrorMessageList(createRecipeDto, errorMessage);
			redirectAttributes.addAttribute(CommonConst.KEY_CREATERECIPE_DTO, createRecipeDto);
			return CommonConst.REDIRECT_CREATERECIPE;
		}
		
		return CommonConst.SCREENID_RECIPELIST;
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
