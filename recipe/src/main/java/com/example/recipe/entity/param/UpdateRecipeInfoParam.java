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
	
	private double recipeAveRating;
	
	private int ratingTotalCount;
	
	private int favTotalCount;
	
	private String recipeImg;
	
	private String recipeDescrip;
	
	private String publishStatus;
	
	private Boolean deleteFlag;
	
	private Timestamp registerDate;
	
	private String registeredUserId;
	
	private Timestamp updateDate;
	
	private String updatedUserId;
	
	public UpdateRecipeInfoParam() {
		super("UpdateRecipe", "mapper.RecipeInfo");
	}

}
