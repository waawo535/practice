package com.example.recipe.entity.param;

import com.example.recipe.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectRecipeSearchListParam extends BaseParam {
	
	private String searchCondition;
	
	public SelectRecipeSearchListParam() {
		super("SelectRecipeSearchList", "mapper.RecipeInfo");
	}
}
