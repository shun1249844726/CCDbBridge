package com.lexinsmart.dao;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.lexinsmart.model.SysLog;
import com.lexinsmart.utils.DBCP;

public class SysLogDao {
	DBCP dbcp = DBCP.getInstance();
	Connection connection =null;
	PreparedStatement ptmt = null;

	public SysLogDao(Connection connection) {
		this.connection = connection;
	}
	public void release() {
		DBCP.releaseConnection(connection);
		DBCP.closeStatement(ptmt);
		System.out.println("释放 SysLogDao 连接和语句");
	}
	public void addSysLog(SysLog sysLog) {

		try {
//			connection = dbcp.getConnection();
			String sql = " insert into sys_log " + " (username,ip,logintime,proxy,content) " + " values(?,?,?,?,?) ";
			ptmt = connection.prepareStatement(sql);
			ptmt.setString(1, sysLog.getUsername());
			ptmt.setString(2, sysLog.getIp());
			ptmt.setTimestamp(3, sysLog.getLogintime());
			ptmt.setString(4, sysLog.getProxy());
			ptmt.setString(5, sysLog.getContent());

			ptmt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
//			dbcp.closeStatement(ptmt);
//			dbcp.releaseConnection(connection);
		}
	}

	// public static void main(String[] args) {
	// // TODO Auto-generated method stub
	// InetAddress ia=null;
	// try {
	// ia=ia.getLocalHost();
	//
	// String localname=ia.getHostName();
	// String localip=ia.getHostAddress();
	// System.out.println("本机名称是："+ localname);
	// System.out.println("本机的ip是 ："+localip);
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
}
