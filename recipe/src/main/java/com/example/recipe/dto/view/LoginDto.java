package com.example.recipe.dto.view;

import com.example.recipe.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto extends BaseDto {
	
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
