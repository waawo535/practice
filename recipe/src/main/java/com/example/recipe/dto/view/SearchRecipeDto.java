package com.example.recipe.dto.view;

import com.example.recipe.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRecipeDto extends BaseDto {
	private String searchCondition;
}
