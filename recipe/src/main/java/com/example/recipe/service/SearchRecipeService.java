package com.example.recipe.service;

import java.util.List;

import jakarta.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.recipe.base.BaseService;
import com.example.recipe.common.MyBatisDao;
import com.example.recipe.dto.ServiceIn.SearchRecipeSearchIn;
import com.example.recipe.entity.param.SelectRecipeListByFavParam;
import com.example.recipe.entity.param.SelectRecipeListByPopularityParam;
import com.example.recipe.entity.param.SelectRecipeListByTimeParam;
import com.example.recipe.entity.param.SelectUserRecipeRelationParam;
import com.example.recipe.entity.result.SelectRecipeByIdEntity;
import com.example.recipe.entity.result.SelectRecipeUserRelationEntity;

@Service
public class SearchRecipeService extends BaseService {
	
	@Inject
	SearchRecipeService(MyBatisDao dao){
		this.dao = dao;
	}
	
	public void search(SearchRecipeSearchIn inDto) {
		//レシピリスト一覧（人気順）を取得する
		SelectRecipeListByPopularityParam selectRecipeListByPopularityParam = new SelectRecipeListByPopularityParam();
		selectRecipeListByPopularityParam.setDeleteFlag(false);
		selectRecipeListByPopularityParam.setRegisteredUserId(inDto.getUserId());
		selectRecipeListByPopularityParam.setSearchCondition(inDto.getSearchRecipeDto().getSearchCondition());
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
		selectRecipeListByTimeParam.setSearchCondition(inDto.getSearchRecipeDto().getSearchCondition());
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
		//レシピリスト一覧（お気に入り登録順）を取得する
		SelectRecipeListByFavParam selectRecipeListByFavParam = new SelectRecipeListByFavParam();
		selectRecipeListByFavParam.setDeleteFlag(false);
		selectRecipeListByFavParam.setRegisteredUserId(inDto.getUserId());
		selectRecipeListByFavParam.setSearchCondition(inDto.getSearchRecipeDto().getSearchCondition());
		List<SelectRecipeByIdEntity> listByFav = dao.selectList(selectRecipeListByFavParam);
		for(SelectRecipeByIdEntity list:listByFav) {
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
		inDto.getUserPortalDto().setListByFav(listByFav);
	}
}
