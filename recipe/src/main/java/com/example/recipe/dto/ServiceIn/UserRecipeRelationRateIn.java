package com.example.recipe.dto.ServiceIn;

import com.example.recipe.base.BaseService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRecipeRelationRateIn extends BaseService {
	
	private String recipeId;
	
	private String userId;
	
	private String rating;
}
