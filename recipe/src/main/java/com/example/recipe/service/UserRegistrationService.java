package com.example.recipe.service;

import jakarta.inject.Inject;
import jakarta.inject.Named;

import com.example.recipe.base.BaseService;
import com.example.recipe.common.MyBatisDao;
import com.example.recipe.entity.param.SelectRegisteredUserParam;
import com.example.recipe.entity.result.SelectRegisteredUserEntity;

@Named
public class UserRegistrationService extends BaseService{
	
	/*
	 * コンストラクタ
	 */
	@Inject
	public UserRegistrationService(MyBatisDao dao) {
		this.dao = dao;
	}
	
	/*
	 * ユーザ登録
	 */
	public String register(String userName) {
		
		SelectRegisteredUserParam selectRegisteredUserParam = new SelectRegisteredUserParam();
		selectRegisteredUserParam.setUserName(userName);
		SelectRegisteredUserEntity selectRegisteredUserEntity = dao.selectByPk(selectRegisteredUserParam);
		
		if(selectRegisteredUserEntity.getUserName()==null) {
			//入力したユーザ名と一致するユーザが存在する場合
			//あとで戻り値のデータ型考える
			return null;
		}else {
			//入力したユーザ名と一致するユーザが存在しない場合
			
		}
		
		return null;
	}
}
