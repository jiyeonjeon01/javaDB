package com.kh.java.model;

import java.sql.Date;
import java.text.SimpleDateFormat;

//ID NUMBER(4), -- 알바생 아이디
//NAME VARCHAR2(10) NOT NULL, -- 알바생 이름
//WAGE NUMBER(5)NOT NULL, -- 시급
//CHECKIN DATE NOT NULL, -- 출근 시간
//CHECKOUT DATE NOT NULL, -- 퇴근 시간
//HOURS NUMBER(2, 1), -- 하루동안 일한 시간
//PAY NUMBER(6), -- 하루에 받는 임금
//RANK NUMBER(4) 
public class ParttimeVO {
	private int id; // pk, 알바생 아이디 
	private String name; // 알바생 이름
	private int wage; // 시급
	private Date checkIn; // 출근시간
	private Date checkOut; // 퇴근시간
	private double hours; // 총 근무시간
	private int pay; // 하루에 받을 임금
	private int rank; // 순위
	
	public ParttimeVO () {
		super();
	}

	public ParttimeVO(int id, String name, int wage, Date checkIn, Date checkOut, double hours, int pay, int rank) {
		super();
		this.id = id;
		this.name = name;
		this.wage = wage;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.hours = hours;
		this.pay = pay;
		this.rank = rank;
	}

	public ParttimeVO(int id, String name, int wage, Date checkIn, Date checkOut) {
		super();
		this.id = id;
		this.name = name;
		this.wage = wage;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public ParttimeVO(String name, int wage, Date checkIn, Date checkOut) {
		super();
		this.name = name;
		this.wage = wage;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWage() {
		return wage;
	}

	public void setWage(int wage) {
		this.wage = wage;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	public double getHours() {
		return hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
	    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

	    String formattedCheckIn = (checkIn != null) ? timeFormat.format(checkIn) : "N/A";
	    String formattedCheckOut = (checkOut != null) ? timeFormat.format(checkOut) : "N/A";

	    return String.format(
	        "알바생 정보 [id=%d, name=%s, wage=%d, checkIn=%s, checkOut=%s, hours=%.1f, pay=%d, rank=%d]",
	        id, name, wage, formattedCheckIn, formattedCheckOut, hours, pay, rank
	    );
	}
}
