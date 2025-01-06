package com.example.recipe.entity.param;

import com.example.recipe.base.BaseParam;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SelectRegisteredUserParam extends BaseParam {
	
	private String UserName;
	
	public SelectRegisteredUserParam() {
		//第一引数SQLID
		//第二引数namespace
		super("SelectRegisteredUser", "com.example.entity.result");
	}
}
