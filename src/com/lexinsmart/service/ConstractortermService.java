package com.lexinsmart.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.lexinsmart.dao.CompanyDao;
import com.lexinsmart.dao.CompanyPrivilegeDao;
import com.lexinsmart.dao.EntranceGuardDao;
import com.lexinsmart.dao.PlantimeDao;
import com.lexinsmart.dao.StaffDao;
import com.lexinsmart.model.Company;
import com.lexinsmart.model.Companyprivilege;
import com.lexinsmart.model.OAConstructionp;
import com.lexinsmart.model.OAContractortem;
import com.lexinsmart.model.Plantime;
import com.lexinsmart.model.SEConstructionp;
import com.lexinsmart.model.Staff;
import com.lexinsmart.utils.DBCP;
import com.lexinsmart.utils.DateUtils;
import com.lexinsmart.utils.TypeChange;

/**
 * 承包商管理
 * 
 * @author xushun
 *
 */
public class ConstractortermService {
	static String staticCompany = "";

//	DBCP dbcp = DBCP.getInstance();
	Connection connection = null;// 数据库的连接

	CompanyDao companyDao =null;
	EntranceGuardDao entranceGuardDao = null;
	CompanyPrivilegeDao companyPrivilegeDao = null;
	StaffDao staffDao =null;
	PlantimeDao plantimeDao = null;
	
	public ConstractortermService() {
		try {
			connection = DBCP.getConnection();// 事物管理，最后commit；
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 设置承包商主表
	 * 
	 * @param contractortem
	 * @throws SQLException
	 */
	public void setContractorterm(OAContractortem contractortem) throws SQLException {
		staticCompany = contractortem.getContractorn();

		try {
			// 1. 添加单位表
			companyDao = new CompanyDao(connection);
			if (!companyDao.checkIsExist(contractortem.getContractorn())) {
				Company company = new Company();
				company.setRequestid(contractortem.getRequestid());
				company.setCompany(contractortem.getContractorn());
				company.setCtype(3);// 承包商 3 劳务：1 访客：2 货运4
				company.setRemarks("");
				companyDao.addCompany(company);
				System.out.println("add company");
			}

			// 3 添加单位权限
			entranceGuardDao = new EntranceGuardDao(connection);
			List<Integer> ids = entranceGuardDao.getId(0, false); // 获取门禁的ID
			int cid = companyDao.getId(contractortem.getContractorn());// 查询单位外键的表ID
			Companyprivilege companyprivilege = new Companyprivilege();
			companyprivilege.setCid(cid);

			companyPrivilegeDao = new CompanyPrivilegeDao(connection);
			Integer egid = 0;

			for (int i = 0; i < ids.size(); i++) {
				egid = ids.get(i);// 查询ID
				companyprivilege.setEgid(egid);

				int privilegeid = companyPrivilegeDao.getIdIfExist(cid, egid);
				if (privilegeid > 0) {
//					companyPrivilegeDao.delete(privilegeid);
//					companyPrivilegeDao.addCompanyPrivilege(companyprivilege);//
					// 循环，看多少个门禁ID了
//					System.out.println("exit ");
				} else if (privilegeid == 0) {
					companyPrivilegeDao.addCompanyPrivilege(companyprivilege);//
					// 循环，看多少个门禁ID了
					System.out.println("add companyprivilege");
				}
			}

			connection.commit();
		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();
		}
	}

	/**
	 * 设置承包商子表
	 * 
	 * @param constructionp
	 * @throws SQLException
	 */
	public void setConstructionp(OAConstructionp constructionp) throws SQLException {

		try {
			// 1. 添加员工表
			Staff staff = new Staff();
			CompanyDao companyDao = new CompanyDao(connection);
			String requestid = companyDao.getrequestId(constructionp.getDepart());
			staff.setRequestid(requestid);
			staff.setName(constructionp.getName());
			staff.setSex(constructionp.getSexs());
			staff.setAge(TypeChange.stringToInt(constructionp.getAge()));
			staff.setLocation(constructionp.getLocation());
			staff.setHomeaddr(constructionp.getHomelocation());
			staff.setTelephone(constructionp.getTelephone());
			staff.setCompany(staticCompany);
			staff.setRemarks("承包商："+constructionp.getRemarks());
			staff.setRelative(constructionp.getRelatives());
			staff.setRelationship(constructionp.getRelativeship());
			staff.setTelephone2(constructionp.getTelephone2());
			staff.setIden(constructionp.getIden());
			staff.setPrivilege(false);
			staff.setCtype(3);//承包商人员类型 3

			staffDao = new StaffDao(connection);

			int id = staffDao.getid(constructionp.getIden());
			if (id > 0) {
				staffDao.edit(staff);
				System.out.println("edit staff！");
			} else {
				staffDao.addStaff(staff);
				System.out.println("add staff！");
			}
			
		
			// 2. 添加在厂时间。
			Plantime plantime = new Plantime();
			plantime.setCid(companyDao.getId(staticCompany));
			id = staffDao.getid(constructionp.getIden());//重新查询ID
			plantime.setSid(id);// TODO 使用个人外键控制；
			plantime.setTid(0);

			plantime.setChanger(constructionp.getName());
			plantime.setTelephone(constructionp.getTelephone());
			plantime.setPlanintime(new Timestamp(System.currentTimeMillis()));
			Timestamp outtime = null;
			if (constructionp.getIndates() == null || constructionp.getIndates().equals("")) {
				outtime = null;
			}else{
				outtime = new Timestamp(DateUtils.StringToDate(constructionp.getIndates()).getTime());
			}
			plantime.setPlanouttime(outtime);
			plantimeDao = new PlantimeDao(connection);

			plantimeDao.addPlanTime(plantime);
			System.out.println("add plantime");

			System.out.println("commit");
			connection.commit();
		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();
		} finally {
			
			 companyDao.release();
			 entranceGuardDao.release();
			 companyPrivilegeDao.release();
			 staffDao.release();
			 plantimeDao.release();
			DBCP.releaseConnection(connection);
			System.out.println("释放连接");
		}
	}
}
