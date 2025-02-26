package com.example.recipe.dto.view;

import java.sql.Timestamp;
import java.util.List;

import com.example.recipe.base.BaseDto;
import com.example.recipe.dto.Ingredient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeDetailDto extends BaseDto {
	private String recipeId;
	
	private String recipeName;
	
	private double recipeAveRating;
	
	private int ratingTotalCount;
	
	private String recipeCategory;
	
	private String  recipeImg;
	
	private String recipeDescrip;
	
	private String publishStatus;
	
	private Timestamp registerDate;
	
	private Timestamp updateDate;
	
	private List<Ingredient> recipeIngredients;
	
	private List<String> stepsList;
	
	private boolean favFlg;
	
	private int rating;

}
