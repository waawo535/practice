package com.example.recipe.dto.ServiceOut;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationServiceOut {
	
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
	private String comfirmPassword;
	
	/**
	 * 認証コード
	 */
	private String token;
	
	/**
	 * メールアドレス仮登録フラグ
	 * true:仮登録
	 * false:仮登録でない
	 */
	private boolean provisionallyRegistered;
	
	/**
	 * メールアドレス本登録フラグ
	 * true:メールアドレスが既に登録済み
	 * faslse:メールアドレスは未登録
	 */
	private boolean definitivlyRegistered;
	
	/**
	 * パスワード整合性フラグ
	 * true:整合性がある
	 * false:整合性がない
	 */
	private boolean passwordCheckFlg;
}
