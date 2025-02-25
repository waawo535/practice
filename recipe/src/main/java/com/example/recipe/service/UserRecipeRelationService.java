package com.example.recipe.service;

import jakarta.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.recipe.base.BaseService;
import com.example.recipe.common.DateTimeGenerator;
import com.example.recipe.common.MyBatisDao;
import com.example.recipe.dto.ServiceIn.UserRecipeRelationAlterFavIn;
import com.example.recipe.entity.param.InsertUserRecipeRelation;
import com.example.recipe.entity.param.SelectUserRecipeRelationParam;
import com.example.recipe.entity.param.UpdateUserRecipeRelation;
import com.example.recipe.entity.result.SelectRecipeUserRelationEntity;

@Service
public class UserRecipeRelationService extends BaseService {
	
	MyBatisDao dao;
	
	@Inject
	UserRecipeRelationService(MyBatisDao dao){
		this.dao = dao;
	}
	
	public void alterFavorite(UserRecipeRelationAlterFavIn inDto) {
		
		SelectUserRecipeRelationParam selectUserRecipeRelationParam = new SelectUserRecipeRelationParam();
		selectUserRecipeRelationParam.setRecipeId(inDto.getRecipeId());
		selectUserRecipeRelationParam.setUserId(inDto.getUserId());
		SelectRecipeUserRelationEntity selectRecipeUserRelationEntity = dao.selectByPk(selectUserRecipeRelationParam);
		
		//関連付けがまだされていない場合
		if(selectRecipeUserRelationEntity==null) {
			InsertUserRecipeRelation insertUserRecipeRelation = new InsertUserRecipeRelation();
			insertUserRecipeRelation.setRecipeId(inDto.getRecipeId());
			insertUserRecipeRelation.setUserId(inDto.getUserId());
			insertUserRecipeRelation.setFavFlg(true);
			insertUserRecipeRelation.setRelatedAt(DateTimeGenerator.getTimestampDateTime());
			insertUserRecipeRelation.setUpdatedAt(DateTimeGenerator.getTimestampDateTime());
			dao.insertByValue(insertUserRecipeRelation);
			
			//お気に入り登録済みの場合
		}else if(selectRecipeUserRelationEntity.isFavFlg()){
			UpdateUserRecipeRelation updateUserRecipeRelation = new UpdateUserRecipeRelation();
			updateUserRecipeRelation.setRecipeId(inDto.getRecipeId());
			updateUserRecipeRelation.setUserId(inDto.getUserId());
			updateUserRecipeRelation.setFavFlg(false);
			updateUserRecipeRelation.setUpdatedAt(DateTimeGenerator.getTimestampDateTime());
			dao.updateByValue(updateUserRecipeRelation);
			
			//お気に入り未登録の場合
		}else if(!selectRecipeUserRelationEntity.isFavFlg()) {
			UpdateUserRecipeRelation updateUserRecipeRelation = new UpdateUserRecipeRelation();
			updateUserRecipeRelation.setRecipeId(inDto.getRecipeId());
			updateUserRecipeRelation.setUserId(inDto.getUserId());
			updateUserRecipeRelation.setFavFlg(true);
			updateUserRecipeRelation.setUpdatedAt(DateTimeGenerator.getTimestampDateTime());
			dao.updateByValue(updateUserRecipeRelation);
		}
		
	}
	
}
