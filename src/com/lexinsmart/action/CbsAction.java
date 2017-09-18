package com.lexinsmart.action;

import java.sql.SQLException;

import com.lexinsmart.model.OAConstructionp;
import com.lexinsmart.model.OAContractortem;
import com.lexinsmart.service.ConstractortermService;

public class CbsAction {

	public static void main(String[] args) {
		String requestid = "211";
		String nodeid = null;
		String rowindex = null;
		String charger = null;
		String request = null;
		String contractorn = "40285a905d72f4e3015dc07b9ac64381";
		String traintime = null;
		String requireman = null;
		String insurance = null;
		String teltphone = null;
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

		String p_requestid = null;
		String p_nodeid = null;
		String p_rowindex = null;
		String p_num = null;
		String p_edate = null;
		String p_name = "Ravindra_cbs";
		String p_iden = "ASWPP9874D";
		String p_remarks = null;
		String p_insurancetype = null;
		String p_validity = null;
		String p_reasons = null;
		String p_ifjoin = null;
		String p_age = null;
		String p_location = null;
		String p_depart = null;
		String p_telephone = null;
		String p_homelocation = null;
		String p_relatives = null;
		String p_relativeship = null;
		String p_telephone2 = null;
		String p_sexs = null;
		String p_indates = null;
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
			constractortermService.setContractorterm(contractortem);
			constractortermService.setConstructionp(constructionp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
