package com.lexinsmart.action;

import com.lexinsmart.model.OABlacklist;
import com.lexinsmart.model.OAVehicle;
import com.lexinsmart.service.BlackListService;

public class BlackListAction {
	static OABlacklist oa_blacklist_p = null;
	static OAVehicle vehicle = null;

	public static void main(String[] args) {

		// 人员黑名单。
		String idcard = "411328199309155010";
		String name = "黑1";
		String mantype = "40285a8d55c3ffed0155e226f71356b1";
		String company = "glrsmart";
		String isblack = "40288098276fc2120127704884290210";
		String blackdate = "2017-6-7";
		String reason = "盗窃财物";
		String plleavedate = "2018-8-17";
		String plemployno = "12121212";
		String pldepartment = "dep";

		oa_blacklist_p = new OABlacklist();// 人员黑名单
		oa_blacklist_p.setIdcard(idcard);
		oa_blacklist_p.setName(name);
		oa_blacklist_p.setMantype(mantype);
		oa_blacklist_p.setCompany(company);
		oa_blacklist_p.setIsblack(isblack);
		oa_blacklist_p.setBlackdate(blackdate);
		oa_blacklist_p.setReason(reason);
		oa_blacklist_p.setPlleavedate(plleavedate);
		oa_blacklist_p.setPlemployno(plemployno);
		oa_blacklist_p.setPldepartment(pldepartment);

		String requestid = "999";
		String nodeid = null;
		String rowindex = null;
		String autono = "闽J19718";
		String state = "402864d149df48ff0149df70dc7b0007";

		vehicle = new OAVehicle();
		vehicle.setRequestid(requestid);
		vehicle.setNodeid(nodeid);
		vehicle.setRowindex(rowindex);
		vehicle.setAutono(autono);
		vehicle.setState(state);

		BlackListAction ba = new BlackListAction();

		for (int i = 0; i < 200; i++) {
			// MyThread thread = ba.new MyThread();
			// thread.start();
			// System.out.println(i);
			BlackListService blackListService = new BlackListService();
			blackListService.addBlackListP(oa_blacklist_p);

			BlackListService CblackListService = new BlackListService();
			CblackListService.addBlackListC(vehicle);
		}

	}

	class MyThread extends Thread {
		@Override
		public void run() {
			BlackListService blackListService = new BlackListService();
			blackListService.addBlackListP(oa_blacklist_p);

			BlackListService CblackListService = new BlackListService();
			CblackListService.addBlackListC(vehicle);
		}
	}
}
