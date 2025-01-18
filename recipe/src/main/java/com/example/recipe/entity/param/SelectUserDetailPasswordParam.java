package com.example.recipe.entity.param;

import com.example.recipe.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectUserDetailPasswordParam extends BaseParam {
	
	/**
	 * ユーザID
	 */
	private String userId;
	
	/**
	 * メールアドレス
	 */
	private String mailAdress;
	
	public SelectUserDetailPasswordParam() {
		super("SelectUserDetailPassword", "com.example.entity.result");
	}

}
