package com.example.recipe.dto.ServiceIn;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class IdNumberingServiceGetNumberingIn {
	
	/**
	 * 種別
	 */
	private String idType;
	
	/**
	 * 接頭語
	 */
	private String idPrefix;
}
