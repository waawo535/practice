package com.example.recipe.dto.ServiceIn;

import java.util.List;

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
	
	private String recipeImg;
	
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
}
