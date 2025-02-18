package com.example.recipe.entity.param;

import com.example.recipe.base.BaseParam;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SelectRegisteredUserParam extends BaseParam {
	
	/**
	 * メールアドレス
	 */
	private String emailAddress;
	
	/**
	 * 登録ステータスコード
	 * ０：仮登録（未認証）
	 * １：本登録（認証済み）
	 */
	private String registerStatusCode;
	
	public SelectRegisteredUserParam() {
		//第一引数SQLID
		//第二引数namespace
		super("SelectRegisteredUser", "mapper.Users");
	}
}
