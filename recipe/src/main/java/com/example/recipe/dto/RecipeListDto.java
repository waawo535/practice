package com.example.recipe.dto;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class RecipeListDto implements Serializable {
	/*
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * レシピID
	 */
	private String recipeId;
	
	/*
	 * レシピ名
	 */
	private String recipeName;
	
	/**
	 * 評価
	 */
	private int recipeAveRating;
	
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
}
