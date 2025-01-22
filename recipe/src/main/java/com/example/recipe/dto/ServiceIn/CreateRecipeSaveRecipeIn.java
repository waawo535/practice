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
	
	private String recipeDiscrip;
	
	private HashMap<String, String> recipeIngredients;
	
	private ArrayList<String> stepsList;
	
	private String publishStatus;
}
