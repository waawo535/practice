package com.example.recipe.common;


import java.util.List;

import jakarta.inject.Named;

import com.example.recipe.base.BaseEntity;
import com.example.recipe.base.BaseExample;
import com.example.recipe.base.BaseParam;

@Named("dao")
public class MyBatisDao extends SqlSessionDaoSupportEx {
	
	/*
	 * 1件検索
	 * 主キーの値に合致する1件の検索結果を取得する場合に使用
	 * @param entity
	 * @param <Entity> entity
	 * @return エンティティ
	 */
	public <Entity extends BaseEntity, Param extends BaseParam> Entity selectByPk(Param param) {
		String sqlId = param.getSqlId();
		return getSqlSession().selectOne(param.getNameSpace()+"."+sqlId, param);
	}
	
	public <Entity extends BaseEntity, Param extends BaseParam> int insertByValue(Param param) {
		String sqlId = param.getSqlId();
		return getSqlSession().insert(param.getNameSpace()+"."+sqlId, param);
	}
	public <Entity extends BaseEntity, Param extends BaseParam> int updateByValue(Param param) {
		String sqlId = param.getSqlId();
		return getSqlSession().update(param.getNameSpace()+"."+sqlId, param);
	}
	
	
	/*
	 * 複数件検索
	 * 主キーが存在する場合は主キーでソート
	 * 1テーブルに対し全体のデータを取得するときに使用
	 * @param entity
	 * @param <Entity> entity
	 * @return エンティティリスト
	 */
	public <Entity extends BaseEntity> List<Entity> selectList(Entity entity) {
		return getSqlSession().selectList(entity.getNameSpace()+"."+entity.getTableName()+".selectAll", entity);
	}
	
	/*
	 * 件数検索
	 * 1テーブルに対し、検索対象に合致するデータの件数を取得する場合に使用
	 * 検索件数はBaseExampleクラスをインスタンス化し作成
	 * 変数がnullとなるカラムの検索条件は除外
	 * @param entity
	 * @param <Entity> entity
	 * @param example
	 * @return 件数
	 */
	public <Entity extends BaseEntity> Long countByValue(Entity entity, BaseExample example) {

		return getSqlSession().selectOne(entity.getNameSpace()+"."+entity.getTableName()+".countByExample", example);
	}
	
	/*
	 * 1件追加
	 * 1件のデータをnullを含めて追加したい場合に使用
	 * 変数がnullとなるカラムには値を設定しない
	 */
//	public <Entity extends BaseEntity> int insertByValue(Entity entity, BaseExample example) {
//		return getSqlSession().insert(entity.getNameSpace()+"."+entity.getTableName()+".insertSelective", entity);
//	}
	
	/*
	 * 1件更新
	 * 主キーの値に合致する1件のデータを部分的に更新したい場合に使用
	 * 変数に値を設定したカラムのみを更新
	 * 変数がnullになるカラムは更新しない
	 * 
	 */
	public <Entity extends BaseEntity> int updateByPk(Entity entity, BaseExample example) {
		return getSqlSession().update(entity.getNameSpace()+"."+entity.getTableName()+".updateByPrimaryKeySelective", entity);
	}
	
	/*
	 * 1件削除
	 * 主キーの値に合致する1件のデータを削除するときに使用
	 */
	public <Entity extends BaseEntity> int deleteByPk(Entity entity, BaseExample example) {
		return getSqlSession().delete(entity.getNameSpace()+"."+entity.getTableName()+".deleteByPrimaryKey", entity);
	}
}



