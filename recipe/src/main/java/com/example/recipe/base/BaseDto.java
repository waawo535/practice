package com.example.recipe.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.recipe.dto.ErrorMessageDto;
import com.example.recipe.dto.SuccessMessageDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDto implements Serializable {
	
	/**
	 * エラーメッセージリスト
	 */
	private ArrayList<ErrorMessageDto> errormessageAreaList;
	
	/**
	 * エラー項目Map
	 */
	private HashMap<String, String> errorColumnMap;
	
	/**
	 * 成功メッセージリスト
	 */
	private ArrayList<SuccessMessageDto> successmessageAreaList;
	
	/**
	 * コンストラクタ
	 */
	protected BaseDto() {
		this.errormessageAreaList = new ArrayList<ErrorMessageDto>();
		this.errorColumnMap = new HashMap<String, String>();
		this.successmessageAreaList = new ArrayList<SuccessMessageDto>();
	}
}
