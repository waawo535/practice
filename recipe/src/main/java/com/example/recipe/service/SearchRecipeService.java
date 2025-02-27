package com.example.recipe.service;

import java.util.List;

import jakarta.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.recipe.base.BaseService;
import com.example.recipe.common.MyBatisDao;
import com.example.recipe.dto.ServiceIn.SearchRecipeSearchIn;
import com.example.recipe.entity.param.SelectRecipeSearchListParam;
import com.example.recipe.entity.result.SelectRecipeByIdEntity;

@Service
public class SearchRecipeService extends BaseService {
	
	@Inject
	SearchRecipeService(MyBatisDao dao){
		this.dao = dao;
	}
	
	public void search(SearchRecipeSearchIn inDto) {
		SelectRecipeSearchListParam selectRecipeSearchListParam = new SelectRecipeSearchListParam();
		selectRecipeSearchListParam.setSearchCondition(inDto.getSearchCondition().getSearchCondition());
		List<SelectRecipeByIdEntity> selectRecipeByIdEntity = dao.selectList(selectRecipeSearchListParam);
		
		inDto.getUserPortalDto().set;
	}
}
