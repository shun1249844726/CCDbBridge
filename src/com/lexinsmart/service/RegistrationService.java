package com.lexinsmart.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.lexinsmart.dao.CompanyDao;
import com.lexinsmart.dao.CompanyPrivilegeDao;
import com.lexinsmart.dao.EntranceGuardDao;
import com.lexinsmart.dao.PlantimeDao;
import com.lexinsmart.dao.StaffDao;
import com.lexinsmart.model.Company;
import com.lexinsmart.model.Companyprivilege;
import com.lexinsmart.model.OAFkjcsub;
import com.lexinsmart.model.OARegistration;
import com.lexinsmart.model.Plantime;
import com.lexinsmart.model.Staff;
import com.lexinsmart.utils.DBCP;
import com.lexinsmart.utils.DateUtils;
import com.lexinsmart.utils.DropDownTools;
import com.lexinsmart.utils.TypeChange;

public class RegistrationService {
	DBCP dbcp = DBCP.getInstance();
	Connection connection = null;

	public RegistrationService() {
		try {
			connection = dbcp.getConnection();
			connection.setAutoCommit(false);
		} catch (Exception e) {
		}
	}

	public void setRegistration(OARegistration registration) {

		try {
			// 1. 添加单位表
			CompanyDao companyDao = new CompanyDao(connection);
			if (!companyDao.checkIsExist(registration.getLfunit())) {
				Company company = new Company();
				company.setRequestid(registration.getRequestid());
				company.setCompany(registration.getLfunit());// 来访单位
				company.setCtype(2);// 货运 1 访客
				company.setRemarks("");

				companyDao.addCompany(company);
				System.out.println("add company");

			}

			// 2. 添加在厂时间。
			Plantime plantime = new Plantime();
			int cid = companyDao.getId(registration.getLfunit());// 查询单位外键的表ID
			plantime.setCid(cid);
			plantime.setSid(0);
			plantime.setTid(0);

			plantime.setChanger(registration.getGuestname());
			plantime.setTelephone(null);
			String intime = registration.getVisitingdate() + " " + registration.getVisitingtime();// TODO
																									// 确认日期和时间的格式
			String outtime = registration.getLeavedate() + " " + registration.getLeavetime();
			plantime.setPlanintime(DateUtils.StringToTimestamp(intime));
			plantime.setPlanouttime(DateUtils.StringToTimestamp(outtime));
			PlantimeDao plantimeDao = new PlantimeDao(connection);

			int id = plantimeDao.getIdIfExist(cid, 0, 0);
			if (id > 0) {
				plantimeDao.deleteplantime(id);
				plantimeDao.addPlanTime(plantime);
				System.out.println("delete & add plantime");
			} else if (id == 0) {
				plantimeDao.addPlanTime(plantime);
				System.out.println("add plantime");
			}

			// 3 添加单位权限
			EntranceGuardDao entranceGuardDao = new EntranceGuardDao(connection);
			List<Integer> ids = entranceGuardDao.getId(0, false);

			Companyprivilege companyprivilege = new Companyprivilege();
			companyprivilege.setCid(cid);

			CompanyPrivilegeDao companyPrivilegeDao = new CompanyPrivilegeDao(connection);
			Integer egid = 0;// TODO 查询ID

			for (int i = 0; i < ids.size(); i++) {
				egid = ids.get(i);
				companyprivilege.setEgid(egid);

				int privilegeid = companyPrivilegeDao.getIdIfExist(cid, egid);
				if (privilegeid > 0) {
					companyPrivilegeDao.delete(privilegeid);
					companyPrivilegeDao.addCompanyPrivilege(companyprivilege);//
					// 循环，看多少个门禁ID了
					System.out.println("delete & add  companyprivilege");
				} else if (privilegeid == 0) {
					companyPrivilegeDao.addCompanyPrivilege(companyprivilege);//
					// 循环，看多少个门禁ID了
					System.out.println("add companyprivilege");
				}

			}
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}	
	}
	
	public void setFkjcsub(OAFkjcsub oaFkjcsub) throws SQLException{
		try {
			// 1. 添加员工表
			Staff staff = new Staff();
			staff.setRequestid(oaFkjcsub.getRequestid());
			staff.setName(oaFkjcsub.getFkname());
			String sex = DropDownTools.stringToSex(oaFkjcsub.getGender());
			staff.setSex(sex);
			staff.setAge(TypeChange.stringToInt(oaFkjcsub.getAge()));
			staff.setLocation(oaFkjcsub.getBirthplace());
			staff.setHomeaddr(oaFkjcsub.getHomeaddr());
			staff.setTelephone(oaFkjcsub.getTelephone());
			staff.setCompany(oaFkjcsub.getFkcom());//TODO 所属公司，是不是同主表来访单位。
			staff.setRemarks("");
			staff.setRelative(oaFkjcsub.getRelative());
			staff.setRelationship(oaFkjcsub.getRelationship());
			staff.setTelephone2(oaFkjcsub.getRelativetel());
			staff.setIden(oaFkjcsub.getQtnum());

			StaffDao staffDao = new StaffDao(connection);

			if (oaFkjcsub.getQtnum() != null && !staffDao.checkIsExist(oaFkjcsub.getQtnum())) {
				staffDao.addStaff(staff);
				System.out.println("add staff！");
			}
			System.out.println("commit");

			connection.commit();
		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();
		} finally {
			dbcp.releaseConnection(connection);
		}
	}
}
