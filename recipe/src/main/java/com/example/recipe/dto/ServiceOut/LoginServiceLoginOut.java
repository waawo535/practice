package com.example.recipe.dto.ServiceOut;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginServiceLoginOut {
	
	/**
	 * メールアドレス存在チェックフラグ
	 * true:存在する
	 * false:存在しない
	 */
	private boolean mailAddressExists;
	/**
	 * 本登録フラグ
	 * true:本登録
	 * false:本登録でない
	 */
	private boolean DefinitivlyRegistered;
	
	/**
	 * 仮登録フラグ
	 * true:仮登録
	 * false:仮登録でない
	 */
	private boolean ProvisionallyRegistered;
	
	/**
	 * パスワード整合性チェックフラグ
	 * true:整合性がある
	 * false:整合性がない（エラー）
	 */
	private boolean passwordCheckPassed;
	
	/**
	 * ユーザ名
	 */
	private String userName;
	
	/**
	 * プロフィール画像URL
	 */
	private String profileImgUrl;

}
