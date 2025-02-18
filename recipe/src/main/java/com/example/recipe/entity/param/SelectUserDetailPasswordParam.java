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
	private String emailAddress;
	
	public SelectUserDetailPasswordParam() {
		super("SelectUserDetailPassword", "mapper.UserDetail");
	}

}
