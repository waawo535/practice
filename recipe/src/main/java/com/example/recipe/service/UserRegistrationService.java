package com.example.recipe.service;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.inject.Inject;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.recipe.base.BaseService;
import com.example.recipe.common.DateTimeGenerator;
import com.example.recipe.common.MyBatisDao;
import com.example.recipe.common.util.CommonConst;
import com.example.recipe.dto.ServiceIn.UserRegistrationServiceCheckTokenIn;
import com.example.recipe.dto.ServiceIn.UserRegistrationServiceIn;
import com.example.recipe.dto.ServiceOut.UserRegistrationServiceCheckTokenOut;
import com.example.recipe.dto.ServiceOut.UserRegistrationServiceOut;
import com.example.recipe.entity.param.InsertRegisteredUserParam;
import com.example.recipe.entity.param.InsertUserDetailParam;
import com.example.recipe.entity.param.InsertUserEmailAuthsParam;
import com.example.recipe.entity.param.SelectRegisteredUserParam;
import com.example.recipe.entity.param.SelectUserEmailAuthsParam;
import com.example.recipe.entity.param.UpdateRegisterStatusParam;
import com.example.recipe.entity.result.SelectRegisteredUserEntity;
import com.example.recipe.entity.result.SelectUserEmailAuthsEntity;

@Service
public class UserRegistrationService extends BaseService{
	
	IdNumberingService idNumberingService;

	/**
	 * コンストラクタ
	 * @param dao
	 */
	@Inject
	public UserRegistrationService(MyBatisDao dao, IdNumberingService idNumberingService) {
		this.dao = dao;
		this.idNumberingService = idNumberingService;
	}
	
	/**
	 * ユーザ登録
	 * メールアドレス存在チェックとパスワード整合性チェック
	 * @param emailAdress
	 * @return
	 */
	public UserRegistrationServiceOut register(UserRegistrationServiceIn userRegistrationServiceIn) {
		
		UserRegistrationServiceOut userRegistrationServiceOut = new UserRegistrationServiceOut();
		
		//ユーザ未作成判定処理
		SelectRegisteredUserParam selectRegisteredUserParam = new SelectRegisteredUserParam();
		selectRegisteredUserParam.setEmailAdress(userRegistrationServiceIn.getEmailAdress());
		SelectRegisteredUserEntity selectRegisteredUserEntity = dao.selectByPk(selectRegisteredUserParam);
		
		//ユーザ登録済み判定処理
		userRegistrationServiceOut.setProvisionallyRegistered(false);
		userRegistrationServiceOut.setDefinitivlyRegistered(false);
		if(!(selectRegisteredUserEntity==null)) {
			//仮登録済みの場合
			if(selectRegisteredUserEntity.getRegisterStatusCode().equals(CommonConst.PROVISIONAL_REGISTRATION)) {
				userRegistrationServiceOut.setProvisionallyRegistered(true);
				
				//本登録済みの場合
			}else if(selectRegisteredUserEntity.getRegisterStatusCode().equals(CommonConst.DEFINITIVE_REGISTRATION)){
				userRegistrationServiceOut.setDefinitivlyRegistered(true);
			}
		}
		
		//パスワードとパスワード（確認用）の整合性チェック
		boolean passwordIntegrity = passwordCheck(userRegistrationServiceIn.getPassword(), userRegistrationServiceIn.getComfirmPassword());
		userRegistrationServiceOut.setPasswordCheckFlg(passwordIntegrity);
		
		//パスワードが不整合又はメールアドレスが登録済みの場合
		if(!passwordIntegrity||userRegistrationServiceOut.isDefinitivlyRegistered()) {
			//呼び出し元に戻る
			return userRegistrationServiceOut;
		}
		
		//ユーザIDを採番
		String userId = idNumberingService.getNumbering(CommonConst.ID_TYPE_US, null);
		
		//認証コードを取得
		String token = generateToken();
		
		//ユーザメール認証テーブルに認証情報を登録
		InsertUserEmailAuthsParam insertUserEmailAuthsParam = new InsertUserEmailAuthsParam();
		insertUserEmailAuthsParam.setUserId(userId);
		insertUserEmailAuthsParam.setEmailAdress(userRegistrationServiceIn.getEmailAdress());
		insertUserEmailAuthsParam.setToken(token);
		insertUserEmailAuthsParam.setExpiryDate(Timestamp.valueOf(generateExpiryDate()));
		dao.insertByValue(insertUserEmailAuthsParam);
		
		//ユーザテーブルに値を登録
		InsertRegisteredUserParam insertRegisteredUserParam = new InsertRegisteredUserParam();
		insertRegisteredUserParam.setUserId(userId);
		insertRegisteredUserParam.setDeleteFlag(false);
		insertRegisteredUserParam.setRegisterStatusCode(CommonConst.PROVISIONAL_REGISTRATION);
		insertRegisteredUserParam.setRegisterDate(DateTimeGenerator.getTimestampDateTime());
		insertRegisteredUserParam.setRegisteredUserId(userId);
		dao.insertByValue(insertRegisteredUserParam);
		
		//パスワードをハッシュ化
		String hashedPassword = hashPassword(userRegistrationServiceIn.getPassword());
		
		//ユーザ詳細テーブルに値を登録
		InsertUserDetailParam insertUserDetailParam = new InsertUserDetailParam();
		insertUserDetailParam.setUserId(userId);
		insertUserDetailParam.setUserName("TBD");
		insertUserDetailParam.setEmailAdress(userRegistrationServiceIn.getEmailAdress());
		insertUserDetailParam.setPassword(hashedPassword);
		insertUserDetailParam.setRegisterDate(DateTimeGenerator.getTimestampDateTime());
		insertUserDetailParam.setRegisteredUserId(userId);
		dao.insertByValue(insertUserDetailParam);
		
		//OutパラメタにユーザIDと認証コードを設定
		userRegistrationServiceOut.setUserId(userId);
		userRegistrationServiceOut.setToken(token);
		
		return userRegistrationServiceOut;
	}
	
