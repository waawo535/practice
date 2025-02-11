package com.example.recipe.dto.ServiceOut;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import com.example.recipe.entity.result.SelectUserRecipeListEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoManagementShowUserInfoOut {
	
	private String userId;
	
	private String userName;
	
	private String emailAddress;
	
	private String phoneNumber;
	
	private String profileImgUrl;
	
	private String bio;
	
	private LocalDate birthDate;
	
	private String gender;
	
	private Timestamp registerDate;
	
	private Timestamp updateDate;
	
	private List<SelectUserRecipeListEntity> recipeList;
}
