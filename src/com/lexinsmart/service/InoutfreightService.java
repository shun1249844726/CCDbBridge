package com.lexinsmart.service;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lexinsmart.dao.CompanyDao;
import com.lexinsmart.dao.EntranceGuardDao;
import com.lexinsmart.dao.PlantimeDao;
import com.lexinsmart.dao.SingleNumberDao;
import com.lexinsmart.dao.StaffDao;
import com.lexinsmart.dao.SysLogDao;
import com.lexinsmart.dao.TruckDao;
import com.lexinsmart.dao.TruckPrivilegeDao;
import com.lexinsmart.model.Company;
import com.lexinsmart.model.Inoutfreight;
import com.lexinsmart.model.Plantime;
import com.lexinsmart.model.Singlenumber;
import com.lexinsmart.model.Staff;
import com.lexinsmart.model.SysLog;
import com.lexinsmart.model.Truck;
import com.lexinsmart.model.TruckPrivilege;
import com.lexinsmart.utils.DBCP;
import com.lexinsmart.utils.DateUtils;
import com.lexinsmart.utils.DropDownTools;
import com.lexinsmart.utils.TypeChange;

/**
 * 货运司机
 * 
 * @author xushun
 *
 */
public class InoutfreightService {
	private Singlenumber singlenumber = new Singlenumber();// 1.提入单号
	private Staff staff = new Staff();// 2. 司机
	private Staff staff1 = new Staff();// 3. 随车人1
	private Staff staff2 = new Staff();// 4 随车人2
	private Staff staff3 = new Staff();// 5. 随车人司机3
	private Staff staff4 = new Staff();// 6. 随车人司机4
	private Truck truck = new Truck();// 7 插入车辆表
	private TruckPrivilege truckPrivilege = new TruckPrivilege();// 8.车辆权限表
	private Company company = new Company();// 11货运人员的公司

	Inoutfreight inoutfreight;// 货运人员

//	DBCP dbcp = DBCP.getInstance();
	Connection connection = null;// 数据库的连接

