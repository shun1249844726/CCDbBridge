package com.lexinsmart.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.lexinsmart.dao.CompanyDao;
import com.lexinsmart.dao.CompanyPrivilegeDao;
import com.lexinsmart.dao.EntranceGuardDao;
import com.lexinsmart.dao.PlantimeDao;
import com.lexinsmart.dao.StaffDao;
import com.lexinsmart.model.Company;
import com.lexinsmart.model.Companyprivilege;
import com.lexinsmart.model.EntranceGuard;
import com.lexinsmart.model.OAConstructionp;
import com.lexinsmart.model.OATocsbusiness;
import com.lexinsmart.model.Plantime;
import com.lexinsmart.model.Staff;
import com.lexinsmart.utils.DBCP;
import com.lexinsmart.utils.DateUtils;
import com.lexinsmart.utils.TypeChange;

public class ToBusinessService {

	Connection connection = null;// 数据库的连接

	CompanyDao companyDao = null;
	StaffDao staffDao = null;
	PlantimeDao plantimeDao = null;
	CompanyPrivilegeDao companyPrivilegeDao = null;
	EntranceGuardDao entranceGuardDao = null;

	public ToBusinessService() {
		try {
			connection = DBCP.getConnection();
			connection.setAutoCommit(false);
		} catch (Exception e) {
		}
	}

	public void setToBusinessService(OATocsbusiness oaTocsbusiness) {
		try {

			String companyName = oaTocsbusiness.getTradepart() + "出差单位";
			// 添加 公司表
			companyDao = new CompanyDao(connection);
			if (!companyDao.checkIsExist(companyName)) {// 出差人员单位
				Company company = new Company();
				company.setCompany(companyName);
				company.setRequestid(oaTocsbusiness.getRequestid());
				company.setCtype(2);// TODO 出差人员类型 待定
				company.setRemarks("出差单位");
				companyDao.addCompany(company);
				System.out.println("add company");
			}
			// 添加 个人表
			String idenString = oaTocsbusiness.getCardnum();//身份证用卡号
			String remarksUseIden= oaTocsbusiness.getIden();//备注写身份证号
			staffDao = new StaffDao(connection);
			Staff staff = new Staff();
			staff.setRequestid(oaTocsbusiness.getRequestid());
			staff.setName(oaTocsbusiness.getTranname());
			staff.setIden(idenString);
			staff.setSex(oaTocsbusiness.getSex());
			staff.setAge(TypeChange.stringToInt(oaTocsbusiness.getAge()));
			staff.setLocation(oaTocsbusiness.getLoaction());
			staff.setHomeaddr(oaTocsbusiness.getFamlocation());
			staff.setTelephone(oaTocsbusiness.getTel());
			staff.setCompany(companyName);
			staff.setRemarks(remarksUseIden); 
			staff.setRelative(oaTocsbusiness.getRelationname());
			staff.setRelationship(oaTocsbusiness.getRelaction());
			staff.setTelephone2(oaTocsbusiness.getRelactiontel());
			staff.setPrivilege(false);
			staff.setCtype(2);// TODO 出差人员类型 待定
			

			int sid = 0;
			if (idenString != null && !idenString.equals("")) {
				sid = staffDao.getid(idenString);
				System.out.println("sid:"+sid +"  "+idenString);
				if (sid > 0) {
					long time = new Date().getTime();
					Timestamp nowtime = new Timestamp(time);
					staffDao.editIden(nowtime+"/"+idenString, idenString);
					staffDao.addStaff(staff);
					System.out.println("edit old and add staff！");
				} else {
					staffDao.addStaff(staff);
					System.out.println("add staff！");
				}
			}

			// 添加单位权限表
			companyPrivilegeDao = new CompanyPrivilegeDao(connection);
			int cid = companyDao.getId(companyName);// 查询单位外键的表ID

			Companyprivilege companyprivilege = new Companyprivilege();
			companyprivilege.setCid(cid);

			entranceGuardDao = new EntranceGuardDao(connection);
			List<Integer> ids = entranceGuardDao.getId(0, false);// 默认给1道门权限。
			for (int i = 0; i < ids.size(); i++) {
				Integer egid = ids.get(i);// 查询ID
				companyprivilege.setEgid(egid);
				int privilegeid = companyPrivilegeDao.getIdIfExist(cid, egid);
				if (privilegeid > 0) {
					System.out.println(cid+" "+companyName + "  的:  " + egid + "  存在");
				} else if (privilegeid == 0) {
					companyPrivilegeDao.addCompanyPrivilege(companyprivilege);//
					// 循环，看多少个门禁ID了
					System.out.println("add companyprivilege");
				}
			}

			// 添加计划在厂时间
			plantimeDao = new PlantimeDao(connection);
			Plantime plantime = new Plantime();
			plantime.setCid(cid);
			plantime.setSid(sid);
			plantime.setTid(0);
			plantime.setChanger(oaTocsbusiness.getTranname());
			plantime.setTelephone(oaTocsbusiness.getTel());
			plantime.setRequesid(oaTocsbusiness.getRequestid());
			String intime = oaTocsbusiness.getStartdate() + " " + oaTocsbusiness.getStarttime();
			String outtime = oaTocsbusiness.getEnddate() + " " + oaTocsbusiness.getEndtime();

			plantime.setPlanintime(DateUtils.StringToTimestamp(intime));
			plantime.setPlanouttime(DateUtils.StringToTimestamp(outtime));

			plantimeDao.addPlanTime(plantime);
			System.out.println("add plantime");

			connection.commit();
			System.out.println("commit！");

		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			 companyDao.release();
			 staffDao.release();
			 plantimeDao.release();
			 companyPrivilegeDao.release();
			 entranceGuardDao.release();
			 
			 DBCP.releaseConnection(connection);
			 System.out.println("释放连接！");
		}
	}
}
