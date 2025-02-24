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
import com.example.recipe.common.checker.NullOrEmptyChecker;
import com.example.recipe.common.util.CommonConst;
import com.example.recipe.dto.ServiceIn.CreateRecipeSaveRecipeIn;
import com.example.recipe.dto.ServiceOut.CreateRecipeSaveRecipeOut;
import com.example.recipe.entity.param.InsertRecipeInfoParam;
import com.example.recipe.entity.param.InsertRecipeIngredientsParam;
import com.example.recipe.entity.param.InsertRecipeStepsParam;

@Service
public class CreateRecipeService extends BaseService {
	
	IdNumberingService idNumberingService;
	
	/**
	 * コンストラクタ
	 * @param dao
	 */
	@Inject
	public CreateRecipeService(MyBatisDao dao, IdNumberingService idNumberingService) {
		this.dao = dao;
		this.idNumberingService = idNumberingService;
	}
	
	
	public CreateRecipeSaveRecipeOut saveRecipe(CreateRecipeSaveRecipeIn inDto) throws IOException {
		CreateRecipeSaveRecipeOut outDto = new CreateRecipeSaveRecipeOut();
		
		String fileName = "";
		//ファイルアップロードをしているかどうか判定
		if(!NullOrEmptyChecker.isNullOrEmpty(inDto.getRecipeImg().getOriginalFilename())) {
			//ファイル保存処理
			fileName = createFileName(inDto.getRecipeImg(), inDto.getUserId());
			saveFile(inDto.getRecipeImg(), fileName);
		}
		
		//レシピID取得
		String recipeId = idNumberingService.getNumbering(CommonConst.ID_TYPE_RC, inDto.getUserId());
		
		//レシピテーブル登録処理
		InsertRecipeInfoParam insertRecipeInfoParam = new InsertRecipeInfoParam();
		insertRecipeInfoParam.setUserId(inDto.getUserId());
		insertRecipeInfoParam.setRecipeId(recipeId);
		insertRecipeInfoParam.setRecipeName(inDto.getRecipeName());
		insertRecipeInfoParam.setRecipeAveRating(null);
		insertRecipeInfoParam.setRecipeImg(fileName);
		insertRecipeInfoParam.setRecipeDescrip(inDto.getRecipeDiscrip());
		insertRecipeInfoParam.setPublishStatus(inDto.getPublishStatus());
		insertRecipeInfoParam.setDeleteFlg(false);
		insertRecipeInfoParam.setRegisterDate(DateTimeGenerator.getTimestampDateTime());
		insertRecipeInfoParam.setRegisteredUserId(inDto.getUserId());
		insertRecipeInfoParam.setUpdateDate(DateTimeGenerator.getTimestampDateTime());
		insertRecipeInfoParam.setUpdatedUserId(inDto.getUserId());
		dao.insertByValue(insertRecipeInfoParam);
		
		//材料テーブル登録処理
		InsertRecipeIngredientsParam insertRecipeIngredientsParam = new InsertRecipeIngredientsParam();
		if(!NullOrEmptyChecker.isNullOrEmpty(inDto.getRecipeIngredients())) {
			for(int i=1;i<=inDto.getRecipeIngredients().size();i++) {
				insertRecipeIngredientsParam.setRecipeId(recipeId);
				insertRecipeIngredientsParam.setIngredientNumber(i);
				insertRecipeIngredientsParam.setIngredientName(inDto.getRecipeIngredients().get(i-1).getIngredientName());
				insertRecipeIngredientsParam.setAmount(inDto.getRecipeIngredients().get(i-1).getAmount());
				dao.insertByValue(insertRecipeIngredientsParam);
			}
		}
		
		//手順テーブル登録処理
		InsertRecipeStepsParam insertRecipeStepsParam = new InsertRecipeStepsParam();
		if(!NullOrEmptyChecker.isNullOrEmpty(inDto.getStepsList())) {
			for(int i=1;i<=inDto.getStepsList().size();i++) {
				insertRecipeStepsParam.setRecipeId(recipeId);
				insertRecipeStepsParam.setStepNumber(i);
				insertRecipeStepsParam.setInstruction(inDto.getStepsList().get(i-1));
				dao.insertByValue(insertRecipeStepsParam);
			}
		}
		
		//戻り値いらない気がする
		return outDto;
	}
	
	
	/**
	 * ファイルの名前を「userId_yyyyMMddHHmmss.拡張子」に編集する
	 * @param fileName
	 * @param userId
	 */
	private String createFileName(MultipartFile multipartFile, String userId){
		
		//システム日付をyyyyMMddHHmmssに変換
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		 String now = DateTimeGenerator.now().format(formatter);
		 
		//拡張子を取得
		String extension = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
		
		//「userId_yyyyMMddHHmmss.拡張子」のファイル名を生成
		String newFileName = "RecipeThumbnail" + "_" + userId + "_" + now + extension;
		
		return newFileName;
	}
	
	/**
	 * ファイル保存
	 * @param fileName
	 * @throws IOException
	 */
	private void saveFile(MultipartFile multipartFile, String fileName) throws IOException {
		//保存先パスを指定
		String folderPath = System.getProperty("user.dir") + "/uploads/";
		Path filePath = Paths.get(folderPath, fileName);
		
		try {
			//ファイルを保存
			multipartFile.transferTo(filePath);
		}catch(IOException e) {
			throw new IOException();
		}
	}
}
