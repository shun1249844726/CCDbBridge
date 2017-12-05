package com.lexinsmart.model;

import java.sql.Timestamp;

/**
 * 车辆称重
 * @author xushun
 *
 */
public class WeightingModel {
	private String carno;
	private String singleno;
	private Timestamp weighttime;
	private int isinfactory;
	public String getCarno() {
		return carno;
	}
	public void setCarno(String carno) {
		this.carno = carno;
	}
	public String getSingleno() {
		return singleno;
	}
	public void setSingleno(String singleno) {
		this.singleno = singleno;
	}
	public Timestamp getWeighttime() {
		return weighttime;
	}
	public void setWeighttime(Timestamp weighttime) {
		this.weighttime = weighttime;
	}
	public int getIsinfactory() {
		return isinfactory;
	}
	public void setIsinfactory(int isinfactory) {
		this.isinfactory = isinfactory;
	}
	
}
