package com.example.recipe.entity.param;

import com.example.recipe.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectRecipeListByTimeParam extends BaseParam {
	
	private String registeredUserId;
	
	private boolean deleteFlag;
	
	public SelectRecipeListByTimeParam() {
		super("SelectRecipeListByTime", "mapper.RecipeInfo");
	}

}
