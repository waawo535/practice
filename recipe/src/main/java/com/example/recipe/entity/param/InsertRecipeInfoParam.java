package com.example.recipe.entity.param;

import java.sql.Timestamp;

import com.example.recipe.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertRecipeInfoParam extends BaseParam {
	
	private String userId;
	
	private String recipeId;
	
	private String recipeName;
	
	private String recipeCategory;
	
	private String recipeAveRating;
	
	private String recipeIngredients;
	
	private String recipeImg;
	
	private String recipeDiscrip;
	
	private String recipeMainTxt;
	
	private boolean deleteFlg;
	
	private Timestamp registerDate;
	
	private String registereduserId;
	
	private Timestamp updateDate;
	
	private String updatedUserId;
	
	//テーブルに追加したい
	//private String publishStatus;
	
	public InsertRecipeInfoParam() {
		super("InsertRecipeInfo", "com.example.entity.result");
	}

}
