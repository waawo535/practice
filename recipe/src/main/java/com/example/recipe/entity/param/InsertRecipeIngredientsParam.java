package com.example.recipe.entity.param;

import com.example.recipe.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertRecipeIngredientsParam extends BaseParam {
	
	private String recipeId;
	
	private int ingredientNumber;
	
	private String ingredientName;
	
	private String amount;
	
	public InsertRecipeIngredientsParam() {
		super("InsertRecipeIngredients", "mapper.RecipeIngredients");
	}

}
