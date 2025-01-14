package com.example.recipe.common.checker;

public class NullOrEmptyChecker {
	
	/**
	 * NULLまたは空文字かを判定
	 * @param str
	 * @return true:NULLまたは空文字 false:NULL及び空文字ではない
	 */
	public static boolean isNullOrEmpty(String str) {
		boolean result;
		result = str == null || "".equals(str);
		return result;
	}
}
