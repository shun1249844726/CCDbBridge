package com.lexinsmart.action;

import com.lexinsmart.service.JKLEDService;

public class JKLEDAction {
	public static void main(String args[]) {
		/** 只用填写下面内容 从这里开始 **/
		String weatherType = "阵雨"; // 天气
		String fengxiang = "东南风 3-4级"; // 风力
		String temperature = "气温 8-12度"; // 气温
		String humidity = "湿度79%";// 湿度
		/** 到这里 **/


		/**
		 * 将参数 拼接成 显示内容，控制显示内容的格式 
		 * 大屏显示内容
		 * 天气信息few我
		 */
		String weather = weatherType + "\n" + fengxiang + "\n" + temperature + "\n" + humidity;

		JKLEDService jkledService = new JKLEDService(weather);
		jkledService.sendToLed();
	}
}
