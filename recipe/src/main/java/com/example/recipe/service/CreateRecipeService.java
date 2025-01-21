package com.example.recipe.service;

import org.springframework.stereotype.Service;

import com.example.recipe.base.BaseService;
import com.example.recipe.dto.ServiceIn.CreateRecipeSaveRecipeIn;
import com.example.recipe.dto.ServiceOut.CreateRecipeSaveRecipeOut;

@Service
public class CreateRecipeService extends BaseService {
	
	public CreateRecipeSaveRecipeOut saveRecipe(CreateRecipeSaveRecipeIn inDto) {
		CreateRecipeSaveRecipeOut outDto = new CreateRecipeSaveRecipeOut();
		
		
		
		return outDto;
	}
}
