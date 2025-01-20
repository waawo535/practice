package com.example.recipe.dto.view;

import java.sql.Timestamp;
import java.time.LocalDate;

import com.example.recipe.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoManagementDto extends BaseDto {
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
}
