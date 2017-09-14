package com.lexinsmart.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lexinsmart.dao.CompanyDao;
import com.lexinsmart.dao.PlantimeDao;
import com.lexinsmart.dao.SingleNumberDao;
import com.lexinsmart.dao.StaffDao;
import com.lexinsmart.model.Company;
import com.lexinsmart.model.Inoutfreight;
import com.lexinsmart.model.Plantime;
import com.lexinsmart.model.Singlenumber;
import com.lexinsmart.model.Staff;
import com.lexinsmart.utils.DBCP;
import com.lexinsmart.utils.DropDownTools;
import com.lexinsmart.utils.TypeChange;

public class InoutfreightService {
	
	public InoutfreightService() {
		
	}
	
	/**
	 * 将出入场货运人员登记表信息写入各个数据库；
	 * 
	 * @param inoutfreight
	 * @throws SQLException
	 */
	public void setInoutfreight(Inoutfreight inoutfreight) throws SQLException {

		Float xzdw = DropDownTools.stringToTon(inoutfreight.getXzdw());// 换算限载吨位

		Connection connection = DBCP.getConnection();// 事物管理，最后commit；
		connection.setAutoCommit(false);
		try {
			CompanyDao companyDao = new CompanyDao();// 判断单位表 ID是否重复
			if (companyDao.checkIsExist(inoutfreight.getId())) {
				System.out.println("数据id已经存在！不能重复添加");
				return;
			}
			// 提取公司表信息
			Company company = new Company();
			company.setId(TypeChange.stringToInt(inoutfreight.getId()));
			company.setRequestid(inoutfreight.getRequestid());//
			company.setCompany(inoutfreight.getCarno());// TODO 单位名称，暂时用车牌号
			companyDao.addCompany(company);

			// 提入单号表
			SingleNumberDao singleNumberDao = new SingleNumberDao();
			if (!singleNumberDao.checkIsExist(inoutfreight.getSingleno())) {// 提入单号是否重复
				Singlenumber singlenumber = new Singlenumber();
				singlenumber.setCid(TypeChange.stringToInt(inoutfreight.getId()));
				singlenumber.setCarno(inoutfreight.getCarno());
				singlenumber.setXzdw(xzdw);
				singlenumber.setCarheight(TypeChange.stringToFloat(inoutfreight.getCarheight()));
				singlenumber.setHandplanno(inoutfreight.getHandplanno());
				singlenumber.setSingleno(inoutfreight.getSingleno());

				singleNumberDao.addSingleNumber(singlenumber);
			}

			// 插入司机
			StaffDao staffDao = new StaffDao();
			if (inoutfreight.getLicensenum() != null && !staffDao.checkIsExist(inoutfreight.getLicensenum())) {
				Staff staff = new Staff();
				staff.setRequestid(inoutfreight.getRequestid());
				staff.setName(inoutfreight.getDriver());
				staff.setSex(inoutfreight.getSex());
				staff.setAge(TypeChange.stringToInt(inoutfreight.getAge()));
				staff.setLocation(inoutfreight.getBirthplace());
				staff.setHomeaddr(inoutfreight.getHomeaddr());
				staff.setTelephone(inoutfreight.getPhone());
				staff.setCompany(inoutfreight.getCarno());
				staff.setRemarks("");
				staff.setRelative(inoutfreight.getRelative());
				staff.setRelationship(inoutfreight.getRelationship());
				staff.setTelephone2(inoutfreight.getRelativetel());
				staff.setIden(inoutfreight.getLicensenum());

				staffDao.addStaff(staff);
			}

			// 插入随车人1
			if (inoutfreight.getIdcard1() != null) {
				Staff staff = new Staff();
				staff.setRequestid(inoutfreight.getRequestid());
				staff.setName(inoutfreight.getAttendant1());
				staff.setSex(inoutfreight.getSex1());
				staff.setAge(TypeChange.stringToInt(inoutfreight.getAge1()));
				staff.setLocation(inoutfreight.getBirthplace1());
				staff.setHomeaddr(inoutfreight.getHomeaddr1());
				staff.setTelephone(inoutfreight.getPhone1());
				staff.setCompany(inoutfreight.getCarno());
				staff.setRemarks("");
				staff.setRelative(inoutfreight.getRelative1());
				staff.setRelationship(inoutfreight.getRelationship1());
				staff.setTelephone2(inoutfreight.getRelativetel1());
				staff.setIden(inoutfreight.getIdcard1());

				staffDao.addStaff(staff);
			}

			// 插入在厂时间
			Plantime plantime = new Plantime();
			plantime.setCid(TypeChange.stringToInt(inoutfreight.getId()));
			plantime.setChanger(inoutfreight.getDriver());
			plantime.setTelephone(inoutfreight.getPhone());
			PlantimeDao plantimeDao = new PlantimeDao();
			plantimeDao.addPlanTime(plantime);

			System.out.println("插入数据成功！");
			connection.commit();
		} catch (Exception e) {
			System.out.println("插入数据  失败！");
			e.printStackTrace();
			connection.rollback();
		}
	}
}
