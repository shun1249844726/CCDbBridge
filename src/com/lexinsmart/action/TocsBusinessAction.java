package com.lexinsmart.action;

import com.lexinsmart.model.OATocsbusiness;
import com.lexinsmart.service.ToBusinessService;

public class TocsBusinessAction {

	public static void main(String[] args) {
		  String requestid= null;
		  String nodeid= null;
		  String rowindex= null;
		  String applier= null;
		  String applydate= null;
		  String applydeapry= null;
		  String company= null;
		  String ccentre= null;
		  String factory= null;//厂区别 常熟厂
		  String telphone= null;
		  String tradepart= "出差人员单位";//出差人员单位
		  String sex= "男";//性别
		  String age= "40";//年龄
		  String loaction= "江苏苏州";//籍贯
		  String iden= "433546198300909080";//证件号
		  String tel= "15976867687";//联系电话
		  String famlocation= "常熟市长春路1号";//家庭住址
		  String relationname= "亲属";//主要亲属姓名
		  String relaction= "父子";//亲属关系 
		  String relactiontel= "110110";//亲属联系电话
		  String startdate= "2017-10-09";//出差开始日期        格式为:yyyy-MM-dd  格式必须控制！
		  String starttime= "11:49:45";//出差开始时间    格式必须控制！
		  String enddate= "2017-11-09";//结束日期  格式必须控制
		  String endtime= "11:49:45";//结束时间	格式必须控制
		  String busday= "30";//出差天数
		  String reason= "设备维护";//事由
		  String shuttlebus= null;
		  String cardnum= "13141516";//卡号，必填！不能为空！
		  String floor= null;
		  String roomnum= null;
		  String otherreason= null;
		  String tranname= "出差人";//出差人员姓名
		  String tradep= "出差部门";//出差部门
		  String tratype= "供应商";//出差人员类型
		  //类型 ： 客户	供应商	政府官员	关联公司
		  String accom= null;
		  String comaccom= null;
		  String place= null;
		 
		  
		  OATocsbusiness oaTocsbusiness = new OATocsbusiness();
		  oaTocsbusiness.setRequestid(requestid);
		  oaTocsbusiness.setNodeid(nodeid);
		  oaTocsbusiness.setRowindex(rowindex);
		  oaTocsbusiness.setApplier(applier);
		  oaTocsbusiness.setApplydate(applydate);
		  oaTocsbusiness.setApplydeapry(applydeapry);
		  oaTocsbusiness.setCompany(company);
		  oaTocsbusiness.setCcentre(ccentre);
		  oaTocsbusiness.setFactory(factory);
		  oaTocsbusiness.setTelphone(telphone);
		  oaTocsbusiness.setTradepart(tradepart);
		  oaTocsbusiness.setSex(sex);
		  oaTocsbusiness.setAge(age);
		  oaTocsbusiness.setLoaction(loaction);
		  oaTocsbusiness.setIden(iden);
		  oaTocsbusiness.setTel(tel);
		  oaTocsbusiness.setFamlocation(famlocation);
		  oaTocsbusiness.setRelationname(relationname);
		  oaTocsbusiness.setRelaction(relaction);
		  oaTocsbusiness.setRelactiontel(relactiontel);
		  oaTocsbusiness.setStartdate(startdate);;
		  oaTocsbusiness.setStarttime(starttime);
		  oaTocsbusiness.setEnddate(enddate);
		  oaTocsbusiness.setEndtime(endtime);
		  oaTocsbusiness.setBusday(busday);
		  oaTocsbusiness.setReason(otherreason);
		  oaTocsbusiness.setShuttlebus(shuttlebus);
		  oaTocsbusiness.setCardnum(cardnum);
		  oaTocsbusiness.setFloor(floor);
		  oaTocsbusiness.setRoomnum(roomnum);
		  oaTocsbusiness.setOtherreason(otherreason);
		  oaTocsbusiness.setTranname(tranname);
		  oaTocsbusiness.setTradep(tradep);
		  oaTocsbusiness.setTratype(tratype);
		  oaTocsbusiness.setAccom(comaccom);
		  oaTocsbusiness.setPlace(place);
		  
		  
		  ToBusinessService businessService = new ToBusinessService();
		  businessService.setToBusinessService(oaTocsbusiness);
		  
	}

}
