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
	public void addPlanTime(Plantime plantime){
		try {
			Connection connection = DBCP.getConnection();
			String sql =
					" insert into plan_time "+ 
					" (cid,changer,telephone,planintime) "+
					" values(?,?,?,?) ";
			
			PreparedStatement ptmt = (PreparedStatement) connection.prepareStatement(sql);
			ptmt.setInt(1, plantime.getCid());
			ptmt.setString(2, plantime.getChanger());
			ptmt.setString(3, plantime.getTelephone());
			ptmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			
			ptmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
