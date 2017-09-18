package com.lexinsmart.model;

import java.sql.Timestamp;

public class Plantime {
	private Integer id;
	private Integer cid;
	private Integer sid;
	private Integer tid;

	private String changer;
	private String telephone;
	private Timestamp planintime;
	private Timestamp planouttime;
	private String requesid;
	
	public String getRequesid() {
		return requesid;
	}
	public void setRequesid(String requesid) {
		this.requesid = requesid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getChanger() {
		return changer;
	}
	public void setChanger(String changer) {
		this.changer = changer;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Timestamp getPlanintime() {
		return planintime;
	}
	public void setPlanintime(Timestamp planintime) {
		this.planintime = planintime;
	}
	public Timestamp getPlanouttime() {
		return planouttime;
	}
	public void setPlanouttime(Timestamp planouttime) {
		this.planouttime = planouttime;
	}
	
	

}
