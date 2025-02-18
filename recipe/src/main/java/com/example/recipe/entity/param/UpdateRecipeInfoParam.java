package com.example.recipe.entity.param;

import java.sql.Timestamp;

import com.example.recipe.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRecipeInfoParam extends BaseParam {
	
	private String userId;
	
	private String recipeId;
	
	private String recipeName;
	
	private int recipeAveRating;
	
	private int ratingTotalCount;
	
	private String recipeImg;
	
	private String recipeDescrip;
	
	private Boolean deleteFlag;
	
	private Timestamp registerDate;
	
	private String registeredUserId;
	
	private Timestamp updateDate;
	
	private String updatedUserId;
	
	public UpdateRecipeInfoParam() {
		super("UpdateRecipe", "mapper.RecipeInfo");
	}

}
