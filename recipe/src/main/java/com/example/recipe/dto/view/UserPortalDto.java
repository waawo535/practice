package com.example.recipe.dto.view;

import java.util.List;

import com.example.recipe.base.BaseDto;
import com.example.recipe.entity.result.SelectRecipeByIdEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPortalDto extends BaseDto {
	
	private List<SelectRecipeByIdEntity> listByPopularity;
	
	private List<SelectRecipeByIdEntity> listByTime;
}
