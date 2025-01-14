package com.example.recipe.dto.CommonIn;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleFieldCheckCheckRegExIn {
	
	/**
	 * 判定文字列
	 */
	private String str;
	
	/**
	 * 正規表現文字列
	 */
	private String regEx;
}
