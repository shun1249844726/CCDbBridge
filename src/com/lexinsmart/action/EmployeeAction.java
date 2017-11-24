package com.lexinsmart.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lexinsmart.model.Employee;
import com.lexinsmart.model.EmployeeString;
import com.lexinsmart.service.EmployeeService;

public class EmployeeAction {

	public static void main(String[] args) {
		List<EmployeeString> employeesstringList = new ArrayList<EmployeeString>();
		EmployeeService employeeService = new EmployeeService();
		for (int i = 0; i < 1; i++) {
			EmployeeString employeestring = new EmployeeString();
			
			String name ="namedddd";
			String cardno ="1333161395979";
			String checkintime = "2017-09-01 07:27:57";
			String checkouttime = null;
			employeestring.setName(name);
			employeestring.setCardno(cardno);
			employeestring.setCheckintime(checkintime);
			employeestring.setCheckouttime(checkouttime);
			
			employeesstringList.add(employeestring);
		}
		Date dateStart = new Date();
		employeeService.setEmployees(employeesstringList);
		System.out.println("usetime:   "+(System.currentTimeMillis() - dateStart.getTime()));
	}
}
