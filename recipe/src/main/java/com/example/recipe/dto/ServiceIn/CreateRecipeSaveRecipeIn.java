package com.example.recipe.dto.ServiceIn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.recipe.dto.Ingredient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRecipeSaveRecipeIn {
	
	private String userId;
	
	private String recipeId;
	
	private String recipeName;
	
	private String recipeCategory;
	
	private MultipartFile  recipeImg;
	
	/**
	 * 説明
	 */
	private String recipeDiscrip;
	
	/**
	 * 材料
	 */
	private List<Ingredient> recipeIngredients;
	
	/**
	 * 手順
	 */
	private List<String> stepsList;
	
	/**
	 * 公開非公開フラグ
	 */
	private String publishStatus;
	
	/**
	 * コンストラクタ
	 */
	public CreateRecipeSaveRecipeIn() {
		this.recipeIngredients = new ArrayList<Ingredient>();
		this.stepsList = new ArrayList<String>();
	}
}
