package com.kh.java.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.java.model.ParttimeVO;

public class ParttimeRegisterManager {
	public static Scanner sc = new Scanner(System.in);
	
	public static void totalSelectManager() throws SQLException {
		ArrayList<ParttimeVO> parttimeList = new ArrayList<ParttimeVO>();
		parttimeList = ParttimeDAO.totalSelect();
		printParttimeList(parttimeList);
	}
	
	public static void insertManager() throws SQLException {
		System.out.println("알바생 이름을 입력하세요: ");
		String name = sc.nextLine();
		System.out.println("시급을 입력하세요: ");
		int wage = Integer.parseInt(sc.nextLine());
		System.out.println("출근 시간을 입력하세요 (형식: HH:mm): ");
	    String checkInStr = sc.nextLine();
	    System.out.println("퇴근 시간을 입력하세요 (형식: HH:mm): ");
	    String checkOutStr = sc.nextLine();

	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	    Date checkIn = null;
	    Date checkOut = null;

	    try {
	        checkIn = new java.sql.Date(sdf.parse(checkInStr).getTime());
	        checkOut = new java.sql.Date(sdf.parse(checkOutStr).getTime());
	    } catch (ParseException e) {
	        System.out.println("잘못된 형식입니다. HH:mm 형식으로 입력해주세요.");
	        e.printStackTrace();
	    }
	    
	    ParttimeVO pvo = new ParttimeVO(name, wage, checkIn, checkOut);
		boolean successFlag = ParttimeDAO.parttimeInsert(pvo);
		
		if(successFlag == true) {
			System.out.println("입력 처리 성공");
		}else {
			System.out.println("입력 처리 실패");
		}
	}
	
	public static void updateManager() throws SQLException {
		System.out.println("수정할 알바생 번호를 입력하세요: ");
		int id = Integer.parseInt(sc.nextLine());
		
		System.out.println("새로운 이름을 입력하세요: ");
		String name = sc.nextLine();
		System.out.println("새로운 시급을 입력하세요: ");
		int wage = Integer.parseInt(sc.nextLine());
		System.out.println("새로운 출근 시간을 입력하세요 (형식: HH:mm): ");
	    String checkInStr = sc.nextLine();
	    System.out.println("새로운 퇴근 시간을 입력하세요 (형식: HH:mm): ");
	    String checkOutStr = sc.nextLine();

	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	    Date checkIn = null;
	    Date checkOut = null;

	    try {
	        checkIn = new java.sql.Date(sdf.parse(checkInStr).getTime());
	        checkOut = new java.sql.Date(sdf.parse(checkOutStr).getTime());
	    } catch (ParseException e) {
	        System.out.println("잘못된 형식입니다. HH:mm 형식으로 입력해주세요.");
	        e.printStackTrace();
	    }
		
		ParttimeVO pvo = new ParttimeVO(id, name, wage, checkIn, checkOut);
		ParttimeDAO.parttimeUpdate(pvo);
	}
	
	public static void deleteManager() throws SQLException {
		System.out.println("삭제할 학생 번호를 입력하세요: ");
		int id = Integer.parseInt(sc.nextLine());
		
		ParttimeVO pvo = new ParttimeVO();
		pvo.setId(id);
		
		boolean successFlag = ParttimeDAO.parttimeDelete(pvo);
		
		if(successFlag == true) {
			System.out.println("삭제처리 성공");
		}else {
			System.out.println("삭제 처리 실패");
		}
	}
	
	public static void sortManager() throws SQLException {
		ArrayList<ParttimeVO> parttimeList = null;
		parttimeList = ParttimeDAO.parttimeSort();
		printParttimeList(parttimeList);
	}
	
	public static void printParttimeList (ArrayList<ParttimeVO> parttimeList) {
		System.out.println("======================================================================================");
		for(ParttimeVO pvo : parttimeList) {
			System.out.println(pvo.toString());
		}
		System.out.println("======================================================================================");
	}

}
