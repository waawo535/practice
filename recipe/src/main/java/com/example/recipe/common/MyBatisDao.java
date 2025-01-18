package com.example.recipe.common;


import java.util.List;

import jakarta.inject.Named;

import com.example.recipe.base.BaseEntity;
import com.example.recipe.base.BaseParam;

@Named("dao")
public class MyBatisDao extends SqlSessionDaoSupportEx {

	public <Entity extends BaseEntity, Param extends BaseParam> Entity selectByPk(Param param) {
		String sqlId = param.getSqlId();
		return getSqlSession().selectOne(param.getNameSpace()+"."+sqlId, param);
	}
	
	public <Entity extends BaseEntity> List<Entity> selectList(Entity entity) {
		return getSqlSession().selectList(entity.getNameSpace()+"."+entity.getTableName()+".selectAll", entity);
	}
	
	public <Entity extends BaseEntity, Param extends BaseParam> int insertByValue(Param param) {
		String sqlId = param.getSqlId();
		return getSqlSession().insert(param.getNameSpace()+"."+sqlId, param);
	}
	
	public <Entity extends BaseEntity, Param extends BaseParam> int updateByValue(Param param) {
		String sqlId = param.getSqlId();
		return getSqlSession().update(param.getNameSpace()+"."+sqlId, param);
	}
	
	public <Entity extends BaseEntity, Param extends BaseParam> int deleteByPk(Param param) {
		String sqlId = param.getSqlId();
		return getSqlSession().delete(param.getNameSpace()+"."+sqlId, param);
	}
	

}



