package com.example.recipe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationFormDto {
	
	/**
	 * メールアドレス
	 */
	private String emailAdress;
	
	/**
	 * パスワード
	 */
	private String password;
}
