package com.lexinsmart.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
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

/**
 * 访客管理
 * 
 * @author xushun
 * 
 */
public class RegistrationService {
	static String lfunit = "";
	// DBCP dbcp = DBCP.getInstance();
	Connection connection = null;
	CompanyDao companyDao = null;
	PlantimeDao plantimeDao = null;
	EntranceGuardDao entranceGuardDao = null;
	CompanyPrivilegeDao companyPrivilegeDao = null;
	StaffDao staffDao = null;
	Timestamp outtimetemp = null;

	public RegistrationService() {
		try {
			connection = DBCP.getConnection();
			connection.setAutoCommit(false);
		} catch (Exception e) {
		}
	}

	/**
	 * 根据填入的访客主表 信息，将信息拆分到各个表
	 * 
	 * @param registration
	 */
	public void setRegistration(OARegistration registration) {
		lfunit = registration.getLfunit() + "访客单位";
		String scene = registration.getScene();
		try {
			// 1. 添加单位表
			companyDao = new CompanyDao(connection);
			if (!companyDao.checkIsExist(lfunit)) {
				Company company = new Company();
				company.setRequestid(registration.getRequestid());
				company.setCompany(lfunit);// 来访单位
				company.setCtype(2);// 货运 1 访客
				if (scene.equals("是")) {
					company.setRemarks("能进入二道门的访客单位");
				} else {
					company.setRemarks("不能进入二道门的访客单位");
				}
				companyDao.addCompany(company);
				System.out.println("add company");
			}

			// 2. 添加在厂时间。
			Plantime plantime = new Plantime();
			int cid = companyDao.getId(lfunit);// 查询单位外键的表ID
			plantime.setCid(cid);
			plantime.setSid(0);
			plantime.setTid(0);

			plantime.setChanger(registration.getGuestname());
			plantime.setTelephone(null);
			String intime = registration.getVisitingdate() + " " + registration.getVisitingtime();
			// 确认日期和时间的格式
			String outtime = registration.getLeavedate() + " " + registration.getLeavetime();
			outtimetemp = DateUtils.StringToTimestamp(outtime);
			plantime.setPlanintime(DateUtils.StringToTimestamp(intime));
			plantime.setPlanouttime(outtimetemp);
			plantimeDao = new PlantimeDao(connection);

			// int id = plantimeDao.getIdIfExist(cid, 0, 0);
			// if (id > 0) {
			// plantimeDao.deleteplantime(id);
			// plantimeDao.addPlanTime(plantime);
			// System.out.println("delete & add plantime");
			// } else if (id == 0) {
			plantimeDao.addPlanTime(plantime);
			System.out.println("add plantime");
			// }

			// 3 添加单位权限
			entranceGuardDao = new EntranceGuardDao(connection);
			List<Integer> ids = entranceGuardDao.getId(0, false);
			if (scene.equals("是")) {// 如果是可以进入二道门的，把二道门也给添加进去。
				List<Integer> erDaoMenids = entranceGuardDao.getId(0, true);
				for (int i = 0; i < erDaoMenids.size(); i++) {
					ids.add(erDaoMenids.get(i));
				}
			}

			Companyprivilege companyprivilege = new Companyprivilege();
			companyprivilege.setCid(cid);

			companyPrivilegeDao = new CompanyPrivilegeDao(connection);
			Integer egid = 0;// 查询ID

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
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	/**
	 * 访客子表
	 * 
	 * @param oaFkjcsub
	 * @throws SQLException
	 */
	public void setFkjcsub(OAFkjcsub oaFkjcsub) throws SQLException {
		try {
			// 1. 添加员工表
			Staff staff = new Staff();
			staffDao = new StaffDao(connection);
			String iden = oaFkjcsub.getQtnum();

			staff.setRequestid(oaFkjcsub.getRequestid());
			staff.setName(oaFkjcsub.getFkname());
			// String sex = DropDownTools.stringToSex(oaFkjcsub.getGender());
			staff.setSex(oaFkjcsub.getGender());
			staff.setAge(TypeChange.stringToInt(oaFkjcsub.getAge()));
			staff.setLocation(oaFkjcsub.getBirthplace());
			staff.setHomeaddr(oaFkjcsub.getHomeaddr());
			staff.setTelephone(oaFkjcsub.getTelephone());

			staff.setCompany(lfunit);// 访客的单位用来访单位。
			staff.setRemarks("访客");
			staff.setRelative(oaFkjcsub.getRelative());
			staff.setRelationship(oaFkjcsub.getRelationship());
			staff.setTelephone2(oaFkjcsub.getRelativetel());
			staff.setIden(iden);
			staff.setPrivilege(false);
			staff.setCtype(2);

			int sid = staffDao.getid(iden);
			if (sid > 0) {
				// 用来判断是否是承包商，或者劳务工的访客。
				String companyOld = staffDao.getCompany(iden);
				System.out.println("old:" + companyOld);
				int oldcid = companyDao.getId(companyOld);
				List<Timestamp> times_c = plantimeDao.getPlantimeByCid(oldcid);
				if (times_c.size() > 0) {
					System.out.println("size: " + times_c.size() + " " + Collections.max(times_c));
					if (Collections.max(times_c).getTime() > outtimetemp.getTime()) {
						System.out.println("劳务单位计划时间更长");
						staff.setCompany(staffDao.getCompany(iden));
						staff.setCtype(staffDao.getctype(iden));
						staff.setRemarks("原单位的时间还没到");
					}
				}
				List<Timestamp> times = plantimeDao.getPlantimeBySid(sid);
				if (times.size() > 0) {
					System.out.println("size: " + times.size() + " " + Collections.max(times));
					if (Collections.max(times).getTime() > outtimetemp.getTime()) {
						System.out.println("劳务单位中人员的计划时间更长");
						staff.setCompany(staffDao.getCompany(iden));
						staff.setCtype(staffDao.getctype(iden));
						staff.setRemarks("承包商或劳务工的在厂时间还没到的中途访客");
					}
				}
				System.out.println("最终使用：" + staff.getCompany() + "  ctype:" + staff.getCtype());
				// 到这里

				staffDao.edit(staff);
				System.out.println("edit staff！");
			} else {
				staffDao.addStaff(staff);
				System.out.println("add staff！");
			}

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
		plantimeDao.release();
		entranceGuardDao.release();
		companyPrivilegeDao.release();
		staffDao.release();

		DBCP.releaseConnection(connection);
		System.out.println("释放连接");
	}
}
