package com.lexinsmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.lexinsmart.model.Company;
import com.lexinsmart.utils.DBCP;
import com.lexinsmart.utils.TypeChange;

public class CompanyDao {
	Connection conn = null;
	PreparedStatement ptmt = null;
	DBCP dbcp =  DBCP.getInstance();
	/**
	 * 添加公司
	 * @param company
	 */
	public void addCompany(Company company){

		try {
			 conn = dbcp.getConnection();
			String sql = "" + " insert into company " + " (id,requestid,company,ctype,created,forbidden,atfactorynum) " + " values("
					+ "?,?,?,1,?,false,0) ";
			 ptmt = (PreparedStatement) conn.prepareStatement(sql);

			ptmt.setInt(1, company.getId());
			ptmt.setString(2, company.getRequestid());
			ptmt.setString(3, company.getCompany());
			ptmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));

			ptmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbcp.closeStatement(ptmt);
			dbcp.releaseConnection(conn);
		}
	}
	public boolean checkIsExist(String id) {
		boolean isExist = false;
		try {
			 conn = dbcp.getConnection();

			String sql;
			sql = "SELECT id FROM company where id=? ";
			ptmt = (PreparedStatement) conn.prepareStatement(sql);
			ptmt.setInt(1, TypeChange.stringToInt(id));
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
		}finally {
			dbcp.closeStatement(ptmt);
			dbcp.releaseConnection(conn);
		}
		return isExist;
	}
}
