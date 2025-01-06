package com.example.recipe.service;

import jakarta.inject.Inject;
import jakarta.inject.Named;

import com.example.recipe.base.BaseService;
import com.example.recipe.common.MyBatisDao;
import com.example.recipe.dto.TestServiceIn;
import com.example.recipe.entity.param.SelectRecipeDetailParam;
import com.example.recipe.entity.result.SelectRecipeDetailEntity;

@Named
public class TestService extends BaseService{
	
	private final MyBatisDao dao;
	
	/*
	 * コンストラクタ
	 */
	@Inject
	public TestService(MyBatisDao dao) {
		this.dao = dao;
	}
	
	/*
	 * なんらかを取得するテスト用サービス
	 */
	public SelectRecipeDetailEntity getSomeThing(TestServiceIn testServiceIn) {
		
		SelectRecipeDetailParam selectRecipeDetailParam = new SelectRecipeDetailParam();
		selectRecipeDetailParam.setRecipeId(testServiceIn.getRecipeId());
		return dao.selectByPk(selectRecipeDetailParam);
	}
}
