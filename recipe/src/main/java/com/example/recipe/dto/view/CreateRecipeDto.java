package com.example.recipe.dto.view;

import java.util.ArrayList;
import java.util.HashMap;

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
	
	private HashMap<String, String> recipeIngredients;
	
	private ArrayList<String> stepsList;
	
	private String publishStatus;
	
}	
