package com.example.recipe.service;

import java.util.List;

import jakarta.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.recipe.base.BaseService;
import com.example.recipe.common.MyBatisDao;
import com.example.recipe.dto.ServiceIn.UserPortalInitShowIn;
import com.example.recipe.entity.param.SelectRecipeListByPopularityParam;
import com.example.recipe.entity.param.SelectRecipeListByTimeParam;
import com.example.recipe.entity.param.SelectUserRecipeRelationParam;
import com.example.recipe.entity.result.SelectRecipeByIdEntity;
import com.example.recipe.entity.result.SelectRecipeUserRelationEntity;

@Service
public class UserPortalService extends BaseService {
	
	MyBatisDao dao;
	
	@Inject
	UserPortalService(MyBatisDao dao){
		this.dao = dao;
	}
	/**
	 * 初期表示用
	 */
	public void initShow(UserPortalInitShowIn inDto) {
		
		//レシピリスト一覧（人気順）を取得する
		SelectRecipeListByPopularityParam selectRecipeListByPopularityParam = new SelectRecipeListByPopularityParam();
		selectRecipeListByPopularityParam.setDeleteFlag(false);
		selectRecipeListByPopularityParam.setRegisteredUserId(inDto.getUserId());
		List<SelectRecipeByIdEntity> listByPopularity = dao.selectList(selectRecipeListByPopularityParam);
		for(SelectRecipeByIdEntity list:listByPopularity) {
			// お気に入りステータス取得
			SelectUserRecipeRelationParam selectUserRecipeRelationParam = new SelectUserRecipeRelationParam();
			selectUserRecipeRelationParam.setRecipeId(list.getRecipeId());
			selectUserRecipeRelationParam.setUserId(inDto.getUserId());
			SelectRecipeUserRelationEntity selectRecipeUserRelationEntity = dao.selectByPk(selectUserRecipeRelationParam);
			if(selectRecipeUserRelationEntity!=null) {
				list.setFavFlg(selectRecipeUserRelationEntity.isFavFlg());
			}
		}
		
		//レシピリスト一覧（新着順）を取得する
		SelectRecipeListByTimeParam selectRecipeListByTimeParam = new SelectRecipeListByTimeParam();
		selectRecipeListByTimeParam.setDeleteFlag(false);
		selectRecipeListByTimeParam.setRegisteredUserId(inDto.getUserId());
		List<SelectRecipeByIdEntity> listByTime = dao.selectList(selectRecipeListByTimeParam);
		for(SelectRecipeByIdEntity list:listByTime) {
			// お気に入りステータス取得
			SelectUserRecipeRelationParam selectUserRecipeRelationParam = new SelectUserRecipeRelationParam();
			selectUserRecipeRelationParam.setRecipeId(list.getRecipeId());
			selectUserRecipeRelationParam.setUserId(inDto.getUserId());
			SelectRecipeUserRelationEntity selectRecipeUserRelationEntity = dao.selectByPk(selectUserRecipeRelationParam);
			if(selectRecipeUserRelationEntity!=null) {
				list.setFavFlg(selectRecipeUserRelationEntity.isFavFlg());
			}
		}
		inDto.getUserPortalDto().setListByPopularity(listByPopularity);
		inDto.getUserPortalDto().setListByTime(listByTime);
	}
	
	/**
	 * レシピ一覧を取得する
	 */
	public  void getRecipeLists() {
		
		
		
	}
}
