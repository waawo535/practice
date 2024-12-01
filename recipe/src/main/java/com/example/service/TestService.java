package com.example.service;

import jakarta.inject.Inject;
import jakarta.inject.Named;

import com.example.base.BaseService;
import com.example.common.MyBatisDao;
import com.example.dto.TestServiceIn;
import com.example.dto.TestServiceOut;
import com.example.entity.param.SelectRecipeDetailParam;
import com.example.entity.result.SelectRecipeDetailEntity;

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
	public TestServiceOut getSomeThing(TestServiceIn testServiceIn) {
		
		TestServiceOut testServiceOut = new TestServiceOut();
		
		SelectRecipeDetailParam selectRecipeDetailParam = new SelectRecipeDetailParam();
		selectRecipeDetailParam.setRecipeId(1);
		
		SelectRecipeDetailEntity selectRecipeDetailEntity = dao.selectByPk(selectRecipeDetailParam);
		
		testServiceOut = (TestServiceOut) selectRecipeDetailEntity;
		
		return testServiceOut;
	}
}
