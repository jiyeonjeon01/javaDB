package com.kh.parttimeMVCProject.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.parttimeMVCProject.model.EmployeeVO;
// empid, name, rage, e_depname
public class EmployeeRegisterManager {
	public static Scanner sc = new Scanner(System.in);
	
	// select
	public void selectManager() throws SQLException {
		ArrayList<EmployeeVO> employeeList = new ArrayList<EmployeeVO>();
		EmployeeDAO empDAO = new EmployeeDAO();
		employeeList = empDAO.employeeSelect();
		if(employeeList == null) {
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		printEmployeeList(employeeList);
	}

	// insert
	public void insertManager() throws SQLException {
		EmployeeDAO empDAO = new EmployeeDAO();
		ArrayList<EmployeeVO> employeeList = null;
		String name; // 알바생 이름
		int rage;
		String e_depname;
		
		System.out.println("부서 전체 리스트");
		employeeList = empDAO.employeeSelect();
		if(employeeList == null) {
			System.out.println("데이터가 존재하지 않습니다.");
		}else {
			printEmployeeList(employeeList);
		}
		
		System.out.print("알바생 이름 입력: ");
		name = sc.nextLine();
		System.out.print("알바생 시급 입력: ");
		rage = sc.nextInt();
		sc.nextLine();
		System.out.println("알바생 부서 입력: ");
		e_depname = sc.nextLine();
		
		EmployeeVO evo = new EmployeeVO(name, rage, e_depname);
		boolean successFlag = empDAO.employeeInsert(evo);
		
		if(successFlag == false) {
			System.out.println("입력처리 실패");
			return;
		}
		System.out.println();
		System.out.println("알바생 전체 리스트");
		employeeList = empDAO.employeeSelect();
		if(employeeList == null) {
			System.out.println("알바생 정보가 없습니다.");
			return;
		}
		printEmployeeList(employeeList);
	}

	// update
	public void updateManager() throws SQLException {
	    EmployeeDAO edao = new EmployeeDAO();
	    ArrayList<EmployeeVO> employeeList = edao.employeeSelect();
	    
	    if (employeeList == null || employeeList.isEmpty()) {
	        System.out.println("데이터가 존재하지 않습니다.");
	        return;
	    }
	    printEmployeeList(employeeList);

	    System.out.print("수정할 알바생의 ID를 입력하세요: ");
	    int empid = Integer.parseInt(sc.nextLine());

	    System.out.print("새로운 알바생 이름을 입력하세요: ");
	    String name = sc.nextLine();

	    System.out.print("새로운 시급을 입력하세요: ");
	    int rage = sc.nextInt();
	    sc.nextLine(); // 버퍼 정리

	    System.out.print("새로운 부서 이름을 입력하세요: ");
	    String e_depname = sc.nextLine();

	    // 입력값 출력 (디버깅용)
	    System.out.println("입력값 확인: empid=" + empid + ", name=" + name + ", rage=" + rage + ", e_depname=" + e_depname);

	    EmployeeVO evo = new EmployeeVO(empid, name, rage, e_depname);
	    boolean successFlag = edao.employeeUpdate(evo);

	    if (successFlag) {
	        System.out.println("수정 처리 성공");
	    } else {
	        System.out.println("수정 처리 실패");
	    }
	}



	// delete
	public void deleteManager() throws SQLException {
		EmployeeDAO edao = new EmployeeDAO();
		
		System.out.print("삭제할 알바생 번호를 입력하세요: ");
		int empid = sc.nextInt();
		sc.nextLine();
		EmployeeVO evo = new EmployeeVO();
		evo.setEmpid(empid);
		boolean successFlag = edao.employeeDelete(evo);
		
		if(successFlag == true) {
			System.out.println("삭제처리 성공");
		}else {
			System.out.println("삭제처리 실패");
		}
	}

	// sort: 임금순
	public void sortManager() throws SQLException {
	    EmployeeDAO edao = new EmployeeDAO();
	    ArrayList<EmployeeVO> employeeList = edao.employeeSort();

	    if (employeeList == null || employeeList.isEmpty()) {
	        System.out.println("데이터가 존재하지 않습니다.");
	        return;
	    }

	    printEmployeeList(employeeList); // 정렬된 리스트 출력
	}

	
	// arrayList
	public void printEmployeeList(ArrayList<EmployeeVO> employeeList) {
		for(EmployeeVO data : employeeList) {
			System.out.println(data);
		}
	}

}
