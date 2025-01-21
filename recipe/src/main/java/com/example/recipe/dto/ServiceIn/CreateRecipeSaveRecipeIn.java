package com.example.recipe.dto.ServiceIn;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRecipeSaveRecipeIn {
	private String recipeId;
	
	private String recipeName;
	
	private String recipeCategory;
	
	private String recipeImg;
	
	private String recipeDiscrip;
	
	private String recipeIngredients;
	
	private String stepsList;
	
	private String publishStatus;
}
