package com.lexinsmart.action;

import com.lexinsmart.service.WeighService;
/**
 * 称重管理
 * @author xushun
 *
 */
public class WeightAction {

	public static void main(String[] args) {
		String singleno = "10020t333"; //提入单号，
		
		WeighService weighService = new WeighService();
		weighService.inWeight(singleno);//  入厂称重调用这个方法，
	//	weighService.outWeight(singleno);// 出厂称重调用该方法，注：不能进出同时调用，与上一个函数同时只能使用一个
		weighService.refreshCurrentnum();//刷新实时在厂的车辆数。
		weighService.release();
		
	}
}
