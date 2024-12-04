package com.kh.parttimeMVCProject.model;

import java.sql.Date;
import java.sql.Timestamp;

//WHID NUMBER, --PK, 근무ID 
//WDATE DATE  NOT NULL, -- 근무 날짜
//CHECKIN TIMESTAMP NOT NULL, --  출근 시간 
//CHECKOUT TIMESTAMP NOT NULL, -- 퇴근 시간
//WH_EMPID NUMBER NOT NULL, -- FK, 직원ID
//HOURS NUMBER(2, 1) NOT NULL, -- 퇴근시간-출근시간(일한시간)
//AMOUNT NUMBER NOT NULL -- 임금(시급*일한시간)

public class WorkhourVO {
	private int whid; // pk, 근무id
	private Date wdate; // 근무 날짜
	private int checkin; // 출근시간 (String으로 변경)
	private int checkout; // 퇴근시간 (String으로 변경)
	private int wh_empid; // fk, 직원id
	private int hours; // 근무시간 
	private int amount; // 임금
	
	public WorkhourVO() {
		super();
	}
	public WorkhourVO(int checkin, int checkout) {
		super();
		this.checkin = checkin;
		this.checkout = checkout;
	}
	public WorkhourVO(int checkin, int checkout, int wh_empid) {
		super();
		this.checkin = checkin;
		this.checkout = checkout;
		this.wh_empid = wh_empid;
	}
	public WorkhourVO(Date wdate, int checkin,int checkout, int wh_empid) {
		super();
		this.wdate = wdate;
		this.checkin = checkin;
		this.checkout = checkout;
		this.wh_empid = wh_empid;
	}
	public WorkhourVO(int whid, Date wdate, int checkin, int checkout, int wh_empid) {
		super();
		this.whid = whid;
		this.wdate = wdate;
		this.checkin = checkin;
		this.checkout = checkout;
		this.wh_empid = wh_empid;
	}
	public WorkhourVO(Date wdate, int checkin, int checkout, int wh_empid, int hours, int amount) {
		super();
		this.wdate = wdate;
		this.checkin = checkin;
		this.checkout = checkout;
		this.wh_empid = wh_empid;
		this.hours = hours;
		this.amount = amount;
	}
	public WorkhourVO(int whid, Date wdate, int checkin, int checkout, int wh_empid, int hours,
			int amount) {
		super();
		this.whid = whid;
		this.wdate = wdate;
		this.checkin = checkin;
		this.checkout = checkout;
		this.wh_empid = wh_empid;
		this.hours = hours;
		this.amount = amount;
	}
	
	public int getWhid() {
		return whid;
	}
	public void setWhid(int whid) {
		this.whid = whid;
	}
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}
	public int  getCheckin() {
		return checkin;
	}
	public void setCheckin(int checkin) {
		this.checkin = checkin;
	}
	public int getCheckout() {
		return checkout;
	}
	public void setCheckout(int checkout) {
		this.checkout = checkout;
	}
	public int getWh_empid() {
		return wh_empid;
	}
	public void setWh_empid(int wh_empid) {
		this.wh_empid = wh_empid;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "근무시간 정보 [근무시간 번호: " + whid + ", 근무일: " + wdate + ", 출근시간: " + checkin + "시, 퇴근시간: " + checkout
				+ "시, 알바생 번호: " + wh_empid + ", 일한 시간: " + hours + "시간, 하루 임금: " + amount + "원]";
	}
	
}
