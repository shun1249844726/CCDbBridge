package com.lexinsmart.model;

import java.sql.Timestamp;

public class Employee {
	private String name;
	private String cardno;
	private Timestamp checkintime;
	private Timestamp checkouttime;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public Timestamp getCheckintime() {
		return checkintime;
	}
	public void setCheckintime(Timestamp checkintime) {
		this.checkintime = checkintime;
	}
	public Timestamp getCheckouttime() {
		return checkouttime;
	}
	public void setCheckouttime(Timestamp checkouttime) {
		this.checkouttime = checkouttime;
	}

}
