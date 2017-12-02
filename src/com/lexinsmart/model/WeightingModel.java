package com.lexinsmart.model;

import java.sql.Timestamp;

public class WeightingModel {
	private String carno;
	private String singleno;
	private Timestamp weighttime;
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
	
}
