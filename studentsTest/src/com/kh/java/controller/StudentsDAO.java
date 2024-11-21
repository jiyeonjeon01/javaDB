package com.kh.java.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.java.model.StudentsVO;

public class StudentsDAO {
	public static String selectSQL  = "SELECT * FROM STUDENTS";
	public static String insertSQL = "INSERT INTO STUDENTS (ID, NAME, KOR, ENG, MATH) VALUES (STUDENTS_ID_SEQ.NEXTVAL, ?, ?, ?, ?)";
	public static String callRankProcSQL = "{call STUDENTS_RANK_PROC()}";
	public static String updateSQL = "UPDATE STUDENTS SET NAME = ?, KOR = ?, ENG = ?, MATH = ? WHERE ID = ? ";
	public static String deleteSQL = "DELETE FROM STUDENTS WHERE ID = ?";
	public static String sortSQL = "SELECT * FROM STUDENTS ORDER BY RANK";
	
	public static ArrayList<StudentsVO> totalSelect() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<StudentsVO> studentsList = new ArrayList<StudentsVO>();

		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery(selectSQL);

		while (rs.next()) {
		int id = rs.getInt("ID");
		String name = rs.getString("NAME");
		int kor = rs.getInt("KOR");
		int eng = rs.getInt("ENG");
		int math = rs.getInt("MATH");
		int sum = rs.getInt("SUM");
		int avg = rs.getInt("AVG");
		int rank = rs.getInt("RANK");

		StudentsVO stu = new StudentsVO(id, name, kor, eng, math, sum, avg, rank);
		studentsList.add(stu);
		}
		//stuListPrint(studentsList);
		DBUtility.dbClose(con, stmt, rs);

		return studentsList;
	}
		
	public static boolean studentsInsert(StudentsVO svo) throws SQLException {
		// Connection
		boolean successFlag = false;
		Connection con = null;
		CallableStatement cstmt = null;
		PreparedStatement pstmt = null;

		// 1. Load, 2. Connect
		con = DBUtility.dbCon();
		
		pstmt = con.prepareStatement(insertSQL);
		pstmt.setString(1, svo.getName());
		pstmt.setInt(2, svo.getKor());
		pstmt.setInt(3, svo.getEng());
		pstmt.setInt(4, svo.getMath());
		
		int result1 = pstmt.executeUpdate();
		System.out.println((result1 != 0 ) ? "입력 성공" : "입력 실패");
		
		cstmt = con.prepareCall(callRankProcSQL);
		int result2 = cstmt.executeUpdate();
		System.out.println((result2 != 0) ? "프로시저 성공" : "프로시저 실패");
		
		DBUtility.dbClose(con, pstmt);
		successFlag = (result1 != 0 && result2 != 0)? true : false;
		
		return successFlag;
	}
	
//	수정 전
//	public static boolean studentsUpdate(StudentsVO svo) throws SQLException {
//		boolean successFlag = false;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		con = DBUtility.dbCon();
//		pstmt = con.prepareStatement(updateSQL);
//		pstmt.setString(1, svo.getName());
//		pstmt.setInt(2, svo.getKor());
//		pstmt.setInt(3, svo.getEng());
//		pstmt.setInt(4, svo.getMath());
//		pstmt.setInt(5, svo.getId());
//		
//		int result = pstmt.executeUpdate();
//		
//		successFlag = (result != 0 ? true : false);
//		
//		DBUtility.dbClose(con, pstmt);
//		return successFlag;
//	}
	// 수정 후
	public static boolean studentsUpdate(StudentsVO svo) throws SQLException {
		boolean successFlag = false;
		Connection con = null;
		CallableStatement cstmt = null;
		PreparedStatement pstmt = null;
		
		//1. 
		con = DBUtility.dbCon();
		
		pstmt = con.prepareStatement(updateSQL);
		pstmt.setString(1, svo.getName());
		pstmt.setInt(2, svo.getKor());
		pstmt.setInt(3, svo.getEng());
		pstmt.setInt(4, svo.getMath());
		pstmt.setInt(5, svo.getId());
		
		int result1 = pstmt.executeUpdate();
		cstmt = con.prepareCall(callRankProcSQL);
		int result2 = cstmt.executeUpdate();
	
		
		successFlag = (result1 != 0 && result2 != 0 )? true : false;
		
		DBUtility.dbClose(con, pstmt, cstmt);
		return successFlag;
	}
	
	public static boolean studentsDelete(StudentsVO svo) throws SQLException {
		boolean successFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
	
		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(deleteSQL);
		pstmt.setInt(1, svo.getId());
		int result = pstmt.executeUpdate();
		
		successFlag = (result != 0) ? true : false;
		
		DBUtility.dbClose(con, pstmt);
		return successFlag;
	}
	
	public static ArrayList<StudentsVO> studentsSort() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<StudentsVO> studentsList = new ArrayList<StudentsVO>();

		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery(selectSQL);

		while (rs.next()) {
			int id = rs.getInt("ID");
			String name = rs.getString("NAME");
			int kor = rs.getInt("KOR");
			int eng = rs.getInt("ENG");
			int math = rs.getInt("MATH");
			int sum = rs.getInt("SUM");
			int avg = rs.getInt("AVG");
			int rank = rs.getInt("RANK");

			StudentsVO stu = new StudentsVO(id, name, kor, eng, math, sum, avg, rank);
			studentsList.add(stu);
		}
		DBUtility.dbClose(con, stmt, rs);
		return studentsList;
	}
	
	
}


