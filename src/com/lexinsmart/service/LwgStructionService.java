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
import com.lexinsmart.model.Plantime;
import com.lexinsmart.model.SEConstructionp;
import com.lexinsmart.model.SELwgstruction;
import com.lexinsmart.model.Staff;
import com.lexinsmart.utils.DBCP;
import com.lexinsmart.utils.TypeChange;
import com.sun.tools.javac.code.Attribute.Array;

public class LwgStructionService {

	DBCP dbcp = DBCP.getInstance();
	Connection connection = null;// 数据库的连接

	public LwgStructionService() {
		try {
			connection = dbcp.getConnection();// 事物管理，最后commit；
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setLwgstruction(SELwgstruction lwgstruction) throws SQLException {
		try {
			// 1.添加劳务工单位

			CompanyDao companyDao = new CompanyDao(connection);

			if (!companyDao.checkIsExist(lwgstruction.getContractorn())) {
				Company company = new Company();
				company.setRequestid(lwgstruction.getRequestid());
				company.setCompany(lwgstruction.getContractorn());
				company.setCtype(1);// 劳务单位 ：1
				companyDao.addCompany(company);
				System.out.println("add company");
			}

			// 2. 添加在厂时间。
			Plantime plantime = new Plantime();
			int cid = companyDao.getId(lwgstruction.getContractorn());// 查询单位外键的表ID
			plantime.setCid(cid);
			plantime.setSid(0);
			plantime.setTid(0);

			plantime.setChanger(lwgstruction.getCharger());
			plantime.setTelephone(lwgstruction.getTelephone());
			plantime.setPlanintime(null);
			plantime.setPlanouttime(null);
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
			List<Integer> ids = entranceGuardDao.getId(1, false);

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
			connection.rollback();
			e.printStackTrace();
		} finally {
			// dbcp.releaseConnection(connection);
		}
	}

	public void setConstructionp(SEConstructionp constructionp) throws SQLException {

		try {
			// 1. 添加员工表
			Staff staff = new Staff();
			staff.setRequestid(constructionp.getRequesstid());
			staff.setName(constructionp.getName());
			staff.setSex(constructionp.getSexs());
			staff.setAge(TypeChange.stringToInt(constructionp.getAge()));
			staff.setLocation(constructionp.getLocation());
			staff.setHomeaddr(constructionp.getHomelocation());
			staff.setTelephone(constructionp.getTelephone());
			staff.setCompany(constructionp.getDepart());
			staff.setRemarks(constructionp.getRemarks());
			staff.setRelative(constructionp.getRelatives());
			staff.setRelationship(constructionp.getRelativeship());
			staff.setTelephone2(constructionp.getTelephone2());
			staff.setIden(constructionp.getIden());

			StaffDao staffDao = new StaffDao(connection);

			if (constructionp.getIden() != null && !staffDao.checkIsExist(constructionp.getIden())) {
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
