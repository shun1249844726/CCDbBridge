package com.lexinsmart.action;


import java.sql.SQLException;

import com.lexinsmart.model.OAFkjcsub;
import com.lexinsmart.model.OARegistration;
import com.lexinsmart.service.RegistrationService;

/**
 * 访客测试类
 * @author xushun
 *
 */
public class RegistrationAction {

	public static void main(String[] args) {
		 String requestid ="2011";
		 String nodeid =null;
		 String rowindex =null;
		 String guestname ="朱2渊";//访客名称
		 String guestnumber =null;
		 String specificlocation =null;
		 String scene = "是";
		 String certificatenumber =null;
		 String arrivenumber =null;
		 String sjleavenumber =null;
		 String legacynumber =null;
		 String reqman =null;
		 String reqdate =null;
		 String reqdept =null;
		 String comtype =null;
		 String fktype =null;
		 String visitingdate = "2017-09-22";//预计来访时期   yyyy-mm-dd
		 String visitingtime ="11:40:23";//预计来访时间     hh:mm:ss
		 String leavedate ="2017-09-29";//预计出厂时间
		 String leavetime ="14:20:49";
		 String visitobject =null;
		 String things =null;
		 String enterdate =null;
		 String entertime =null;
		 String outdate =null;
		 String outtime =null;
		 String attachment =null;
		 String relationtel =null;
		 String accompantedin =null;
		 String accompaniedout =null;
		 String state =null;
		 String certificate =null;
		 String returndocuments =null;
		 String lsnumber =null;
		 String choosedoc =null;
		 String lfunit ="glrsmarttd";//来访单位。
		 String whylegacy =null;
		 String guestscope =null;
		 String bmid =null;
		 String hddept =null;
		 String tsyq =null;
		 String fkxz =null;
		 String bm1 =null;
		 String bm2 =null;
		 String cqb =null;
		 String planroute =null;
		 String lsctrlarea =null;
		 String hdleader =null;
		 String allleader = null;
		 String currentnode =null;
		 String deptscode =null;
		 String title =null;
		 String entourage =null;
		 String needwelcone =null;
		 String generals =null;
		 String meal =null;
		 String box =null;
		 String transport =null;
		 String meeting =null;
		 String fkdwty =null;
		 String lfunit2 =null;
		
		 OARegistration registration = new OARegistration();
		 registration.setRequestid(requestid);
		 registration.setNodeid(nodeid);
		 registration.setRowindex(rowindex);
		 registration.setGuestname(guestname);
		 registration.setGuestnumber(guestnumber);
		 registration.setSpecificlocation(specificlocation);
		 registration.setScene(scene);
		 registration.setCertificatenumber(certificatenumber);
		 registration.setArrivenumber(arrivenumber);
		 registration.setSjleavenumber(sjleavenumber);
		 registration.setLegacynumber(legacynumber);
		 registration.setReqman(reqman);
		 registration.setReqdate(reqdate);
		 registration.setReqdept(reqdept);
		 registration.setComtype(comtype);
		 registration.setFktype(fktype);
		 registration.setVisitingdate(visitingdate);
		 registration.setVisitingtime(visitingtime);
		 registration.setLeavedate(leavedate);
		 registration.setLeavetime(leavetime);
		 registration.setVisitobject(visitobject);
		 registration.setThings(things);
		 registration.setEnterdate(enterdate);
		 registration.setEntertime(entertime);
		 registration.setOutdate(outdate);
		 registration.setOuttime(outtime);
		 registration.setAttachment(attachment);
		 registration.setRelationtel(relationtel);
		 registration.setAccompaniedout(accompaniedout);
		 registration.setAccompantedin(accompantedin);
		 registration.setState(state);
		 registration.setCertificate(certificate);
		 registration.setReturndocuments(returndocuments);
		 registration.setLsnumber(lsnumber);
		 registration.setChoosedoc(choosedoc);
		 registration.setLfunit(lfunit);
		 registration.setWhylegacy(whylegacy);
		 registration.setGuestscope(guestscope);
		 registration.setBmid(bmid);
		 registration.setHddept(hddept);
		 registration.setTsyq(tsyq);
		 registration.setFkxz(fkxz);
		 registration.setBm1(bm1);
		 registration.setBm2(bm2);
		 registration.setCqb(cqb);
		 registration.setPlanroute(planroute);
		 registration.setLsctrlarea(lsctrlarea);
		 registration.setHdleader(hdleader);
		 registration.setAllleader(allleader);
		 registration.setCurrentnode(currentnode);
		 registration.setDeptscode(deptscode);
		 registration.setTitle(title);
		 registration.setEntourage(entourage);
		 registration.setNeedwelcone(needwelcone);
		 registration.setGenerals(generals);
		 registration.setMeal(meal);
		 registration.setBox(box);
		 registration.setTransport(transport);
		 registration.setMeeting(meeting);
		 registration.setFkdwty(fkdwty);
		 registration.setLfunit2(lfunit2);

		String p_requestid = "2011";
		String p_nodeid = null;
		String p_rowindex = null;
		String p_sno = null;
		String p_fkname = "徐顺";//访客名字
		String p_sfnum = null;
		String p_fknum = null;
		String p_qtnum = "411328199309155010";//访客证件号码
		String p_byear = null;
		String p_gender = "40285a8d4d459849014d45cbfcd60194";//性别
		String p_edate = null;
		String p_zjnum = null;
		String p_sex = null;
		String p_age = null;
		String p_birthplace = null;
		String p_fkcom = "glrss";//  所属公司是不是等同上面的公司
		String p_homeaddr = null;
		String p_telephone = null;
		String p_relative = null;
		String p_relationship = null;
		String p_relativetel = null;
		OAFkjcsub fkjcsub = new OAFkjcsub();
		fkjcsub.setRequestid(p_requestid);
		fkjcsub.setNodeid(p_nodeid);
		fkjcsub.setRowindex(p_rowindex);
		fkjcsub.setSno(p_sno);
		fkjcsub.setFkname(p_fkname);
		fkjcsub.setSfnum(p_sfnum);
		fkjcsub.setFknum(p_fknum);
		fkjcsub.setQtnum(p_qtnum);
		fkjcsub.setByear(p_byear);
		fkjcsub.setGender(p_gender);
		fkjcsub.setEdate(p_edate);
		fkjcsub.setZjnum(p_zjnum);
		fkjcsub.setSex(p_sex);
		fkjcsub.setAge(p_age);
		fkjcsub.setBirthplace(p_birthplace);
		fkjcsub.setFkcom(p_fkcom);
		fkjcsub.setHomeaddr(p_homeaddr);
		fkjcsub.setTelephone(p_telephone);
		fkjcsub.setRelative(p_relative);
		fkjcsub.setRelationship(p_relationship);
		fkjcsub.setRelativetel(p_relativetel);

		try {
			RegistrationService registrationService = new RegistrationService();
			registrationService.setRegistration(registration);  //设置访客主表
			registrationService.setFkjcsub(fkjcsub);//设置访客子表
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
