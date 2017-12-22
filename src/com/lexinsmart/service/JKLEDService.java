package com.lexinsmart.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class JKLEDService {
	private String ccpDays;
	private String cbsDays;
	private String weather;

	public JKLEDService(String ccpdays, String cbsdays, String weather) {
		this.ccpDays = ccpdays;
		this.cbsDays = cbsdays;
		this.weather = weather;
	}

	public void sendToLed() {
//		 String url = "http://localhost:8080/JKLEDserver/jkl";
		String url = "http://192.168.3.59:8038/server/ledscreen_weather";

		// =========定义HttpClient=========
		HttpClient client = new HttpClient();
	
		// =========实例化HTTP方法 =========
		PostMethod method = new PostMethod(url);
		
		method.setRequestHeader(new Header("Content-Type", "multipart/form-data"));
		// 提交参数
		 method.addParameter("ccpdays",ccpDays);
		 method.addParameter("cbsdays",cbsDays);
		 method.addParameter("weather",weather);
		
//		 NameValuePair name=new NameValuePair("ccpdays", "allen");
//	        
//	      NameValuePair[] data = {name}; 
//	      method.setRequestBody(data);  
	      
	      
		method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		try {
			int returnCode = client.executeMethod(method);
			System.out.println("returnCode : " + returnCode);
			System.out.println("============================================");
			System.out.println(method.getResponseBodyAsString());
			System.out.println("======^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^=========");
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
