package com.example.recipe.entity.result;

import com.example.recipe.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectRecipeIngredientsEntity extends BaseEntity {
	
	private String recipeID;
	
	private int ingredientNumber;
	
	private String ingredientName;
	
	private String amount;
	
	public SelectRecipeIngredientsEntity(String tableName, String nameSpace) {
		super(tableName, nameSpace);
	}

}
