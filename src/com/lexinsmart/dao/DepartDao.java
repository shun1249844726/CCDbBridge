package com.lexinsmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lexinsmart.utils.DBCP;

/**
 * departdao 用来操作仓库表。主要是修改当前人数
 * @author xushun
 *
 */
public class DepartDao {
	Connection connection = null;
	PreparedStatement ptmt = null;
	DBCP dbcp = DBCP.getInstance();
	ResultSet rest = null;

	public DepartDao(Connection connection) {
		this.connection = connection;
	}

	public void release() {
		DBCP.releaseConnection(connection);
		DBCP.closeStatement(ptmt, rest);
		System.out.println("释放获取departDao的连接");
	}

	public ArrayList<String> getDeparts() {
		ArrayList<String> departs = new ArrayList<String>();
		String sql = "SELECT name FROM depart";
		try {
			ptmt = connection.prepareStatement(sql);
			rest = ptmt.executeQuery();
			while (rest.next()) {
				departs.add(rest.getString(1));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return departs;
	}

	public void updateCurrentNum(ArrayList<String> departs, ArrayList<Integer> currentnums) {
		String sql = "UPDATE depart SET currentnum=? WHERE `name`=? ";
		try {
			ptmt = connection.prepareStatement(sql);
			for (int i = 0; i < departs.size(); i++) {
				ptmt.setInt(1, currentnums.get(i));
				ptmt.setString(2, departs.get(i));
				ptmt.addBatch();
			}
			
			// 执行批处理操作并返回计数组成的数组  
            int[] rows = ptmt.executeBatch();  
            // 对行数赋值  
            int row = rows.length;  
            System.out.println("editrow:   "+row);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
