package com.lexinsmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lexinsmart.model.Singlenumber;
import com.lexinsmart.utils.DBCP;

public class SingleNumberDao {
	public void addSingleNumber(Singlenumber singlenumber) {
		try {
			Connection connection = DBCP.getConnection();
			String sql = " insert into single_number " + " (cid,carno,xzdw,carheight,handplanno,singleno,scantimes) "
					+ " values(?,?,?,?,?,?,0) ";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, singlenumber.getCid());
			stmt.setString(2, singlenumber.getCarno());
			stmt.setFloat(3, singlenumber.getXzdw());
			stmt.setFloat(4, singlenumber.getCarheight());
			stmt.setString(5, singlenumber.getHandplanno());
			stmt.setString(6, singlenumber.getSingleno());
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean checkIsExist(String singleno) {
		boolean isExist = false;
		try {
			Connection connection = DBCP.getConnection();
			String sql = "select singleno from single_number where singleno=? ";
			PreparedStatement ptmt = connection.prepareStatement(sql);
			ptmt.setString(1, singleno);

			ResultSet rs = ptmt.executeQuery();
			rs.last();
			if (rs.getRow() > 0) {
				isExist = true;
			} else {
				isExist = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isExist;
	}
}
