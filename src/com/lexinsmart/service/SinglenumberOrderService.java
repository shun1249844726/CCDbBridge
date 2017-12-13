package com.lexinsmart.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.lexinsmart.dao.CarHighTempDao;
import com.lexinsmart.dao.SingleNumberOrderDao;
import com.lexinsmart.model.SingleNumberOrder;
import com.lexinsmart.utils.DBCP;
/**
 * 提入单号牌号表的操作管理。
 * @author xushun
 *
 */
public class SinglenumberOrderService {
	Connection connection = null;// 数据库的连接
	SingleNumberOrderDao orderDao = null;
	CarHighTempDao carHighDao = null;

	public SinglenumberOrderService() {
		try {
			connection = DBCP.getConnection();// 事物管理，最后commit；
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addOrder(SingleNumberOrder order) {
		try {
			orderDao = new SingleNumberOrderDao(connection);
			carHighDao = new CarHighTempDao(connection);
			
			if (orderDao.checkIsExist(order.getSingleno())) {
				System.out.println("提入单号存在！");
				return;
			} 
			order.setHight(carHighDao.getcarhigh(order.getCarno()));
			
			int orderno = orderDao.getOrderno(order.getDepart(),order.getTtype()) + 1;
			order.setOrderno(orderno);
			orderDao.addOrder(order);
			System.out.println("add order");
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void release() {
		orderDao.release();
		carHighDao.release();

		DBCP.releaseConnection(connection);
		System.out.println("释放singlenumberorderservice 连接");
	}
}