	/**
	 * 入力された認証コード整合性チェックと有効期限チェック
	 * @param userRegistrationServiceCheckTokenIn
	 * @return
	 */
	public UserRegistrationServiceCheckTokenOut checkToken(UserRegistrationServiceCheckTokenIn userRegistrationServiceCheckTokenIn) {
		
		UserRegistrationServiceCheckTokenOut userRegistrationServiceCheckTokenOut = new UserRegistrationServiceCheckTokenOut();
		
		//登録ステータスコードを取得
		SelectRegisteredUserParam selectRegisteredUserParam = new SelectRegisteredUserParam();
		selectRegisteredUserParam.setEmailAdress(userRegistrationServiceCheckTokenIn.getEmailAdress());
		SelectRegisteredUserEntity selectRegisteredUserEntity = dao.selectByPk(selectRegisteredUserParam);
		
		//登録ステータスチェック
		if(selectRegisteredUserEntity.getRegisterStatusCode().equals(CommonConst.PROVISIONAL_REGISTRATION)) {
			userRegistrationServiceCheckTokenOut.setRegistrationStatusFlg(true);
		}else {
			userRegistrationServiceCheckTokenOut.setRegistrationStatusFlg(false);
		}
		
		//DBに保存した認証情報を取得
		SelectUserEmailAuthsParam selectUserEmailAuthsParam = new SelectUserEmailAuthsParam();
		selectUserEmailAuthsParam.setUserId(userRegistrationServiceCheckTokenIn.getUserId());
		selectUserEmailAuthsParam.setEmailAdress(userRegistrationServiceCheckTokenIn.getEmailAdress());
		SelectUserEmailAuthsEntity selectUserEmailAuthsEntity = dao.selectByPk(selectUserEmailAuthsParam);
		
		//認証コード整合性チェック
		if(selectUserEmailAuthsEntity.getToken().equals(userRegistrationServiceCheckTokenIn.getToken())) {
			//整合性あり
			userRegistrationServiceCheckTokenOut.setTokenValidateFlg(true);
		}else {
			//整合性なし
			userRegistrationServiceCheckTokenOut.setTokenValidateFlg(false);
		}
		
		//有効期限チェック
		if(selectUserEmailAuthsEntity.getExpiryDate().after(DateTimeGenerator.getTimestampDateTime())) {
			//有効期限内
			userRegistrationServiceCheckTokenOut.setExpiryDateValidateFlg(true);
		}else {
			//有効期限切れ
			userRegistrationServiceCheckTokenOut.setExpiryDateValidateFlg(false);
		}
		
		return userRegistrationServiceCheckTokenOut;
	}
	
	//認証後のユーザ本登録
	public void definitiveRegistration(UserRegistrationServiceIn userRegistrationServiceIn) {
		
		//ユーザの登録ステータスを本登録にする
		UpdateRegisterStatusParam updateRegisterStatusParam = new UpdateRegisterStatusParam();
		updateRegisterStatusParam.setUserId(userRegistrationServiceIn.getUserId());
		updateRegisterStatusParam.setRegisterStatusCode(CommonConst.DEFINITIVE_REGISTRATION);
		dao.updateByValue(updateRegisterStatusParam);
		
		
	}
	
	/**
	 * パスワードとパスワード（確認用）の整合性チェック
	 * @param password
	 * @param confirmPassword
	 * @return true:一致 false:不一致
	 */
	public boolean passwordCheck(String password, String confirmPassword) {
		return password.equals(confirmPassword);
	}
	
	/**
	 * 登録パスワードのハッシュ化
	 * @param password
	 * @return
	 */
	public String hashPassword(String password) {
		// ランダムなsaltを生成してパスワードをハッシュ化
		String salt = BCrypt.gensalt();
		String hashedPassword = BCrypt.hashpw(password, salt);
		
		return hashedPassword;
	}
	
	/**
	 * 認証コードの生成
	 * @return
	 */
    public String generateToken() {
    	//public static final なフィールドにした方がいいのか？
   	 	SecureRandom random = new SecureRandom();
   	 	// 0 から 999999
   	 	int code = random.nextInt(1_000_000); 
   	 	// 6桁にゼロ埋め
   	 	String formattedCode = String.format("%06d", code);
        return String.valueOf(formattedCode);
    }
    
    public LocalDateTime generateExpiryDate() {
    	return DateTimeGenerator.now().plusMinutes(15);
    }
}
