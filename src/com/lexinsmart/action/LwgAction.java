package com.lexinsmart.action;

import java.sql.SQLException;

import com.lexinsmart.model.SEConstructionp;
import com.lexinsmart.model.SELwgstruction;
import com.lexinsmart.service.LwgStructionService;

/**
 * 劳务工测试类
 * @author xushun
 *
 */
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
		String p_age= null;//年龄
		String p_location= null;//籍贯
		String p_depart= "40285a905d72f4e3015dc07b9ac64381";//所属部门     
		String p_telephone= null;//联系电话
		String p_homelocation= null;//家庭住址
		String p_relatives= null;//亲属名字
		String p_relativeship= null;//亲属关系
		String p_teltphone2= null;//亲属联系电话
		String p_sexs= null;//性别
		String p_indates= "2018-09-27";//入厂 有效期。。。。必填
		String p_num= null;//序号
		String p_name= "lwg2";//姓名
		String p_iden= "6667775555876777772";//身份证号
		String p_insurancetype= null;
		String p_validity= null;
		String p_remarks= null;//备注
		String p_edate= null;
		String p_ifjoin= null;//是否加入黑名单
		String p_reasons= null;//加入黑名单原因
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
			lwgStructionService.setLwgstruction(lwgstruction);// 设置劳务工主表
			lwgStructionService.setConstructionp(constructionp);//设置劳务工子表
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
