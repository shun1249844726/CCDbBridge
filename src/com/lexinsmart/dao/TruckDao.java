package com.lexinsmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.lexinsmart.model.Truck;
import com.lexinsmart.utils.DBCP;

public class TruckDao {
	DBCP dbcp = DBCP.getInstance();
	Connection connection = null;
	PreparedStatement ptmt = null;

	/**
	 * 添加车辆表
	 * 
	 * @param truck
	 */
	public void addTruck(Truck truck) {
		try {
			connection = dbcp.getConnection();
			String sql = " insert into truck "
					+ " (requestid,company,ttype,carno,xzdw,atfactorynum,created,forbidden,privilege) "
					+ " values(?,?,?,?,?,0,?,false,false) ";
			ptmt = connection.prepareStatement(sql);
			ptmt.setString(1, truck.getRequestid());
			ptmt.setString(2, truck.getCompany());
			ptmt.setString(3, truck.getTtype());
			ptmt.setString(4, truck.getCarno());
			ptmt.setFloat(5, truck.getXzdw());
			ptmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));

			ptmt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbcp.closeStatement(ptmt);
			dbcp.releaseConnection(connection);
		}

	}

	public boolean checkIsExist(String carno) {
		ResultSet rs = null;
		boolean isExist = false;
		try {
			connection = dbcp.getConnection();
			String sql = "select carno from truck where carno=? ";
			ptmt = connection.prepareStatement(sql);
			ptmt.setString(1, carno);

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
			dbcp.closeStatement(ptmt, rs);
		}
		return isExist;
	}

	public int getId(String carno) {
		ResultSet resultSet = null;
		int id = 0;
		try {
			connection = dbcp.getConnection();
			String sql = " select id from truck where carno=? ";
			ptmt = connection.prepareStatement(sql);
			ptmt.setString(1, carno);
			resultSet = ptmt.executeQuery();
			while (resultSet.next()) {
				id = resultSet.getInt("id");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbcp.closeStatement(ptmt, resultSet);
		}

		return id;
	}
}
