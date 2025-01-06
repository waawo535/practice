package com.example.recipe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationFormDto {
	
	/*
	 * ユーザ名
	 */
	private String userName;
	
	/*
	 * パスワード
	 */
	private String password;
}
