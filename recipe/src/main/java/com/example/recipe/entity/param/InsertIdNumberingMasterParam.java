package com.example.recipe.entity.param;

import java.sql.Timestamp;

import com.example.recipe.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertIdNumberingMasterParam extends BaseParam {
	
	/**
	 * ID種別
	 */
	private String idType;
	
	/**
	 * 接頭語
	 */
	private String idPrefix;
	
	/**
	 * 連番
	 */
	private String idSerialNumber;
	
	/**
	 * 登録年月日時分秒
	 */
	private Timestamp registerDate;
	
	/**
	 * 登録ユーザID
	 */
	private String registeredUserId;
	
	/**
	 * 更新年月日時分秒
	 */
	private Timestamp updateDate;
	
	/**
	 * 更新ユーザID
	 */
	private String updatedUserId;
	
	public InsertIdNumberingMasterParam() {
		super("InsertIdNumberingMaster", "com.example.entity.result");
	}

}
