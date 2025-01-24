package com.example.recipe.dto;

import com.example.recipe.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ingredient extends BaseDto {
	
	/**
	 * 材料名
	 */
	private String ingredientName;
	
	/**
	 * 量
	 */
	private String amount;
	
	/**
	 * 材料カテゴリ
	 */
	private String category;
}
