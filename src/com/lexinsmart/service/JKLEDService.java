package com.lexinsmart.service;

import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class JKLEDService {
	private String weather;

	public JKLEDService( String weather) {
		this.weather = weather;
	}

	/**
	 * 发送数据给LED屏幕显示
	 */
	public boolean sendToLed(){
		boolean isSuccessed = false;
//		 String url = "http://localhost:8080/JKLEDserver/jkl";
		String url = "http://192.168.224.167:8038/server/ledscreen_weather";//ip地址

		// =========定义HttpClient=========
		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(3000);  
		client.getHttpConnectionManager().getParams().setSoTimeout(3000);  //等待结果超时
		// =========实例化HTTP方法 =========
		PostMethod method = new PostMethod(url);
		method.setRequestHeader(new Header("Content-Type", "multipart/form-data"));
		// 提交参数
		 method.addParameter("weather",weather);
     	      
		method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

		try {
			int returnCode = client.executeMethod(method);
			System.out.println("returnCode : " + returnCode);
			System.out.println(method.getResponseBodyAsString());
			isSuccessed = true;
		} catch (HttpException e) {
			e.printStackTrace();
			isSuccessed = false;
		} catch (IOException e) {
			e.printStackTrace();
			isSuccessed = false;
		}
		return isSuccessed; 
	}
}
