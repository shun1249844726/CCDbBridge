package com.lexinsmart.action;

import com.lexinsmart.model.MajorCountModel;
import com.lexinsmart.service.CurrentCountService;

public class MajorCountAction {
	public static void main(String[] args) {
		
		MajorCountModel majorCountModel = new MajorCountModel();
		CurrentCountService  countService = new CurrentCountService();
		majorCountModel = countService.getMajorCount();
		
		System.out.println("厂内员工："+majorCountModel.getStaffNumber());
		System.out.println("劳务人员："+majorCountModel.getLwgNumber());
		System.out.println("访客人员："+majorCountModel.getRegistrationNumber());
		System.out.println("承包商："+majorCountModel.getConstructionNumber());
		System.out.println("货车数量："+majorCountModel.getFreightCarNumber());
		System.out.println("货运人员："+majorCountModel.getFreightstaffNumber());

	}
}
