package com.lexinsmart.action;

import com.lexinsmart.service.WeighService;

public class WeightAction {

	public static void main(String[] args) {
		String singleno = "1020t000274618";
		
		WeighService weighService = new WeighService();
		weighService.inWeight(singleno);
	//	weighService.outWeight(singleno);
		weighService.refreshCurrentnum();
		weighService.release();
		
		
	}
}
