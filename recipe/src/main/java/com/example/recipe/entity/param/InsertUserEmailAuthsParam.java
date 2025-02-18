package com.example.recipe.entity.param;

import java.sql.Timestamp;

import com.example.recipe.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertUserEmailAuthsParam extends BaseParam {
	
	/**
	 * ユーザID
	 */
	private String userId;
	
	/**
	 * メールアドレス
	 */
	private String emailAddress;
	
	/**
	 * 認証コード
	 */
	private String token;
	
	/**
	 * 有効期限切れ年月日時分秒
	 */
	private Timestamp expiryDate;
	
	public InsertUserEmailAuthsParam() {
		super("InsertUserEmailAuths", "mapper.UserEmailAuths");
	}

}
