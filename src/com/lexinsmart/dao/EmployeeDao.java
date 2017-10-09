package com.lexinsmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.lexinsmart.model.Employee;
import com.lexinsmart.utils.DBCP;

public class EmployeeDao {
	Connection conn = null;
	PreparedStatement ptmt = null;
	ResultSet rest = null;
	public EmployeeDao(Connection connection) {
		this.conn = connection;
	}
	public void release() {
		DBCP.releaseConnection(conn);
		DBCP.closeStatement(ptmt, rest);
		System.out.println("释放 EmployeeDao 连接和语句");
	}
	public void addEmployees(List<Employee> employees) {
		try {
			int row = 0;
			String sql = "insert into employee (name,cardno,checkintime,checkouttime) "
					+ " values(?,?,?,?) " ;
			ptmt = conn.prepareStatement(sql);
			for (int i = 0; i < employees.size(); i++) {
				ptmt.setString(1, employees.get(i).getName());
				ptmt.setString(2, employees.get(i).getCardno());
				ptmt.setTimestamp(3, employees.get(i).getCheckintime());
				ptmt.setTimestamp(4, employees.get(i).getCheckouttime());
				ptmt.addBatch();
			}
			// 执行批处理操作并返回计数组成的数组  
            int[] rows = ptmt.executeBatch();  
            // 对行数赋值  
            row = rows.length;  
            System.out.println("addrow:   "+row);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void edit(List<Employee> employeeList) {
		try {
			int row = 0;
			String sql = "update employee set name=?,checkintime=?,checkouttime=? where cardno=?";
			ptmt = conn.prepareStatement(sql);

			for (int i = 0; i < employeeList.size(); i++) {
				ptmt.setString(1, employeeList.get(i).getName());
				ptmt.setTimestamp(2, employeeList.get(i).getCheckintime());
				ptmt.setTimestamp(3, employeeList.get(i).getCheckouttime());
				ptmt.setString(4, employeeList.get(i).getCardno());

				ptmt.addBatch();
			}
			// 执行批处理操作并返回计数组成的数组  
            int[] rows = ptmt.executeBatch();  
            // 对行数赋值  
            row = rows.length;  
            System.out.println("editrow:   "+row);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	public boolean checkIsExist(String cardno) {
		boolean isExist = false;
		try {
			// conn = dbcp.getConnection();

			String sql;
			sql = "SELECT id FROM employee where cardno=? ";
			ptmt = (PreparedStatement) conn.prepareStatement(sql);
			ptmt.setString(1, cardno);
			rest = ptmt.executeQuery();
			rest.last();
			if (rest.getRow() > 0) {
				isExist = true;
			} else {
				isExist = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// dbcp.closeStatement(ptmt);
			// dbcp.releaseConnection(conn);
		}
		return isExist;
	}


}
