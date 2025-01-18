package com.example.recipe.dto;

import com.example.recipe.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionInfoDto extends BaseDto {
	
	/**
	 * ユーザID
	 */
	private String userId;
	
	/**
	 * ユーザ名
	 */
	private String userName;
	
	/**
	 * プロフィール画像URL
	 */
	private String profileImgUrl;
	
	/**
	 * 遷移元画面名
	 */
	private String prevScreen;
}
