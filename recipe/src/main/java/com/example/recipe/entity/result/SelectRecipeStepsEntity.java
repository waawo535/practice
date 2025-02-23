package com.example.recipe.entity.result;

import com.example.recipe.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectRecipeStepsEntity extends BaseEntity {
	
	private String recipeId;
	
	private int stepNumber;
	
	private String instruction;
	
	public SelectRecipeStepsEntity(String tableName, String nameSpace) {
		super(tableName, nameSpace);
		// TODO 自動生成されたコンストラクター・スタブ
	}

}
