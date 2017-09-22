package com.lexinsmart.action;

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
import com.lexinsmart.service.InoutfreightService;
import com.lexinsmart.utils.TypeChange;

/**
 * 货运司机测试类
 * @author xushun
 *
 */
public class InoutFreightAction {
	public static void main(String[] args) {
		Inoutfreight inoutfreight = new Inoutfreight();
		String requestid = "1031";
		String nodeid = null;
		String rowindex = null;
		String handplanno = "ZT201604004227";// 装卸计划号
		String depotname = "LBE原料非保税库12";// 主仓库名称
		String printime = null;
		String factype = "4028804d2083a7ed012083ebb988005b"; // 厂区别 常熟厂
		String singleno = "1020T000063115,1020T000063113,1020T000063114";// 提入单号
		String carno = "苏JJ58959";// 车牌号码
		String idcard1 = "320923197912034511";// 证件号(一)
		String idcard2 = null;// 证件号(二)
		String idcard3 = null;// 证件号(三)
		String idcard4 = null;// 证件号(四)
		String inregistime = null;//
		String outregistime = null;//
		String registerman = null;//
		String modifyman = null;//
		String company = "货运司机公司1";//公司
		String comtype = null;//
		String licensenum = "320924198111130870";// 证件号(司机)
		String createtime = null;//
		String parmcarno = null;//
		String parmno = null;//
		String driver = "史明聪";// 司机
		String attendant1 = "周立通1";// 随车人员(一)
		String attendant2 = null;//
		String attendant3 = null;//
		String attendant4 = null;//
		Integer infactorynum = null;// 进厂人数
		Integer atfactorynum = null;// 在厂人数
		Integer outfactorynum = null;// 离厂人数
		String driverstate = null;//
		String state1 = null;//
		String state2 = null;//
		String state3 = null;//
		String state4 = null;//
		String sinstate = null;//
		String xzdw = "40285a8d57fea8b501581e8cc344545f";// 限载吨位
		String bloodvalue = null;//
		String carheight = "2.3";// 车货高度
		String alcoholtest = null;//
		Integer bloodvalue1 = null;//
		Integer bloodvalue2 = null;//
		Integer bloodvalue3 = null;//
		Integer bloodvalue4 = null;//
		String alcoholtest1 = null;//
		String alcoholtest2 = null;//
		String alcoholtest3 = null;//
		String alcoholtest4 = null;//
		String age = null;// 年龄(司机)
		String age1 = null;// 年龄(一)
		String age2 = null;//
		String age3 = null;//
		String age4 = null;//
		String sex = null;// 性别(司机)
		String sex1 = null;//
		String sex2 = null;//
		String sex3 = null;//
		String sex4 = null;//
		String birthplace = null;// 籍贯(司机)
		String birthplace1 = null;// 籍贯(一)
		String birthplace2 = null;//
		String birthplace3 = null;//
		String birthplace4 = null;//
		String hygs = "司机公司";// 所属公司(司机)
		String hygs1 = null;//
		String hygs2 = null;//
		String hygs3 = null;//
		String hygs4 = null;//
		String homeaddr = null;// 家庭住址(司机)
		String homeaddr1 = null;//
		String homeaddr2 = null;//
		String homeaddr3 = null;//
		String homeaddr4 = null;//
		String phone = null;// 联系电话(司机)
		String phone1 = null;//
		String phone2 = null;//
		String phone3 = null;//
		String phone4 = null;//
		String relative = null;// 主要亲属(司机)
		String relative1 = null;//
		String relative2 = null;//
		String relative3 = null;//
		String relative4 = null;//
		String relationship = null;// 亲属关系(司机)
		String relationship1 = null;//
		String relationship2 = null;//
		String relationship3 = null;//
		String relationship4 = null;//
		String relativetel = null;// 亲属联系电话(司机)
		String relativetel1 = null;//
		String relativetel2 = null;//
		String relativetel3 = null;//
		String relativetel4 = null;//
		String intime = null;// 车辆实际进厂时间
		String indate = null;// 车辆实际进厂日期
		String outdate = null;// 车辆实际出厂日期
		String outtime = null;// 车辆实际出厂时间
		String cartype = "402864d1491d17c001491d2b922c0013";// 车辆类型 必填
		String carcate = null;// 车型归类
		inoutfreight.setRequestid(requestid);
		inoutfreight.setNodeid(nodeid);
		inoutfreight.setRowindex(rowindex);
		inoutfreight.setHandplanno(handplanno);
		inoutfreight.setDepotname(depotname);
		inoutfreight.setPrintime(printime);
		inoutfreight.setFactype(factype);
		inoutfreight.setSingleno(singleno);
		inoutfreight.setCarno(carno);
		inoutfreight.setIdcard1(idcard1);
		inoutfreight.setIdcard2(idcard2);
		inoutfreight.setIdcard3(idcard3);
		inoutfreight.setIdcard4(idcard4);
		inoutfreight.setInregistime(inregistime);
		inoutfreight.setOutregistime(outregistime);
		inoutfreight.setRegisterman(registerman);
		inoutfreight.setModifyman(modifyman);
		inoutfreight.setCompany(company);
		inoutfreight.setComtype(comtype);
		inoutfreight.setLicensenum(licensenum);
		inoutfreight.setCreatetime(createtime);
		inoutfreight.setParmcarno(parmcarno);
		inoutfreight.setParmno(parmno);
		inoutfreight.setDriver(driver);
		inoutfreight.setAttendant1(attendant1);
		inoutfreight.setAttendant2(attendant2);
		inoutfreight.setAttendant3(attendant3);
		inoutfreight.setAttendant4(attendant4);
		inoutfreight.setInfactorynum(infactorynum);
		inoutfreight.setAtfactorynum(atfactorynum);
		inoutfreight.setOutfactorynum(outfactorynum);
		inoutfreight.setDriverstate(driverstate);
		inoutfreight.setState1(state1);
		inoutfreight.setState2(state2);
		inoutfreight.setState3(state3);
		inoutfreight.setState4(state4);
		inoutfreight.setSinstate(sinstate);
		inoutfreight.setXzdw(xzdw);
		inoutfreight.setBloodvalue(bloodvalue);
		inoutfreight.setCarheight(carheight);
		inoutfreight.setAlcoholtest(alcoholtest);
		inoutfreight.setBloodvalue1(bloodvalue1);
		inoutfreight.setBloodvalue2(bloodvalue2);
		inoutfreight.setBloodvalue3(bloodvalue3);
		inoutfreight.setBloodvalue4(bloodvalue4);
		inoutfreight.setAlcoholtest1(alcoholtest1);
		inoutfreight.setAlcoholtest2(alcoholtest2);
		inoutfreight.setAlcoholtest3(alcoholtest3);
		inoutfreight.setAlcoholtest4(alcoholtest4);
		inoutfreight.setAge(age);
		inoutfreight.setAge1(age1);
		inoutfreight.setAge2(age2);
		inoutfreight.setAge3(age3);
		inoutfreight.setAge4(age4);
		inoutfreight.setSex(sex);
		inoutfreight.setSex1(sex1);
		inoutfreight.setSex2(sex2);
		inoutfreight.setSex3(sex3);
		inoutfreight.setSex4(sex4);
		inoutfreight.setBirthplace(birthplace);
		inoutfreight.setBirthplace1(birthplace1);
		inoutfreight.setBirthplace2(birthplace2);
		inoutfreight.setBirthplace3(birthplace3);
		inoutfreight.setBirthplace4(birthplace4);

		inoutfreight.setHygs(hygs);
		inoutfreight.setHygs1(hygs1);
		inoutfreight.setHygs2(hygs2);
		inoutfreight.setHygs3(hygs3);
		inoutfreight.setHygs4(hygs4);
		inoutfreight.setHomeaddr(homeaddr);
		inoutfreight.setHomeaddr1(homeaddr1);
		inoutfreight.setHomeaddr2(homeaddr2);
		inoutfreight.setHomeaddr3(homeaddr3);
		inoutfreight.setHomeaddr4(homeaddr4);
		inoutfreight.setPhone(phone);
		inoutfreight.setPhone1(phone1);
		inoutfreight.setPhone2(phone2);
		inoutfreight.setPhone3(phone3);
		inoutfreight.setPhone4(phone4);
		inoutfreight.setRelative(relative);
		inoutfreight.setRelative1(relative1);
		inoutfreight.setRelative2(relative2);
		inoutfreight.setRelative3(relative3);
		inoutfreight.setRelative4(relative4);
		inoutfreight.setRelationship(relationship);
		inoutfreight.setRelationship1(relationship1);
		inoutfreight.setRelationship2(relationship2);
		inoutfreight.setRelationship3(relationship3);
		inoutfreight.setRelationship4(relationship4);
		inoutfreight.setRelativetel(relativetel);
		inoutfreight.setRelativetel1(relativetel1);
		inoutfreight.setRelativetel2(relativetel2);
		inoutfreight.setRelativetel3(relativetel3);
		inoutfreight.setRelativetel4(relativetel4);
		inoutfreight.setIntime(intime);
		inoutfreight.setIndate(indate);
		inoutfreight.setOutdate(outdate);
		inoutfreight.setOuttime(outtime);
		inoutfreight.setCartype(cartype);
		inoutfreight.setCarcate(carcate);

		InoutfreightService inoutfreightService = new InoutfreightService(inoutfreight);
		try {
			 inoutfreightService.setInoutfreight();//添加

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}
}
