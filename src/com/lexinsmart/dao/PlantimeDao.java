package com.lexinsmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import com.lexinsmart.model.Plantime;
import com.lexinsmart.utils.DBCP;

public class PlantimeDao {
	DBCP dbcp = DBCP.getInstance();
	Connection connection = null;
	PreparedStatement ptmt = null;

	public PlantimeDao(Connection connection) {
		this.connection =  connection;
	}
	public void addPlanTime(Plantime plantime) {
		try {
//			connection = dbcp.getConnection();
			String sql = " insert into plan_time " + " (cid,sid,tid,changer,telephone,planintime,planouttime) "
					+ " values(?,?,?,?,?,?,?) ";

			ptmt = (PreparedStatement) connection.prepareStatement(sql);
			ptmt.setInt(1, plantime.getCid());
			ptmt.setInt(2, plantime.getSid());
			ptmt.setInt(3, plantime.getTid());

			ptmt.setString(4, plantime.getChanger());
			ptmt.setString(5, plantime.getTelephone());
			ptmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			ptmt.setTimestamp(7, plantime.getPlanouttime());

			ptmt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
//			dbcp.closeStatement(ptmt);
//			dbcp.releaseConnection(connection);
			
		}

	}
}
