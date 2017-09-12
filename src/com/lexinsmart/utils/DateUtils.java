package com.lexinsmart.utils;

import java.sql.Timestamp;

public class DateUtils {
	/**
	 * String ->Timestamp
	 * String tsStr = "2011-05-09 11:49:45";  
	 * @param tsStr
	 * @return
	 */
	public static Timestamp StringToTimestamp(String tsStr){
		Timestamp ts = new Timestamp(System.currentTimeMillis());  
        try {  
            ts = Timestamp.valueOf(tsStr);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
		return ts;
	}
}
