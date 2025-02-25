package com.example.recipe.entity.result;

import java.sql.Timestamp;

import com.example.recipe.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectRecipeUserRelationEntity extends BaseEntity {
	
    private String userId;
    private String recipeId;
    private boolean status;
    private boolean  favFlg;
    private String note;
    private int  rating;
    private Timestamp related_at;
    private Timestamp updated_at;
    
	public SelectRecipeUserRelationEntity(String tableName, String nameSpace) {
		super(tableName, nameSpace);
	}

}
