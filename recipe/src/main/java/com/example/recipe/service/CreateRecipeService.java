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
import com.example.recipe.dto.ServiceIn.CreateRecipeSaveRecipeIn;
import com.example.recipe.dto.ServiceOut.CreateRecipeSaveRecipeOut;

@Service
public class CreateRecipeService extends BaseService {
	
	MultipartFile multipartFile;
	/**
	 * コンストラクタ
	 * @param dao
	 */
	@Inject
	public CreateRecipeService(MyBatisDao dao, MultipartFile multipartFile) {
		this.dao = dao;
		this.multipartFile = multipartFile;
	}
	
	
	public CreateRecipeSaveRecipeOut saveRecipe(CreateRecipeSaveRecipeIn inDto) throws IOException {
		CreateRecipeSaveRecipeOut outDto = new CreateRecipeSaveRecipeOut();
		
		//ファイル保存処理
		String fileName = createFileName(inDto.getRecipeImg(), inDto.getUserId());
		saveFile(fileName);
		
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
