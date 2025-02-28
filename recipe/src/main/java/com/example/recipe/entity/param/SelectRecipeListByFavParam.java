package com.example.recipe.entity.param;

import com.example.recipe.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectRecipeListByFavParam extends BaseParam {
	
	private String registeredUserId;
	
	private boolean deleteFlag;
	
	private String searchCondition;
	
	public SelectRecipeListByFavParam() {
		super("SelectRecipeListByFav", "mapper.RecipeInfo");
	}

}
