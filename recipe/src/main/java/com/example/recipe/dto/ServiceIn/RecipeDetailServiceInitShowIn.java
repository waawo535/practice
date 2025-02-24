package com.example.recipe.dto.ServiceIn;

import com.example.recipe.base.BaseDto;
import com.example.recipe.dto.view.RecipeDetailDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeDetailServiceInitShowIn extends BaseDto {
	private String recipeId;
	private RecipeDetailDto recipeDetailDto;
}
