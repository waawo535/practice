package com.example.recipe.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessMessageDto implements Serializable {
	
	/**
	 * 成功メッセージID
	 */
	private String successMessageId;
	
	/**
	 * 成功メッセージ内容
	 */
	private String successMessage;
}
