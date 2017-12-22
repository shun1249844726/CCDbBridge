package com.lexinsmart.action;

import java.util.Calendar;
import java.util.Date;

import com.lexinsmart.service.JKLEDService;
import com.lexinsmart.utils.DateUtils;

public class JKLEDAction {
	public static void main(String args[]) {
		/**   只用填写下面内容  从这里开始   **/
		String ccpLastAccidentDate = "2017-12-10";// 确保格式，
		String cbsLastAccidentDate = "2017-11-10";// 确保格式
		String weatherType = "晴"; //天气
		String fengxiang = "北风 3-4级"; //风力
		String temperature = "气温 0-10度"; // 气温
		String humidity = "湿度85%";//湿度
		/**				到这里         **/
		
		
		Date ccpDate = DateUtils.StringToDate(ccpLastAccidentDate);
		Date cbsDate = DateUtils.StringToDate(cbsLastAccidentDate);
		Date nowDate = new Date(System.currentTimeMillis());
		int ccpLastAccidentDays = daysOfTwo(ccpDate, nowDate);
		int cbsLastAccidentDays = daysOfTwo(cbsDate, nowDate);
		//显示内容
		String arg1 = "距" + ccpLastAccidentDate + "\n" 
								+ "长春化工安全" + "\n" 
								+ "生产无事故" + ccpLastAccidentDays + "天";
		String arg2 = "距承包商安全"+"\n"
								+ "无事故" + cbsLastAccidentDays+"天";
		String arg3 = weatherType +"\n"
								+ fengxiang +"\n"
								+ temperature +"\n"
								+humidity;
		
		JKLEDService jkledService = new JKLEDService(arg1, arg2, arg3);
		jkledService.sendToLed();
		
	}

	public static int daysOfTwo(Date fDate, Date oDate) {
		Calendar aCalendar = Calendar.getInstance();
		aCalendar.setTime(fDate);
		int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
		aCalendar.setTime(oDate);
		int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
		return day2 - day1;
	}
}
