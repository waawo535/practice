package com.example.recipe.service;

import jakarta.inject.Inject;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.recipe.base.BaseService;
import com.example.recipe.common.MyBatisDao;
import com.example.recipe.common.util.CommonConst;
import com.example.recipe.dto.ServiceIn.LoginServiceLoginIn;
import com.example.recipe.dto.ServiceOut.LoginServiceLoginOut;
import com.example.recipe.entity.param.SelectRegisteredUserParam;
import com.example.recipe.entity.param.SelectUserDetailParam;
import com.example.recipe.entity.param.SelectUserDetailPasswordParam;
import com.example.recipe.entity.result.SelectRegisteredUserEntity;
import com.example.recipe.entity.result.SelectUserDetailEntity;
import com.example.recipe.entity.result.SelectUserDetailPasswordEntity;

@Service
public class LoginService extends BaseService {

	/**
	 * コンストラクタ
	 * @param dao
	 */
	@Inject
	public LoginService(MyBatisDao dao, IdNumberingService idNumberingService) {
		this.dao = dao;
	}
	
	//メアド存在チェックとパスワード整合性チェック
	public LoginServiceLoginOut login(LoginServiceLoginIn loginServiceLoginIn) {
		
		LoginServiceLoginOut loginServiceLoginOut = new LoginServiceLoginOut();
		loginServiceLoginOut.setMailAddressExists(false);
		loginServiceLoginOut.setDefinitivlyRegistered(false);
		loginServiceLoginOut.setProvisionallyRegistered(false);
		loginServiceLoginOut.setPasswordCheckPassed(false);
		
		SelectRegisteredUserParam selectRegisteredUserParam= new SelectRegisteredUserParam();
		selectRegisteredUserParam.setEmailAdress(loginServiceLoginIn.getEmailAddress());
		SelectRegisteredUserEntity selectRegisteredUserEntity = dao.selectByPk(selectRegisteredUserParam);
		
		
		//本登録の場合と仮登録の場合を考える
		//DBにメアドが存在する場合
		if(!(selectRegisteredUserEntity==null)) {
			loginServiceLoginOut.setMailAddressExists(true);
			
			//本登録の場合
			if(selectRegisteredUserEntity.getRegisterStatusCode().equals(CommonConst.DEFINITIVE_REGISTRATION)) {
				loginServiceLoginOut.setDefinitivlyRegistered(true);
				
				//仮登録の場合
			}else if(selectRegisteredUserEntity.getRegisterStatusCode().equals(CommonConst.PROVISIONAL_REGISTRATION)){
				loginServiceLoginOut.setProvisionallyRegistered(true);
			}
			
			//DBにメールアドレスが存在しない場合
		}else {
			return loginServiceLoginOut;
		}
		
		//パスワード整合性チェック
		//パスワードをDBから取得
		SelectUserDetailPasswordParam selectUserDetailPasswordParam = new SelectUserDetailPasswordParam();
		selectUserDetailPasswordParam.setEmailAdress(loginServiceLoginIn.getEmailAddress());
		SelectUserDetailPasswordEntity selectUserDetailPasswordEntity = dao.selectByPk(selectUserDetailPasswordParam);
		
		//入力パスワードとDBから取得したハッシュ化パスワードを比較
		boolean passwordCheck = BCrypt.checkpw(loginServiceLoginIn.getPassword(), selectUserDetailPasswordEntity.getPassword());
		 if(passwordCheck) {
			 loginServiceLoginOut.setPasswordCheckPassed(true);
		 }
		 
		 if(!loginServiceLoginOut.isDefinitivlyRegistered()||loginServiceLoginOut.isProvisionallyRegistered()||!loginServiceLoginOut.isPasswordCheckPassed()) {
			 return loginServiceLoginOut;
		 }
		 //DB整合性がない場合はリターン、大丈夫な場合はセッションに保存するためのユーザ情報を取得する
		 SelectUserDetailParam selectUserDetailParam = new SelectUserDetailParam();
		 selectUserDetailParam.setUserId(selectUserDetailPasswordEntity.getUserId());
		 SelectUserDetailEntity selectUserDetailEntity = dao.selectByPk(selectUserDetailParam);
		 
		 //Outパラメタに取得したユーザ情報を設定
		 loginServiceLoginOut.setUserId(selectUserDetailPasswordEntity.getUserId());
		 loginServiceLoginOut.setUserName(selectUserDetailEntity.getUserName());
		 loginServiceLoginOut.setProfileImgUrl(selectUserDetailEntity.getProfileImgUrl());
		 
		return loginServiceLoginOut;
	}
}
