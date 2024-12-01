package com.example.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.common.MyBatisDao;

public class BaseService {
	
	/*
	 * ロガーの宣言
	 */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/*
	 * 共通DAO
	 */
	@Autowired
	protected MyBatisDao dao;
	
}
