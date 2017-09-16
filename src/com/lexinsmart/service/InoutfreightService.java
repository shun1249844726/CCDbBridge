package com.lexinsmart.service;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.lexinsmart.dao.EntranceGuardDao;
import com.lexinsmart.dao.PlantimeDao;
import com.lexinsmart.dao.SingleNumberDao;
import com.lexinsmart.dao.StaffDao;
import com.lexinsmart.dao.SysLogDao;
import com.lexinsmart.dao.TruckDao;
import com.lexinsmart.dao.TruckPrivilegeDao;
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
	private Plantime plantime = new Plantime();// 9. 在厂时间。

	Inoutfreight inoutfreight;

	public InoutfreightService(Inoutfreight inoutfreight) {

		this.inoutfreight = inoutfreight;

		staff.setRequestid(inoutfreight.getRequestid());
		staff.setName(inoutfreight.getDriver());
		staff.setSex(inoutfreight.getSex());
		staff.setAge(TypeChange.stringToInt(inoutfreight.getAge()));
		staff.setLocation(inoutfreight.getBirthplace());
		staff.setHomeaddr(inoutfreight.getHomeaddr());
		staff.setTelephone(inoutfreight.getPhone());
		staff.setCompany(inoutfreight.getHygs());
		staff.setRemarks("");
		staff.setRelative(inoutfreight.getRelative());
		staff.setRelationship(inoutfreight.getRelationship());
		staff.setTelephone2(inoutfreight.getRelativetel());
		staff.setIden(inoutfreight.getLicensenum());

		staff1.setRequestid(inoutfreight.getRequestid());
		staff1.setName(inoutfreight.getAttendant1());
		staff1.setSex(inoutfreight.getSex1());
		staff1.setAge(TypeChange.stringToInt(inoutfreight.getAge1()));
		staff1.setLocation(inoutfreight.getBirthplace1());
		staff1.setHomeaddr(inoutfreight.getHomeaddr1());
		staff1.setTelephone(inoutfreight.getPhone1());
		staff1.setCompany(inoutfreight.getHygs1());
		staff1.setRemarks("");
		staff1.setRelative(inoutfreight.getRelative1());
		staff1.setRelationship(inoutfreight.getRelationship1());
		staff1.setTelephone2(inoutfreight.getRelativetel1());
		staff1.setIden(inoutfreight.getIdcard1());

		staff2.setRequestid(inoutfreight.getRequestid());
		staff2.setName(inoutfreight.getAttendant2());
		staff2.setSex(inoutfreight.getSex2());
		staff2.setAge(TypeChange.stringToInt(inoutfreight.getAge2()));
		staff2.setLocation(inoutfreight.getBirthplace2());
		staff2.setHomeaddr(inoutfreight.getHomeaddr2());
		staff2.setTelephone(inoutfreight.getPhone2());
		staff2.setCompany(inoutfreight.getHygs2());
		staff2.setRemarks("");
		staff2.setRelative(inoutfreight.getRelative2());
		staff2.setRelationship(inoutfreight.getRelationship2());
		staff2.setTelephone2(inoutfreight.getRelativetel2());
		staff2.setIden(inoutfreight.getIdcard2());

		staff3.setRequestid(inoutfreight.getRequestid());
		staff3.setName(inoutfreight.getAttendant3());
		staff3.setSex(inoutfreight.getSex3());
		staff3.setAge(TypeChange.stringToInt(inoutfreight.getAge3()));
		staff3.setLocation(inoutfreight.getBirthplace3());
		staff3.setHomeaddr(inoutfreight.getHomeaddr3());
		staff3.setTelephone(inoutfreight.getPhone3());
		staff3.setCompany(inoutfreight.getHygs3());
		staff3.setRemarks("");
		staff3.setRelative(inoutfreight.getRelative3());
		staff3.setRelationship(inoutfreight.getRelationship3());
		staff3.setTelephone2(inoutfreight.getRelativetel3());
		staff3.setIden(inoutfreight.getIdcard3());

		plantime.setCid(0);//
		plantime.setSid(0);//
		plantime.setTid(1);// 货运 用车辆外键 限制
		plantime.setChanger(inoutfreight.getDriver());
		plantime.setTelephone(inoutfreight.getPhone());
		plantime.setPlanouttime(null);

		truck.setRequestid(inoutfreight.getRequestid());
		truck.setCompany(inoutfreight.getHygs());
		truck.setTtype(inoutfreight.getCartype()); // 查询车型
		truck.setCarno(inoutfreight.getCarno());
		float xzdw = DropDownTools.stringToTon(inoutfreight.getXzdw());
		truck.setXzdw(xzdw);

	}

	/**
	 * 将出入场货运人员登记表信息写入各个数据库；
	 * 
	 * @throws SQLException
	 */
	public void setInoutfreight() throws SQLException {

		DBCP dbcp = DBCP.getInstance();
		Connection connection = dbcp.getConnection();// 事物管理，最后commit；
		connection.setAutoCommit(false);

		try {
			Date c = new Date();
			// 1提入单号表
			SingleNumberDao singleNumberDao = new SingleNumberDao(connection);
			String singleno = inoutfreight.getSingleno();
			String[] singlenos = TypeChange.convertStrToArray(singleno);
			int singlenonum = singlenos.length;// 提入单号中包含的提入单号数量

			for (int i = 0; i < singlenonum; i++) {
				if (!singleNumberDao.checkIsExist(singlenos[i])) {// 提入单号是否重复
					singlenumber.setCarno(inoutfreight.getCarno());
					singlenumber.setCarheight(TypeChange.stringToFloat(inoutfreight.getCarheight()));
					singlenumber.setTtype(inoutfreight.getCartype());// 车型
																		// cartype
					singlenumber.setHandplanno(inoutfreight.getHandplanno());
					singlenumber.setSingleno(singlenos[i]);// 提入单号 拆分。
					singlenumber.setDepart(inoutfreight.getDepotname());// 装卸地点
																		// =
																		// depotname
					singleNumberDao.addSingleNumber(singlenumber);
					System.out.println("insert singlenumber！");
				}
			}

			// 2插入司机
			StaffDao staffDao = new StaffDao(connection);
			if (inoutfreight.getLicensenum() != null && !staffDao.checkIsExist(inoutfreight.getLicensenum())) {
				staffDao.addStaff(staff);
				System.out.println("insert staff！");

			}

			// 3插入随车人1
			if (inoutfreight.getIdcard1() != null && !staffDao.checkIsExist(inoutfreight.getIdcard1())) {
				staffDao.addStaff(staff1);
				System.out.println("insert staff1！");

			}
			// 4插入随车人2
			if (inoutfreight.getIdcard2() != null && !staffDao.checkIsExist(inoutfreight.getIdcard2())) {
				staffDao.addStaff(staff2);
				System.out.println("insert staff2！");

			}
			// 5插入随车人3
			if (inoutfreight.getIdcard3() != null && !staffDao.checkIsExist(inoutfreight.getIdcard3())) {
				staffDao.addStaff(staff3);
				System.out.println("insert staff3！");

			}
			// 6插入随车人4
			if (inoutfreight.getIdcard4() != null && !staffDao.checkIsExist(inoutfreight.getIdcard4())) {
				staffDao.addStaff(staff4);
				System.out.println("insert staff4！");

			}

			// 7.插入车辆表
			TruckDao truckDao = new TruckDao(connection);
			if (!truckDao.checkIsExist(inoutfreight.getCarno())) {
				System.out.println("insert truck！");
				truckDao.addTruck(truck);
			}

			int carid = truckDao.getId(inoutfreight.getCarno());// 车辆ID
			EntranceGuardDao entranceGuardDao = new EntranceGuardDao(connection);
			List<Integer> ids = entranceGuardDao.getId(1);
			for (int i = 0; i < ids.size(); i++) {
				// 8.插入车辆权限表
				truckPrivilege.setEgid(ids.get(i));// 查询门禁表id
				truckPrivilege.setTid(carid); // 查询车辆表ID
				TruckPrivilegeDao truckPrivilegeDao = new TruckPrivilegeDao(connection);

				if (!truckPrivilegeDao.checkIsExist(ids.get(i), carid)) {
					truckPrivilegeDao.addTruckPrivilege(truckPrivilege);
					System.out.println("insert truckPrivilege！");
				}
			}

			// 9.插入在厂时间
			PlantimeDao plantimeDao = new PlantimeDao(connection);
			plantime.setTid(carid);
			plantimeDao.addPlanTime(plantime);
			System.out.println("insert plantime！");

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

			connection.commit();
			System.out.println("commit！");
			Date d = new Date();
			System.out.println("耗时： " + "\t" + (d.getTime() - c.getTime()) + " ms");
		} catch (Exception e) {
			System.out.println("插入数据  失败！");
			e.printStackTrace();
			connection.rollback();
		} finally {
			dbcp.releaseConnection(connection);
		}
	}

	public void editInoutfreight() {

	}
}
