package com.example.recipe.entity.result;

import java.sql.Timestamp;

import com.example.recipe.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectUserEmailAuthsEntity extends BaseEntity {
	
	/**
	 * ユーザID
	 */
	private String userId;
	
	/**
	 * メールアドレス
	 */
	private String emailAdress;
	
	/**
	 * 認証コード
	 */
	private String token;
	
	/**
	 * 有効期限年月日時分秒
	 */
	private Timestamp expiryDate;
	
	public SelectUserEmailAuthsEntity() {
		super("SelectUserEmailAuths", "com.example.entity.result");
	}

}
