package com.example.entity.param;

import com.example.base.BaseParam;

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
		super("selectrecipeinfo", "com.example.entity.result");
	}
}
