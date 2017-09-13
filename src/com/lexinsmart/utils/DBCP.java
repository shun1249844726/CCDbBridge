package com.lexinsmart.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

//import org.apache.commons.dbcp2.BasicDataSourceFactory;


public class DBCP {
	// 原理：让数据源动态生成连接
	private static DataSource myDataSource;
	private static Connection conn;
	private static Properties prop;

	// 单例模式
	private DBCP() {

	}

	// 在静态代码块中进行配置文件与程序的关联
	static {
		prop = new Properties();
		String commConfigFilePath = "./conf/dbcp.properties";
	//	String commConfigFilePath = DBCP.class.getResource("/").getFile() +"dbcp.properties";
		InputStream in = null;
		try {			
			in = new BufferedInputStream(new FileInputStream(commConfigFilePath));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			prop.load(in);
			myDataSource = BasicDataSourceFactory.createDataSource(prop);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取连接
	public static Connection getConnection() throws SQLException {
		try {
			conn = myDataSource.getConnection();
			System.out.println("获取连接成功！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	// 断开连接
	public static void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}