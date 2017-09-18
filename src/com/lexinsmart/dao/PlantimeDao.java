package com.lexinsmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		this.connection = connection;
	}

	public void addPlanTime(Plantime plantime) {
		try {
			// connection = dbcp.getConnection();
			String sql = " insert into plan_time " + " (cid,sid,tid,changer,telephone,planintime,planouttime,requestid) "
					+ " values(?,?,?,?,?,?,?,?) ";

			ptmt = (PreparedStatement) connection.prepareStatement(sql);
			ptmt.setInt(1, plantime.getCid());
			ptmt.setInt(2, plantime.getSid());
			ptmt.setInt(3, plantime.getTid());

			ptmt.setString(4, plantime.getChanger());
			ptmt.setString(5, plantime.getTelephone());
			ptmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			ptmt.setTimestamp(7, plantime.getPlanouttime());
			ptmt.setString(8, plantime.getRequesid());

			ptmt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// dbcp.closeStatement(ptmt);
			// dbcp.releaseConnection(connection);
		}

	}
	public void deleteplantime(int id) {
		try {
			String sql = "delete from plan_time where id="+id;
			ptmt = connection.prepareStatement(sql);
			ptmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getIdIfExist(int cid,int sid,int tid){
		int id=-1;
		try {
			String sql = "select id from plan_time where cid=? and sid=? and tid=? ";
			ptmt = connection.prepareStatement(sql);
			ptmt.setInt(1, cid);
			ptmt.setInt(2, sid);
			ptmt.setInt(3, tid);
			
			ResultSet rs = ptmt.executeQuery();
			rs.last();
			if (rs.getRow()>0) {
				rs.beforeFirst();
				while(rs.next()){
					id = rs.getInt("id");
				}
			} else{
				id=0;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return id;
		
	}
}
