package com.kh.parttimeMVCProject.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.parttimeMVCProject.model.DepartmentVO;
import com.kh.parttimeMVCProject.model.EmployeeVO;

//EMPID NUMBER, -- PK, 직원ID
//NAME VARCHAR2(20) NOT NULL,-- 직원 이름
//RAGE NUMBER NOT NULL, -- 시급
//E_DEPNAME VARCHAR2(20) NOT NULL -- FK, 부서 이름 

//private int empid; // 직원id, pk, seq
//private String name; // 직원 이름 
//private int rage; // 시급
//private String e_depname; // 부서이름, fk
//1.알바생정보 보여주기  2.알바생정보 추가하기  3.알바생정보 수정하기  4.알바생정보 삭제하기  5.알바생 임금순나열  6.메인메뉴로 나가기

public class EmployeeDAO {
	
	public static final String EMPLOYEE_SELECT = "SELECT * FROM EMPLOYEE";
	public static final String EMPLOYEE_INSERT = "INSERT INTO EMPLOYEE VALUES(DEPARTMENT_SEQ.NEXTVAL, ?, ?, ?)";
	public static final String EMPLOYEE_UPDATE = "UPDATE EMPLOYEE SET NAME = ?, RAGE = ?, E_DEPNAME = ? WHERE EMPID = ?";
	public static final String EMPLOYEE_DELETE = "DELETE FROM EMPLOYEE WHERE EMPID = ?";
	public static final String EMPLOYEE_SORT = "SELECT * FROM EMPLOYEE ORDER BY RAGE DESC";
	
	// select
	public ArrayList<EmployeeVO> employeeSelect() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		ArrayList<EmployeeVO> employeeList = new ArrayList<EmployeeVO>();
		
		con = DBUtility.dbCon();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(EMPLOYEE_SELECT);
			
			if(rs.next()) {
				do {
					int empid = rs.getInt("EMPID");
					String name = rs.getString("NAME");
					int rage = rs.getInt("RAGE");
					String e_depname = rs.getString("E_DEPNAME");
					
					EmployeeVO evo = new EmployeeVO(empid, name, rage, e_depname);
					employeeList.add(evo);
				} while (rs.next());
			}else {
				employeeList = null;
			}
		} catch (SQLException e){
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, stmt, rs);
		}
		
		return employeeList;
	}
	
	// insert
	public boolean employeeInsert(EmployeeVO evo) {
		// Connection
		boolean successFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		con = DBUtility.dbCon();
		try {
			pstmt = con.prepareStatement(EMPLOYEE_INSERT);
			pstmt.setString(1, evo.getName());
			pstmt.setInt(2, evo.getRage());
			pstmt.setString(3, evo.getE_depname());
			
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
	public static boolean employeeUpdate(EmployeeVO evo) {
	    boolean successFlag = false; 
	    Connection con = null;
	    PreparedStatement pstmt = null;

	    try {
	        con = DBUtility.dbCon();
	        pstmt = con.prepareStatement(EMPLOYEE_UPDATE);
	        pstmt.setString(1, evo.getName());
	        pstmt.setInt(2, evo.getRage());
	        pstmt.setString(3, evo.getE_depname());
	        pstmt.setInt(4, evo.getEmpid()); // EMPID 바인딩 확인

	        int result = pstmt.executeUpdate();
	        successFlag = (result != 0); // 업데이트 성공 여부
	    } catch (SQLException e) {
	        System.out.println("SQL 예외 발생: " + e.getMessage());
	    } finally {
	        DBUtility.dbClose(con, pstmt); // 자원 해제
	    }
	    return successFlag; 
	}

	
	// delete
	public static boolean employeeDelete(EmployeeVO evo) throws SQLException {
		boolean successFlag =false; 
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(EMPLOYEE_DELETE);
		pstmt.setInt(1, evo.getEmpid());
		int result = pstmt.executeUpdate();
		successFlag = (result != 0) ? true : false ;

		DBUtility.dbClose(con, pstmt);
		return successFlag; 
	}
	
	// sort
	public static ArrayList<EmployeeVO> employeeSort() throws SQLException {
	    Connection con = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    ArrayList<EmployeeVO> employeeList = new ArrayList<EmployeeVO>();

	    con = DBUtility.dbCon();
	    stmt = con.createStatement();
	    rs = stmt.executeQuery(EMPLOYEE_SORT);

	    while (rs.next()) { 
	        int empid = rs.getInt("EMPID");
	        String name = rs.getString("NAME");
	        int rage = rs.getInt("RAGE");
	        String e_depname = rs.getString("E_DEPNAME");

	        // 필드 값 초기화
	        EmployeeVO evo = new EmployeeVO(empid, name, rage, e_depname);
	        employeeList.add(evo); 
	    }

	    DBUtility.dbClose(con, stmt, rs);
	    return employeeList; 
	}
}
