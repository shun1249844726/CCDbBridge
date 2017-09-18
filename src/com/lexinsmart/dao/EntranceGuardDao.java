package com.lexinsmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lexinsmart.utils.DBCP;

public class EntranceGuardDao {
	Connection connection = null;
	PreparedStatement ptmt = null;
	DBCP dbcp = DBCP.getInstance();

	public EntranceGuardDao(Connection connection) {
		this.connection = connection;
	}
	public List<Integer> getId(int type) {
		ResultSet rest = null;
		List<Integer> ids = new ArrayList<Integer>();
		try {
//			connection = dbcp.getConnection();
			String sql = "select id from entrance_guard where type=?";
			ptmt = connection.prepareStatement(sql);
			ptmt.setInt(1, type);
			rest = ptmt.executeQuery();
			while (rest.next()) {
				ids.add(rest.getInt("id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
//			dbcp.closeStatement(ptmt, rest);
//			dbcp.releaseConnection(connection);
		}
		return ids;
	}
	
	public List<Integer> getId(int type,boolean privilege){
		ResultSet rest = null;
		List<Integer> ids = new ArrayList<Integer>();
		try {
			String sql = " select id from entrance_guard where type=? and privilege=? ";
			ptmt = connection.prepareStatement(sql);
			ptmt.setInt(1,type );
			ptmt.setBoolean(2, privilege);
			rest = ptmt.executeQuery();
			while(rest.next()){
				ids.add(rest.getInt("id"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ids;
		
	}
}
