package com.example.recipe.entity.param;

import com.example.recipe.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectRecipeListParam extends BaseParam {
	
	/**
	 * ユーザID
	 */
	private String userId;
	
	/**
	 * 取得件数
	 */
	private int limit;
	
	/**
	 * 取得開始位置
	 */
	private int offset;
	
	public SelectRecipeListParam() {
		super("initShowList", "com.example.");
	}
}
