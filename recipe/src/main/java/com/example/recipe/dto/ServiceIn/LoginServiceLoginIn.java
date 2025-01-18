package com.example.recipe.dto.ServiceIn;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginServiceLoginIn {
	 
	/**
	 * ユーザID
	 */
	private String userId;
	
	/**
	 * メールアドレス
	 */
	private String emailAddress;
	
	/**
	 * パスワード
	 */
	private String password;
}
