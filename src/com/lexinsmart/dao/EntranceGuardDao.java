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

	public List<Integer> getId(int type) {
		ResultSet rest = null;
		List<Integer> ids = new ArrayList<Integer>();
		try {
			connection = dbcp.getConnection();
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
			dbcp.closeStatement(ptmt, rest);
			dbcp.releaseConnection(connection);
		}
		return ids;
	}

	public static void main(String[] args) {
		EntranceGuardDao entranceGuardDao = new EntranceGuardDao();
		entranceGuardDao.getId(1);
	}
}
