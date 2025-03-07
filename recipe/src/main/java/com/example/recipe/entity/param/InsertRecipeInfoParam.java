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
	
	private double recipeAveRating;
	
	private String recipeImg;
	
	private String recipeDescrip;
	
	private String publishStatus;
	
	private boolean deleteFlg;
	
	private Timestamp registerDate;
	
	private String registeredUserId;
	
	private Timestamp updateDate;
	
	private String updatedUserId;
	
	//テーブルに追加したい
	//private String publishStatus;
	
	public InsertRecipeInfoParam() {
		super("InsertRecipeInfo", "mapper.RecipeInfo");
	}

}
