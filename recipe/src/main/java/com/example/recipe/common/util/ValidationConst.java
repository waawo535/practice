package com.example.recipe.common.util;

public class ValidationConst {
	//------------------------------正規表現------------------------------
	/** 正規表現　メールアドレス*/
	public static final String REGEX_EMAIL = "^(?=.{1,255}$)(?=.{1,64}@.{1,255}$)"
															+ "(?!\\.)[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+(?<!\\.)@[a-zA-Z0-9](?:[a-zA-Z0-9-]{2,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z]{2,})+$";
	/** 正規表現　電話番号*/
	public static final String REGEX_PHONENUMBER = "0[5789]0-\\d{4}-\\d{4}|"
																			+ "0(?=.{6})\\d{2,4}-\\d+-\\d{4}|"
																			+ "0(?=.{6})\\d{2,4}\\(\\d+\\)\\d{4}|"
																			+ "0\\d{9}|\\d{1,4}-\\d{4}|"
																			+ "\\(\\d{1,4}\\)\\d{4}";
	//------------------------------文字種別------------------------------
	//* 文字種別　半角英字*/
	public static final String CHARTYPE_HALFWIDTH_ALPHABET ="a-zA-Z";
	/** 文字種別　半角数字*/
	public static final String CHARTYPE_HALFWIDTH_NUMBER = "0-9";
	/** 文字種別　半角記号*/
	public static final String CHARTYPE_HALFWIDTH_SYMBOL = "!#$%()*+\\-./:;=?@\\[\\]`\\{|\\}~";
	/** 文字種別　全角文字*/
	public static final String CHARTYPE_FULLWIDTH = "^[^\\x00-\\x7F]+$";
	/** 文字種別　禁則文字*/
	public static final String CHARTYPE_FORBIDDENCHAR = ".*[\"'&<>\\x{FF61}-\\x{FF9F}\\u005C].*";
	/** 文字種別　外字*/
	public static final String CHARTYPE_EXTERNALCHAR = "";
	/** 文字種別　機種依存文字*/
	public static final String CHARTYPE_PLATFORMDEPENDENTCHAR = "";
}
