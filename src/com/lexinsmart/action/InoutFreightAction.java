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
		String requestid = "10320";
		String nodeid = null;
		String rowindex = null;
		String handplanno = "FZT201710002880";// 装卸计划号
		String depotname =
//				"PBT原材料保税库,qin,yao,long,"
//				+ "PBT原材料保税库,qin,yao,long,"
//				+ "PBT原材料保税库,qin,yao,long,"
//				+ "PBT原材料保税库,qin,yao,long,"
//				+ "PBT原材料保税库,qin,yao,long,"
//				+ "yi,DFR原料非保税库,haha,fei,"
//				+ "xu,shun,yuan,han,"
//				+ "yang,le,yong,zhou,"
//				+ "jie,jia,heihei,zu,"
//				+ "xu,shun,yuan,han,"
//				+ "yang,le,yong,zhou,"
//				+ "jie,jia,heihei,zu,"
//				+ "xu,shun,yuan,han,"
//				+ "yang,le,yong,zhou,"
				 "jie,jia,heihei,zu,";// 主仓库名称

		String printime = null;
		String factype = "4028804d2083a7ed012083ebb988005b"; // 厂区别 常熟厂
		String singleno = 
//				"1020T00260274620,1020T000274h621,1020T000we274619,1020T0jx00274618,"
//				+ "1020T000274hg62a0,1020T0xfh002746a21,1020Tgf0002746a19,1020Tdg00027a618,"
//				+ "1020T0002746442ea0,1020T00027466ea21,1020T000n2746ea19,1020sT00027ea618,"
//				+ "1020T000274e2bha0,1020T000e278uy746a21,1020T60002746ea19,1020T000e727a618,"
//				+ "1020T000efw27462a0,1020Tk0002w746a21,1020T0rg6002746a19,1020T0e068s027a618,"
//				+ "1020T000ew242746782a0,1020T0002w7449056a21,1020T0rg04502jno746a19,1020Tuihy0e04s027a618,"
//				+ "10207T000ew274w62a0,1020T0ll00j2w746a21,10i20T540rg002746a19,1020Thj0e056s027a618,"
//				+ "1020T000ewwr27jk62a0,1020Tc0002w77846a21,1020T0rg0d027436a19,1020T8hj70e0s027a618,"
//				+ "1020T000eer2a0,1020T62w746a21,1020Tetsy2746a19,1020T0a5r6027a618,"
//				+ "1020T0ryj00ewx2a0,1020T0007ryj6a21,1020T0xr274rj6a19,1020T0y8027a618,"
//				+ "1020Tj00ewf23a0,1020T006746a21,1020T0ryu86746a19,10209027a618,"
//				+ "1025ew2ag7462a0,1020562xcw746a21,1020The0r5a19,1020T0ghjs027a618,"
//				+ "1020T0567ew27xxf462a0,1020T002w746a21,1020T0rherg002t67,1020T0ere0s0k,"
//				+ "1020T000ew2x2a0,1020T00236a21,1020T0rg3419,1020T054e0u,"
				 "1020T00ka0,1020T000s21,1020T0rg00j,1020T0e0k8,";// 提入单号
		String carno = "苏EE7088";// 车牌号码
		String idcard1 = "EE7088EE7088";// 证件号(一)
		String idcard2 = null;// 证件号(二)
		String idcard3 = null;// 证件号(三)
		String idcard4 = null;// 证件号(四)
		String inregistime = null;//
		String outregistime = null;//   
		String registerman = null;//
		String modifyman = null;//
		String company = "EE7088";//公司
		String comtype = null;//
		String licensenum = "EE7088EE7088EE7088";// 证件号(司机)
		String createtime = null;//
		String parmcarno = null;//
		String parmno = null;//
		String driver = "司机EE7088";// 司机
		String attendant1 = "随车1";// 随车人员(一)
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
		String sex = "女";// 性别(司机)
		String sex1 = null;//
		String sex2 = null;//
		String sex3 = null;//
		String sex4 = null;//
		String birthplace = null;// 籍贯(司机)
		String birthplace1 = null;// 籍贯(一)
		String birthplace2 = null;//
		String birthplace3 = null;//
		String birthplace4 = null;//
		String hygs = "司机公司1";// 所属公司(司机)
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
		String cartype = "40Fire";// 车辆类型 必填
		String carcate = "平板货车";// 车型归类
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
