package com.kh.parttimeMVCProject.model;

import java.sql.Date;

//HISTORYID NUMBER, -- PK, 지급 기록 ID
//H_EMPID NUMBER NOT NULL,  -- FK, 직원 ID
//H_WHID NUMBER NOT NULL,  -- FK, 근무시간 ID
//STATUS VARCHAR2(10) NOT NULL,  -- 지급 상태 (완료, 미완료 등)
//PAY_DATE DATE,   -- 지급 날짜
//METHOD VARCHAR2(20)  -- 지급 방식 (현금, 계좌 이체 등)

public class HistoryVO {
	private int historyid; // pk, 기록id
	private int h_empid; // fk, 직원id
	private int h_wdid; // fk, 근무시간id
	private String status; // 지급상태 (check: 완료, 미완료)
	private Date pay_date; // 지급 날짜
	private String method; // 지급 방식 
	
	public HistoryVO() {
		super();
	}
	public HistoryVO(int h_empid, int h_wdid, String status) {
		super();
		this.h_empid = h_empid;
		this.h_wdid = h_wdid;
		this.status = status;
	}
	public HistoryVO(int historyid, int h_empid, int h_wdid, String status) {
		super();
		this.historyid = historyid;
		this.h_empid = h_empid;
		this.h_wdid = h_wdid;
		this.status = status;
	}
	public HistoryVO(int h_empid, int h_wdid, String status, Date pay_date, String method) {
		super();
		this.h_empid = h_empid;
		this.h_wdid = h_wdid;
		this.status = status;
		this.pay_date = pay_date;
		this.method = method;
	}
	public HistoryVO(int historyid, int h_empid, int h_wdid, String status, Date pay_date, String method) {
		super();
		this.historyid = historyid;
		this.h_empid = h_empid;
		this.h_wdid = h_wdid;
		this.status = status;
		this.pay_date = pay_date;
		this.method = method;
	}
	
	public int getHistoryid() {
		return historyid;
	}
	public void setHistoryid(int historyid) {
		this.historyid = historyid;
	}
	public int getH_empid() {
		return h_empid;
	}
	public void setH_empid(int h_empid) {
		this.h_empid = h_empid;
	}
	public int getH_wdid() {
		return h_wdid;
	}
	public void setH_wdid(int h_wdid) {
		this.h_wdid = h_wdid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getPay_date() {
		return pay_date;
	}
	public void setPay_date(Date pay_date) {
		this.pay_date = pay_date;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	@Override
	public String toString() {
		return "HistoryVO [historyid=" + historyid + ", h_empid=" + h_empid + ", h_wdid=" + h_wdid + ", status="
				+ status + ", pay_date=" + pay_date + ", method=" + method + "]";
	}
}
