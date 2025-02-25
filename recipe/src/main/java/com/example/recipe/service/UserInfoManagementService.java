package com.example.recipe.service;

import java.util.ArrayList;
import java.util.List;

import jakarta.inject.Inject;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.recipe.base.BaseService;
import com.example.recipe.common.DateTimeGenerator;
import com.example.recipe.common.MyBatisDao;
import com.example.recipe.dto.ServiceIn.UserInfoManagementEditProfileIn;
import com.example.recipe.dto.ServiceIn.UserInfoManagementShowUserInfoIn;
import com.example.recipe.dto.ServiceOut.UserInfoManagementEditProfileOut;
import com.example.recipe.entity.param.SelectRecipeByIdParam;
import com.example.recipe.entity.param.SelectUserDetailParam;
import com.example.recipe.entity.param.SelectUserRecipeListParam;
import com.example.recipe.entity.param.SelectUserRecipeRelationParam;
import com.example.recipe.entity.param.UpdateUserDetailProfileParam;
import com.example.recipe.entity.result.SelectRecipeByIdEntity;
import com.example.recipe.entity.result.SelectRecipeUserRelationEntity;
import com.example.recipe.entity.result.SelectUserDetailEntity;
import com.example.recipe.entity.result.SelectUserRecipeListEntity;

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
	
	/**
	 * DBからユーザ情報を取得する
	 * @param inDto
	 * @return
	 */
	public void showUserInfo(UserInfoManagementShowUserInfoIn inDto) {
		
		//ユーザ情報を取得
		SelectUserDetailParam selectUserDetailParam = new SelectUserDetailParam();
		selectUserDetailParam.setUserId(inDto.getUserId());
		SelectUserDetailEntity selectUserDetailEntity = dao.selectByPk(selectUserDetailParam);
		
		//ユーザが作成したレシピ情報を取得
		SelectUserRecipeListParam selectUserRecipeListParam = new SelectUserRecipeListParam();
		selectUserRecipeListParam.setUserId(inDto.getUserId());
		selectUserRecipeListParam.setDeleteFlag(false);
		selectUserRecipeListParam.setLimit(10);
		selectUserRecipeListParam.setOffset(0);
		List<SelectUserRecipeListEntity> selectUserRecipeListEntityList = dao.selectList(selectUserRecipeListParam);
		
		//お気に入り登録済みのレシピを取得
		SelectUserRecipeRelationParam selectUserRecipeRelationParam = new SelectUserRecipeRelationParam();
		selectUserRecipeRelationParam.setUserId(inDto.getUserId());
		List<SelectRecipeUserRelationEntity> selectRecipeUserRelationEntity = dao.selectList(selectUserRecipeRelationParam);
		List<SelectRecipeByIdEntity> selectFavList = new ArrayList<>();
		SelectRecipeByIdParam selectRecipeByIdParam = new SelectRecipeByIdParam();
		int i = 0;
		for(SelectRecipeUserRelationEntity list:selectRecipeUserRelationEntity) {
			if(selectRecipeUserRelationEntity.get(i).isFavFlg()) {
				selectRecipeByIdParam.setRecipeId(list.getRecipeId());
				SelectRecipeByIdEntity selectRecipeByIdEntity = dao.selectByPk(selectRecipeByIdParam);
				selectFavList.add(selectRecipeByIdEntity);
			}
			i++;
		}
		
		//Outパラメタに取得したユーザ情報を設定
		BeanUtils.copyProperties(selectUserDetailEntity, inDto.getUserInfoManagementDto());
		inDto.getUserInfoManagementDto().setRecipeList(selectUserRecipeListEntityList);
		inDto.getUserInfoManagementDto().setFavoriteRecipeList(selectFavList);
	}
	
	/**
	 * プロフィール編集
	 * @param inDto
	 * @return
	 */
	public UserInfoManagementEditProfileOut editProfile(UserInfoManagementEditProfileIn inDto) {
		
		UserInfoManagementEditProfileOut outDto = new UserInfoManagementEditProfileOut();
		
		UpdateUserDetailProfileParam param = new UpdateUserDetailProfileParam();
		param.setUserName(inDto.getUserName());
		param.setBirthDate(inDto.getBirthDate());
		param.setBio(inDto.getBio());
		param.setGender(inDto.getGender());
		param.setEmailAddress(inDto.getEmailAddress());
		param.setPhoneNumber(inDto.getPhoneNumber());
		param.setProfileImgUrl(inDto.getProfileImgUrl());
		param.setUpdatedUserId(inDto.getUserId());
		param.setUpdateDate(DateTimeGenerator.getTimestampDateTime());
		
		
		
		return outDto;
	}
}
