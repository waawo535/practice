package com.example.recipe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleFieldCheckCheckForBiddenCharOut {
	
	/**
	 * チェック結果
	 * true:禁則文字を含む
	 * false:禁則文字を含まない
	 */
	private boolean result;
	
	/**
	 * カンマ区切りの禁則文字一覧
	 */
	private String errorList;
	
}
