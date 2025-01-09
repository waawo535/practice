package com.example.recipe.entity.param;

import com.example.recipe.base.BaseParam;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SelectSerialNumberParam extends BaseParam{
	
	/**
	 * 種別
	 */
	private String IdType;
	
	/**
	 * 接頭語
	 */
	private String IdPrefix;
	
	public SelectSerialNumberParam() {
		super("SelectSerialNumber", "com.example.entity.result");
	}

}
