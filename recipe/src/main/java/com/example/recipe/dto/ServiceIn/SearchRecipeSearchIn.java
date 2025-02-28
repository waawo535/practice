package com.example.recipe.dto.ServiceIn;

import com.example.recipe.base.BaseDto;
import com.example.recipe.dto.view.SearchRecipeDto;
import com.example.recipe.dto.view.UserPortalDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRecipeSearchIn extends BaseDto {
	
	private String userId;
	
	private String screenId;
	
	private SearchRecipeDto searchRecipeDto;
	
	private UserPortalDto userPortalDto;
}
