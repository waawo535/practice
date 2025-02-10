package com.example.recipe.common.checker;

public class NullOrEmptyChecker {
	
	/**
	 * NULLまたは空文字かを判定
	 * @param obj
	 * @return true:NULLまたは空文字 false:NULL及び空文字ではない
	 */
	public static boolean isNullOrEmpty(Object obj) {
		boolean result;
		result = obj == null || "".equals(obj);
		return result;
	}
}
