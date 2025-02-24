package com.example.recipe.entity.param;

import com.example.recipe.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectRecipeListByPopularityParam extends BaseParam {
	
	private String registeredUserId;
	
	private boolean deleteFlag;
	
	public SelectRecipeListByPopularityParam() {
		super("SelectRecipeListByPopularity", "mapper.RecipeInfo");
	}

}
