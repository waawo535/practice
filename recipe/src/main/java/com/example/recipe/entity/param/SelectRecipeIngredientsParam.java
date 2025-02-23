package com.example.recipe.entity.param;

import com.example.recipe.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectRecipeIngredientsParam extends BaseParam {
	
	private String recipeId;
	
	public SelectRecipeIngredientsParam() {
		super("SelectRecipeIngredients", "mapper.RecipeIngredients");
	}

}
