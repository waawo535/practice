package com.example.recipe.service;

import java.util.ArrayList;
import java.util.List;

import jakarta.inject.Inject;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.recipe.base.BaseService;
import com.example.recipe.common.MyBatisDao;
import com.example.recipe.dto.Ingredient;
import com.example.recipe.dto.ServiceIn.RecipeDetailServiceInitShowIn;
import com.example.recipe.entity.param.SelectRecipeByIdParam;
import com.example.recipe.entity.param.SelectRecipeIngredientsParam;
import com.example.recipe.entity.param.SelectRecipeStepsParam;
import com.example.recipe.entity.param.SelectUserRecipeRelationParam;
import com.example.recipe.entity.result.SelectRecipeByIdEntity;
import com.example.recipe.entity.result.SelectRecipeIngredientsEntity;
import com.example.recipe.entity.result.SelectRecipeStepsEntity;
import com.example.recipe.entity.result.SelectRecipeUserRelationEntity;

@Service
public class RecipeDetailService extends BaseService {
	
	MyBatisDao dao;
	
	@Inject
	RecipeDetailService(MyBatisDao dao){
		this.dao = dao;
	}
	public void initShow(RecipeDetailServiceInitShowIn inDto) {
		//レシピ情報取得
		SelectRecipeByIdParam selectRecipeByIdParam = new SelectRecipeByIdParam();
		selectRecipeByIdParam.setRecipeId(inDto.getRecipeId());
		SelectRecipeByIdEntity selectRecipeByIdEntity = dao.selectByPk(selectRecipeByIdParam);
		
		//材料取得
		SelectRecipeIngredientsParam selectRecipeIngredientsParam = new SelectRecipeIngredientsParam();
		selectRecipeIngredientsParam.setRecipeId(inDto.getRecipeId());
		List<SelectRecipeIngredientsEntity> selectRecipeIngredientsEntity = dao.selectList(selectRecipeIngredientsParam);
		List<Ingredient> ingredients = new ArrayList<>();
		for(SelectRecipeIngredientsEntity entity:selectRecipeIngredientsEntity) {
			Ingredient ingredient = new Ingredient();
			ingredient.setIngredientName(entity.getIngredientName());
			ingredient.setAmount(entity.getAmount());
			ingredients.add(ingredient);
		}
		
		//手順取得
		SelectRecipeStepsParam selectRecipeStepsParam = new SelectRecipeStepsParam();
		selectRecipeStepsParam.setRecipeId(inDto.getRecipeId());
		List<SelectRecipeStepsEntity> selectRecipeStepsEntity = dao.selectList(selectRecipeStepsParam);
		List<String> stepList = new ArrayList<>();
		for(SelectRecipeStepsEntity instruction:selectRecipeStepsEntity) {
			stepList.add(instruction.getInstruction());
		}
		
		// お気に入りステータス取得
		SelectUserRecipeRelationParam selectUserRecipeRelationParam = new SelectUserRecipeRelationParam();
		selectUserRecipeRelationParam.setRecipeId(inDto.getRecipeId());
		selectUserRecipeRelationParam.setUserId(inDto.getUserId());
		SelectRecipeUserRelationEntity selectRecipeUserRelationEntity = dao.selectByPk(selectUserRecipeRelationParam);
		
		BeanUtils.copyProperties(selectRecipeByIdEntity, inDto.getRecipeDetailDto());
		inDto.getRecipeDetailDto().setRecipeIngredients(ingredients);
		inDto.getRecipeDetailDto().setStepsList(stepList);
		if(selectRecipeUserRelationEntity!=null) {
			inDto.getRecipeDetailDto().setFavFlg(selectRecipeUserRelationEntity.isFavFlg());
		}
	}
}
