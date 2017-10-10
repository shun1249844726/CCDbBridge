package com.lexinsmart.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.lexinsmart.dao.CBlackListDAO;
import com.lexinsmart.dao.PBlacklistDao;
import com.lexinsmart.dao.TruckDao;
import com.lexinsmart.model.BlacklistC;
import com.lexinsmart.model.BlacklistP;
import com.lexinsmart.model.OABlacklist;
import com.lexinsmart.model.OAVehicle;
import com.lexinsmart.utils.DBCP;
import com.lexinsmart.utils.DateUtils;
import com.lexinsmart.utils.DropDownTools;

/**
 *  黑名单的管理
 * @author xushun
 *
 */
public class BlackListService {
//	DBCP dbcp = DBCP.getInstance();
	Connection connection = null;// 数据库的连接

	/**
	 *  添加人员黑名单
	 * @param blacklist  人员黑名单
	 */
	public void addBlackListP(OABlacklist blacklist) {
		PBlacklistDao pBlacklistDao = null;
		try {

			connection = DBCP.getConnection();// 事物管理，最后commit；
			connection.setAutoCommit(false);

			pBlacklistDao = new PBlacklistDao(connection);

			BlacklistP blacklistP = new BlacklistP();
			blacklistP.setIdcard(blacklist.getIdcard());
			blacklistP.setName(blacklist.getName());
			blacklistP.setMantype(DropDownTools.stringToType(blacklist.getMantype()));
			blacklistP.setCompany(blacklist.getCompany());
			if (blacklist.getIsblack().equals("40288098276fc2120127704884290210")) {// 是
				blacklistP.setIsblack(true);
			} else if (blacklist.getIsblack().equals("40288098276fc2120127704884290211")) {
				blacklistP.setIsblack(false);
			}
			blacklistP.setBlackdate(new Timestamp(DateUtils.StringToDate(blacklist.getBlackdate()).getTime()));
			blacklistP.setReason(blacklist.getReason());
			blacklistP.setPlleavedate(new Timestamp(DateUtils.StringToDate(blacklist.getPlleavedate()).getTime()));
			blacklistP.setPlemployno(blacklist.getPlemployno());
			blacklistP.setPldepartment(blacklist.getPldepartment());

			int id = pBlacklistDao.getId(blacklist.getIdcard());
			if (id > 0) {
				pBlacklistDao.edit(blacklistP);
				System.out.println("edit blacklistp");
			} else {
				pBlacklistDao.addBlackList(blacklistP);
				System.out.println("add blacklistp");
			}
			connection.commit();
			System.out.println("commit");
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			pBlacklistDao.release();// 增加释放连接
			DBCP.releaseConnection(connection);
			System.out.println("释放连接");

		}
	}

	/**
	 * 添加车辆黑名单
	 * @param vehicle
	 */
	public void addBlackListC(OAVehicle vehicle) {
		CBlackListDAO cBlackListDAO =null;
		try {
			connection = DBCP.getConnection();// 事物管理，最后commit；
			connection.setAutoCommit(false);

			cBlackListDAO = new CBlackListDAO(connection);
			TruckDao truckDao = new TruckDao(connection);

			BlacklistC blacklistC = new BlacklistC();
			blacklistC.setRequestid("" + truckDao.getId(vehicle.getAutono()));
			blacklistC.setCarno(vehicle.getAutono());
			blacklistC.setState(DropDownTools.stringToState(vehicle.getState()));

			int id = cBlackListDAO.getid(vehicle.getAutono());
			if (id > 0) {
				cBlackListDAO.edit(blacklistC);
				System.out.println("edit blacklist_c");
			} else if (id == 0) {
				cBlackListDAO.addCblack(blacklistC);
				System.out.println("add blacklist_c");

			}
			connection.commit();
			System.out.println("commit");
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			cBlackListDAO.release();
			DBCP.releaseConnection(connection);
			System.out.println("释放连接");

		}
	}
}
