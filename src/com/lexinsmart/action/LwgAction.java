package com.lexinsmart.action;

import java.sql.SQLException;

import com.lexinsmart.model.SEConstructionp;
import com.lexinsmart.model.SELwgstruction;
import com.lexinsmart.service.LwgStructionService;

public class LwgAction {

	public static void main(String[] args) {
		// 劳务工 主表
		SELwgstruction lwgstruction = new SELwgstruction();
		String requestid= "111";
		String nodeid= null;
		String rowindex= null;
		String contractorn="40285a905d72f4e3015dc07b9ac64381";//劳务商名称；
		String insurancetype= null;
		String insurancetime= null;
		String charger= "lwgCharger";//负责人
		String telephone= "16757576567";//负责人电话
		String traintime= null;
		String cexpire= null;
		String enclossure= null;
		String requireman= null;
		String currenttime= null;
		String insurance= null;
		String request= null;
		String factory= null;
		
		lwgstruction.setRequestid(requestid);
		lwgstruction.setNodeid(nodeid);
		lwgstruction.setRowindex(rowindex);
		lwgstruction.setContractorn(contractorn);
		lwgstruction.setInsurancetype(insurancetype);
		lwgstruction.setInsurancetime(insurancetime);
		lwgstruction.setCharger(charger);
		lwgstruction.setTelephone(telephone);
		lwgstruction.setTraintime(traintime);
		lwgstruction.setCexpire(cexpire);
		lwgstruction.setEnclossure(enclossure);
		lwgstruction.setRequireman(requireman);
		lwgstruction.setCurrenttime(currenttime);
		lwgstruction.setInsurance(insurance);
		lwgstruction.setRequest(request);
		lwgstruction.setFactory(factory);
		
		
		
		// 劳务工子表
		SEConstructionp constructionp = new SEConstructionp();
		String p_requesstid= "111";
		String p_nodeid= null;
		String p_rowindex= null;
		String p_age= null;
		String p_location= null;
		String p_depart= null;
		String p_telephone= null;
		String p_homelocation= null;
		String p_relatives= null;
		String p_relativeship= null;
		String p_teltphone2= null;
		String p_sexs= null;
		String p_indates= null;
		String p_num= null;
		String p_name= "lwg1";
		String p_iden= "666777555587677777";
		String p_insurancetype= null;
		String p_validity= null;
		String p_remarks= null;
		String p_edate= null;
		String p_ifjoin= null;
		String p_reasons= null;
		constructionp.setRequesstid(p_requesstid);
		constructionp.setNodeid(p_nodeid);
		constructionp.setRowindex(p_rowindex);
		constructionp.setAge(p_age);
		constructionp.setLocation(p_location);
		constructionp.setDepart(p_depart);
		constructionp.setTelephone(p_telephone);
		constructionp.setHomelocation(p_homelocation);
		constructionp.setRelatives(p_relatives);
		constructionp.setRelativeship(p_relativeship);
		constructionp.setTeltphone2(p_teltphone2);
		constructionp.setSexs(p_sexs);
		constructionp.setIndates(p_indates);
		constructionp.setNum(p_num);
		constructionp.setName(p_name);
		constructionp.setIden(p_iden);
		constructionp.setInsurancetype(p_insurancetype);
		constructionp.setValidity(p_validity);
		constructionp.setRemarks(p_remarks);
		constructionp.setEdate(p_edate);
		constructionp.setIfjoin(p_ifjoin);
		constructionp.setReasons(p_reasons);
	
		
		LwgStructionService lwgStructionService = new LwgStructionService();
		try {
			lwgStructionService.setLwgstruction(lwgstruction);
			lwgStructionService.setConstructionp(constructionp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
