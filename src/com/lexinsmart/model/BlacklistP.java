package com.lexinsmart.model;

import java.sql.Timestamp;

public class BlacklistP {
	private String idcard;
	private String name;
	private String mantype;
	private String company;
	private Integer isblack;
	private Timestamp blackdate;
	private String reason;
	private Timestamp plleavedate;
	private String plemployno;
	private String pldepartment;
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMantype() {
		return mantype;
	}
	public void setMantype(String mantype) {
		this.mantype = mantype;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Integer getIsblack() {
		return isblack;
	}
	public void setIsblack(Integer isblack) {
		this.isblack = isblack;
	}
	public Timestamp getBlackdate() {
		return blackdate;
	}
	public void setBlackdate(Timestamp blackdate) {
		this.blackdate = blackdate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Timestamp getPlleavedate() {
		return plleavedate;
	}
	public void setPlleavedate(Timestamp plleavedate) {
		this.plleavedate = plleavedate;
	}
	public String getPlemployno() {
		return plemployno;
	}
	public void setPlemployno(String plemployno) {
		this.plemployno = plemployno;
	}
	public String getPldepartment() {
		return pldepartment;
	}
	public void setPldepartment(String pldepartment) {
		this.pldepartment = pldepartment;
	}
	
}
