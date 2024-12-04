package com.kh.parttimeMVCProject.model;

//EMPID NUMBER, -- PK, 직원ID
//NAME VARCHAR2(20) NOT NULL,-- 직원 이름
//RAGE NUMBER NOT NULL, -- 시급
//E_DEPNAME VARCHAR2(20) NOT NULL -- FK, 부서 이름 

public class EmployeeVO {
	private int empid; // 직원id, pk, seq
	private String name; // 직원 이름 
	private int rage; // 시급
	private String e_depname; // 부서이름, fk
	
	public EmployeeVO() {
		super();
	}
	public EmployeeVO(int empid, String name, int rage, String e_depname) {
		super();
		this.empid = empid;
		this.name = name;
		this.rage = rage;
		this.e_depname = e_depname;
	}
	public EmployeeVO(String name, int rage, String e_depname) {
		super();
		this.name = name;
		this.rage = rage;
		this.e_depname = e_depname;
	}
	
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRage() {
		return rage;
	}
	public void setRage(int rage) {
		this.rage = rage;
	}
	public String getE_depname() {
		return e_depname;
	}
	public void setE_depname(String e_depname) {
		this.e_depname = e_depname;
	}
	
	@Override
	public String toString() {
		return "알바생 정보 [알바생 번호: " + empid + ", 성명: " + name + ", 시급: " + rage + "원, 부서 이름: " + e_depname + "]";
	}
}
