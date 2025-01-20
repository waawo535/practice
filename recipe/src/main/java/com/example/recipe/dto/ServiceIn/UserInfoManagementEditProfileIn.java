package com.example.recipe.dto.ServiceIn;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoManagementEditProfileIn {
	
	/**
	 * ユーザID 
	 */
	private String userId;
	
	/**
	 * ユーザ名
	 */
	private String userName;
	
	/**
	 * メールアドレス
	 */
	private String emailAddress;
	
	/**
	 * 電話番号
	 */
	private String phoneNumber;
	
	/**
	 * パスワード
	 */
	private String password;
	
	/**
	 * プロフィール画像URL
	 */
	private String profileImgUrl;
	
	/**
	 * bio
	 */
	private String bio;
	
	/**
	 * 誕生日
	 */
	private String birthDate;
	
	/**
	 * 性別
	 */
	private String gender;
	
	/**
	 * 登録年月日時分秒
	 */
	private Timestamp registerDate;
	
	/**
	 * 登録ユーザID
	 */
	private String registeredUserId;
	
	/**
	 * 更新年月日時分秒
	 */
	private Timestamp updateDate;
	
	/**
	 * 更新ユーザID
	 */
	private String updatedUserId;
}
