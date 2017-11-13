package com.lexinsmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lexinsmart.model.MajorCountModel;
import com.lexinsmart.utils.DBCP;

public class CurrentCountDao {
	Connection connection = null;
	PreparedStatement ptmt = null;
	DBCP dbcp = DBCP.getInstance();
	ResultSet rest = null;

	public CurrentCountDao(Connection connection) {
		this.connection = connection;
	}

	public void release() {
		DBCP.releaseConnection(connection);
		DBCP.closeStatement(ptmt, rest);
		System.out.println("释放获取在厂人数的连接");
	}

	public MajorCountModel getMajorCount() {
		MajorCountModel majorCountModel = new MajorCountModel();

		String sql = "select company,currentnum from counter";
		try {
			ptmt = connection.prepareStatement(sql);
			rest = ptmt.executeQuery();
			while (rest.next()) {
				String company = rest.getString(1);
				int currentnum = rest.getInt(2);

				if (company.equals("厂内员工")) {
					majorCountModel.setStaffNumber(currentnum);
				} else if (company.equals("劳务人员")) {
					majorCountModel.setLwgNumber(currentnum);
				} else if (company.equals("访客人员")) {
					majorCountModel.setRegistrationNumber(currentnum);
				} else if (company.equals("承包商")) {
					majorCountModel.setConstructionNumber(currentnum);
				} else if (company.equals("货车数量")) {
					majorCountModel.setFreightCarNumber(currentnum);
				} else if (company.equals("货运人员")) {
					majorCountModel.setFreightstaffNumber(currentnum);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return majorCountModel;

	}
}
