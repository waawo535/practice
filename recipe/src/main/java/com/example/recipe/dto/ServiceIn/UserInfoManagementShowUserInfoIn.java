package com.example.recipe.dto.ServiceIn;

import com.example.recipe.dto.view.UserInfoManagementDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoManagementShowUserInfoIn {
	
	/**
	 * ユーザID
	 */
	private String userId;
	
	private UserInfoManagementDto userInfoManagementDto;
}
