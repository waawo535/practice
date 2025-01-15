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
	 * メールアドレス存在フラグ
	 * true:メールアドレスが既に登録済み
	 * faslse:メールアドレスは未登録
	 */
	private boolean emailExistingFlg;
	
	/**
	 * パスワード整合性フラグ
	 * true:整合性がある
	 * false:整合性がない
	 */
	private boolean passwordCheckFlg;
}
