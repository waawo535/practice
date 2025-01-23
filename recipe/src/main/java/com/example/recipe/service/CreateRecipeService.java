package com.example.recipe.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

import jakarta.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.recipe.base.BaseService;
import com.example.recipe.common.DateTimeGenerator;
import com.example.recipe.common.MyBatisDao;
import com.example.recipe.common.util.CommonConst;
import com.example.recipe.dto.ServiceIn.CreateRecipeSaveRecipeIn;
import com.example.recipe.dto.ServiceOut.CreateRecipeSaveRecipeOut;
import com.example.recipe.entity.param.InsertRecipeInfoParam;
import com.example.recipe.entity.param.InsertRecipeIngredientsParam;

@Service
public class CreateRecipeService extends BaseService {
	
	MultipartFile multipartFile;
	
	IdNumberingService idNumberingService;
	
	/**
	 * コンストラクタ
	 * @param dao
	 */
	@Inject
	public CreateRecipeService(MyBatisDao dao, MultipartFile multipartFile, IdNumberingService idNumberingService) {
		this.dao = dao;
		this.multipartFile = multipartFile;
		this.idNumberingService = idNumberingService;
	}
	
	
	public CreateRecipeSaveRecipeOut saveRecipe(CreateRecipeSaveRecipeIn inDto) throws IOException {
		CreateRecipeSaveRecipeOut outDto = new CreateRecipeSaveRecipeOut();
		
		//ファイル保存処理
		String fileName = createFileName(inDto.getRecipeImg(), inDto.getUserId());
		saveFile(fileName);
		
		//レシピID取得
		String recipeId = idNumberingService.getNumbering(CommonConst.ID_TYPE_RC, inDto.getUserId());
		
		//レシピテーブル登録処理
		InsertRecipeInfoParam insertRecipeInfoParam = new InsertRecipeInfoParam();
		insertRecipeInfoParam.setUserId(inDto.getUserId());
		insertRecipeInfoParam.setRecipeId(recipeId);
		insertRecipeInfoParam.setRecipeName(inDto.getRecipeName());
		insertRecipeInfoParam.setRecipeCategory(inDto.getRecipeCategory());
		insertRecipeInfoParam.setRecipeAveRating(null);
		insertRecipeInfoParam.setRecipeIngredients(null);
		insertRecipeInfoParam.setRecipeImg(fileName);
		insertRecipeInfoParam.setRecipeDiscrip(inDto.getRecipeDiscrip());
		insertRecipeInfoParam.setRecipeMainTxt(null);
		insertRecipeInfoParam.setDeleteFlg(false);
		insertRecipeInfoParam.setRegisterDate(DateTimeGenerator.getTimestampDateTime());
		insertRecipeInfoParam.setRegistereduserId(inDto.getUserId());
		insertRecipeInfoParam.setUpdateDate(DateTimeGenerator.getTimestampDateTime());
		insertRecipeInfoParam.setUpdatedUserId(inDto.getUserId());
		dao.insertByValue(insertRecipeInfoParam);
		
		//材料テーブル登録処理
		InsertRecipeIngredientsParam insertRecipeIngredientsParam = new InsertRecipeIngredientsParam();
		for(int i=1;i<=inDto.getRecipeIngredients().size();i++) {
			insertRecipeIngredientsParam.setRecipeId(recipeId);
			insertRecipeIngredientsParam.setIngredientNumber(i);
			//HashMapじゃだめだからあとで考える
			insertRecipeIngredientsParam.setIngredientName(inDto.getRecipeIngredients().);
		}
		//手順テーブル登録処理
		
		
		return outDto;
	}
	
	
	/**
	 * ファイルの名前を「userId_yyyyMMddHHmmss.拡張子」に編集する
	 * @param fileName
	 * @param userId
	 */
	private String createFileName(String fileName, String userId){
		
		//システム日付をyyyyMMddHHmmssに変換
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		 String now = DateTimeGenerator.now().format(formatter);
		 
		//拡張子を取得
		String extension = fileName.substring(fileName.lastIndexOf("."));
		
		//「userId_yyyyMMddHHmmss.拡張子」のファイル名を生成
		String newFileName = userId + "_" + now + extension;
		
		return newFileName;
	}
	
	/**
	 * ファイル保存
	 * @param fileName
	 * @throws IOException
	 */
	private void saveFile(String fileName) throws IOException {
		//保存先パスを指定
		String folderPath = "src/main/resources/static/img";
		Path filePath = Paths.get(folderPath, fileName);
		
		try {
			//ファイルを保存
			multipartFile.transferTo(filePath);
		}catch(IOException e) {
			throw new IOException();
		}
	}
}
