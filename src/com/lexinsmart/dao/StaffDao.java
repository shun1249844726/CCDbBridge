package com.lexinsmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.lexinsmart.model.Staff;
import com.lexinsmart.utils.DBCP;

public class StaffDao {
	DBCP dbcp = DBCP.getInstance();
	Connection conn = dbcp.getConnection();
	PreparedStatement ptmt = null;

	public StaffDao(Connection connection) {
		this.conn = connection;
	}
	public void addStaff(Staff staff) {
		try {

//			conn = (Connection) dbcp.getConnection();
			String sql = " insert into staff "
					+ " (requestid,name,sex,age,location,homeaddr,telephone,company,remarks,relative,relationship,telephone2,iden,created,privilege) "
					+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,false) ";
			ptmt = (PreparedStatement) conn.prepareStatement(sql);
			ptmt.setString(1, staff.getRequestid());
			ptmt.setString(2, staff.getName());
			ptmt.setString(3, staff.getSex());
			ptmt.setInt(4, staff.getAge());
			ptmt.setString(5, staff.getLocation());
			ptmt.setString(6, staff.getHomeaddr());
			ptmt.setString(7, staff.getTelephone());
			ptmt.setString(8, staff.getCompany());
			ptmt.setString(9, staff.getRemarks());
			ptmt.setString(10, staff.getRelative());
			ptmt.setString(11, staff.getRelationship());
			ptmt.setString(12, staff.getTelephone2());
			ptmt.setString(13, staff.getIden());
			ptmt.setTimestamp(14, new Timestamp(System.currentTimeMillis()));

			ptmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
//			dbcp.closeStatement(ptmt);
//			dbcp.releaseConnection(conn);
		}
	}

	public boolean checkIsExist(String iden) {
		boolean isExist = false;
		ResultSet rs =null;
		try {
//			 conn = dbcp.getConnection();
			String sql = "select iden from staff where iden=? ";
			 ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, iden);

			 rs = ptmt.executeQuery();
			rs.last();
			if (rs.getRow() > 0) {
				isExist = true;
			} else {
				isExist = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
//			dbcp.closeStatement(ptmt, rs);
//			dbcp.releaseConnection(conn);
		}
		return isExist;
	}
}
