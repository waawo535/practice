package com.example.recipe.entity.param;

import com.example.recipe.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteRecipeStepsParam extends BaseParam {
	
	private String recipeId;
	
	public DeleteRecipeStepsParam() {
		super("DeleteRecipeSteps", "mapper.RecipeSteps");
	}

}
