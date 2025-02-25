package com.example.recipe.dto.ServiceIn;

import com.example.recipe.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRecipeRelationAlterFavIn extends BaseDto {
	
	private String recipeId;
	
	private String userId;
}
