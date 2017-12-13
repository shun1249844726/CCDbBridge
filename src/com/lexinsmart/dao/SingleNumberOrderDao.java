package com.lexinsmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lexinsmart.model.SingleNumberOrder;
import com.lexinsmart.utils.DBCP;
/**
 * 提入单号表操作
 * @author xushun
 *
 */
public class SingleNumberOrderDao {
	Connection connection = null;
	PreparedStatement ptmt = null;
	DBCP dbcp = DBCP.getInstance();
	ResultSet rest = null;
	
	public SingleNumberOrderDao(Connection conn ) {
		this.connection  = conn;
	}
	public void release() {
		DBCP.releaseConnection(connection);
		DBCP.closeStatement(ptmt, rest);
		System.out.println("释放 SingleNumberOrderDao 连接和语句");
	}
	public void addOrder(SingleNumberOrder order){
		try {
	//		connection = dbcp.getConnection();
			String sql = " insert into single_number_order "
					+ "  (carno,high,singleno,weigh_times,orderno,depart,create_time,ttype) " + " values(?,?,?,?,?,?,?,?) ";
			ptmt = connection.prepareStatement(sql);
			ptmt.setString(1, order.getCarno());
			ptmt.setFloat(2, order.getHight());
			ptmt.setString(3, order.getSingleno());
			ptmt.setInt(4, order.getWeightimes());
			ptmt.setInt(5, order.getOrderno());
			ptmt.setString(6, order.getDepart());
			ptmt.setTimestamp(7, order.getCreatetime());
			ptmt.setString(8, order.getTtype());

			ptmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
//			dbcp.closeStatement(ptmt);
//			dbcp.releaseConnection(connection);
		}
	}
	public int getOrderno(String depart,String ttype){
		int orderno = 1;
		String sql = " SELECT MAX(orderno) FROM single_number_order ";
		try {
			ptmt = connection.prepareStatement(sql);
//			ptmt.setString(1, depart);
//			ptmt.setString(2, ttype);
			
			rest = ptmt.executeQuery();
			while (rest.next()) {
				orderno = rest.getInt(1);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return orderno;
	}
	
	public boolean checkIsExist(String singleno) {
		boolean isExist = false;
		try {
	//		connection = dbcp.getConnection();
			String sql = "select singleno from single_number_order where singleno=? ";
			ptmt = connection.prepareStatement(sql);
			ptmt.setString(1, singleno);

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
	
	public void weight(String singleno,java.sql.Timestamp nowtime, String sql){
		try {
			ptmt = connection.prepareStatement(sql);
			ptmt.setTimestamp(1, nowtime);
			ptmt.setString(2, singleno);
			ptmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getCurrentnumByName(String name){
		int currentnum=0;
		String sql = "SELECT COUNT(depart) FROM single_number_order WHERE depart=? AND weigh_times=1 ";
		
		try {
			ptmt = connection.prepareStatement(sql);
			ptmt.setString(1, name);
			rest = ptmt.executeQuery();
			while(rest.next()){
				currentnum = rest.getInt(1);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return currentnum;
		
	}
}
