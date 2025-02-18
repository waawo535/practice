package com.example.recipe.service;

import jakarta.inject.Inject;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.recipe.base.BaseService;
import com.example.recipe.common.MyBatisDao;
import com.example.recipe.dto.ServiceIn.EditRecipeServiceUpdateIn;
import com.example.recipe.entity.param.DeleteRecipeParam;
import com.example.recipe.entity.param.UpdateRecipeInfoParam;

@Service
public class EditRecipeService extends BaseService {
	
	@Inject
	 EditRecipeService(MyBatisDao dao) {
		this.dao = dao;
	}
	
	/**
	 * 
	 * @param editRecipeServiceUpdateIn
	 */
	public void update(EditRecipeServiceUpdateIn editRecipeServiceUpdateIn) {
		UpdateRecipeInfoParam updateRecipeInfoParam = new UpdateRecipeInfoParam();
		BeanUtils.copyProperties(editRecipeServiceUpdateIn, updateRecipeInfoParam);
		dao.updateByValue(updateRecipeInfoParam);
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
