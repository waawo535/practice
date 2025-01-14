package com.example.recipe.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessageDto implements Serializable {
	
	/**
	 * エラーメッセージID
	 */
	private String errorMessageId;
	
	/**
	 * エラーメッセージ内容
	 */
	private String errorMessage;
}
