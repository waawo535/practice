package com.example.recipe.service;

import jakarta.inject.Inject;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.recipe.base.BaseService;
import com.example.recipe.common.DateTimeGenerator;
import com.example.recipe.common.MyBatisDao;
import com.example.recipe.common.util.CommonConst;
import com.example.recipe.dto.ServiceIn.UserRegistrationServiceIn;
import com.example.recipe.dto.ServiceOut.UserRegistrationServiceOut;
import com.example.recipe.entity.param.InsertRegisteredUserParam;
import com.example.recipe.entity.param.InsertUserDetailParam;
import com.example.recipe.entity.param.SelectRegisteredUserParam;
import com.example.recipe.entity.result.SelectRegisteredUserEntity;

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
	 * @param emailAdress
	 * @return
	 */
	public UserRegistrationServiceOut register(UserRegistrationServiceIn userRegistrationServiceIn) {
		
		UserRegistrationServiceOut userRegistrationServiceOut = new UserRegistrationServiceOut();
		
		//ユーザ未作成判定処理
		SelectRegisteredUserParam selectRegisteredUserParam = new SelectRegisteredUserParam();
		selectRegisteredUserParam.setEmailAdress(userRegistrationServiceIn.getEmailAdress());
		SelectRegisteredUserEntity selectRegisteredUserEntity = dao.selectByPk(selectRegisteredUserParam);
		
		if(!(selectRegisteredUserEntity==null)) {
			//入力したメアドと一致するユーザが存在する場合
			userRegistrationServiceOut.setEmailExistingFlg(true);
		}else {
			//入力したメアドと一致するユーザが存在しない場合
			userRegistrationServiceOut.setEmailExistingFlg(false);
		}
		
		//パスワードとパスワード（確認用）の整合性チェック
		boolean passwordIntegrity = passwordCheck(userRegistrationServiceIn.getPassword(), userRegistrationServiceIn.getComfirmPassword());
		userRegistrationServiceOut.setPasswordCheckFlg(passwordIntegrity);
		
		//パスワードが不整合又はメールアドレスが登録済みの場合
		if(!passwordIntegrity||userRegistrationServiceOut.isEmailExistingFlg()) {
			//呼び出し元に戻る
			return userRegistrationServiceOut;
		}
		
		//パスワードをハッシュ化
		String hashedPassword = hashPassword(userRegistrationServiceIn.getPassword());
		
		//ユーザIDを採番
		String userId = idNumberingService.getNumbering(CommonConst.ID_TYPE_US, null);
		
		//ユーザテーブルに値を登録
		InsertRegisteredUserParam insertRegisteredUserParam = new InsertRegisteredUserParam();
		insertRegisteredUserParam.setUserId(userId);
		insertRegisteredUserParam.setDeleteFlag(false);
		insertRegisteredUserParam.setRegisterDate(DateTimeGenerator.getTimestampDateTime());
		insertRegisteredUserParam.setRegisteredUserId(userId);
		dao.insertByValue(insertRegisteredUserParam);
		
		//ユーザ詳細テーブルに値を登録
		InsertUserDetailParam insertUserDetailParam = new InsertUserDetailParam();
		insertUserDetailParam.setUserId(userId);
		insertUserDetailParam.setUserName("TBD");
		insertUserDetailParam.setEmailAdress(userRegistrationServiceIn.getEmailAdress());
		insertUserDetailParam.setPassword(hashedPassword);
		insertUserDetailParam.setRegisterDate(DateTimeGenerator.getTimestampDateTime());
		insertUserDetailParam.setRegisteredUserId(userId);
		dao.insertByValue(insertUserDetailParam);
		
		return userRegistrationServiceOut;
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
}
