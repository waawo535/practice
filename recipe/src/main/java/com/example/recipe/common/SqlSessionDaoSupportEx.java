package com.example.recipe.common;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.support.DaoSupport;
import org.springframework.util.Assert;

public class SqlSessionDaoSupportEx extends DaoSupport {
	
	/*
	 * SQLSession
	 */
	private SqlSession sqlSession;
	
	/*
	 * SQLSession
	 */
	private SqlSession sqlSessionNumbering;
	
	/*
	 * external SQL Session
	 */
	private boolean externalSqlSession;
	
	/*
	 * 与えられたSQLセッションファクトリから生成した
	 * SQLセッションテンプレートをSQKセッションとして設定する
	 * @param sqlSessionFactory SQLセッションファクトリ
	 * @param sqlSessionFactoryNumbering 
	 */
	@Autowired(required=false)
	public final void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory, SqlSessionFactory sqlSessionFactoryNumbering) {
		if(this.externalSqlSession) {
			this.sqlSession = new SqlSessionTemplate(sqlSessionFactory);
			this.sqlSessionNumbering = new SqlSessionTemplate(sqlSessionFactoryNumbering);
		}
	}
	
	/*
	 * 与えられたSQLセッションテンプレートをSQLセッションとして設定する
	 * @param sqlSessionTemplate SQLセッションテンプレート
	 */
	@Autowired(required=true)
	@Qualifier("sqlSession")
	public final void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSession = sqlSessionTemplate;
		this.externalSqlSession = true;
	}
	
	/*
	 *SQLセッションを取得する
	 *@return SQLセッション 
	 */
	public final SqlSession getSqlSession() {
		return this.sqlSession;
	}
	
	/*
	 * 採番共通部品用SQLセッションを取得する
	 * @return SQLセッション
	 */
	public SqlSession getSqlSessionNumbering() {
		return this.sqlSessionNumbering;
	}
	
	/*
	 * DAOの初期化時に設定を確認する
	 */
	@Override
	protected void checkDaoConfig() throws IllegalArgumentException {
		Assert.notNull(this.sqlSession, "Property 'sqlSessionFactory' or 'sqlSessionTemplate' are required");
	}

}
