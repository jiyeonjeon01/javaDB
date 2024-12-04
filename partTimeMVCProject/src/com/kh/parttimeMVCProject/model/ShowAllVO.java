package com.kh.parttimeMVCProject.model;

import java.sql.Date;

public class ShowAllVO {
//	private int depid; // 부서id, pk, seq
//	private String depname; // 부서이름("주방", "홀")
	
//	private int empid; // 직원id, pk, seq
//	private String name; // 직원 이름 
//	private int rage; // 시급
//	private String e_depname; // 부서이름, fk
	
//	private int whid; // pk, 근무id
//	private Date wdate; // 근무 날짜
//	private int checkin; // 출근시간 (String으로 변경)
//	private int checkout; // 퇴근시간 (String으로 변경)
//	private int wh_empid; // fk, 직원id
//	private double hours; // 근무시간 
//	private int amount; // 임금
	
	private int depid;
	private String depname;
	private String name;
	private int rage;
	private int whid;
	private Date wdate;
	private int checkin;
	private int checkout;
	private int hours;
	private int amount;
	
	public ShowAllVO(int depid, String depname, String name, int rage, int whid, Date wdate, int checkin, int checkout,
			int hours, int amount) {
		super();
		this.depid = depid;
		this.depname = depname;
		this.name = name;
		this.rage = rage;
		this.whid = whid;
		this.wdate = wdate;
		this.checkin = checkin;
		this.checkout = checkout;
		this.hours = hours;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "ShowAllVO [depid=" + depid + ", depname=" + depname + ", name=" + name + ", rage=" + rage + ", whid="
				+ whid + ", wdate=" + wdate + ", checkin=" + checkin + ", checkout=" + checkout + ", hours=" + hours
				+ ", amount=" + amount + "]";
	}
}
