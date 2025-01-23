package com.example.recipe.dto.ServiceIn;

import java.util.ArrayList;
import java.util.HashMap;

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
	private HashMap<String, String> recipeIngredients;
	
	/**
	 * 手順
	 */
	private ArrayList<String> stepsList;
	
	/**
	 * 公開非公開フラグ
	 */
	private String publishStatus;
}
