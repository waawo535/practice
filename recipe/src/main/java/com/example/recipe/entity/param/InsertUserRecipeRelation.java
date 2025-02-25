package com.example.recipe.entity.param;

import java.sql.Timestamp;

import com.example.recipe.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertUserRecipeRelation extends BaseParam {
    private String userId;
    private String recipeId;
    private boolean status;
    private boolean  favFlg;
    private String note;
    private int  rating;
    private Timestamp relatedAt;
    private Timestamp updatedAt;
	public InsertUserRecipeRelation() {
		super("InsertUserRecipeRelation", "mapper.UserRecipeRelation");
	}

}
