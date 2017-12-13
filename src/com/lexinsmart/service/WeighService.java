package com.lexinsmart.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.lexinsmart.dao.DepartDao;
import com.lexinsmart.dao.SingleNumberOrderDao;
import com.lexinsmart.utils.DBCP;

/**
 * 称重service 
 * @author xushun
 *
 */
public class WeighService {
	Connection connection = null;// 数据库的连接
	PreparedStatement ptmt = null;
	DBCP dbcp = DBCP.getInstance();
	ResultSet rest  = null;
	SingleNumberOrderDao singleNumberOrderDao = null;
	DepartDao departDao = null;

	public WeighService() {
		try {
			connection = DBCP.getConnection();
			connection.setAutoCommit(false);
		} catch (Exception e) {
		}
	}

	public void inWeight(String singleno){
		singleno = singleno.toLowerCase();
		String sql = "UPDATE single_number_order SET weigh_times=1, in_factory_time=?,is_in_factory=1 "
				+ "  WHERE singleno=?";
		Timestamp nowtime = new Timestamp(System.currentTimeMillis());
		singleNumberOrderDao = new SingleNumberOrderDao(connection);
		singleNumberOrderDao.weight(singleno,nowtime, sql);
		System.out.println("add in weight");
		try {
			connection.commit();
			System.out.println("commit");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	public void outWeight(String singleno){
		singleno = singleno.toLowerCase();
		Timestamp nowtime = new Timestamp(System.currentTimeMillis());

		String sql = "UPDATE single_number_order SET weigh_times=2, out_factory_time=?,is_in_factory=2 "
				+ "  WHERE singleno=?";
		singleNumberOrderDao = new SingleNumberOrderDao(connection);

		singleNumberOrderDao.weight(singleno,nowtime,sql);
		System.out.println("add out weight");
		try {
			connection.commit();
			System.out.println("commit");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}
	public void release() {
		singleNumberOrderDao.release();
		departDao.release();
		DBCP.releaseConnection(connection);
		System.out.println("释放连接");

	}

	public void refreshCurrentnum() {
		departDao = new DepartDao(connection);
		singleNumberOrderDao = new SingleNumberOrderDao(connection);
		
		ArrayList<String> departs = departDao.getDeparts();
		ArrayList<Integer> currentnums = new ArrayList<Integer>();
		
		for (int i = 0; i < departs.size(); i++) {			
			currentnums.add(singleNumberOrderDao.getCurrentnumByName(departs.get(i)));
		}
		
		departDao.updateCurrentNum(departs,currentnums);
		System.out.println("refresh currentnumber ");
		try {
			connection.commit();
			System.out.println("commit");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		
	}
}
