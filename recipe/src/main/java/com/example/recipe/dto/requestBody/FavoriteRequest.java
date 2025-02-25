package com.example.recipe.dto.requestBody;

import com.example.recipe.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoriteRequest extends BaseDto {
	private String recipeId;
}
