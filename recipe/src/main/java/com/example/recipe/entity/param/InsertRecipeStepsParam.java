package com.example.recipe.entity.param;

import com.example.recipe.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertRecipeStepsParam extends BaseParam {
	
	private String recipeId;
	
	private int stepNumber;
	
	private String instruction;
	
	public InsertRecipeStepsParam() {
		super("InsertRecipeSteps", "com.example.entity.result");
	}
}
