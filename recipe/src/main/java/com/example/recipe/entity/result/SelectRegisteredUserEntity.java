package com.example.recipe.entity.result;

import com.example.recipe.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SelectRegisteredUserEntity extends BaseEntity {
	
	private String emailAdress;
	
	public SelectRegisteredUserEntity() {
		super("SelectRegisteredUser", "com.example.entity.result");
	}
}
