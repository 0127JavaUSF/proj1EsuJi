package com.revature.util;

import java.sql.Timestamp;

public class EmployeeResponse {
	private int reimb_id;
	private int reimb_amount;
	private String reimb_status;
	private String reimb_desc;
	private Timestamp reimb_Sub_Time;
	private Timestamp reimb_App_Time;
	private String rimb_rec;
	private String reimb_expType;
	
	public EmployeeResponse(int id, int amount, String status, String expType, String desc, Timestamp subTime, Timestamp appTime, String rimbURL) {
		reimb_id = id;
		reimb_amount = amount;
		reimb_status = status;
		reimb_desc = desc;
		reimb_Sub_Time = subTime;
		reimb_App_Time = appTime;
		rimb_rec = rimbURL;
		reimb_expType = expType;
	}

	public String getReimb_expType() {
		return reimb_expType;
	}

	public void setReimb_expType(String reimb_expType) {
		this.reimb_expType = reimb_expType;
	}

	public int getReimb_id() {
		return reimb_id;
	}

	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}

	public int getReimb_amount() {
		return reimb_amount;
	}

	public void setReimb_amount(int reimb_amount) {
		this.reimb_amount = reimb_amount;
	}

	public String getReimb_status() {
		return reimb_status;
	}

	public void setReimb_status(String reimb_status) {
		this.reimb_status = reimb_status;
	}

	public String getReimb_desc() {
		return reimb_desc;
	}

	public void setReimb_desc(String reimb_desc) {
		this.reimb_desc = reimb_desc;
	}

	public Timestamp getReimb_Sub_Time() {
		return reimb_Sub_Time;
	}

	public void setReimb_Sub_Time(Timestamp reimb_Sub_Time) {
		this.reimb_Sub_Time = reimb_Sub_Time;
	}

	public String getRimb_rec() {
		return rimb_rec;
	}

	public void setRimb_rec(String rimb_rec) {
		this.rimb_rec = rimb_rec;
	}

	public Timestamp getReimb_App_Time() {
		return reimb_App_Time;
	}

	public void setReimb_App_Time(Timestamp reimb_App_Time) {
		this.reimb_App_Time = reimb_App_Time;
	}
}


