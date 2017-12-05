package com.lexinsmart.model;

import java.sql.Timestamp;
/**
 * 提入单号
 * @author xushun
 *
 */
public class SingleNumberOrder {
	private String carno;
	private float hight;
	private String singleno;
	private int weightimes;
	private int orderno;
	private String depart;
	private Timestamp createtime;
	private String ttype;

	public String getCarno() {
		return carno;
	}

	public void setCarno(String carno) {
		this.carno = carno;
	}

	public float getHight() {
		return hight;
	}

	public void setHight(float hight) {
		this.hight = hight;
	}

	public String getSingleno() {
		return singleno;
	}

	public void setSingleno(String singleno) {
		this.singleno = singleno;
	}

	public int getWeightimes() {
		return weightimes;
	}

	public void setWeightimes(int weightimes) {
		this.weightimes = weightimes;
	}

	public int getOrderno() {
		return orderno;
	}

	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getTtype() {
		return ttype;
	}

	public void setTtype(String ttype) {
		this.ttype = ttype;
	}

}
