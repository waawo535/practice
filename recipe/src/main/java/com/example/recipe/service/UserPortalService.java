package com.example.recipe.service;

import org.springframework.stereotype.Service;

import com.example.recipe.base.BaseService;
import com.example.recipe.dto.ServiceIn.UserPortalInitShowIn;
import com.example.recipe.dto.ServiceOut.UserPortalInitShowOut;

@Service
public class UserPortalService extends BaseService {
	
	/**
	 * 初期表示用
	 */
	public UserPortalInitShowOut initShow(UserPortalInitShowIn inDto) {
		UserPortalInitShowOut outDto = new UserPortalInitShowOut();
		
		//レシピリスト一覧を取得する
		
		
		
		return outDto;
	}
	
	/**
	 * レシピ一覧を取得する
	 */
	public  void getRecipeLists() {
		
		
		
	}
}
