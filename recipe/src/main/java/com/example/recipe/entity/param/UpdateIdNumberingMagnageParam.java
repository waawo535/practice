package com.example.recipe.entity.param;

import java.sql.Timestamp;

import com.example.recipe.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateIdNumberingMagnageParam extends BaseParam {
	
	/**
	 * 連番
	 */
	private String idSerialNumber;

	/**
	 * 更新年月日時分秒
	 */
	private Timestamp updateDate;
	
	/**
	 * 更新ユーザID
	 */
	private String updatedUserId;
	
	/**
	 * ID種別
	 */
	private String idType;
	
	/**
	 * 接頭語
	 */
	private String idPrefix;
	
	public UpdateIdNumberingMagnageParam() {
		super("UpdateIdNumberingMagnage", "com.example.entity.result");
	}

}
