package com.example.recipe.service;

import jakarta.inject.Inject;
import jakarta.inject.Named;

import com.example.recipe.base.BaseService;
import com.example.recipe.common.DateTimeGenerator;
import com.example.recipe.common.MyBatisDao;
import com.example.recipe.entity.param.InsertRegisteredUserParam;
import com.example.recipe.entity.param.InsertUserDetailParam;
import com.example.recipe.entity.param.SelectRegisteredUserParam;
import com.example.recipe.entity.result.SelectRegisteredUserEntity;

@Named
public class UserRegistrationService extends BaseService{
	
	/**
	 * コンストラクタ
	 * @param dao
	 */
	@Inject
	public UserRegistrationService(MyBatisDao dao) {
		this.dao = dao;
	}
	
	/**
	 * ユーザ登録
	 * @param emailAdress
	 * @return
	 */
	public boolean register(String emailAdress) {
		
		//登録成功フラグ
		boolean registerSuccessFlg;
		
		SelectRegisteredUserParam selectRegisteredUserParam = new SelectRegisteredUserParam();
		selectRegisteredUserParam.setEmailAdress(emailAdress);
		SelectRegisteredUserEntity selectRegisteredUserEntity = dao.selectByPk(selectRegisteredUserParam);
		
		if(selectRegisteredUserEntity.getEmailAdress()==null) {
			//入力したメアドと一致するユーザが存在する場合
			registerSuccessFlg = false;
		}else {
			//入力したメアドと一致するユーザが存在しない場合
			
			//ユーザIDを採番
			IdNumberingService idNumberingService = new IdNumberingService();
			String userId = idNumberingService.getNumbering("US");
			
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
			insertUserDetailParam.setEmailAdress(emailAdress);
			registerSuccessFlg = true;
			
		}
		return registerSuccessFlg;
	}
	
	public void registerUserDetail() {
		
	}
}
