package com.example.recipe.common.util;

public class CommonConst {
	
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
	
	//------------------------------ID種別------------------------------
	/** ID種別　ユーザID*/
	public static final String ID_TYPE_US = "US";
	/** ID種別　レシピID*/
	public static final String ID_TYPE_RC = "RC";
	/** */
	public static final String dummy2 = "";
	//------------------------------ID種別------------------------------
	/** 登録ステータスコード：仮登録*/
	public static final String PROVISIONAL_REGISTRATION ="0";
	/** 登録ステータスコード：本登録*/
	public static final String DEFINITIVE_REGISTRATION = "1";
	//------------------------------キー------------------------------
	/** セッション情報保持DTO*/
	public static final String KEY_SESSIONINFO_DTO = "SessionInfo";
	/** システム情報*/
	public static final String KEY_SYSYTEMINFO_DTO = "SystemInfo";
	/** 遷移元画面名*/
	public static final String KEY_PREVSCREEN = "prevScreen";
	/** ユーザ登録画面表示DTO*/
	public static final String KEY_USERREGISTRATION_DTO = "UserRegistrationDto";
	/** ユーザ認証画面表示DTO*/
	public static final String KEY_USERAUTHENTICATION_DTO = "UserAuthenticationDto";
	/** ログイン画面表示DTO*/
	public static final String KEY_LOGIN_DTO = "LoginDto";
	/** ユーザ情報管理画面表示用DTO*/
	public static final String KEY_USERINFOMANAGEMENT_DTO = "UserInfoManagementDto";
	//------------------------------画面名------------------------------
	/** ユーザ登録画面*/
	public static final String SCREENID_USERREGISTRATION = "UserRegistration";
	/** 認証画面*/
	public static final String SCREENID_AUTHENTICATION = "UserAuthentication";
	/** ログイン画面*/
	public static final String SCREENID_LOGIN = "Login";
	/**ログアウト*/
	public static final String SCREENID_LOGOUT = "Logout";
	/** ユーザポータル画面*/
	public static final String SCREENID_USERPORTAL = "UserPortal";
	/** ユーザ情報登録画面*/
	public static final String SCREENID_USERINFOREGISTRATION = "UserInfoRegistration";
	/** ユーザ情報管理画面*/
	public static final String SCREENID_USERINFOMANAGEMENT = "UserInfoManagement";
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
	/** 認証画面*/
	public static final String REDIRECT_AUTHENTICATION = "redirect:/UserRegistration/authentication/initShow";
	/** ログイン画面*/
	public static final String REDIRECT_LOGIN = "redirect:/Login/initShow";
	/** */
	
	/** ユーザ情報登録画面*/
	public static final String REDIRECT_USERINFOREGISTRATION = "redirect:/UserInfoRegistration/initShow";
	/** ユーザ情報管理画面*/
	public static final String REDIRECT_USERINFOMANAGEMENT = "redirect:/UserInfoManagement/initShow";
	/** レシピ一覧画面*/
	public static final String REDIRECT_RECIPELIST = "redirect:/RecipeList/initShow";
	/** レシピ詳細画面*/
	public static final String REDIRECT_RECIPEDETAIL = "redirect:/RecipeDetail/initShow";
	/** レシピ登録画面*/
	public static final String REDIRECT_RECIPEREGISTRATION = "redirect:/RecipeRegistration/initShow";
	/** レシピ情報管理画面*/
	public static final String REDIRECT_RECIPEMANAGEMENT = "redirect:/RecipeManagement/initShow";
}
