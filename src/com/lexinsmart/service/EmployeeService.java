package com.lexinsmart.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.lexinsmart.dao.EmployeeDao;
import com.lexinsmart.model.Employee;
import com.lexinsmart.model.EmployeeString;
import com.lexinsmart.utils.DBCP;
import com.lexinsmart.utils.DateUtils;

public class EmployeeService {
	Connection connection = null;// 数据库的连接

	public EmployeeService() {
		try {
			connection = DBCP.getConnection();// 事物管理，最后commit；
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setEmployees(List<EmployeeString> employeesstring) {
		
		EmployeeDao employeeDao = new EmployeeDao(connection);
		List<Employee> employeeList = new ArrayList<Employee>();
		List<Employee> employeeEditList = new ArrayList<Employee>();

		for (int i = 0; i < employeesstring.size(); i++) {
			String name = employeesstring.get(i).getName();
			String cardno = employeesstring.get(i).getCardno();
			//"2011-05-09 11:49:45"
			
			Timestamp checkintime = null;
			Timestamp checkouttime = null;
			if ( employeesstring.get(i).getCheckintime() == null || employeesstring.get(i).getCheckintime().equals("")) {
				checkintime = null;
			} else {
				 checkintime = DateUtils.StringToTimestamp(employeesstring.get(i).getCheckintime());
			}
			if (employeesstring.get(i).getCheckouttime() ==  null|| employeesstring.get(i).getCheckouttime().equals("") ) {
				checkouttime = null;
			} else {
				checkouttime = DateUtils.StringToTimestamp(employeesstring.get(i).getCheckouttime());
			}			
			Employee employee = new Employee();
			employee.setName(name);
			employee.setCardno(cardno);
			employee.setCheckintime(checkintime);
			employee.setCheckouttime(checkouttime);
			
			if (employeeDao.checkIsExist(cardno)) {
				employeeEditList.add(employee);
			}else {
				employeeList.add(employee);
			}
		}
		
		System.out.println("edits:  "+employeeEditList.size());
		employeeDao.edit(employeeEditList);
		employeeDao.addEmployees(employeeList);
		try {
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			DBCP.releaseConnection(connection);
		}
	}
}
