package com.lexinsmart.service;

import java.sql.Connection;

import com.lexinsmart.dao.CurrentCountDao;
import com.lexinsmart.model.MajorCountModel;
import com.lexinsmart.utils.DBCP;

public class CurrentCountService {
	Connection connection = null;// 数据库的连接

	public MajorCountModel getMajorCount() {
		MajorCountModel majorCountModel = new MajorCountModel();
		connection = DBCP.getConnection();// 事物管理，最后commit；
		CurrentCountDao countDao = new CurrentCountDao(connection);
		try{
			majorCountModel = countDao.getMajorCount();

		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			countDao.release();
		}
		return majorCountModel;
	}
}
