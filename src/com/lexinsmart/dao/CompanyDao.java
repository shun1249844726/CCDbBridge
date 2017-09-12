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
	/**
	 * 添加公司
	 * @param company
	 */
	public void addCompany(Company company){
		try {
			Connection conn = DBCP.getConnection();
			String sql = "" + " insert into company " + " (id,requestid,company,ctype,created,forbidden,atfactorynum,truck_in) " + " values("
					+ "?,?,?,1,?,false,0,false) ";
			PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);

			ptmt.setInt(1, company.getId());
			ptmt.setString(2, company.getRequestid());
			ptmt.setString(3, company.getCompany());
			ptmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));

			ptmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean checkIsExist(String id) {
		boolean isExist = false;
		try {
			Connection connection = DBCP.getConnection();
			PreparedStatement ptmt = null;

			String sql;
			sql = "SELECT id FROM company where id=? ";
			ptmt = (PreparedStatement) connection.prepareStatement(sql);
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
		}
		return isExist;
	}
}
