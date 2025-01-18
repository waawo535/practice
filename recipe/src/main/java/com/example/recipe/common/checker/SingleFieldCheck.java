package com.example.recipe.common.checker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.inject.Named;

import com.example.recipe.common.util.CommonConst;
import com.example.recipe.dto.SingleFieldCheckCheckForBiddenCharOut;
import com.example.recipe.dto.CommonIn.SingleFieldCheckIsMailAdressIn;

@Named
public class SingleFieldCheck {
	
	/**
	 * 半角英数記号チェック
	 * @param str
	 * @return
	 */
	public boolean isHalfWidthAlphanumSymbol(String str) {
		return checkRegEx(str, "^["+ CommonConst.CHARTYPE_HALFWIDTH_ALPHABET+CommonConst.CHARTYPE_HALFWIDTH_NUMBER+CommonConst.CHARTYPE_HALFWIDTH_SYMBOL + "]+$");
	}
	
	/**
	 * 全角文字チェック
	 * @param str
	 * @return
	 */
	public boolean isFullWidth(String str) {
		return str.matches(CommonConst.CHARTYPE_FULLWIDTH);
	}
	
	public boolean isMailAdress(SingleFieldCheckIsMailAdressIn mailAdressIn) {
		
		boolean result = false;
		
		//正規表現チェック
		boolean matchesRegex  = checkRegEx(mailAdressIn.getEmailAddress(), CommonConst.REGEX_EMAIL);
		
		//null拒否フラグチェック
		if(mailAdressIn.isNullDenialFlg()) {
			if(mailAdressIn.getEmailAddress()==null||mailAdressIn.getEmailAddress().equals("")) {
				
			}
		}
		
		return result;
	}
	
	/**
	 * 正規表現と文字列全体がマッチするかチェック
	 * 領域シーケンスの全体がこの正規表現エンジンのパターンとマッチした場合にのみtrue
	 * @param checkRegExIn
	 * @return
	 */
	public boolean checkRegEx(String str, String regEx) {
		
		boolean result;
		
		//Inパラメタ.正規表現をコンパイル
		Pattern pattern = Pattern.compile(regEx);
		
		//マッチさせたい文字列に対するMatcherをPatternから作る
		Matcher matcher = pattern.matcher(str);
		
		//文字列の全体が正規表現とマッチするか調べる
		result = matcher.matches();
		
		return result;
	}
	
	/**
	 * 
	 * @param str
	 * @return true:禁則文字を含む（エラー） false:禁則文字を含まない
	 */
	public SingleFieldCheckCheckForBiddenCharOut checkForbiddenChar(String str) {
		
		SingleFieldCheckCheckForBiddenCharOut singleFieldCheckCheckForBiddenCharOut = new SingleFieldCheckCheckForBiddenCharOut();
		
		boolean forbiddenFlg = false;
		StringBuffer forbiddenCharList = new StringBuffer();
		String checkChar;
		
		for(int i=0; i<str.length(); i++) {
			//前から順に一文字ずつ取り出す
			checkChar = str.substring(i, i+1);
			//取り出した文字が禁則文字であるかどうかチェック
			if(checkRegEx(checkChar, CommonConst.CHARTYPE_FORBIDDENCHAR)) {
				//禁則文字である場合
				
				//その禁則文字が1つ目である場合
				if(forbiddenCharList.length()==0) {
					forbiddenCharList.append(checkChar);
					
					//既出の禁則文字である場合
				}else if(forbiddenCharList.indexOf(checkChar)>0){
					//処理なし
					
					//それ以外
				} else {
					forbiddenCharList.append(","+checkChar);
				}
				
				//チェック結果をtrue(禁則文字あり)に設定
				forbiddenFlg = true;
			}else {
				//禁則文字でない場合
				//処理なし
			}
		}
		
		//Outパラメタに結果フラグと禁則文字リストを設定
		singleFieldCheckCheckForBiddenCharOut.setResult(forbiddenFlg);
		singleFieldCheckCheckForBiddenCharOut.setErrorList(forbiddenCharList.toString());
		
		return singleFieldCheckCheckForBiddenCharOut;
	}
	
	public void checkExternalChar() {
		
	}
	
	public void checkPlatformDependentChar() {
		
	}
	
	/**
	 * 最大桁数チェック
	 * @param str
	 * @param limit
	 * @return true:最大桁数超過（エラー） false:最大桁数以内
	 */
	public boolean checkMaxCharLimit(String str, int limit) {
		return str.length() > limit;
	}
	
	/**
	 * 最小桁数チェック
	 * @param str
	 * @param limit
	 * @return true:最小桁数未満（エラー） fase:最小桁数以上
	 */
	public boolean checkMinCharLimit(String str, int limit) {
		return str.length() < limit;
	}
	
	/**
	 * 最大・最小桁数チェック
	 * @param str
	 * @param maxLimit
	 * @param minLimit
	 * @return true:制約を満たさないとき（エラー） false:制約を満たすとき
	 */
	public boolean checkCharLimit(String str, int maxLimit, int minLimit) {
		
		boolean result;
		
		//制約を満たさないときにtrue
		if(str.length() > maxLimit||str.length() < minLimit) {
			result = true;
		}else {
			result = false;
		}
		
		return result;
	}
}
