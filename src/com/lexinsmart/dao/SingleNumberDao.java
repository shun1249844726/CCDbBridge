package com.lexinsmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.lexinsmart.model.Singlenumber;
import com.lexinsmart.utils.DBCP;

/**
 * 提入单号表操作
 * @author xushun
 *
 */
public class SingleNumberDao {
	Connection connection = null;
	PreparedStatement ptmt = null;
	DBCP dbcp = DBCP.getInstance();


	public SingleNumberDao(Connection conn ) {
		this.connection  = conn;
	}
	public void addSingleNumber(Singlenumber singlenumber) {
		try {
	//		connection = dbcp.getConnection();
			String sql = " insert into single_number "
					+ " (carno,carheight,ttype,handplanno,singleno,depart,scantimes,requestid) " + " values(?,?,?,?,?,?,0,?) ";
			ptmt = connection.prepareStatement(sql);
			ptmt.setString(1, singlenumber.getCarno());
			ptmt.setFloat(2, singlenumber.getCarheight());
			ptmt.setString(3, singlenumber.getTtype());
			ptmt.setString(4, singlenumber.getHandplanno());
			ptmt.setString(5, singlenumber.getSingleno());
			ptmt.setString(6, singlenumber.getDepart());
			ptmt.setString(7, singlenumber.getRequestid());

			ptmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
//			dbcp.closeStatement(ptmt);
//			dbcp.releaseConnection(connection);
		}
	}

	public boolean checkIsExist(String singleno) {
		ResultSet rs = null;
		boolean isExist = false;
		try {
	//		connection = dbcp.getConnection();
			String sql = "select singleno from single_number where singleno=? ";
			ptmt = connection.prepareStatement(sql);
			ptmt.setString(1, singleno);

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
		} finally {
//			dbcp.closeStatement(ptmt, rs);
//			dbcp.releaseConnection(connection);
		}
		return isExist;
	}
	public void edit(Singlenumber singlenumber){
		try {
			String sql = "update single_number set carno=?,carheight=?,ttype=?,handplanno=?,singleno=?,"+
					"depart=?,requestid=? where singleno=?";
			ptmt=connection.prepareStatement(sql);
			ptmt.setString(1,singlenumber.getCarno());
			ptmt.setFloat(2, singlenumber.getCarheight());
			ptmt.setString(3, singlenumber.getTtype());
			ptmt.setString(4,singlenumber.getHandplanno());
			ptmt.setString(5,singlenumber.getSingleno());
			ptmt.setString(6,singlenumber.getDepart());
			ptmt.setString(7, singlenumber.getRequestid());
			ptmt.setString(8, singlenumber.getSingleno());
			ptmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
