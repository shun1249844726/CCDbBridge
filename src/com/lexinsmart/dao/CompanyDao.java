package com.lexinsmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;

import com.lexinsmart.model.Company;
import com.lexinsmart.utils.DBCP;
import com.lexinsmart.utils.TypeChange;

/**
 * 公司数据表的操作
 * 
 * @author xushun
 *
 */
public class CompanyDao {
	Connection conn = null;
	PreparedStatement ptmt = null;
	DBCP dbcp = DBCP.getInstance();

	/**
	 * 添加公司
	 * 
	 * @param company
	 */
	public CompanyDao(Connection connection) {
		this.conn = connection;
	}

	public void addCompany(Company company) {

		try {
			// conn = dbcp.getConnection();
			String sql = "" + " insert into company " + " (requestid,company,ctype,created,forbidden,atfactorynum) "
					+ " values(" + "?,?,?,?,false,0) ";
			ptmt = (PreparedStatement) conn.prepareStatement(sql);

			ptmt.setString(1, company.getRequestid());
			ptmt.setString(2, company.getCompany());
			ptmt.setInt(3, company.getCtype());
			ptmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));

			ptmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// dbcp.closeStatement(ptmt);
			// dbcp.releaseConnection(conn);
		}
	}

	/**
	 * 查查是否存在
	 * 
	 * @param company
	 * @return 存在：true 不存在 false
	 */
	public boolean checkIsExist(String company) {
		boolean isExist = false;
		try {
			// conn = dbcp.getConnection();

			String sql;
			sql = "SELECT id FROM company where company=? ";
			ptmt = (PreparedStatement) conn.prepareStatement(sql);
			ptmt.setString(1, company);
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
		} finally {
			// dbcp.closeStatement(ptmt);
			// dbcp.releaseConnection(conn);
		}
		return isExist;
	}

	/**
	 * 根据公司名称获取ID
	 * 
	 * @param company
	 * @return ID
	 */
	public int getId(String company) {
		int id = 0;
		try {
			String sql = " select id from company where company=?";
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, company);
			ResultSet rest = ptmt.executeQuery();
			while (rest.next()) {
				id = rest.getInt("id");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	/**
	 * 根据公司名称获取requestid
	 * 
	 * @param company
	 * @return ID
	 */
	public String getrequestId(String company) {
		String requestid = "";
		try {
			String sql = " select requestid from company where company=?";
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, company);
			ResultSet rest = ptmt.executeQuery();
			while (rest.next()) {
				requestid = rest.getString("requestid");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return requestid;
	}

	/**
	 * 根据ID 删除一列数据
	 * 
	 * @param id
	 */
	public void delete(int id) {
		try {
			String sql = "delete from company where id=" + id;
			ptmt = conn.prepareStatement(sql);
			ptmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void edit(int id, Map<String, Object> editmap) {
		try {
			String sql = "update company set company=?,ctype=?,remarks=? where id=" + id;
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, (String) editmap.get("company"));
			ptmt.setInt(2, (Integer) editmap.get("ctype"));
			ptmt.setString(3, (String) editmap.get("remarks"));
			ptmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
