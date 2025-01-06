package com.example.recipe.base;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BaseParam implements Serializable {
	/*
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * SQLID
	 */
	private String sqlId;
	
	/*
	 * MapperXMLの名前空間
	 */
	private String nameSpace;
	
	public BaseParam(String sqlId, String nameSpace) {
		this.sqlId = sqlId;
		this.nameSpace = nameSpace;
	}
}
