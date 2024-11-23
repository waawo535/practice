package com.example.dto;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ListShowDto implements Serializable {
	/*
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = 1L;
	
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
}