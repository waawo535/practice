package com.example.recipe.dto.ServiceIn;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationServiceIn {
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
	
	/**
	 * パスワード（確認用）
	 */
	private String comfirmPassword;
}
