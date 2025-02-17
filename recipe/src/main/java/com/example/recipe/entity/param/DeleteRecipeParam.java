package com.example.recipe.entity.param;

import com.example.recipe.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteRecipeParam extends BaseParam {
	
	private String recipeId;
	
	public DeleteRecipeParam() {
		super("DeleteRecipe", "mapper.DeleteRecipe");
	}

}
