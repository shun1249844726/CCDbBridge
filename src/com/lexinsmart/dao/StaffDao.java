package com.lexinsmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;

import com.lexinsmart.model.Staff;
import com.lexinsmart.utils.DBCP;

/**
 * 人员管理表操作
 * @author xushun
 *
 */
public class StaffDao {
	DBCP dbcp = DBCP.getInstance();
	Connection connection = null;
	PreparedStatement ptmt = null;

	public StaffDao(Connection connection) {
		this.connection = connection;
	}

	public void addStaff(Staff staff) {
		try {

			// conn = (Connection) dbcp.getConnection();
			String sql = " insert into staff "
					+ " (requestid,name,sex,age,location,homeaddr,telephone,company,remarks,relative,relationship,telephone2,iden,created,privilege) "
					+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			ptmt = (PreparedStatement) connection.prepareStatement(sql);
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
			ptmt.setBoolean(15,staff.isPrivilege());

			ptmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// dbcp.closeStatement(ptmt);
			// dbcp.releaseConnection(conn);
		}
	}

	public boolean checkIsExist(String iden) {
		boolean isExist = false;
		ResultSet rs = null;
		try {
			// conn = dbcp.getConnection();
			String sql = "select iden from staff where iden=? ";
			ptmt = connection.prepareStatement(sql);
			ptmt.setString(1, iden);

			rs = ptmt.executeQuery();
			rs.last();
			if (rs.getRow() > 0) {
				isExist = true;
			} else {
				isExist = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// dbcp.closeStatement(ptmt, rs);
			// dbcp.releaseConnection(conn);
		}
		return isExist;
	}

	public void deleteStaffbyid(int id) {
		try {
			String sql = "delete from staff where id="+id;
			ptmt = connection.prepareStatement(sql);
			ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public int getid(String iden){
		ResultSet resultSet = null;
		int id = 0;
		try {
//			connection = dbcp.getConnection();
			String sql = " select id from staff where iden=? ";
			ptmt = connection.prepareStatement(sql);
			ptmt.setString(1, iden);
			resultSet = ptmt.executeQuery();
			while (resultSet.next()) {
				id = resultSet.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			dbcp.closeStatement(ptmt, resultSet);
//			dbcp.releaseConnection(connection);
		}
		return id;
	}
	public void edit(Staff staff){
		try {
			String sql = "update staff set "+
					"name=?,"+
					"sex=?,"+
					"age=?,"+
					"location=?,"+
					"homeaddr=?,"+
					"telephone=?,"+
					"company=?,"+
					"remarks=?,"+
					"relative=?,"+
					"relationship=?,"+
					"telephone2=?,"+
					"iden=?,"+
					"privilege=?,"+
					"requestid=?"+
					"where iden =?";
			ptmt = connection.prepareStatement(sql);
			ptmt.setString(1,staff.getName());
			ptmt.setString(2,staff.getSex());
			ptmt.setInt(3,staff.getAge());
			ptmt.setString(4,staff.getLocation());
			ptmt.setString(5,staff.getHomeaddr());
			ptmt.setString(6,staff.getTelephone());
			ptmt.setString(7,staff.getCompany());
			ptmt.setString(8,staff.getRemarks());
			ptmt.setString(9,staff.getRelative());
			ptmt.setString(10,staff.getRelationship());
			ptmt.setString(11,staff.getTelephone2());
			ptmt.setString(12,staff.getIden());
			ptmt.setBoolean(13,staff.isPrivilege());
			ptmt.setString(14,staff.getRequestid());
			ptmt.setString(15,staff.getIden());
			
			ptmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
