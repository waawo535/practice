package com.example.recipe.entity.result;

import java.sql.Timestamp;

import com.example.recipe.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SelectRecipeListEntity extends BaseEntity {
	
	private String userId;
	
	private String recipeId;
	
	private String recipeName;
	
	private double recipeAveRating;
	
	private String recipeImg;
	
	private String recipeDiscrip;
	
	private Timestamp registerDate;
	
	private String registereduserId;
	
	private Timestamp updateDate;
	
	private String updatedUserId;
	
	public SelectRecipeListEntity() {
		super("SelectRecipeList", "com.example.entity.result");
	}
}
