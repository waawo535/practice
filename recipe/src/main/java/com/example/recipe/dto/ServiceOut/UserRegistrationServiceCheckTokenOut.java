package com.example.recipe.dto.ServiceOut;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationServiceCheckTokenOut {
	
	/**
	 * 仮登録チェックフラグ
	 * true:仮登録
	 * false:仮登録以外
	 */
	private boolean registrationStatusFlg;
	
	/**
	 *認証コード整合性チェックフラグ
	 *true:整合性あり
	 *false:整合性なし（エラー）
	 */
	private boolean tokenValidateFlg;
	
	/**
	 * 有効期限チェックフラグ
	 * true:有効期限内
	 * false:有効期限切れ（エラー）
	 */
	private boolean expiryDateValidateFlg;
}
