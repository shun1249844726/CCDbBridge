package com.lexinsmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lexinsmart.model.BlacklistC;
import com.lexinsmart.utils.DBCP;

/**
 * 黑名单 车辆管理
 * 
 * @author xushun
 *
 */
public class CBlackListDAO {
	Connection connection = null;
	PreparedStatement ptmt = null;
	DBCP dbcp = DBCP.getInstance();

	public CBlackListDAO(Connection connection) {
		this.connection = connection;
	}

	/**
	 * 添加车辆黑名单
	 * @param blacklistC
	 */
	public void addCblack(BlacklistC blacklistC) {
		try {
			String sql = "insert into blacklist_c " + " (requestid,carno,state ) " + " values(?,?,?) ";
			ptmt = connection.prepareStatement(sql);
			ptmt.setString(1, blacklistC.getRequestid());
			ptmt.setString(2, blacklistC.getCarno());
			ptmt.setString(3, blacklistC.getState());
			ptmt.execute();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 根据车牌号获取在数据库中的ID
	 * @param carno
	 * @return
	 */
	public int getid(String carno) {
		int id = 0;
		try {
			String sql = "select id from blacklist_c where carno=?";
			ptmt = connection.prepareStatement(sql);
			ptmt.setString(1, carno);

			ResultSet rest = ptmt.executeQuery();
			while(rest.next()){
				id = rest.getInt("id");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return id;
	}
	/**
	 * 根据ID删除一列数据
	 * @param id
	 */
	public void delete(int id){
		try {
			String sql = "delete from blacklist_c where id="+id;
			ptmt = connection.prepareStatement(sql);
			ptmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void edit(BlacklistC blacklistC){
		try {
			String sql = " update blacklist_c set "+
					"  requestid=?,"+
					"  state=? "+
					" where carno=?";
			ptmt = connection.prepareStatement(sql);
			ptmt.setString(1, blacklistC.getRequestid());
			ptmt.setString(2, blacklistC.getState());
			ptmt.setString(3, blacklistC.getCarno());
			
			ptmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
