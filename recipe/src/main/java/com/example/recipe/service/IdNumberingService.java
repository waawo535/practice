package com.example.recipe.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.recipe.base.BaseService;
import com.example.recipe.entity.param.SelectSerialNumberParam;
import com.example.recipe.entity.result.SelectSerialNumberEntity;

public class IdNumberingService extends BaseService {
	
	/**
	 * 採番したIDを返却する
	 * @param idType
	 * @return
	 */
	public String getNumbering(String idType) {
		
		//システム日付をyyMMdd形式で取得
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
		String idPrefix = today.format(formatter);
		
		//現在の連番を取得
		SelectSerialNumberParam selectSerialNumberParam = new SelectSerialNumberParam();
		selectSerialNumberParam.setIdType(idType);
		selectSerialNumberParam.setIdPrefix(idPrefix);
		SelectSerialNumberEntity selectSerialNumberEntity = dao.selectByPk(selectSerialNumberParam);
		
		//現在の連番をもとに次の連番を作成
		int  nextSerialNumber = (Integer.parseInt(selectSerialNumberEntity.getIdSerialNumber())) + 1;
		
		String numberedId = idType + idPrefix + nextSerialNumber;
		
		return numberedId;
	}
}
