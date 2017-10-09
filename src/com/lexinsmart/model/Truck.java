package com.lexinsmart.model;

import java.sql.Timestamp;

public class Truck {
	private Integer id;
	private String requestid;
	private String company;
	private String ttype;
	private String carno;
	private Float xzdw;
	private Integer atfactorynum;
	private Timestamp created;
	private Boolean forbidden;
	private Boolean privilege;
	private Timestamp intime;
	private Timestamp outtime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRequestid() {
		return requestid;
	}
	public void setRequestid(String requestid) {
		this.requestid = requestid;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getTtype() {
		return ttype;
	}
	public void setTtype(String ttype) {
		this.ttype = ttype;
	}
	public String getCarno() {
		return carno;
	}
	public void setCarno(String carno) {
		this.carno = carno;
	}
	public Float getXzdw() {
		return xzdw;
	}
	public void setXzdw(Float xzdw) {
		this.xzdw = xzdw;
	}
	public Integer getAtfactorynum() {
		return atfactorynum;
	}
	public void setAtfactorynum(Integer atfactorynum) {
		this.atfactorynum = atfactorynum;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public Boolean getForbidden() {
		return forbidden;
	}
	public void setForbidden(Boolean forbidden) {
		this.forbidden = forbidden;
	}
	public Boolean getPrivilege() {
		return privilege;
	}
	public void setPrivilege(Boolean privilege) {
		this.privilege = privilege;
	}
	public Timestamp getIntime() {
		return intime;
	}
	public void setIntime(Timestamp intime) {
		this.intime = intime;
	}
	public Timestamp getOuttime() {
		return outtime;
	}
	public void setOuttime(Timestamp outtime) {
		this.outtime = outtime;
	}
	
	
	
}
