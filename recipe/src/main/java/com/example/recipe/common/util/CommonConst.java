package com.example.recipe.common.util;

public class CommonConst {
	
	//------------------------------正規表現------------------------------
	//** 正規表現　メールアドレス*/
	public static final String REGEX_EMAIL = "^(?=.{1,255}$)(?=.{1,64}@.{1,255}$)"
															+ "(?!\\.)[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+(?<!\\.)@[a-zA-Z0-9](?:[a-zA-Z0-9-]{2,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z]{2,})+$";
	//** 正規表現　電話番号*/
	public static final String REGEX_PHONENUMBER = "0[5789]0-\\d{4}-\\d{4}|"
																			+ "0(?=.{6})\\d{2,4}-\\d+-\\d{4}|"
																			+ "0(?=.{6})\\d{2,4}\\(\\d+\\)\\d{4}|"
																			+ "0\\d{9}|\\d{1,4}-\\d{4}|"
																			+ "\\(\\d{1,4}\\)\\d{4}";
	//------------------------------文字種別------------------------------
	//** 文字種別　半角英字*/
	public static final String CHARTYPE_HALFWIDTH_ALPHABET ="a-zA-Z";
	//** 文字種別　半角数字*/
	public static final String CHARTYPE_HALFWIDTH_NUMBER = "0-9";
	//** 文字種別　半角記号*/
	public static final String CHARTYPE_HALFWIDTH_SYMBOL = "!#$%()*+\\-./:;=?@\\[\\]`\\{|\\}~";
	//** 文字種別　全角文字*/
	public static final String CHARTYPE_FULLWIDTH = "^[^\\x00-\\x7F]+$";
	//** 文字種別　禁則文字*/
	public static final String CHARTYPE_FORBIDDENCHAR = ".*[\"'&<>\\x{FF61}-\\x{FF9F}\\u005C].*";
	//** 文字種別　外字*/
	public static final String CHARTYPE_EXTERNALCHAR = "";
	//** 文字種別　機種依存文字*/
	public static final String CHARTYPE_PLATFORMDEPENDENTCHAR = "";
	
	//------------------------------ID種別------------------------------
	//** ID種別　ユーザID*/
	public static final String ID_TYPE_US = "US";
	//** ID種別　レシピID*/
	public static final String ID_TYPE_RC = "RC";
	//** */
	public static final String dummy2 = "";
	
	//------------------------------modelに設定するキー------------------------------
	//** ユーザ登録画面表示DTO*/
	public static final String KEY_USERREGISTRATION_DTO = "UserRegistrationDto";
	
	//------------------------------画面名------------------------------
	/** ユーザ登録画面*/
	public static final String SCREENID_USERREGISTRATION = "UserRegistration";
	/** ログイン画面*/
	public static final String SCREENID_LOGIN = "Login";
	/** ユーザ情報登録画面*/
	public static final String SCREENID_USERINFOREGISTRATION = "UserInfoRegistration";
	/** ユーザ情報管理画面*/
	public static final String SCREENID_USERMANAGEMENT = "UserManagement";
	/** レシピ一覧画面*/
	public static final String SCREENID_RECIPELIST = "RecipeList";
	/** レシピ詳細画面*/
	public static final String SCREENID_RECIPEDETAIL = "RecipeDetail";
	/** レシピ登録画面*/
	public static final String SCREENID_RECIPEREGISTRATION = "RecipeRegistration";
	/** レシピ情報管理画面*/
	public static final String SCREENID_RECIPEMANAGEMENT = "RecipeManagement";
	
	//------------------------------リダイレクト------------------------------
	/** ユーザ登録画面*/
	public static final String REDIRECT_USERREGISTRATION = "redirect:/UserRegistration/initShow";
	/** ログイン画面*/
	public static final String REDIRECT_LOGIN = "redirect:/Login";
	/** ユーザ情報登録画面*/
	public static final String REDIRECT_USERINFOREGISTRATION = "redirect:/UserInfoRegistration";
	/** ユーザ情報管理画面*/
	public static final String REDIRECT_USERMANAGEMENT = "redirect:/UserManagement";
	/** レシピ一覧画面*/
	public static final String REDIRECT_RECIPELIST = "redirect:/RecipeList";
	/** レシピ詳細画面*/
	public static final String REDIRECT_RECIPEDETAIL = "redirect:/RecipeDetail";
	/** レシピ登録画面*/
	public static final String REDIRECT_RECIPEREGISTRATION = "redirect:/RecipeRegistration";
	/** レシピ情報管理画面*/
	public static final String REDIRECT_RECIPEMANAGEMENT = "redirect:/RecipeManagement";
}
