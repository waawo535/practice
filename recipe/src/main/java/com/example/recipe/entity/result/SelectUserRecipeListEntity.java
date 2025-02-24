package com.example.recipe.entity.result;

import java.nio.file.Path;
import java.sql.Timestamp;

import com.example.recipe.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectUserRecipeListEntity extends BaseEntity {
	
	 private String recipeId;
	 private String  recipeName;
	 private int recipeAveRating;
	 private String recipeImg;
	 private Path recipeImgPath;
	 private String recipeDescrip;
	 private Timestamp registerDate;
	 private Timestamp updateDate; 
	
	
	
	public SelectUserRecipeListEntity() {
		super("'SelectUserRecipeList'", "com.example.entity.result");
	}

}
