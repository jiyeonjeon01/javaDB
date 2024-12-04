package com.kh.parttimeMVCProject.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.parttimeMVCProject.model.DepartmentVO;

//DEPID NUMBER, -- PK, 부서ID
//DEPNAME VARCHAR2(20) NOT NULL -- 부서 이름 

//private int depid; // 부서id, pk, seq
//private String depname; // 부서이름("주방", "홀")
//1.부서정보 보여주기  2.부서정보 추가하기  3.부서정보 수정하기  4.부서정보 삭제하기  5.메인메뉴로 나가기

public class DepartmentDAO {
	
	public static final String DEPARTMENT_SELECT = "SELECT * FROM DEPARTMENT";
	public static final String DEPARTMENT_INSERT = "INSERT INTO DEPARTMENT(DEPID, DEPNAME) VALUES(DEPARTMENT_SEQ.NEXTVAL, ?)";
	public static final String DEPARTMENT_UPDATE = "UPDATE DEPARTMENT SET DEPNAME = ? WHERE DEPID = ?";
	public static final String DEPARTMENT_DELETE = "DELETE FROM DEPARTMENT WHERE DEPID = ?";
	public static final String DEPARTMENT_SORT = "SELECT * FROM DEPARTMENT ORDER BY DEPID";
	
	// select
	public ArrayList<DepartmentVO> departmentSelect() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		ArrayList<DepartmentVO> departmentList = new ArrayList<DepartmentVO>();
		
		con = DBUtility.dbCon();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(DEPARTMENT_SELECT);
			
			if(rs.next()) {
				do {
					int depid = rs.getInt("DEPID");
					String depname = rs.getString("DEPNAME");
					
					DepartmentVO dvo = new DepartmentVO(depid, depname);
					departmentList.add(dvo);
				} while (rs.next());
			}else {
				departmentList = null;
			}
		} catch (SQLException e){
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, stmt, rs);
		}
		
		return departmentList;
	}
	
	// insert
	public boolean departmentInsert(DepartmentVO dvo) {
		// Connection
		boolean successFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		con = DBUtility.dbCon();
		try {
			pstmt = con.prepareStatement(DEPARTMENT_INSERT);
			pstmt.setString(1, dvo.getDepname());
			
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
	public boolean departmentUpdate(DepartmentVO dvo) throws SQLException {
	    boolean successFlag = false; 
	    Connection con = null;
	    PreparedStatement pstmt = null;

	    try {
	        con = DBUtility.dbCon();
	        pstmt = con.prepareStatement(DEPARTMENT_UPDATE);
	        pstmt.setString(1, dvo.getDepname()); // 새 depname
	        pstmt.setInt(2, dvo.getDepid());     // 기준 depid
	        int result = pstmt.executeUpdate();
	        successFlag = (result != 0); // 업데이트 성공 여부
	    } finally {
	        DBUtility.dbClose(con, pstmt); // 자원 해제
	    }
	    return successFlag; 
	}

	// delete
	public boolean departmentDelete(DepartmentVO dvo) throws SQLException {
		boolean successFlag =false; 
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(DEPARTMENT_DELETE);
		pstmt.setInt(1, dvo.getDepid());
		int result = pstmt.executeUpdate();
		successFlag = (result != 0) ? true : false ;

		DBUtility.dbClose(con, pstmt);
		return successFlag; 
	}
	
	// sort
	public ArrayList<DepartmentVO> departmentSort() throws SQLException {
	    Connection con = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    ArrayList<DepartmentVO> departmentList = new ArrayList<>();

	    try {
	        con = DBUtility.dbCon();
	        stmt = con.createStatement();
	        rs = stmt.executeQuery(DEPARTMENT_SORT); // SQL: SELECT * FROM DEPARTMENT ORDER BY DEPID ASC;

	        while (rs.next()) { // 반복문으로 처리
	            int depid = rs.getInt("DEPID");
	            String depname = rs.getString("DEPNAME");

	            DepartmentVO dvo = new DepartmentVO(depid, depname); // 생성자 사용
	            departmentList.add(dvo); // 리스트에 추가
	        }

	        if (departmentList.isEmpty()) { // 데이터가 없을 경우 null 처리
	            departmentList = null;
	        }
	    } finally {
	        DBUtility.dbClose(con, stmt, rs); // 자원 해제
	    }
	    return departmentList;
	}

	
	
}
