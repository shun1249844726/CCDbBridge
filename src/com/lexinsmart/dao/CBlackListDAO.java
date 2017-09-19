package com.lexinsmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
	public void delete(int id){
		try {
			String sql = "delete from blacklist_c where id="+id;
			ptmt = connection.prepareStatement(sql);
			ptmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
