package com.example.recipe.common;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateTimeGenerator {
	
	/**
	 * システム日付を現在時刻として取得
	 * @return
	 */
    public static LocalDateTime now() {
        return LocalDateTime.now(ZoneId.systemDefault());
    }
    
    /**
     * フォーマットされた現在時刻を取得
     * @param pattern
     * @return
     */
    public static String getFormattedDateTime(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return now().format(formatter);
    }
    	
    /**
     * timestamp形式のシステム日付を取得
     * @return
     */
    public static Timestamp getTimestampDateTime() {
    	return Timestamp.valueOf(now());
    }
}
