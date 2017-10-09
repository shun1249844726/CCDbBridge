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
	ResultSet rest = null;

	public TruckDao(Connection connection) {
		this.connection = connection;
	}
	public void release() {
		DBCP.releaseConnection(connection);
		DBCP.closeStatement(ptmt, rest);
		System.out.println("释放 TruckDao 连接和语句");
	}

	/**
	 * 添加车辆表
	 * 
	 * @param truck
	 */
	public void addTruck(Truck truck) {
		try {
//			connection = dbcp.getConnection();
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
//			dbcp.closeStatement(ptmt);
//			dbcp.releaseConnection(connection);
		}

	}

	public boolean checkIsExist(String carno) {
		boolean isExist = false;
		try {
//			connection = dbcp.getConnection();
			String sql = "select carno from truck where carno=? ";
			ptmt = connection.prepareStatement(sql);
			ptmt.setString(1, carno);

			rest = ptmt.executeQuery();
			rest.last();
			if (rest.getRow() > 0) {
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

	public int getId(String carno) {
		int id = 0;
		try {
//			connection = dbcp.getConnection();
			String sql = " select id from truck where carno=? ";
			ptmt = connection.prepareStatement(sql);
			ptmt.setString(1, carno);
			rest = ptmt.executeQuery();
			while (rest.next()) {
				id = rest.getInt("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
//			dbcp.closeStatement(ptmt, resultSet);
//			dbcp.releaseConnection(connection);
		}
		return id;
	}
	public void edit(Truck truck){
		try {
			String sql ="update truck set "+
					"  requestid=?, "+
					"  company=?, "+
					"  ttype=?, "+
					"  carno=?, "+
					"  xzdw=? "+
					"  where carno=?";
			ptmt = connection.prepareStatement(sql);
			ptmt.setString(1, truck.getRequestid());
			ptmt.setString(2, truck.getCompany());
			ptmt.setString(3, truck.getTtype());
			ptmt.setString(4, truck.getCarno());
			ptmt.setFloat(5, truck.getXzdw());
			ptmt.setString(6, truck.getCarno());
			ptmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
