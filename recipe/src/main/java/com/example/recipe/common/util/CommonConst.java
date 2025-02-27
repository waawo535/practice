package com.example.recipe.common.util;

public class CommonConst {
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
	/** 公開ステータス：非公開*/
	public static final String PUBLIC = "0";
	/** 公開ステータス：公開*/
	public static final String PRIVATE = "1";
	//------------------------------キー------------------------------
	/** セッション情報保持DTO*/
	public static final String KEY_SESSIONINFO = "SessionInfo";
	/** セッション情報保持DTO*/
	public static final String KEY_SYSTEMINFO = "SystemInfo";
	/** 遷移元画面名*/
	public static final String KEY_PREVSCREEN = "prevScreen";
	/** ユーザ登録画面表示DTO*/
	public static final String KEY_USR01 = "UserRegistrationDto";
	/** ユーザ認証画面表示DTO*/
	public static final String KEY_AUTH01 = "UserAuthenticationDto";
	/** ログイン画面表示DTO*/
	public static final String KEY_LGN01 = "LoginDto";
	/** ユーザポータル画面表示DTO*/
	public static final String KEY_UPRT01 = "UserPortalDto";
	/** ユーザ情報管理画面表示用DTO*/
	public static final String KEY_UINF01 = "UserInfoManagementDto";
	/** */
	public static final String KEY_RCP01 = "CreateRecipeDto";
	/** */
	public static final String KEY_RCP02 = "EditRecipeDto";
	/** */
	public static final String KEY_RCP03 = "RecipeDetailDto";
	//------------------------------画面名------------------------------
	/** ユーザ登録画面*/
	public static final String SCREENID_USR01 = "UserRegistration";
	/** 認証画面*/
	public static final String SCREENID_AUTH01 = "UserAuthentication";
	/** ログイン画面*/
	public static final String SCREENID_LGN01 = "Login";
	/**ログアウト*/
	public static final String SCREENID_OUT01 = "Logout";
	/** ユーザポータル画面*/
	public static final String SCREENID_UPRT01 = "UserPortal";
	/** ユーザ情報管理画面*/
	public static final String SCREENID_UINF01 = "UserInfoManagement";
	/** レシピ作成画面*/
	public static final String SCREENID_RCP01 = "CreateRecipe";
	/** レシピ編集画面*/
	public static final String SCREENID_RCP02 = "EditRecipe";
	/** レシピ詳細画面*/
	public static final String SCREENID_RCP03 = "RecipeDetail";
	
	//------------------------------リダイレクト------------------------------
	/** ユーザ登録画面*/
	public static final String REDIRECT_USR01 = "redirect:/UserRegistration/initShow";
	/** 認証画面*/
	public static final String REDIRECT_AUTH01 = "redirect:/UserRegistration/authentication/initShow";
	/** ログイン画面*/
	public static final String REDIRECT_LGN01 = "redirect:/Login/initShow";
	/** ユーザポータル画面*/
	public static final String REDIRECT_UPRT01 = "redirect:/UserPortal/initShow";
	/** ユーザ情報管理画面*/
	public static final String REDIRECT_UINF01 = "redirect:/UserInfoManagement/initShow";
	/** レシピ作成画面*/
	public static final String REDIRECT_RCP01 = "redirect:/CreateRecipe/initShow";
	/** レシピ詳細画面*/
	public static final String REDIRECT_RCP03 = "redirect:/RecipeDetail/initShow";
}
