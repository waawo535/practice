package com.example.recipe.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import jakarta.inject.Inject;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.recipe.base.BaseService;
import com.example.recipe.common.DateTimeGenerator;
import com.example.recipe.common.MyBatisDao;
import com.example.recipe.common.checker.NullOrEmptyChecker;
import com.example.recipe.dto.Ingredient;
import com.example.recipe.dto.ServiceIn.EditRecipeServiceGetRecipeDetailIn;
import com.example.recipe.dto.ServiceIn.EditRecipeServiceUpdateIn;
import com.example.recipe.dto.ServiceIn.UserRecipeRelationRateIn;
import com.example.recipe.entity.param.DeleteRecipeIngredientsParam;
import com.example.recipe.entity.param.DeleteRecipeParam;
import com.example.recipe.entity.param.DeleteRecipeStepsParam;
import com.example.recipe.entity.param.InsertRecipeIngredientsParam;
import com.example.recipe.entity.param.InsertRecipeStepsParam;
import com.example.recipe.entity.param.InsertUserRecipeRelation;
import com.example.recipe.entity.param.SelectRecipeByIdParam;
import com.example.recipe.entity.param.SelectRecipeIngredientsParam;
import com.example.recipe.entity.param.SelectRecipeStepsParam;
import com.example.recipe.entity.param.SelectUserRecipeRelationParam;
import com.example.recipe.entity.param.UpdateRecipeInfoParam;
import com.example.recipe.entity.param.UpdateUserRecipeRelation;
import com.example.recipe.entity.result.SelectRecipeByIdEntity;
import com.example.recipe.entity.result.SelectRecipeIngredientsEntity;
import com.example.recipe.entity.result.SelectRecipeStepsEntity;
import com.example.recipe.entity.result.SelectRecipeUserRelationEntity;

@Service
public class EditRecipeService extends BaseService {
	
	@Inject
	 EditRecipeService(MyBatisDao dao) {
		this.dao = dao;
	}
	
	/**
	 * 
	 * @param inDto
	 */
	public void getRecipeDetail(EditRecipeServiceGetRecipeDetailIn inDto) {
		//レシピ情報取得
		SelectRecipeByIdParam selectRecipeByIdParam = new SelectRecipeByIdParam();
		selectRecipeByIdParam.setRecipeId(inDto.getEditRecipeDto().getRecipeId());
		SelectRecipeByIdEntity selectRecipeByIdEntity = dao.selectByPk(selectRecipeByIdParam);
		
//		String recipeImg = "/img/" + selectRecipeByIdEntity.getRecipeImg();
		
		//材料取得
		SelectRecipeIngredientsParam selectRecipeIngredientsParam = new SelectRecipeIngredientsParam();
		selectRecipeIngredientsParam.setRecipeId(inDto.getEditRecipeDto().getRecipeId());
		List<SelectRecipeIngredientsEntity> selectRecipeIngredientsEntity = dao.selectList(selectRecipeIngredientsParam);
		List<Ingredient> ingredients = new ArrayList<>();
		for(SelectRecipeIngredientsEntity entity:selectRecipeIngredientsEntity) {
			Ingredient ingredient = new Ingredient();
			ingredient.setIngredientName(entity.getIngredientName());
			ingredient.setAmount(entity.getAmount());
			ingredients.add(ingredient);
		}
		
		//手順取得
		SelectRecipeStepsParam selectRecipeStepsParam = new SelectRecipeStepsParam();
		selectRecipeStepsParam.setRecipeId(inDto.getEditRecipeDto().getRecipeId());
		List<SelectRecipeStepsEntity> selectRecipeStepsEntity = dao.selectList(selectRecipeStepsParam);
		List<String> stepList = new ArrayList<>();
		for(SelectRecipeStepsEntity instruction:selectRecipeStepsEntity) {
			stepList.add(instruction.getInstruction());
		}
		
		inDto.getEditRecipeDto().setRecipeId(selectRecipeByIdEntity.getRecipeId());
		inDto.getEditRecipeDto().setRecipeName(selectRecipeByIdEntity.getRecipeName());
//		inDto.getEditRecipeDto().setRecipeImg(recipeImg);
		inDto.getEditRecipeDto().setRecipeDescrip(selectRecipeByIdEntity.getRecipeDescrip());
		inDto.getEditRecipeDto().setRecipeIngredients(ingredients);
		inDto.getEditRecipeDto().setStepsList(stepList);
	}
	
