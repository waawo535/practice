package com.example.recipe.service;

import jakarta.inject.Inject;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.recipe.base.BaseService;
import com.example.recipe.common.MyBatisDao;
import com.example.recipe.dto.ServiceIn.UserInfoManagementShowUserInfoIn;
import com.example.recipe.dto.ServiceOut.UserInfoManagementShowUserInfoOut;
import com.example.recipe.entity.param.SelectUserDetailParam;
import com.example.recipe.entity.result.SelectUserDetailEntity;

@Service
public class UserInfoManagementService extends BaseService {
	
	/**
	 * コンストラクタ
	 * @param dao
	 */
	@Inject
	public UserInfoManagementService(MyBatisDao dao) {
		this.dao = dao;
	}
	
	public UserInfoManagementShowUserInfoOut showUserInfo(UserInfoManagementShowUserInfoIn inDto) {
		UserInfoManagementShowUserInfoOut outDto = new UserInfoManagementShowUserInfoOut();
		
		//ユーザ情報を取得
		SelectUserDetailParam selectUserDetailParam = new SelectUserDetailParam();
		selectUserDetailParam.setUserId(inDto.getUserId());
		SelectUserDetailEntity selectUserDetailEntity = dao.selectByPk(selectUserDetailParam);
		
		//Outパラメタに取得したユーザ情報を設定
		BeanUtils.copyProperties(selectUserDetailEntity, outDto);
		
		return outDto;
	}
	
	
}
