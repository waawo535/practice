package com.example.recipe.dto.CommonIn;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleFieldCheckIsMailAdressIn {
	
	/**
	 * メールアドレス
	 */
	private String emailAddress;
	
	/**
	 * null拒否フラグ
	 * true:nullを拒否
	 * false:nullを許可
	 */
	private boolean nullDenialFlg;
}
