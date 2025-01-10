package com.example.recipe.entity.param;

import java.sql.Timestamp;

import com.example.recipe.base.BaseParam;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InsertUserDetailParam extends BaseParam {
	
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
	private String emailAdress;
	
	/**
	 * 電話番号
	 */
	private int phoneNumber;
	
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
	private String updateUserId;
	
	public InsertUserDetailParam() {
		super("InsertUserDetail", "com.example.entity.result");
	}
}