	/**
	 * 
	 * @param editRecipeServiceUpdateIn
	 * @throws IOException 
	 */
	public void update(EditRecipeServiceUpdateIn editRecipeServiceUpdateIn) throws IOException {
		
		String recipeId = editRecipeServiceUpdateIn.getEditRecipeDto().getRecipeId();
		
		//レシピテーブルを更新
		UpdateRecipeInfoParam updateRecipeInfoParam = new UpdateRecipeInfoParam();
		BeanUtils.copyProperties(editRecipeServiceUpdateIn.getEditRecipeDto(), updateRecipeInfoParam);
		updateRecipeInfoParam.setUpdatedUserId(editRecipeServiceUpdateIn.getUserId());
		updateRecipeInfoParam.setUpdateDate(DateTimeGenerator.getTimestampDateTime());
		//画像をアップロードした場合は
		String fileName = null;
		if(!NullOrEmptyChecker.isNullOrEmpty(editRecipeServiceUpdateIn.getEditRecipeDto().getRecipeImg().getOriginalFilename())) {
			SelectRecipeByIdParam selectRecipeByIdParam = new SelectRecipeByIdParam();
			selectRecipeByIdParam.setRecipeId(recipeId);
			SelectRecipeByIdEntity selectRecipeByIdEntity = dao.selectByPk(selectRecipeByIdParam);
			fileName = createFileName(editRecipeServiceUpdateIn.getEditRecipeDto().getRecipeImg(), editRecipeServiceUpdateIn.getUserId());
			updateFile(editRecipeServiceUpdateIn.getEditRecipeDto().getRecipeImg(), fileName, selectRecipeByIdEntity.getRecipeImg());
			updateRecipeInfoParam.setRecipeImg(fileName);
		}
		dao.updateByValue(updateRecipeInfoParam);
		
		//材料を更新
		DeleteRecipeIngredientsParam deleteRecipeIngredientsParam = new DeleteRecipeIngredientsParam();
		deleteRecipeIngredientsParam.setRecipeId(recipeId);
		dao.deleteByPk(deleteRecipeIngredientsParam);
		
		int i = 0;
		InsertRecipeIngredientsParam insertRecipeIngredientsParam = new InsertRecipeIngredientsParam();
		for(Ingredient ingredient:editRecipeServiceUpdateIn.getEditRecipeDto().getRecipeIngredients()) {
			insertRecipeIngredientsParam.setRecipeId(recipeId);
			insertRecipeIngredientsParam.setIngredientNumber(i);
			insertRecipeIngredientsParam.setIngredientName(ingredient.getIngredientName());
			insertRecipeIngredientsParam.setAmount(ingredient.getAmount());
			dao.insertByValue(insertRecipeIngredientsParam);
			i++;
		}
		
		//手順を更新
		DeleteRecipeStepsParam deleteRecipeStepsParam= new DeleteRecipeStepsParam();
		deleteRecipeStepsParam.setRecipeId(recipeId);
		dao.deleteByPk(deleteRecipeStepsParam);
		
		int j = 0;
		InsertRecipeStepsParam insertRecipeStepsParam = new InsertRecipeStepsParam();
		for(String steps:editRecipeServiceUpdateIn.getEditRecipeDto().getStepsList()) {
			insertRecipeStepsParam.setRecipeId(recipeId);
			insertRecipeStepsParam.setStepNumber(j);
			insertRecipeStepsParam.setInstruction(steps);
			dao.insertByValue(insertRecipeStepsParam);
			j++;
		}
		
		
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
	
	private void updateFile(MultipartFile multipartFile, String fileName, String prevFileName) throws IOException {
		//保存先パスを指定
		String folderPath = System.getProperty("user.dir") + "/uploads/";
		Path filePath = Paths.get(folderPath, fileName);
		
		try {
			//ファイルを保存
			multipartFile.transferTo(filePath);
		}catch(IOException e) {
			throw new IOException();
		}
        File file = new File(folderPath + prevFileName);
        file.delete();
	}
	
	/**
	 * レシピの削除フラグをtrueにする（レコードは削除しない）
	 * @param recipeId
	 */
	public void delete(String recipeId) {
		DeleteRecipeParam deleteRecipeParam= new DeleteRecipeParam();
		deleteRecipeParam.setRecipeId(recipeId);
		dao.updateByValue(deleteRecipeParam);
	}
	
	/**
	 * 評価
	 * @param inDto
	 */
	public void rate(UserRecipeRelationRateIn inDto) {
		//評価の値
		int rating = Integer.parseInt(inDto.getRating());
		
		SelectUserRecipeRelationParam selectUserRecipeRelationParam = new SelectUserRecipeRelationParam();
		selectUserRecipeRelationParam.setRecipeId(inDto.getRecipeId());
		selectUserRecipeRelationParam.setUserId(inDto.getUserId());
		SelectRecipeUserRelationEntity selectRecipeUserRelationEntity = dao.selectByPk(selectUserRecipeRelationParam);
		
		//ユーザレシピ関連テーブルを更新する
		//関連付け未済の場合
		if(selectRecipeUserRelationEntity==null) {
			InsertUserRecipeRelation insertUserRecipeRelation = new InsertUserRecipeRelation();
			insertUserRecipeRelation.setRecipeId(inDto.getRecipeId());
			insertUserRecipeRelation.setUserId(inDto.getUserId());
			insertUserRecipeRelation.setRating(rating);
			insertUserRecipeRelation.setRelatedAt(DateTimeGenerator.getTimestampDateTime());
			insertUserRecipeRelation.setUpdatedAt(DateTimeGenerator.getTimestampDateTime());
			dao.insertByValue(insertUserRecipeRelation);
			
			//関連付け済の場合
		}else {
			UpdateUserRecipeRelation updateUserRecipeRelation = new UpdateUserRecipeRelation();
			updateUserRecipeRelation.setRecipeId(inDto.getRecipeId());
			updateUserRecipeRelation.setUserId(inDto.getUserId());
			updateUserRecipeRelation.setRating(rating);
			updateUserRecipeRelation.setUpdatedAt(DateTimeGenerator.getTimestampDateTime());
			dao.updateByValue(updateUserRecipeRelation);
		}
		
		SelectRecipeByIdParam selectRecipeByIdParam = new SelectRecipeByIdParam();
		selectRecipeByIdParam.setRecipeId(inDto.getRecipeId());
		SelectRecipeByIdEntity selectRecipeByIdEntity= dao.selectByPk(selectRecipeByIdParam);
		
		//現在の評価
		double currentRating = selectRecipeByIdEntity.getRecipeAveRating();
		//現在の総評価数
		int currentTotalRating = selectRecipeByIdEntity.getRatingTotalCount();
		double prevRating;
		//平均評価と総評価数を更新する
		//ぬるぽ防止
		if(selectRecipeUserRelationEntity!=null) {
			//ユーザがすでに評価を行っていた場合
			if(selectRecipeUserRelationEntity.getRating()!=0) {
				if(currentTotalRating<=1) {
					//ユーザが評価を行う前の平均評価を算出する
					prevRating = 0;
				} else {
					//ユーザが評価を行う前の平均評価を算出する
					prevRating = (currentRating*currentTotalRating-selectRecipeUserRelationEntity.getRating())/(currentTotalRating-1);
				}
				//現在の評価を更新
				currentRating = ((prevRating*(currentTotalRating-1))+rating)/currentTotalRating;
				
				//ユーザがまだ評価を行っていない場合
			}else {
				//現在の評価を更新
				currentRating = (currentRating*currentTotalRating + rating)/(currentTotalRating+1);
				//現在の総評価数を更新
				currentTotalRating += 1;
			}
		}else {
			//現在の評価を更新
			currentRating = (currentRating*currentTotalRating + rating)/(currentTotalRating+1);
			//現在の総評価数を更新
			currentTotalRating += 1;
		}
		
		//レシピ情報テーブルを更新する
		UpdateRecipeInfoParam updateRecipeInfoParam = new UpdateRecipeInfoParam();
		updateRecipeInfoParam.setRecipeId(inDto.getRecipeId());
		updateRecipeInfoParam.setRecipeAveRating(currentRating);
		updateRecipeInfoParam.setRatingTotalCount(currentTotalRating);
		dao.updateByValue(updateRecipeInfoParam);
		
	}
}
