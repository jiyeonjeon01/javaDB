package com.kh.parttimeMVCProject.model;

import java.sql.Date;

public class EmployeeAllVO {
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
	
	private int empid;
	private String name;
	private String depname;
	private int rage;
	
	public EmployeeAllVO(int empid, String name, String depname, int rage) {
		super();
		this.empid = empid;
		this.name = name;
		this.depname = depname;
		this.rage = rage;
	}

	@Override
	public String toString() {
		return "EmployeeAllVO [empid=" + empid + ", name=" + name + ", depname=" + depname + ", rage=" + rage + "]";
	}
}
