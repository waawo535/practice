package com.example.recipe.service;

import jakarta.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.recipe.base.BaseService;
import com.example.recipe.common.MyBatisDao;
import com.example.recipe.entity.param.DeleteRecipeParam;

@Service
public class EditRecipeService extends BaseService {
	
	@Inject
	public void update(MyBatisDao dao) {
		this.dao = dao;
	}
	
	/**
	 * レシピの削除フラグをtrueにする（レコードは削除しない）
	 * @param recipeId
	 */
	public void delete(String recipeId) {
		DeleteRecipeParam deleteRecipeParam= new DeleteRecipeParam();
		deleteRecipeParam.setRecipeId(recipeId);
		dao.updateByValue(deleteRecipeParam);
	}
}
