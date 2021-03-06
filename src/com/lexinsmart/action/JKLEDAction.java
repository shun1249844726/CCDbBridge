package com.lexinsmart.action;

import com.lexinsmart.service.JKLEDService;
/**
 * 大屏天气接口
 * @author xushun
 *
 */
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
		 * 天气信息
		 */
		String weather = weatherType + "\n" + fengxiang + "\n" + temperature + "\n" + humidity;

		JKLEDService jkledService = new JKLEDService(weather);
		boolean isSuccessed = jkledService.sendToLed();
		if (isSuccessed) {
			System.out.println("发送成功显示成功");;
		} else {
			// 目标主机地址。192.168.224.167
			System.out.println("发送成功显示失败，检查目标地址的防火墙是否关闭，网络是否通，能否ping通目标地址");;
		}
	}
}
