package com.example.recipe.service;

import java.util.List;

import jakarta.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.recipe.base.BaseService;
import com.example.recipe.common.MyBatisDao;
import com.example.recipe.dto.RecipeListDto;

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
	public List<RecipeListDto> getRecipeList(int offset, String screenId) {
		return null;
	}
}
