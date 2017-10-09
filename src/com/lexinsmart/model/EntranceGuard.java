package com.lexinsmart.model;

public class EntranceGuard {
	private Integer id;
	private String name;
	private String deviceid;
	private Integer type;
	private Integer direction;
	private boolean onlinestatus;
	private String remarks;
	private boolean privilege;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getDirection() {
		return direction;
	}
	public void setDirection(Integer direction) {
		this.direction = direction;
	}
	public boolean isOnlinestatus() {
		return onlinestatus;
	}
	public void setOnlinestatus(boolean onlinestatus) {
		this.onlinestatus = onlinestatus;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public boolean isPrivilege() {
		return privilege;
	}
	public void setPrivilege(boolean privilege) {
		this.privilege = privilege;
	}
	
}
