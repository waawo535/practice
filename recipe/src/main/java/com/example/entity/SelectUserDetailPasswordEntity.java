package com.example.entity;

import com.example.recipe.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectUserDetailPasswordEntity extends BaseEntity {

	/**
	 * パスワード
	 */
	private String password;
	
	public SelectUserDetailPasswordEntity() {
		super("SelectUserDetailPassword", "com.example.entity.result");
	}
}
