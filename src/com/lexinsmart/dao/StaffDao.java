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
	ResultSet rest = null;

	public StaffDao(Connection connection) {
		this.connection = connection;
	}
	public void release() {
		DBCP.releaseConnection(connection);
		DBCP.closeStatement(ptmt, rest);
		System.out.println("释放 StaffDao 连接和语句");
	}

	public void addStaff(Staff staff) {
		try {

			// conn = (Connection) dbcp.getConnection();
			String sql = " insert into staff "
					+ " (requestid,name,sex,age,location,homeaddr,telephone,company,remarks,relative,relationship,telephone2,iden,created,privilege,ctype) "
					+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
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
			ptmt.setInt(16, staff.getCtype());

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
		try {
			// conn = dbcp.getConnection();
			String sql = "select iden from staff where iden=? ";
			ptmt = connection.prepareStatement(sql);
			ptmt.setString(1, iden);

			rest = ptmt.executeQuery();
			rest.last();
			if (rest.getRow() > 0) {
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
		int id = 0;
		try {
//			connection = dbcp.getConnection();
			String sql = " select id from staff where iden=? ";
			ptmt = connection.prepareStatement(sql);
			ptmt.setString(1, iden);
			rest = ptmt.executeQuery();
			while (rest.next()) {
				id = rest.getInt("id");
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
					"requestid=?,"+
					"ctype=?"+
					" where iden =?";
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
			ptmt.setInt(15, staff.getCtype());
			ptmt.setString(16,staff.getIden());
			
			ptmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getCompany(String iden) {
		String company = "";
		try {
			String sql ="select company from staff where iden=?";
			ptmt = connection.prepareStatement(sql);
			ptmt.setString(1, iden);
			rest = ptmt.executeQuery();
			while (rest.next()) {
				company = rest.getString("company");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return company;
	}
	public Integer getctype(String iden) {
		int ctype = -1;
		try {
			String sql ="select ctype from staff where iden=?";
			ptmt = connection.prepareStatement(sql);
			ptmt.setString(1, iden);
			rest = ptmt.executeQuery();
			while (rest.next()) {
				ctype = rest.getInt("ctype");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ctype;
	}
}
