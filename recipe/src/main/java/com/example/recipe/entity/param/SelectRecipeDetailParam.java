package com.example.recipe.entity.param;

import com.example.recipe.base.BaseParam;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SelectRecipeDetailParam extends BaseParam {
	
	/*
	 * レシピID
	 */
	private int recipeId;
	
	/*
	 * コンストラクタ
	 */
	public SelectRecipeDetailParam() {
		//第一引数SQLID
		//第二引数namespace
		super("SelectRecipeDetail", "com.example.entity.result");
	}
}
