package com.example.recipe.dto.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.recipe.base.BaseDto;
import com.example.recipe.dto.Ingredient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRecipeDto extends BaseDto {
	
	private String recipeId;
	
	private String recipeName;
	
	private String recipeCategory;
	
	private MultipartFile  recipeImg;
	
	private String recipeDiscrip;
	
	private List<Ingredient> recipeIngredients;
	
	private List<String> stepsList;
	
	private String publishStatus;
	
	/**
	 * コンストラクタ
	 */
	public CreateRecipeDto() {
		this.recipeIngredients = new ArrayList<Ingredient>();
		this.stepsList = new ArrayList<String>();
		this.recipeIngredients.add(new Ingredient());
		this.stepsList.add("");
	}
}	
