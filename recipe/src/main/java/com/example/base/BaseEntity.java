package com.example.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BaseEntity {
	/*
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * テーブル名
	 */
	private String tableName;
	
	/*
	 * MapperXMLの名前空間
	 */
	private String nameSpace;
	
	/*
	 * コンストラクタ
	 * @param tableName
	 * @param nameSpace
	 */
	public BaseEntity(String tableName, String nameSpace) {
		this.tableName = tableName;
		this.nameSpace = nameSpace;
	}
}
