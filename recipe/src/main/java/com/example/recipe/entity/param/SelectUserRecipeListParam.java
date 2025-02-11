package com.example.recipe.entity.param;

import com.example.recipe.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectUserRecipeListParam extends BaseParam {
	
	private String userId;
	
	private boolean deleteFlag;
	
	private int limit;
	
	private int offset;
	
	public SelectUserRecipeListParam() {
		super("SelectUserRecipeList", "com.example.entity.result");
	}

}
