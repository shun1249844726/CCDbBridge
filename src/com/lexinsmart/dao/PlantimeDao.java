package com.lexinsmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lexinsmart.model.Plantime;
import com.lexinsmart.utils.DBCP;
/**
 * 计划在厂时间表操作
 * @author xushun
 *
 */
public class PlantimeDao {
	DBCP dbcp = DBCP.getInstance();
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet rest = null;
	public PlantimeDao(Connection connection) {
		this.connection = connection;
	}
	public void release() {
		DBCP.releaseConnection(connection);
		DBCP.closeStatement(ptmt, rest);
		System.out.println("释放 PlantimeDao 连接和语句");
	}
	/**
	 * 添加在厂时间
	 * @param plantime
	 */
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
			ptmt.setTimestamp(6, plantime.getPlanintime());
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
	/**
	 * 根据ID 删除在厂时间
	 * @param id
	 */
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
	/**
	 * 如果存在 返回ID
	 * @param cid
	 * @param sid
	 * @param tid
	 * @return
	 */
	public int getIdIfExist(int cid,int sid,int tid){
		int id=-1;
		try {
			String sql = "select id from plan_time where cid=? and sid=? and tid=? ";
			ptmt = connection.prepareStatement(sql);
			ptmt.setInt(1, cid);
			ptmt.setInt(2, sid);
			ptmt.setInt(3, tid);
			
			rest = ptmt.executeQuery();
			rest.last();
			if (rest.getRow()>0) {
				rest.beforeFirst();
				while(rest.next()){
					id = rest.getInt("id");
				}
			} else{
				id=0;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return id;
	}
	
	public List<Timestamp> getPlantimeBySid(int sid){
		System.out.println("sid:"+sid);
		List<Timestamp> times = new ArrayList<Timestamp>();
		try {
			String sql = "select planouttime from plan_time where sid=? ";
			ptmt = connection.prepareStatement(sql);
			ptmt.setInt(1, sid);
			rest = ptmt.executeQuery();
			while(rest.next()) {
				times.add(rest.getTimestamp("planouttime"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return times;
	}
	public List<Timestamp> getPlantimeByCid(int cid){
		System.out.println("cid:"+cid);
		List<Timestamp> times = new ArrayList<Timestamp>();
		try {
			String sql = "select planouttime from plan_time where cid=? ";
			ptmt = connection.prepareStatement(sql);
			ptmt.setInt(1, cid);
			rest = ptmt.executeQuery();
			while(rest.next()) {
				times.add(rest.getTimestamp("planouttime"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return times;
	}
}
