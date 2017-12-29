package com.lexinsmart.action;

import java.util.Calendar;
import java.util.Date;

import com.lexinsmart.service.JKLEDService;
import com.lexinsmart.utils.DateUtils;

public class JKLEDAction {
	public static void main(String args[]) {
		/** 只用填写下面内容 从这里开始 **/
		String ccpLastAccidentDate = "2015-07-07";// 确保格式，长春化工无事故日期
		String cbsLastAccidentDate = "2015-12-26";// 确保格式，承包商无事故日期。
		String weatherType = "阵雨"; // 天气
		String fengxiang = "东南风 3-4级"; // 风力
		String temperature = "气温 8-12度"; // 气温
		String humidity = "湿度79%";// 湿度
		/** 到这里 **/

		Date ccpDate = DateUtils.StringToDate(ccpLastAccidentDate);
		Date cbsDate = DateUtils.StringToDate(cbsLastAccidentDate);
		Date nowDate = new Date(System.currentTimeMillis());
		int ccpLastAccidentDays = daysOfTwo(ccpDate, nowDate);// 计算相隔天数
		int cbsLastAccidentDays = daysOfTwo(cbsDate, nowDate);
	
		System.out.println("day:"+ccpLastAccidentDays+"   "+cbsLastAccidentDays);
		/**
		 * 将参数 拼接成 显示内容，控制显示内容的格式 
		 * 大屏显示内容  1：距2015年7月7日 长春化工安全生产无事故  **天
		 * 2：距承包商安全无事故**天
		 * 3：天气信息：
		 */
		String arg1 = "距" + ccpLastAccidentDate + "\n" + "长春化工安全" + "\n" + "生产无事故" + "\n" + ccpLastAccidentDays + "天";
		String arg2 = "距承包商安全" + "\n" + "无事故" + cbsLastAccidentDays + "天";
		String arg3 = weatherType + "\n" + fengxiang + "\n" + temperature + "\n" + humidity;

		JKLEDService jkledService = new JKLEDService(arg1, arg2, arg3);
		jkledService.sendToLed();

	}
/**
 * 计算两个日期之间的天数
 * @param fDate
 * @param oDate
 * @return
 */
	public static int daysOfTwo(Date early, Date late) {
		Calendar calst = Calendar.getInstance();   
        Calendar caled = Calendar.getInstance();   
        calst.setTime(early);   
         caled.setTime(late);   
         //设置时间为0时   
         calst.set(Calendar.HOUR_OF_DAY, 0);   
         calst.set(Calendar.MINUTE, 0);   
         calst.set(Calendar.SECOND, 0);   
         caled.set(Calendar.HOUR_OF_DAY, 0);   
         caled.set(Calendar.MINUTE, 0);   
         caled.set(Calendar.SECOND, 0);   
        //得到两个日期相差的天数   
         int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst   
                .getTime().getTime() / 1000)) / 3600 / 24;   
         
        return days;   
	}
}
