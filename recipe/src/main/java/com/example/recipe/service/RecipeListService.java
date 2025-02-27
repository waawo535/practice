package com.example.recipe.service;

import java.util.List;

import jakarta.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.recipe.base.BaseService;
import com.example.recipe.common.MyBatisDao;
import com.example.recipe.common.util.CommonConst;
import com.example.recipe.entity.param.SelectUserRecipeListParam;
import com.example.recipe.entity.result.SelectUserRecipeListEntity;

@Service
public class RecipeListService extends BaseService {
	/**
	 * コンストラクタ
	 * @param dao
	 */
	@Inject
	public RecipeListService(MyBatisDao dao) {
		this.dao = dao;
	}
	
	//レシピ一覧取得
	//画面IDに応じて出力を変えるようにする
	public List<SelectUserRecipeListEntity> getRecipeList(int offset, String screenId, String userId) {
		
		//画面によって処理を変えるかどうか......
		if(screenId.equals(CommonConst.SCREENID_UINF01)) {
			
		}
		
		SelectUserRecipeListParam selectUserRecipeListParam = new SelectUserRecipeListParam();
		selectUserRecipeListParam.setUserId(userId);
		selectUserRecipeListParam.setDeleteFlag(false);
		selectUserRecipeListParam.setLimit(10);
		selectUserRecipeListParam.setOffset(offset);
		List<SelectUserRecipeListEntity> selectUserRecipeListEntityList = dao.selectList(selectUserRecipeListParam);
		
		return selectUserRecipeListEntityList;
	}
}
