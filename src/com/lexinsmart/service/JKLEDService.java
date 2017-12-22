package com.lexinsmart.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
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
        String url = "http://localhost:8080/JKLEDserver/jkl";
        //=========定义HttpClient=========
        HttpClient client = new HttpClient(); 

        //=========实例化HTTP方法 =========
        PostMethod method = new PostMethod(url);

        // 提交参数
        method.addParameter("ccpdays",ccpDays);
        method.addParameter("cbsdays",cbsDays);
        method.addParameter("weather",weather);
        
        
        method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        try {
            int returnCode = client.executeMethod(method);
            System.out.println("returnCode : " + returnCode);
            System.out.println("============================================");
            System.out.println(method.getResponseBodyAsString());
            System.out.println("============================================");
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
