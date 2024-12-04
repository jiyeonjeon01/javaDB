package com.kh.parttimeMVCProject.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.kh.parttimeMVCProject.model.EmployeeVO;
import com.kh.parttimeMVCProject.model.WorkhourVO;

//WHID NUMBER, --PK, 근무시간ID 
//WDATE DATE  NOT NULL, -- 근무 날짜
//CHECKIN TIMESTAMP NOT NULL, --  출근 시간 
//CHECKOUT TIMESTAMP NOT NULL, -- 퇴근 시간
//WH_EMPID NUMBER NOT NULL, -- FK, 직원ID
//HOURS NUMBER(2, 1) NOT NULL, -- 퇴근시간-출근시간(일한시간)
//AMOUNT NUMBER NOT NULL -- 임금(시급*일한시간)

//private int whid; // pk, 근무id
//private Date wdate; // 근무 날짜
//private Timestamp checkin; // 출근시간
//private Timestamp checkout; // 퇴근시간
//private int wh_empid; // fk, 직원id
//private double hours; // 근무시간 
//private int amount; // 임금
//1.근무정보 보여주기  2.근무정보 추가하기  3.근무정보 수정하기  4.근무정보 삭제하기  5.근무 시간순나열  6.메인메뉴로 나가기

public class WorkhourDAO {

	public static final String WORKHOUR_SELECT = "SELECT * FROM WORKHOUR";
	public static final String WORKHOUR_INSERT = "INSERT INTO WORKHOUR (WHID, WDATE, CHECKIN, CHECKOUT, WH_EMPID) VALUES (WORKHOUR_SEQ.NEXTVAL, ?, ?, ?, ?)";
	public static final String WORKHOUR_UPDATE = "UPDATE WORKHOUR SET CHECKIN = ?, CHECKOUT = ? WHERE WHID = ?";
	public static final String WORKHOUR_DELETE = "DELETE FROM WORKHOUR WHERE WHID = ?";
	public static final String WORKHOUR_SORT = "SELECT * FROM WORKHOUR ORDER BY AMOUNT DESC";
	
	// select
	public ArrayList<WorkhourVO> workhourSelect() throws SQLException {
	    Connection con = null;
	    Statement stmt = null;
	    ResultSet rs = null;

	    ArrayList<WorkhourVO> workhourList = new ArrayList<WorkhourVO>();

	    con = DBUtility.dbCon();

	    try {
	        stmt = con.createStatement();
	        rs = stmt.executeQuery(WORKHOUR_SELECT);

	        if (rs.next()) {
	            do {
	                int whid = rs.getInt("WHID");
	                Date wdate = rs.getDate("WDATE");
	                int checkin = rs.getInt("CHECKIN");
	                int checkout = rs.getInt("CHECKOUT");
	                int wh_empid = rs.getInt("WH_EMPID");
	                int hours = rs.getInt("HOURS"); // 수정: HOURS 조회 추가
	                int amount = rs.getInt("AMOUNT"); // 수정: AMOUNT 조회 추가

	                WorkhourVO whvo = new WorkhourVO(whid, wdate, checkin, checkout, wh_empid, hours, amount);
	                workhourList.add(whvo);
	            } while (rs.next());
	        } else {
	            workhourList = null;
	        }
	    } catch (SQLException e) {
	        System.out.println(e.toString());
	    } finally {
	        DBUtility.dbClose(con, stmt, rs);
	    }

	    return workhourList;
	}


