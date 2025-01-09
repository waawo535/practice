package com.example.recipe.entity.param;

import com.example.recipe.base.BaseParam;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InsertRegisteredUserParam extends BaseParam {
	
	/**
	 * ユーザID
	 */
	private String userId;
	
	/**
	 * 削除フラグ
	 */
	private boolean deleteFlag;
	
	/**
	 * 登録年月日時分秒
	 */
	private String registerDate;
	
	/**
	 * 登録ユーザID
	 */
	private String registeredUserId;
	
	public InsertRegisteredUserParam() {
		super("InsertRegisteredUser", "com.example.entity.result");
	}

}
