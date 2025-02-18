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
	private String idType;
	
	/**
	 * 接頭語
	 */
	private String idPrefix;
	
	public SelectSerialNumberParam() {
		super("SelectSerialNumber", "mapper.IdNumberingMaster");
	}

}
