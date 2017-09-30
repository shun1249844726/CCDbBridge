package com.lexinsmart.action;

import java.sql.SQLException;

import com.lexinsmart.model.OAConstructionp;
import com.lexinsmart.model.OAContractortem;
import com.lexinsmart.service.ConstractortermService;

/**
 * 承包商测试类
 * @author xushun
 *
 */
public class CbsAction {

	public static void main(String[] args) {
		String requestid = "222";
		String nodeid = null;
		String rowindex = null;
		String charger ="Cbs1";//承包商负责人
		String request = null;
		String contractorn = "承包商公司222";//承包商名称
		String traintime = null;
		String requireman = null;
		String insurance = null;
		String teltphone = null;//负责人电话
		String insurancetype = null;
		String cexpire = null;
		String currenttime = null;
		String insurancetime = null;
		String enclosure = null;
		String factory = null;

		OAContractortem contractortem = new OAContractortem();// 主表
		contractortem.setRequestid(requestid);
		contractortem.setNodeid(nodeid);
		contractortem.setRowindex(rowindex);
		contractortem.setCharger(charger);
		contractortem.setRequest(request);
		contractortem.setContractorn(contractorn);
		contractortem.setContractorn(contractorn);
		contractortem.setTraintime(traintime);
		contractortem.setRequireman(requireman);
		contractortem.setInsurance(insurance);
		contractortem.setTeltphone(teltphone);
		contractortem.setInsurancetype(insurancetype);
		contractortem.setCexpire(cexpire);
		contractortem.setCurrenttime(currenttime);
		contractortem.setInsurancetime(insurancetime);
		contractortem.setEnclosure(enclosure);
		contractortem.setFactory(factory);

		String p_requestid = "222";
		String p_nodeid = null;
		String p_rowindex = null;
		String p_num = null;
		String p_edate = null;
		String p_name = "cbs2";//姓名
		String p_iden = "411328199309155010";//身份证号
		String p_remarks = null;
		String p_insurancetype = null;
		String p_validity = null;
		String p_reasons = null;//加入黑名单原因
		String p_ifjoin = null;
		String p_age = null;//年龄
		String p_location = null;//籍贯
		String p_depart = "";//所属公司
		String p_telephone = null;//联系电话
		String p_homelocation = null;//家庭住址
		String p_relatives = null;////亲属名字
		String p_relativeship = null;//亲属关系
		String p_telephone2 = null;//亲属电话
		String p_sexs = null;//性别
		String p_indates = "2018-07-05";//入厂有效期／／／／／／必填
		OAConstructionp constructionp = new OAConstructionp();
		constructionp.setRequestid(p_requestid);
		constructionp.setNodeid(p_nodeid);
		constructionp.setRowindex(p_rowindex);
		constructionp.setNum(p_num);
		constructionp.setEdate(p_edate);
		constructionp.setName(p_name);
		constructionp.setIden(p_iden);
		constructionp.setRemarks(p_remarks);
		constructionp.setInsurancetype(p_insurancetype);
		constructionp.setValidity(p_validity);
		constructionp.setReasons(p_reasons);
		constructionp.setIfjoin(p_ifjoin);
		constructionp.setAge(p_age);
		constructionp.setLocation(p_location);
		constructionp.setDepart(p_depart);
		constructionp.setTelephone(p_telephone);
		constructionp.setHomelocation(p_homelocation);
		constructionp.setRelatives(p_relatives);
		constructionp.setRelativeship(p_relativeship);
		constructionp.setTelephone2(p_telephone2);
		constructionp.setSexs(p_sexs);
		constructionp.setIndates(p_indates);

		ConstractortermService constractortermService = new ConstractortermService();
		try {
			constractortermService.setContractorterm(contractortem); //添加承包商主表
			constractortermService.setConstructionp(constructionp);//设置承包商子表
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
