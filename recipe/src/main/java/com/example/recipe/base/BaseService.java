package com.example.recipe.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.recipe.common.MyBatisDao;

public class BaseService {
	
	/*
	 * ロガーの宣言
	 */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/*
	 * 共通DAO
	 */
	protected MyBatisDao dao;
	
}
