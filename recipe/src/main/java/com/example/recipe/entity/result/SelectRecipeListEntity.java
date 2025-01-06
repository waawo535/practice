package com.example.recipe.entity.result;

import com.example.recipe.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SelectRecipeListEntity extends BaseEntity {
	/*
	 * レシピ名
	 */
	private String recipeName;
	
	/*
	 * レシピ画像
	 */
	private String recipeImg;
	
	/*
	 * レシピ説明文
	 */
	private String recipeDescrip;
	
	/*
	 * レシピ本文
	 */
	private String recipeMainTxt;
	
	/*
	 * レシピ登録日
	 */
	private String registerDate;
	
	/*
	 * レシピ最終更新日
	 */
	private String updateDate;
	public SelectRecipeListEntity() {
		super("SelectRecipeList", "com.example.entity.result");
	}
}
