package com.example.recipe.entity.param;

import com.example.recipe.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectUserRecipeRelationParam extends BaseParam {
	
	private String recipeId;
	
	private String userId;
	
	public SelectUserRecipeRelationParam() {
		super("SelectUserRecipeRelation", "mapper.UserRecipeRelation");
	}

}
