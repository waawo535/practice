package com.example.recipe.common;

import com.example.recipe.base.BaseEntity;
import com.example.recipe.base.BaseParam;

public class test {
	public <Entity extends BaseEntity, Param extends BaseParam> Entity selectByPk(Param param) {
	    String sqlId = param.getSqlId();
	    String nameSpace = param.getNameSpace();

	    // 動的にパラメータの値を取得
	    try {
	        // 全てのフィールド名を取得
	        Field[] fields = param.getClass().getDeclaredFields();
	        for (Field field : fields) {
	            field.setAccessible(true); // プライベートフィールドにもアクセス
	            String fieldName = field.getName(); // フィールド名を取得
	            Object value = field.get(fieldName); // フィールドの値を取得
	            if (value != null) {
	                return getSqlSession().selectOne(nameSpace + "." + sqlId, value);
	            }
	        }
	    } catch (IllegalAccessException | NoSuchFieldException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

}
