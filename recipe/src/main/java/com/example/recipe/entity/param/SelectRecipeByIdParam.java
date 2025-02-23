package com.example.recipe.entity.param;

import com.example.recipe.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectRecipeByIdParam extends BaseParam {
	
	private String recipeId;
	
	public SelectRecipeByIdParam() {
		super("SelectRecipeById", "mapper.RecipeInfo");
	}
}
