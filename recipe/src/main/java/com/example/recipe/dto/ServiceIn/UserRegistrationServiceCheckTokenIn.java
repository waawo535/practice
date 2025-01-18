package com.example.recipe.dto.ServiceIn;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationServiceCheckTokenIn {
	
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
	 * 有効期限年月日時分秒
	 */
	private Timestamp expiryDate;
}
