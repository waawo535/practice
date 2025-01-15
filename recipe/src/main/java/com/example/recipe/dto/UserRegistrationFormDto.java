package com.example.recipe.dto;

import com.example.recipe.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationFormDto  extends BaseDto {
	
	/**
	 * ユーザID
	 */
	private String userId;
	
	/**
	 * メールアドレス
	 */
	private String emailAdress;
	
	/**
	 * パスワード
	 */
	private String password;
	
	/**
	 * パスワード（確認用）
	 */
	private String confrimPassword;
	
	/**
	 * 認証コード
	 */
	private String token;
}
