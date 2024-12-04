package com.kh.parttimeMVCProject.model;

//DEPID NUMBER, -- PK, 부서ID
//DEPNAME VARCHAR2(20) NOT NULL -- 부서 이름 

public class DepartmentVO {
	private int depid; // 부서id, pk, seq
	private String depname; // 부서이름("주방", "홀")
	
	public DepartmentVO() {
		super();
	}
	public DepartmentVO(int depid, String depname) {
		super();
		this.depid = depid;
		this.depname = depname;
	}
	public DepartmentVO(String depname) {
		super();
		this.depname = depname;
	}
	
	public int getDepid() {
		return depid;
	}
	public void setDepid(int depid) {
		this.depid = depid;
	}
	public String getDepname() {
		return depname;
	}
	public void setDepname(String depname) {
		this.depname = depname;
	}
	@Override
	public String toString() {
		return "부서 정보 [부서 번호: " + depid + ", 부서 이름: " + depname + "]";
	}
}
