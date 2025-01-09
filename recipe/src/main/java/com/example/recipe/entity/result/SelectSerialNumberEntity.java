package com.example.recipe.entity.result;

import com.example.recipe.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SelectSerialNumberEntity extends BaseEntity {
	
	/**
	 * 連番
	 */
	private String idSerialNumber;
	
	public SelectSerialNumberEntity() {
		super("SelectSerialNumber", "com.example.entity.result");
	}

}
