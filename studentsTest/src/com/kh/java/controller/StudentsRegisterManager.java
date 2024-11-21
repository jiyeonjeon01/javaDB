package com.kh.java.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.java.model.StudentsVO;

public class StudentsRegisterManager {
	public static Scanner sc = new Scanner(System.in);
	// 전체 학생 리스트 출력 기능 
	public static void totalSelectManager() throws SQLException{
		ArrayList<StudentsVO> studentsList = new ArrayList<StudentsVO>();
		studentsList = StudentsDAO.totalSelect();
		printStudentsList(studentsList);
	}	
	
	public static void insertManager() throws SQLException {
		// 3. statement
		System.out.println("학생 이름을 입력하세요: ");
		String name = sc.nextLine();
		System.out.println("국어 점수를 입력하세요: ");
		int kor = Integer.parseInt(sc.nextLine());
		System.out.println("영어 점수를 입력하세요: ");
		int eng = Integer.parseInt(sc.nextLine());
		System.out.println("수학 점수를 입력하세요: ");
		int math = Integer.parseInt(sc.nextLine());
		
		StudentsVO studentsVo = new StudentsVO(name, kor, eng, math);
		boolean successFlag = StudentsDAO.studentsInsert(studentsVo);
		
		if(successFlag == true) {
			System.out.println("입력 처리 성공");
		}else {
			System.out.println("입력 처리 실패");
		}
		
	}
	// 전체 학생 리스트를 출력 진행
	
	public static void updateManager() throws SQLException {
		System.out.println("수정할 학생의 번호를 입력하세요: ");
		int id = Integer.parseInt(sc.nextLine());
		System.out.println("새로운 이름을 입력하세요: ");
		String name = sc.nextLine();
		System.out.println("새로운 국어 점수를 입력하세요: ");
		int kor = Integer.parseInt(sc.nextLine());
		System.out.println("새로운 영어 점수를 입력하세요: ");
		int eng = Integer.parseInt(sc.nextLine());
		System.out.println("새로운 수학 점수를 입력하세요: ");
		int math = Integer.parseInt(sc.nextLine());
		
		StudentsVO svo = new StudentsVO(id, name, kor, eng, math);
		StudentsDAO.studentsUpdate(svo);
	}
	
	
	
	public static void deleteManager() throws SQLException {
		System.out.println("삭제할 학생 번호를 입력하세요: ");
		int id = Integer.parseInt(sc.nextLine());
		
		StudentsVO svo = new StudentsVO();
		svo.setId(id);
		
		boolean successFlag = StudentsDAO.studentsDelete(svo);
		
		if(successFlag == true) {
			System.out.println("삭제처리 성공");
		}else {
			System.out.println("삭제처리 실패");
		}
	}
	
	// 정렬
	public static void sortManager() throws SQLException {
		ArrayList<StudentsVO> studentsList = null;
		studentsList = StudentsDAO.studentsSort();
		printStudentsList(studentsList);
	}
	
	
	// 전체 학생 리스트를 출력 진행 
	public static void printStudentsList (ArrayList<StudentsVO> studentsList) {
		System.out.println("======================================================================================");
		for(StudentsVO sv : studentsList) {
			System.out.println(sv.toString());
		}
		System.out.println("======================================================================================");
	}



}
