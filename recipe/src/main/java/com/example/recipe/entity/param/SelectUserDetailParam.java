package com.example.recipe.entity.param;

import com.example.recipe.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectUserDetailParam extends BaseParam {
	
	/**
	 * ユーザID
	 */
	private String userId;
	
	public SelectUserDetailParam() {
		super("SelectUserDetail", "mapper.UserDetail");
	}
}
