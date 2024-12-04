package com.kh.parttimeMVCProject.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.kh.parttimeMVCProject.model.HistoryVO;

//HISTORYID NUMBER, -- PK, 지급 기록 ID
//H_EMPID NUMBER NOT NULL,  -- FK, 직원 ID
//H_WHID NUMBER NOT NULL,  -- FK, 근무시간 ID
//STATUS VARCHAR2(10) NOT NULL,  -- 지급 상태 (완료, 미완료 등)
//PAY_DATE DATE,   -- 지급 날짜
//METHOD VARCHAR2(20)  -- 지급 방식 (현금, 계좌 이체 등)

//private int historyid; // pk, 기록id
//private int h_empid; // fk, 직원id
//private int h_wdid; // fk, 근무시간id
//private String status; // 지급상태 (check: 완료, 미완료)
//private Date pay_date; // 지급 날짜
//private String method; // 지급 방식 
//1.지급내역정보 보여주기  2.지급내역정보 추가하기  3.지급내역정보 수정하기  4.지급내역정보 삭제하기  5.메인메뉴로 나가기

public class HistoryDAO {

	public static final String HISTORY_SELECT = "SELECT * FROM HISTORY";
	public static final String HISTORY_INSERT = "INSERT INTO HISTORY VALUES(WORKHOUR_SEQ.NEXTVAL, ?, ?, ?, sysdate, ?)";
	public static final String HISTORY_UPDATE = "UPDATE HISTORY SET STATUS = ?, PAY_DATE = ? METHOD = ? WHERE HISTORYID = ?";
	public static final String HISTORY_DELETE = "DELETE FROM HISTORY WHERE HISTORYID = ?";
	
	// select
	public ArrayList<HistoryVO> historySelect() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		ArrayList<HistoryVO> historyList = new ArrayList<HistoryVO>();
		
		con = DBUtility.dbCon();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(HISTORY_SELECT);
			
			if(rs.next()) {
				do {
					int historyid = rs.getInt("HISTIRYID");
					int h_empid = rs.getInt("H_EMPID");
					int h_wdid = rs.getInt("H_WdID");
					String status = rs.getString("STATUS");
					Date pay_date = rs.getDate("PAY_DATE");
					String method = rs.getString("METHOD");
					
					HistoryVO hvo = new HistoryVO(historyid, h_empid, h_wdid, status, pay_date, method);
					historyList.add(hvo);
				} while (rs.next());
			}else {
				historyList = null;
			}
		} catch (SQLException e){
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, stmt, rs);
		}
		
		return historyList;
	}
	
	// insert
	public boolean historyInsert(HistoryVO hvo) {
		// Connection
		boolean successFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		con = DBUtility.dbCon();
		try {
			pstmt = con.prepareStatement(HISTORY_INSERT);
			pstmt.setInt(1, hvo.getHistoryid());
			pstmt.setInt(2, hvo.getH_empid());
			pstmt.setInt(3, hvo.getH_wdid());
			pstmt.setString(4, hvo.getStatus());
			pstmt.setDate(5, hvo.getPay_date());
			pstmt.setString(6, hvo.getMethod());
			
			int result = pstmt.executeUpdate();
			
			successFlag = (result != 0) ? true : false;
		} catch (SQLException e){
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		
		return successFlag;
	}
	
	// update
	public static boolean historyUpdate(HistoryVO hvo) throws SQLException {
		boolean successFlag = false; 
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(HISTORY_UPDATE);
		pstmt.setInt(1, hvo.getHistoryid());
		pstmt.setInt(2, hvo.getH_empid());
		pstmt.setInt(3, hvo.getH_wdid());
		pstmt.setString(4, hvo.getStatus());
		pstmt.setDate(5, hvo.getPay_date());
		pstmt.setString(6, hvo.getMethod());
		
		int result = pstmt.executeUpdate();
		successFlag = (result != 0 ) ? true : false;
		DBUtility.dbClose(con, pstmt);
		return successFlag; 
	}
	
	// delete
	public static boolean historyDelete(HistoryVO hvo) throws SQLException {
		boolean successFlag =false; 
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(HISTORY_DELETE);
		pstmt.setInt(1, hvo.getHistoryid());
		int result = pstmt.executeUpdate();
		successFlag = (result != 0) ? true : false ;

		DBUtility.dbClose(con, pstmt);
		return successFlag; 
	}

}
