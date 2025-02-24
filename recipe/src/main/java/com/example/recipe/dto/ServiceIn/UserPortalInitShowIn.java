package com.example.recipe.dto.ServiceIn;

import com.example.recipe.base.BaseDto;
import com.example.recipe.dto.view.UserPortalDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPortalInitShowIn extends BaseDto {
	
	private String userId;
	
	private UserPortalDto userPortalDto;
}