	/**
	 * 货运司机 主表
	 * 
	 * @param inoutfreight
	 */
	public InoutfreightService(Inoutfreight inoutfreight) {

		this.inoutfreight = inoutfreight;

		staff.setRequestid(inoutfreight.getRequestid());
		staff.setName(inoutfreight.getDriver());
		staff.setSex(inoutfreight.getSex());
		staff.setAge(TypeChange.stringToInt(inoutfreight.getAge()));
		staff.setLocation(inoutfreight.getBirthplace());
		staff.setHomeaddr(inoutfreight.getHomeaddr());
		staff.setTelephone(inoutfreight.getPhone());
		staff.setCompany("货运单位");// 公司写车牌
		staff.setRemarks(inoutfreight.getHygs());// remarks 写所属公司
		staff.setRelative(inoutfreight.getRelative());
		staff.setRelationship(inoutfreight.getRelationship());
		staff.setTelephone2(inoutfreight.getRelativetel());
		staff.setIden(inoutfreight.getLicensenum());
		staff.setCtype(4);

		staff1.setRequestid(inoutfreight.getRequestid());
		staff1.setName(inoutfreight.getAttendant1());
		staff1.setSex(inoutfreight.getSex1());
		staff1.setAge(TypeChange.stringToInt(inoutfreight.getAge1()));
		staff1.setLocation(inoutfreight.getBirthplace1());
		staff1.setHomeaddr(inoutfreight.getHomeaddr1());
		staff1.setTelephone(inoutfreight.getPhone1());
		staff1.setCompany("货运单位");// 公司写车牌
		staff1.setRemarks(inoutfreight.getHygs1());// remarks 写所属公司
		staff1.setRelative(inoutfreight.getRelative1());
		staff1.setRelationship(inoutfreight.getRelationship1());
		staff1.setTelephone2(inoutfreight.getRelativetel1());
		staff1.setIden(inoutfreight.getIdcard1());
		staff1.setCtype(4);


		staff2.setRequestid(inoutfreight.getRequestid());
		staff2.setName(inoutfreight.getAttendant2());
		staff2.setSex(inoutfreight.getSex2());
		staff2.setAge(TypeChange.stringToInt(inoutfreight.getAge2()));
		staff2.setLocation(inoutfreight.getBirthplace2());
		staff2.setHomeaddr(inoutfreight.getHomeaddr2());
		staff2.setTelephone(inoutfreight.getPhone2());
		staff2.setCompany("货运单位");// 公司写车牌
		staff2.setRemarks(inoutfreight.getHygs2());// remarks 写所属公司
		staff2.setRelative(inoutfreight.getRelative2());
		staff2.setRelationship(inoutfreight.getRelationship2());
		staff2.setTelephone2(inoutfreight.getRelativetel2());
		staff2.setIden(inoutfreight.getIdcard2());
		staff2.setCtype(4);


		staff3.setRequestid(inoutfreight.getRequestid());
		staff3.setName(inoutfreight.getAttendant3());
		staff3.setSex(inoutfreight.getSex3());
		staff3.setAge(TypeChange.stringToInt(inoutfreight.getAge3()));
		staff3.setLocation(inoutfreight.getBirthplace3());
		staff3.setHomeaddr(inoutfreight.getHomeaddr3());
		staff3.setTelephone(inoutfreight.getPhone3());
		staff3.setCompany("货运单位");// 公司写车牌
		staff3.setRemarks(inoutfreight.getHygs3());// remarks 写所属公司
		staff3.setRelative(inoutfreight.getRelative3());
		staff3.setRelationship(inoutfreight.getRelationship3());
		staff3.setTelephone2(inoutfreight.getRelativetel3());
		staff3.setIden(inoutfreight.getIdcard3());
		staff3.setCtype(4);


		staff3.setRequestid(inoutfreight.getRequestid());
		staff4.setName(inoutfreight.getAttendant4());
		staff4.setSex(inoutfreight.getSex4());
		staff4.setAge(TypeChange.stringToInt(inoutfreight.getAge4()));
		staff4.setLocation(inoutfreight.getBirthplace4());
		staff4.setHomeaddr(inoutfreight.getHomeaddr4());
		staff4.setTelephone(inoutfreight.getPhone4());
		staff4.setCompany("货运单位");// 公司写车牌
		staff4.setRemarks(inoutfreight.getHygs4());// remarks 写所属公司
		staff4.setRelative(inoutfreight.getRelative4());
		staff4.setRelationship(inoutfreight.getRelationship4());
		staff4.setTelephone2(inoutfreight.getRelativetel4());
		staff4.setIden(inoutfreight.getIdcard4());
		staff4.setCtype(4);

		// plantime.setCid(0);//
		// plantime.setSid(0);//
		// plantime.setTid(1);// 货运 用车辆外键 限制
		// plantime.setChanger(inoutfreight.getDriver());
		// plantime.setTelephone(inoutfreight.getPhone());
		// plantime.setPlanintime(null);
		// plantime.setPlanouttime(null);
		// plantime.setRequesid(inoutfreight.getRequestid());

		truck.setRequestid(inoutfreight.getRequestid());
		truck.setCompany(inoutfreight.getCompany());
		truck.setTtype(inoutfreight.getCartype()); // 查询车型
		truck.setCarno(inoutfreight.getCarno());
		float xzdw = DropDownTools.stringToTon(inoutfreight.getXzdw());
		truck.setXzdw(xzdw);

		company.setRequestid(inoutfreight.getRequestid());
		company.setCompany(inoutfreight.getCarno());// 车辆表的公司，，，，写车牌号
		company.setCtype(4);// 货运单位 4
		company.setRemarks(inoutfreight.getHygs());// 所属公司；

		try {
			connection = DBCP.getConnection();// 事物管理，最后commit；
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 将出入场货运人员登记表信息写入各个数据库；
	 * 
	 * @throws SQLException
	 */
	public void setInoutfreight() throws SQLException {

		try {
			Date c = new Date();
			// 1提入单号表
			SingleNumberDao singleNumberDao = new SingleNumberDao(connection);
			String singleno = inoutfreight.getSingleno();
			String[] singlenos = TypeChange.convertStrToArray(singleno);
			int singlenonum = singlenos.length;// 提入单号中包含的提入单号数量

			for (int i = 0; i < singlenonum; i++) {
				singlenumber.setCarno(inoutfreight.getCarno());
				singlenumber.setCarheight(TypeChange.stringToFloat(inoutfreight.getCarheight()));
				singlenumber.setTtype(inoutfreight.getCartype());// 车型
																	// cartype
				singlenumber.setHandplanno(inoutfreight.getHandplanno());
				singlenumber.setSingleno(singlenos[i].toLowerCase());// 提入单号 拆分。
				singlenumber.setDepart(inoutfreight.getDepotname());// 装卸地点
																	// =
																	// depotname
				singlenumber.setRequestid(inoutfreight.getRequestid());
				if (!singleNumberDao.checkIsExist(singlenos[i])) {// 提入单号是否重复
					singleNumberDao.addSingleNumber(singlenumber);
					System.out.println("insert singlenumber！");
				} else {
					singleNumberDao.edit(singlenumber);
					System.out.println("edit single_number");
				}
			}

			// 2插入司机
			StaffDao staffDao = new StaffDao(connection);
			if (inoutfreight.getLicensenum() != null) {
				if (!staffDao.checkIsExist(inoutfreight.getLicensenum())) {
					staffDao.addStaff(staff);
					System.out.println("insert staff！");
				} else {
					staffDao.edit(staff);
					System.out.println("edit driver");
				}
			}

			// 3插入随车人1
			if (inoutfreight.getIdcard1() != null  ) {
				if (!staffDao.checkIsExist(inoutfreight.getIdcard1())) {
					staffDao.addStaff(staff1);
					System.out.println("insert staff1！");
				}else {
					staffDao.edit(staff1);
					System.out.println("edit staff 1");
				}
			}
			// 4插入随车人2
			if (inoutfreight.getIdcard2() != null  ) {
				if (!staffDao.checkIsExist(inoutfreight.getIdcard2())) {
					staffDao.addStaff(staff2);
					System.out.println("insert staff2！");
				}else {
					staffDao.edit(staff2);
					System.out.println("edit staff 2");
				}
			}
			// 5插入随车人3
			if (inoutfreight.getIdcard3() != null  ) {
				if (!staffDao.checkIsExist(inoutfreight.getIdcard3())) {
					staffDao.addStaff(staff3);
					System.out.println("insert staff 3！");
				}else {
					staffDao.edit(staff3);
					System.out.println("edit staff 3");
				}
			}
			// 6插入随车人4
			if (inoutfreight.getIdcard4() != null  ) {
				if (!staffDao.checkIsExist(inoutfreight.getIdcard4())) {
					staffDao.addStaff(staff4 );
					System.out.println("insert staff 4！");
				}else {
					staffDao.edit(staff4);
					System.out.println("edit staff 4");
				}
			}

			// 7.插入车辆表
			TruckDao truckDao = new TruckDao(connection);
			if (!truckDao.checkIsExist(inoutfreight.getCarno())) {
				System.out.println("insert truck！");
				truckDao.addTruck(truck);
			} else {
				System.out.println("edit truck！");
				truckDao.edit(truck);
			}

//			int carid = truckDao.getId(inoutfreight.getCarno());// 车辆ID
//			EntranceGuardDao entranceGuardDao = new EntranceGuardDao(connection);
//			List<Integer> ids = entranceGuardDao.getId(1);
//			for (int i = 0; i < ids.size(); i++) {
//				// 8.插入车辆权限表
//				truckPrivilege.setEgid(ids.get(i));// 查询门禁表id
//				truckPrivilege.setTid(carid); // 查询车辆表ID
//				TruckPrivilegeDao truckPrivilegeDao = new TruckPrivilegeDao(connection);
//
//				if (!truckPrivilegeDao.checkIsExist(ids.get(i), carid)) {
//					truckPrivilegeDao.addTruckPrivilege(truckPrivilege);
//					System.out.println("insert truckPrivilege！");
//				}
//			}

			// // 9.插入在厂时间
			// PlantimeDao plantimeDao = new PlantimeDao(connection);
			// plantime.setTid(carid);
			// plantimeDao.addPlanTime(plantime);
			// System.out.println("insert plantime！");

			// 10 插入系统日志
			InetAddress ia = null;
			String localname = "";
			String localip = "";
			try {
				ia = InetAddress.getLocalHost();
				localname = ia.getHostName();
				localip = ia.getHostAddress();
			} catch (Exception e) {
				e.printStackTrace();
			}
			SysLog sysLog = new SysLog();
			sysLog.setUsername(localname);
			sysLog.setIp(localip);
			sysLog.setLogintime(new Timestamp(System.currentTimeMillis()));
			sysLog.setProxy("java bridge");
			sysLog.setContent("add inoutfreight ");
			SysLogDao sysLogDao = new SysLogDao(connection);
			sysLogDao.addSysLog(sysLog);
			System.out.println("add syslog！");

			// 11 .写入公司
			CompanyDao companyDao = new CompanyDao(connection);
			if (!companyDao.checkIsExist(company.getCompany())) {
				companyDao.addCompany(company);
				System.out.println("add company");
			}
			// else {
			// companyDao.delete(companyDao.getId(company.getCompany()));
			// companyDao.addCompany(company);
			// System.out.println("delete & add company");
			// }

			connection.commit();
			System.out.println("commit！");
			Date d = new Date();
			System.out.println("耗时： " + "\t" + (d.getTime() - c.getTime()) + " ms");
		} catch (Exception e) {
			System.out.println("插入数据  失败！");
			e.printStackTrace();
			connection.rollback();
		} finally {
			DBCP.releaseConnection(connection);
		}
	}
}
