package com.kh.java.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.java.model.ParttimeVO;

public class ParttimeDAO {
	public static String selectSQL = "SELECT * FROM PARTTIME";
	public static String insertSQL = "INSERT INTO PARTTIME(ID, NAME, WAGE, CHECKIN, CHECKOUT) VALUES (PARTTIME_ID_SEQ.NEXTVAL, ?, ?, ?, ?)";
	public static String callRankProcSQL = "{call PARTTIME_RANK_PROC()}";
	public static String updateSQL = "UPDATE PARTTIME SET NAME = ?, WAGE = ?, CHECKIN = ?, CHECKOUT = ? WHERE ID = ?";
	public static String deleteSQL = "DELETE FROM PARTTIME WHERE ID = ?";
	public static String sortSQL = "SELECT * FROM PARTTIME ORDER BY RANK";
	
	public static ArrayList<ParttimeVO> totalSelect() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<ParttimeVO> parttimeList = new ArrayList<ParttimeVO>();
		
		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery(selectSQL);
		
		while(rs.next()) {
			int id = rs.getInt("ID");
			String name = rs.getString("NAME");
			int wage = rs.getInt("WAGE");
			Date checkIn = rs.getDate("CHECKIN");
			Date checkOut = rs.getDate("CHECKOUT");
			int hours = rs.getInt("HOURS");
			int pay = rs.getInt("PAY");
			int rank = rs.getInt("RANK");
			
			ParttimeVO pt = new ParttimeVO(id, name, wage, checkIn, checkOut, hours, pay, rank);
			parttimeList.add(pt);
		}
		DBUtility.dbClose(con, stmt, rs);
		
		return parttimeList;
	}
	
//	public static boolean parttimeInsert(ParttimeVO pvo) throws SQLException {
//		// Connection
//		boolean successFlag = false;
//		Connection con = null;
//		CallableStatement cstmt = null;
//		PreparedStatement pstmt = null;
//		
//		// 1. load, 2. connect
//		con = DBUtility.dbCon();
//		
//		pstmt = con.prepareStatement(insertSQL);
//		pstmt.setString(1, pvo.getName());
//		pstmt.setInt(2, pvo.getWage());
//		pstmt.setDate(3, pvo.getCheckIn());
//		pstmt.setDate(4, pvo.getCheckOut());
//		
//		int result1 = pstmt.executeUpdate();
//		System.out.println((result1 != 0 ) ? "입력 성공" : "입력 실패");
//		
//		cstmt = con.prepareCall(callRankProcSQL);
//		int result2 = cstmt.executeUpdate();
//		System.out.println((result2 != 0) ? "프로시저 성공" : "프로시저 실패");
//		
//		DBUtility.dbClose(con, pstmt);
//		successFlag = (result1 != 0 && result2 != 0) ? true : false;
//		
//		return successFlag;
//	}
	
	public static boolean parttimeInsert(ParttimeVO pvo) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean successFlag = false;

        try {
            con = DBUtility.dbCon();
            pstmt = con.prepareStatement(insertSQL);

            pstmt.setString(1, pvo.getName());
            pstmt.setInt(2, pvo.getWage());
            pstmt.setDate(3, pvo.getCheckIn());
            pstmt.setDate(4, pvo.getCheckOut());

            int result = pstmt.executeUpdate();
            successFlag = (result != 0);

            System.out.println(successFlag ? "알바생 등록 성공!" : "알바생 등록 실패!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtility.dbClose(con, pstmt);
        }

        return successFlag;
    }
	
	public static boolean parttimeUpdate(ParttimeVO pvo) throws SQLException {
		boolean successFlag = false;
		Connection con = null;
		CallableStatement cstmt = null;
		PreparedStatement pstmt = null;
		
		// 1. 
		con = DBUtility.dbCon();
		
		pstmt = con.prepareStatement(updateSQL);
		pstmt.setString(1, pvo.getName());
		pstmt.setInt(2, pvo.getWage());
		pstmt.setDate(3, pvo.getCheckIn());
		pstmt.setDate(4, pvo.getCheckOut());
		pstmt.setInt(5, pvo.getId());
		
		int result1 = pstmt.executeUpdate();
		cstmt = con.prepareCall(callRankProcSQL);
		int result2 = cstmt.executeUpdate();
		
		DBUtility.dbClose(con, pstmt);
		return successFlag;
	}
	
	public static boolean parttimeDelete(ParttimeVO pvo) throws SQLException {
		boolean successFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(deleteSQL);
		pstmt.setInt(1, pvo.getId());
		int result = pstmt.executeUpdate();
		
		successFlag = (result != 0) ? true : false;
		
		DBUtility.dbClose(con, pstmt);
		return successFlag;
	}
	
	public static ArrayList<ParttimeVO> parttimeSort() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		ArrayList<ParttimeVO> parttimeList = new ArrayList<ParttimeVO>();
	
		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT * FROM PARTTIME ORDER BY PAY DESC");
		
		while(rs.next()) {
			int id = rs.getInt("ID");
			String name = rs.getString("NAME");
			int wage = rs.getInt("WAGE");
			Date checkIn = rs.getDate("CHECKIN");
			Date checkOut = rs.getDate("CHECKOUT");
			int hours = rs.getInt("HOURS");
			int pay = rs.getInt("PAY");
			int rank = rs.getInt("RANK");
			
			ParttimeVO pt = new ParttimeVO(id, name, wage, checkIn, checkOut, hours, pay, rank);
			parttimeList.add(pt);
		}
		
		DBUtility.dbClose(con,  stmt, rs);
		return parttimeList;
	}
	
	
	
}
