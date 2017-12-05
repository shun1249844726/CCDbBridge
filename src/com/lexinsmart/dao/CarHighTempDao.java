package com.lexinsmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lexinsmart.utils.DBCP;
/**
 * 车高临时表。用来查询临时表中的车辆高度
 * @author xushun
 *
 */
public class CarHighTempDao {
	Connection connection = null;
	PreparedStatement ptmt = null;
	DBCP dbcp = DBCP.getInstance();
	ResultSet rest  = null;
	public CarHighTempDao(Connection connection) {
		this.connection = connection;
	}
	public void release() {
		DBCP.releaseConnection(connection);
		DBCP.closeStatement(ptmt, rest);
		System.out.println("释放车辆高度临时表连接");
	}
	
	public float getcarhigh(String carno){
		float high = 0.0f;
		String sql = "SELECT high FROM car_high_tmp WHERE carno=? ";
		try {
			ptmt = connection.prepareStatement(sql);
			ptmt.setString(1, carno);
			
			rest = ptmt.executeQuery();
			while (rest.next()) {
				high = rest.getFloat(1);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return high;
	}
}
