package com.example.recipe.entity.param;

import com.example.recipe.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectUserEmailAuthsParam extends BaseParam {

	/**
	 * ユーザID
	 */
	private String userId;
	
	/**
	 * メールアドレス
	 */
	private String emailAdress;
	
	public SelectUserEmailAuthsParam() {
		super("SelectUserEmailAuths", "com.example.entity.result");
	}
}
