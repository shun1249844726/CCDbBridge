package com.lexinsmart.action;

import java.sql.Timestamp;

import com.lexinsmart.model.WeightingModel;

public class WeightAction {

	public static void main(String[] args) {
		String carno = "";
		String singleno = "";
		Timestamp weightime =new Timestamp(System.currentTimeMillis());
		WeightingModel weightingModel = new WeightingModel();
		weightingModel.setCarno(carno);
		
	}
}
