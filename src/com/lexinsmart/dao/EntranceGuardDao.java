package com.lexinsmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lexinsmart.utils.DBCP;

/**
 * 门禁数据表操作
 * @author xushun
 *
 */
public class EntranceGuardDao {
	Connection connection = null;
	PreparedStatement ptmt = null;
	DBCP dbcp = DBCP.getInstance();
	ResultSet rest = null;

	public EntranceGuardDao(Connection connection) {
		this.connection = connection;
	}
	public void release() {
		DBCP.releaseConnection(connection);
		DBCP.closeStatement(ptmt, rest);
		System.out.println("释放 EntranceGuardDao 连接和语句");
	}
	public static void addddd() throws SQLException {
		String sql = "insert into company_privilege (cid,egid) values(?,?)";
		PreparedStatement pdddd ;
		Connection connectionddd = DBCP.getConnection();// 事物管理，最后commit；
		connectionddd.setAutoCommit(false);
		for (int i = 260; i <= 312; i++) {
			pdddd = connectionddd.prepareStatement(sql);
			pdddd.setInt(1, i);
			pdddd.setInt(2, 28);
			pdddd.execute();
		}
		connectionddd.commit();
		
	}
	public static void main(String[] args) throws SQLException {
		addddd();
	}
	public List<Integer> getId(int type) {
		List<Integer> ids = new ArrayList<Integer>();
		try {
//			connection = dbcp.getConnection();
			String sql = "select id from entrance_guard where type=?";
			ptmt = connection.prepareStatement(sql);
			ptmt.setInt(1, type);
			rest = ptmt.executeQuery();
			while (rest.next()) {
				ids.add(rest.getInt("id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
//			dbcp.closeStatement(ptmt, rest);
//			dbcp.releaseConnection(connection);
		}
		return ids;
	}
	/**
	 * 
	 * @param type 门禁类型  车1  人0
	 * @param privilege 二道门标志  true ：是二道门，  false 不是
	 * @return 搜索到的ID的列表
	 */
	public List<Integer> getId(int type,boolean privilege){
		List<Integer> ids = new ArrayList<Integer>();
		try {
			String sql = " select id from entrance_guard where type=? and privilege=? ";
			ptmt = connection.prepareStatement(sql);
			ptmt.setInt(1,type );
			ptmt.setBoolean(2, privilege);
			rest = ptmt.executeQuery();
			while(rest.next()){
				ids.add(rest.getInt("id"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ids;
		
	}
}
