package com.lexinsmart.model;

import java.sql.Timestamp;

public class Company {
	private Integer id;
	private String requestid;
	private String company;
	private Integer ctype;
	private String remarks;
	private Timestamp created;
	private boolean forbidden;
	private Integer atfactorynum;
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
	public Integer getCtype() {
		return ctype;
	}
	public void setCtype(Integer ctype) {
		this.ctype = ctype;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public boolean isForbidden() {
		return forbidden;
	}
	public void setForbidden(boolean forbidden) {
		this.forbidden = forbidden;
	}
	public Integer getAtfactorynum() {
		return atfactorynum;
	}
	public void setAtfactorynum(Integer atfactorynum) {
		this.atfactorynum = atfactorynum;
	}
}
