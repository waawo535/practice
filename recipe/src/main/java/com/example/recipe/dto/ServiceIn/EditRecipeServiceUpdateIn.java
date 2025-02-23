package com.example.recipe.dto.ServiceIn;

import com.example.recipe.base.BaseDto;
import com.example.recipe.dto.view.EditRecipeDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditRecipeServiceUpdateIn extends BaseDto {
	
	private String userId;
	
	private EditRecipeDto editRecipeDto;
}
