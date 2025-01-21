package com.example.recipe.dto.view;

import com.example.recipe.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRecipeDto extends BaseDto {
	
	private String recipeId;
	
	private String recipeName;
	
	private String recipeCategory;
	
	private String recipeImg;
	
	private String recipeDiscrip;
	
	private String recipeIngredients;
	
	private String stepsList;
	
	private String publishStatus;
	
}	
