package com.example.recipe.dto.view;

import java.util.List;

import com.example.recipe.base.BaseDto;
import com.example.recipe.dto.Ingredient;

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
	
	private List<Ingredient> recipeIngredients;
	
	private List<String> stepsList;
	
	private String publishStatus;
	
}	
