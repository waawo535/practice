package com.example.recipe.entity.param;

import com.example.recipe.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteRecipeIngredientsParam extends BaseParam {
	
	private String recipeId;
	
	public DeleteRecipeIngredientsParam() {
		super("DeleteRecipeIngredients", "mapper.RecipeIngredients");
	}

}
