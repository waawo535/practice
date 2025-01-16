package com.example.recipe.entity.param;

import com.example.recipe.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRegisterStatusParam extends BaseParam {
	
	/**
	 * ユーザID
	 */
	private String userId;
	
	/**
	 * 登録ステータスコード
	 */
	private String registerStatusCode;
	
	public UpdateRegisterStatusParam() {
		super("UpdateRegisterStatus", "com.example.entity.result");
	}

}
