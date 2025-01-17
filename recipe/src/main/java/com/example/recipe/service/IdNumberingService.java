package com.example.recipe.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.inject.Inject;

import org.springframework.stereotype.Component;

import com.example.recipe.base.BaseService;
import com.example.recipe.common.DateTimeGenerator;
import com.example.recipe.common.MyBatisDao;
import com.example.recipe.entity.param.InsertIdNumberingMasterParam;
import com.example.recipe.entity.param.SelectSerialNumberParam;
import com.example.recipe.entity.param.UpdateIdNumberingMagnageParam;
import com.example.recipe.entity.result.SelectSerialNumberEntity;

@Component
public class IdNumberingService extends BaseService {
	
	/**
	 * コンストラクタ
	 * @param dao
	 */
	@Inject
	public IdNumberingService(MyBatisDao dao) {
		this.dao = dao;
	}
	
	/**
	 * 採番したIDを返却する
	 * @param idType
	 * @return
	 */
	public String getNumbering(String idType, String userId) {
		
		//システム日付をyyMMdd形式で取得
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String idPrefix = today.format(formatter);
		
		
		//現在の連番を取得
		SelectSerialNumberParam selectSerialNumberParam = new SelectSerialNumberParam();
		selectSerialNumberParam.setIdType(idType);
		selectSerialNumberParam.setIdPrefix(idPrefix);
		SelectSerialNumberEntity selectSerialNumberEntity = dao.selectByPk(selectSerialNumberParam);
		
		//指定したID種別、接頭語に合致するレコードが存在しない場合
		if(selectSerialNumberEntity==null) {
			//新たにレコードを作成
			InsertIdNumberingMasterParam insertIdNumberingMasterParam = new InsertIdNumberingMasterParam();
			insertIdNumberingMasterParam.setIdType(idType);
			insertIdNumberingMasterParam.setIdPrefix(idPrefix);
			insertIdNumberingMasterParam.setIdSerialNumber("0000000000");
			insertIdNumberingMasterParam.setRegisterDate(DateTimeGenerator.getTimestampDateTime());
			insertIdNumberingMasterParam.setRegisteredUserId("system");
			dao.insertByValue(insertIdNumberingMasterParam);
			
			//登録後、再度連番を取得
			selectSerialNumberParam.setIdType(idType);
			selectSerialNumberParam.setIdPrefix(idPrefix);
			selectSerialNumberEntity = dao.selectByPk(selectSerialNumberParam);
		}
		
		//現在の連番をもとに次の連番を作成
		int  nextSerialNumber = (Integer.parseInt(selectSerialNumberEntity.getIdSerialNumber())) + 1;
		
		//10桁に満たない場合は左に0を詰める
		String formattedNextSerialNumber = String.format("%010d", nextSerialNumber);
		
		String numberedId = idType + idPrefix + formattedNextSerialNumber;
		
		//採番管理マスタを更新する
		UpdateIdNumberingMagnageParam updateIdNumberingMagnageParam = new UpdateIdNumberingMagnageParam();
		updateIdNumberingMagnageParam.setIdType(idType);
		updateIdNumberingMagnageParam.setIdPrefix(idPrefix);
		updateIdNumberingMagnageParam.setIdSerialNumber(formattedNextSerialNumber);
		updateIdNumberingMagnageParam.setUpdateDate(DateTimeGenerator.getTimestampDateTime());
		
		//ユーザ新規登録判定
		if(userId==null) {
			//ユーザ新規登録（ユーザID未作成）の場合
			updateIdNumberingMagnageParam.setUpdatedUserId(numberedId);
		}else {
			//登録済みユーザの場合
			updateIdNumberingMagnageParam.setUpdatedUserId(userId);
		}
		
		//採番管理マスタを最新の連番に更新する
		dao.updateByValue(updateIdNumberingMagnageParam);
		
		return numberedId;
	}
}
