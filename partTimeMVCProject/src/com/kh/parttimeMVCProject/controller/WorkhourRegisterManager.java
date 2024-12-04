package com.kh.parttimeMVCProject.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.parttimeMVCProject.model.EmployeeVO;
import com.kh.parttimeMVCProject.model.WorkhourVO;

public class WorkhourRegisterManager {
	public static Scanner sc = new Scanner(System.in);
	// select
	public void selectManager() throws SQLException {
	    ArrayList<WorkhourVO> workhourList = new ArrayList<WorkhourVO>();
	    WorkhourDAO wdao = new WorkhourDAO();
	    workhourList = wdao.workhourSelect();
	    
	    if (workhourList == null) {
	        System.out.println("데이터가 존재하지 않습니다.");
	        return;
	    }
	    printWorkhourList(workhourList);
	}


	// insert
	public void insertManager() throws SQLException {
	    WorkhourDAO workhourDAO = new WorkhourDAO();
	    EmployeeDAO empDAO = new EmployeeDAO();

	    // 직원 리스트 출력
	    ArrayList<EmployeeVO> employeeList = empDAO.employeeSelect();
	    if (employeeList == null || employeeList.isEmpty()) {
	        System.out.println("직원 데이터가 없습니다.");
	        return;
	    }

	    System.out.println("직원 전체 리스트:");
	    for (EmployeeVO evo : employeeList) {
	        System.out.println(evo);
	    }

	    // 사용자 입력 받기
	    System.out.print("근무한 직원의 ID를 입력하세요: ");
	    int wh_empid = Integer.parseInt(sc.nextLine());

	    System.out.print("출근 시간을 입력하세요 (정수 형식, 예: 10): ");
	    int checkin = Integer.parseInt(sc.nextLine());

	    System.out.print("퇴근 시간을 입력하세요 (정수 형식, 예: 20): ");
	    int checkout = Integer.parseInt(sc.nextLine());

	    // 현재 날짜 가져오기
	    Date wdate = new Date(System.currentTimeMillis());

	    // WorkhourVO 생성
	    WorkhourVO wvo = new WorkhourVO(wdate, checkin, checkout, wh_empid);

	    // 데이터 삽입
	    boolean isSuccess = workhourDAO.workhourInsert(wvo);

	    if (isSuccess) {
	        System.out.println("근무 정보가 성공적으로 입력되었습니다.");

	        // 삽입된 데이터 재조회
	        try (Connection con = DBUtility.dbCon();
	             PreparedStatement pstmt = con.prepareStatement(
	                     "SELECT WHID, WDATE, CHECKIN, CHECKOUT, WH_EMPID, HOURS, AMOUNT FROM WORKHOUR WHERE WH_EMPID = ? AND WDATE = ? AND CHECKIN = ? AND CHECKOUT = ?")) {
	            pstmt.setInt(1, wh_empid);
	            pstmt.setDate(2, wdate);
	            pstmt.setInt(3, checkin);
	            pstmt.setInt(4, checkout);

	            ResultSet rs = pstmt.executeQuery();

	            if (rs.next()) {
	                int whid = rs.getInt("WHID");
	                double hours = rs.getDouble("HOURS");
	                int amount = rs.getInt("AMOUNT");

	                System.out.println("WHID = " + whid + ", HOURS = " + hours + "시간, AMOUNT = " + amount + "원");
	            }
	        } catch (SQLException e) {
	            System.out.println("조회 중 SQL 예외 " + e.getMessage());
	        }
	    } else {
	        System.out.println("근무 정보 입력에 실패하였습니다.");
	    }
	}

	// update
	public void updateManager() throws SQLException {
	    WorkhourDAO whdao = new WorkhourDAO();
	    ArrayList<WorkhourVO> workhourList = whdao.workhourSelect();

	    if (workhourList == null || workhourList.isEmpty()) {
	        System.out.println("데이터가 존재하지 않습니다.");
	        return;
	    }

	    printWorkhourList(workhourList);

	    System.out.print("수정할 근무시간의 ID를 입력하세요: ");
	    int whid = Integer.parseInt(sc.nextLine());

	    // WHID 존재 여부 확인
	    boolean whidExists = workhourList.stream().anyMatch(whvo -> whvo.getWhid() == whid);
	    if (!whidExists) {
	        System.out.println("입력한 WHID가 존재하지 않습니다. 다시 시도해주세요.");
	        return;
	    }

	    System.out.print("수정할 출근시간을 입력하세요 (정수 형식, 예: 10): ");
	    int checkin = Integer.parseInt(sc.nextLine());

	    System.out.print("수정할 퇴근시간을 입력하세요 (정수 형식, 예: 20): ");
	    int checkout = Integer.parseInt(sc.nextLine());

	    System.out.println("입력값 확인: whid = " + whid + ", checkin = " + checkin + ", checkout = " + checkout);

	    WorkhourVO whvo = new WorkhourVO();
	    whvo.setWhid(whid);
	    whvo.setCheckin(checkin);
	    whvo.setCheckout(checkout);

	    boolean successFlag = WorkhourDAO.workhourUpdate(whvo);

	    if (successFlag) {
	        System.out.println("수정 처리 성공");

	        // 수정된 데이터 재조회
	        try (Connection con = DBUtility.dbCon();
	             PreparedStatement pstmt = con.prepareStatement(
	                     "SELECT WHID, WDATE, CHECKIN, CHECKOUT, WH_EMPID, HOURS, AMOUNT FROM WORKHOUR WHERE WHID = ?")) {
	            pstmt.setInt(1, whid);

	            ResultSet rs = pstmt.executeQuery();

	            if (rs.next()) {
	                int hours = rs.getInt("HOURS");
	                int amount = rs.getInt("AMOUNT");

	                System.out.println("수정 후 WHID = " + whid + ", HOURS = " + hours + "시간, AMOUNT = " + amount + "원");
	            }
	        } catch (SQLException e) {
	            System.out.println("조회 중 SQL 예외 발생: " + e.getMessage());
	        }
	    } else {
	        System.out.println("수정 처리 실패");
	    }
	}



	// delete
	public void deleteManager() throws SQLException {
		WorkhourDAO whdao = new WorkhourDAO();
		System.out.print("삭제할 근무정보 번호를 입력하세요: ");
		int whid = sc.nextInt();
		sc.nextLine();
		WorkhourVO whvo = new WorkhourVO();
		whvo.setWhid(whid);
		
		boolean successFlag = whdao.workhourDelete(whvo);
		
		if(successFlag == true) {
			System.out.println("삭제 성공");
		}else {
			System.out.println("삭제 실패");
		}
		
	}

	// sort: 근무시간순
	public void sortManager() throws SQLException {
		WorkhourDAO whdao = new WorkhourDAO();
	    ArrayList<WorkhourVO> workhourList = whdao.workhourSort();

	    if (workhourList == null || workhourList.isEmpty()) {
	        System.out.println("데이터가 존재하지 않습니다.");
	        return;
	    }

	    printWorkhourList(workhourList); // 정렬된 리스트 출력
		
	}
	
	// arrayList
	public void printWorkhourList(ArrayList<WorkhourVO> workhourList) {
		for(WorkhourVO data : workhourList) {
			System.out.println(data);
		}
	}
	
	// arrayList
		public void printEmployeeList(ArrayList<EmployeeVO> employeeList) {
			for(EmployeeVO data : employeeList) {
				System.out.println(data);
			}
		}

}
