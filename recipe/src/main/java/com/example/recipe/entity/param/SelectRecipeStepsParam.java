package com.example.recipe.entity.param;

import com.example.recipe.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectRecipeStepsParam extends BaseParam {
	
	private String recipeId;
	
	public SelectRecipeStepsParam() {
		super("SelectRecipeSteps", "mapper.RecipeSteps");
	}

}
