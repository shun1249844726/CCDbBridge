package com.lexinsmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lexinsmart.model.TruckPrivilege;
import com.lexinsmart.utils.DBCP;

public class TruckPrivilegeDao {
	DBCP dbcp = DBCP.getInstance();

	Connection connection = null;
	PreparedStatement ptmt = null;

	public TruckPrivilegeDao(Connection connection) {
		this.connection = connection;
	}

	public void addTruckPrivilege(TruckPrivilege truckPrivilege) {
		try {
			// connection = dbcp.getConnection();
			String sql = " insert into truck_privilege " + " (egid,tid,tyid) " + " values(?,?,0) ";
			ptmt = connection.prepareStatement(sql);
			ptmt.setInt(1, truckPrivilege.getEgid());
			ptmt.setInt(2, truckPrivilege.getTid());

			ptmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// dbcp.closeStatement(ptmt);
			// dbcp.releaseConnection(connection);
		}
	}

	public boolean checkIsExist(int egid, int tid) {
		boolean isExist = false;
		ResultSet rs = null;
		try {
			// connection = dbcp.getConnection();
			String sql = " select * from truck_privilege where egid=? and tid=?";
			ptmt = connection.prepareStatement(sql);

			ptmt.setInt(1, egid);
			ptmt.setInt(2, tid);
			rs = ptmt.executeQuery();
			rs.last();
			if (rs.getRow() > 0) {
				isExist = true;
			} else {
				isExist = false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// dbcp.closeStatement(ptmt, rs);
			// dbcp.releaseConnection(connection);
		}
		return isExist;
	}

}
