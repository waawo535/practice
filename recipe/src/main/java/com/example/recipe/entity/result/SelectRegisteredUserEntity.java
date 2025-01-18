package com.example.recipe.entity.result;

import com.example.recipe.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SelectRegisteredUserEntity extends BaseEntity {
	
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
	
	public SelectRegisteredUserEntity() {
		super("SelectRegisteredUser", "com.example.entity.result");
	}
}
