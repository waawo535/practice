package com.example.recipe.service;

import jakarta.inject.Inject;
import jakarta.inject.Named;

import com.example.recipe.base.BaseService;
import com.example.recipe.common.DateTimeGenerator;
import com.example.recipe.common.MyBatisDao;
import com.example.recipe.entity.param.InsertRegisteredUserParam;
import com.example.recipe.entity.param.SelectRegisteredUserParam;
import com.example.recipe.entity.result.SelectRegisteredUserEntity;

@Named
public class UserRegistrationService extends BaseService{
	
	//システム日付
	DateTimeGenerator DateTimeGenerator = new DateTimeGenerator();
	
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
	public String register(String emailAdress) {
		
		SelectRegisteredUserParam selectRegisteredUserParam = new SelectRegisteredUserParam();
		selectRegisteredUserParam.setEmailAdress(emailAdress);
		SelectRegisteredUserEntity selectRegisteredUserEntity = dao.selectByPk(selectRegisteredUserParam);
		
		if(selectRegisteredUserEntity.getEmailAdress()==null) {
			//入力したメアドと一致するユーザが存在する場合
			
			//あとで戻り値のデータ型考える
			return null;
		}else {
			//入力したメアドと一致するユーザが存在しない場合
			
			//ユーザIDを採番
			IdNumberingService idNumberingService = new IdNumberingService();
			String userId = idNumberingService.getNumbering("US");
			
			//ユーザテーブルに値を登録
			InsertRegisteredUserParam insertRegisteredUserParam = new InsertRegisteredUserParam();
			insertRegisteredUserParam.setUserId(userId);
			insertRegisteredUserParam.setDeleteFlag(false);
			insertRegisteredUserParam.setRegisterDate(emailAdress);
			insertRegisteredUserParam.setRegisteredUserId(userId);
			dao.insertByValue(insertRegisteredUserParam);
			
			//
			
		}
		
		return null;
	}
}
