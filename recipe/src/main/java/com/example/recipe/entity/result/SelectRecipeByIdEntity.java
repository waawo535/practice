package com.example.recipe.entity.result;

import java.sql.Timestamp;

import com.example.recipe.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectRecipeByIdEntity extends BaseEntity {
	
	private String userId;
	
	private String recipeId;
	
	private String recipeName;
	
	private double recipeAveRating;
	
	private int ratingTotalCount;
	
	private boolean favFlg;
	
	private String recipeImg;
	
	private String recipeDescrip;
	
	private String publishStatus;
	
	private Timestamp registerDate;
	
	private String registereduserId;
	
	private Timestamp updateDate;
	
	private String updatedUserId;
	
	public SelectRecipeByIdEntity(String tableName, String nameSpace) {
		super(tableName, nameSpace);
	}
}
