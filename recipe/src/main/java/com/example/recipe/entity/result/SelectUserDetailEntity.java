package com.example.recipe.entity.result;

import java.sql.Timestamp;
import java.time.LocalDate;

import com.example.recipe.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectUserDetailEntity extends BaseEntity {
	
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
	 * プロフィール画像URL
	 */
	private String profileImgUrl;
	
	/**
	 * 自己紹介
	 */
	private String bio;
	
	/**
	 * 生年月日
	 */
	private LocalDate birthDate;
	
	/**
	 * 性別
	 */
	private String gender;
	
	/**
	 * 登録年月日時分秒
	 */
	private Timestamp registerDate;
	
	/**
	 * 更新年月日時分秒
	 */
	private Timestamp updateDate;
	
	public SelectUserDetailEntity() {
		super("SelectUserDetail", "com.example.entity.result");
	}

}
