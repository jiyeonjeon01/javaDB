package com.kh.parttimeMVCProject.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.parttimeMVCProject.model.DepartmentVO;

public class DepartmentRegisterManager {
	public static Scanner sc = new Scanner(System.in);

	// select
	public void selectManager() throws SQLException {
		ArrayList<DepartmentVO> departmentList = new ArrayList<DepartmentVO>();
		DepartmentDAO depDAO = new DepartmentDAO();
		departmentList = depDAO.departmentSelect();
		if(departmentList == null) {
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		printDepartmentList(departmentList);
	}

	// insert
	public void insertManager() throws SQLException {
		DepartmentDAO depDAO = new DepartmentDAO();
		ArrayList<DepartmentVO> departmentList = null;
		String depname; // 부서명
		
		System.out.println("부서 전체 리스트");
		departmentList = depDAO.departmentSelect();
		if(departmentList == null) {
			System.out.println("데이터가 존재하지 않습니다.");
		}else {
			printDepartmentList(departmentList);
		}
		
		System.out.print("부서 정보 입력(ex.홀, 주방)): ");
		depname = sc.nextLine();
		
		DepartmentVO dvo = new DepartmentVO(depname);
		boolean successFlag = depDAO.departmentInsert(dvo);
		
		if(successFlag == false) {
			System.out.println("입력처리 실패");
			return;
		}
		System.out.println();
		System.out.println("부서 전체 리스트");
		departmentList = depDAO.departmentSelect();
		if(departmentList == null) {
			System.out.println("부서 정보가 없습니다.");
			return;
		}
		printDepartmentList(departmentList);
	}

	// update
	public void updateManager() throws SQLException {
	    DepartmentDAO ddao = new DepartmentDAO();
	    // 전체 부서 리스트를 보여줌
	    ArrayList<DepartmentVO> departmentList = ddao.departmentSelect();
	    if (departmentList == null || departmentList.isEmpty()) {
	        System.out.println("데이터가 존재하지 않습니다.");
	        return;
	    }
	    printDepartmentList(departmentList);
	    
	    // 수정할 부서 ID를 입력받음
	    System.out.print("수정할 부서 ID를 입력하세요: ");
	    int depid = Integer.parseInt(sc.nextLine());
	    
	    // 새로운 부서 이름을 입력받음
	    System.out.print("새로운 부서 이름을 입력하세요: ");
	    String depname = sc.nextLine();
	    
	    DepartmentVO dvo = new DepartmentVO(depid, depname);
	    // 업데이트 실행
	    boolean successFlag = ddao.departmentUpdate(dvo);
	    
	    if (successFlag) {
	        System.out.println("수정 처리 성공");
	    } else {
	        System.out.println("수정 처리 실패");
	    }
	}


	// delete
	public void deleteManager() throws SQLException {
		DepartmentDAO ddao = new DepartmentDAO();
		
		System.out.print("삭제할 부서 번호를 입력하세요: ");
		int depid = sc.nextInt();
		sc.nextLine();
		DepartmentVO dvo = new DepartmentVO();
		dvo.setDepid(depid);
		boolean successFlag = ddao.departmentDelete(dvo);
		
		if(successFlag == true) {
			System.out.println("삭제처리 성공");
		}else {
			System.out.println("삭제처리 실패");
		}
	}

	// sort: 부서번호 
	public void sortManager() throws SQLException {
	    DepartmentDAO ddao = new DepartmentDAO();
	    ArrayList<DepartmentVO> departmentList = ddao.departmentSort();

	    if (departmentList == null) {
	        System.out.println("데이터가 존재하지 않습니다.");
	        return;
	    }

	    printDepartmentList(departmentList); // 정렬된 리스트 출력
	}

	
	// arrayList
	public void printDepartmentList(ArrayList<DepartmentVO> departmentList) {
		for(DepartmentVO data : departmentList) {
			System.out.println(data);
		}
	}
}
