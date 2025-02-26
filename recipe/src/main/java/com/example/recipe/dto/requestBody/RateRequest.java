package com.example.recipe.dto.requestBody;

import com.example.recipe.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RateRequest extends BaseDto {
	private String rating;
}
