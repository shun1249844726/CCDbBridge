package com.lexinsmart.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.lexinsmart.utils.DateUtils;
import com.lexinsmart.utils.TypeChange;

/**
 * 劳务工表
 * 
 * @author xushun
 * 
 */
public class LwgStructionService {

	static String staticCompany = "";
	static String requestidTemp = "";
	// DBCP dbcp = DBCP.getInstance();
	Connection connection = null;// 数据库的连接
	CompanyDao companyDao = null;
	EntranceGuardDao entranceGuardDao = null;
	CompanyPrivilegeDao companyPrivilegeDao = null;
	StaffDao staffDao = null;
	PlantimeDao plantimeDao = null;

	public LwgStructionService() {
		try {
			connection = DBCP.getConnection();// 事物管理，最后commit；
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 设置劳务工主表
	 * 
	 * @param lwgstruction
	 * @throws SQLException
	 */
	public void setLwgstruction(SELwgstruction lwgstruction) throws SQLException {
		try {
			// 1.添加劳务工单位
			staticCompany = lwgstruction.getContractorn();
			requestidTemp = lwgstruction.getRequestid();
			companyDao = new CompanyDao(connection);
			if (!companyDao.checkIsExist(lwgstruction.getContractorn())) { //
				Company company = new Company();
				company.setRequestid(lwgstruction.getRequestid());
				company.setCompany(lwgstruction.getContractorn());
				company.setCtype(1);// 劳务单位 ：1
				companyDao.addCompany(company);
				System.out.println("add company");
			}

			int cid = companyDao.getId(lwgstruction.getContractorn());// 查询单位外键的表ID
			// 3 添加单位权限
			entranceGuardDao = new EntranceGuardDao(connection);
			// List<Integer> ids = entranceGuardDao.getId(0, false);
			List<Integer> ids = entranceGuardDao.getIdsByType(0);

			Companyprivilege companyprivilege = new Companyprivilege();
			companyprivilege.setCid(cid);

			companyPrivilegeDao = new CompanyPrivilegeDao(connection);

			for (int i = 0; i < ids.size(); i++) {
				Integer egid = ids.get(i);
				companyprivilege.setEgid(egid);
				int privilegeid = companyPrivilegeDao.getIdIfExist(cid, egid);
				if (privilegeid > 0) {
					companyPrivilegeDao.delete(privilegeid);
					companyPrivilegeDao.addCompanyPrivilege(companyprivilege);//
					// 循环，看多少个门禁ID了
					System.out.println("delete & add  companyprivilege");
				} else if (privilegeid == 0) {
					companyPrivilegeDao.addCompanyPrivilege(companyprivilege);//
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

	/**
	 * 设置劳务工子表
	 * 
	 * @param constructionp
	 * @throws SQLException
	 */
	public void setConstructionp(SEConstructionp constructionp) throws SQLException {

		try {
			// 1. 添加员工表
			Staff staff = new Staff();
			String requestid = requestidTemp;
			staff.setRequestid(requestid);
			staff.setName(constructionp.getName());
			staff.setSex(constructionp.getSexs());
			staff.setAge(TypeChange.stringToInt(constructionp.getAge()));
			staff.setLocation(constructionp.getLocation());
			staff.setHomeaddr(constructionp.getHomelocation());
			staff.setTelephone(constructionp.getTelephone());
			staff.setCompany(staticCompany);
			staff.setRemarks("劳务工：" + constructionp.getRemarks());
			staff.setRelative(constructionp.getRelatives());
			staff.setRelationship(constructionp.getRelativeship());
			staff.setTelephone2(constructionp.getTelephone2());
			staff.setIden(constructionp.getIden());
			staff.setPrivilege(false);
			staff.setCtype(1);

			staffDao = new StaffDao(connection);
			if (constructionp.getIden() != null && !staffDao.checkIsExist(constructionp.getIden())) {
				staffDao.addStaff(staff);
				System.out.println("add staff");
			} else {
				staffDao.edit(staff);
				System.out.println("edit staff");
			}

			// 2. 添加在厂时间。
			Plantime plantime = new Plantime();
			int cid = companyDao.getId(staticCompany);// 10.3发现劳务工的在厂时间没有cid
			plantime.setCid(cid);
			int sid = staffDao.getid(constructionp.getIden());
			plantime.setSid(sid);
			plantime.setTid(0);
			plantime.setChanger(constructionp.getName());
			plantime.setTelephone(constructionp.getTelephone());
			plantime.setPlanintime(new Timestamp(System.currentTimeMillis()));
			Timestamp outtime = null;
			if (constructionp.getIndates() == null || constructionp.getIndates().equals("")) {
				outtime = null;
			} else {
				outtime = new Timestamp(DateUtils.StringToDate(constructionp.getIndates()).getTime() + 3600* 1000* 24-1);

//				outtime = new Timestamp(DateUtils.StringToDate(constructionp.getIndates()).getTime());
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

		}
	}

	public void release() {
		companyDao.release();
		entranceGuardDao.release();
		companyPrivilegeDao.release();

		staffDao.release();
		plantimeDao.release();

		DBCP.releaseConnection(connection);
		System.out.println("释放连接");

	}

}