	// insert
	public boolean workhourInsert(WorkhourVO wvo) {
	    boolean successFlag = false; // 성공 여부
	    Connection con = null;
	    PreparedStatement pstmt = null;

	    try {
	        con = DBUtility.dbCon();
	        con.setAutoCommit(false); // 자동 커밋 비활성화

//	        String sql = "INSERT INTO WORKHOUR (WHID, WDATE, CHECKIN, CHECKOUT, WH_EMPID) VALUES (WORKHOUR_SEQ.NEXTVAL, ?, ?, ?, ?)";
//	        pstmt = con.prepareStatement(sql);
	        pstmt = con.prepareStatement(WORKHOUR_INSERT);

	        // 파라미터 설정
	        pstmt.setDate(1, wvo.getWdate());  // WDATE
	        pstmt.setInt(2, wvo.getCheckin()); // CHECKIN
	        pstmt.setInt(3, wvo.getCheckout()); // CHECKOUT
	        pstmt.setInt(4, wvo.getWh_empid()); // WH_EMPID (직원 ID)

	        // 쿼리 실행
	        int result = pstmt.executeUpdate();
	        if (result > 0) {
	            con.commit(); // 변경 사항 커밋
	            successFlag = true;
	        } else {
	            con.rollback(); // 실패 시 롤백
	        }
	    } catch (SQLException e) {
	        System.out.println("SQL 예외 발생: " + e.getMessage());
	        try {
	            if (con != null) con.rollback(); // 오류 발생 시 롤백
	        } catch (SQLException rollbackEx) {
	            rollbackEx.printStackTrace();
	        }
	    } finally {
	        DBUtility.dbClose(con, pstmt); // 자원 해제
	    }

	    return successFlag;
	}



	
	// update
	public static boolean workhourUpdate(WorkhourVO wvo) {
	    boolean successFlag = false; 
	    Connection con = null;
	    PreparedStatement pstmt = null;

	    try {
	        con = DBUtility.dbCon();
	        con.setAutoCommit(false); // 자동 커밋 비활성화
	        pstmt = con.prepareStatement(WORKHOUR_UPDATE);
	        
	        // 파라미터 설정 (순서대로 CHECKIN, CHECKOUT, WHID)
	        pstmt.setInt(1, wvo.getCheckin());  // CHECKIN
	        pstmt.setInt(2, wvo.getCheckout()); // CHECKOUT
	        pstmt.setInt(3, wvo.getWhid());      // 수정할 레코드 식별자 (WHID)

	        // 쿼리 실행
	        int result = pstmt.executeUpdate();
	        if (result > 0) {
	            con.commit(); // 변경 사항 커밋
	            successFlag = true;
	        } else {
	            con.rollback(); // 실패 시 롤백
	            System.out.println("업데이트 쿼리 결과: 수정할 레코드를 찾을 수 없습니다.");
	        }
	    } catch (SQLException e) {
	        System.out.println("예외 발생: " + e.getMessage());
	        try {
	            if (con != null) con.rollback(); // 오류 발생 시 롤백
	        } catch (SQLException rollbackEx) {
	            rollbackEx.printStackTrace();
	        }
	    } finally {
	        DBUtility.dbClose(con, pstmt); // 자원 해제
	    }
	    return successFlag; 
	}


	
	// delete
	public static boolean workhourDelete(WorkhourVO wvo) throws SQLException {
		boolean successFlag =false; 
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(WORKHOUR_DELETE);
		pstmt.setInt(1, wvo.getWhid());
		int result = pstmt.executeUpdate();
		successFlag = (result != 0) ? true : false ;

		DBUtility.dbClose(con, pstmt);
		return successFlag; 
	}
	
	// sort
	public static ArrayList<WorkhourVO> workhourSort() throws SQLException{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<WorkhourVO> workhourList = new ArrayList<WorkhourVO>();

		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery(WORKHOUR_SORT);

	    while (rs.next()) { 
	    	int whid = rs.getInt("WHID");
			Date wdate = rs.getDate("WDATE");
			int checkin = rs.getInt("CHECKIN");
			int checkout = rs.getInt("CHECKOUT");
			int wh_empid = rs.getInt("WH_EMPID");
			int hours = rs.getInt("HOURS");
			int amount = rs.getInt("AMOUNT");

			WorkhourVO wvo = new WorkhourVO(whid, wdate, checkin, checkout, wh_empid, hours, amount);
			workhourList.add(wvo);
	    }

		DBUtility.dbClose(con, stmt, rs);
		return workhourList; 
	}

}
