package com.kh.parttimeMVCProject;

import java.sql.SQLException;
import java.util.Scanner;

import com.kh.parttimeMVCProject.controller.DepartmentRegisterManager;
import com.kh.parttimeMVCProject.controller.EmployeeRegisterManager;
import com.kh.parttimeMVCProject.controller.HistoryRegisterManager;
import com.kh.parttimeMVCProject.controller.WorkhourRegisterManager;
import com.kh.parttimeMVCProject.view.EMPLOYEE_CHOICE;
import com.kh.parttimeMVCProject.view.HISTORY_CHOICE;
import com.kh.parttimeMVCProject.view.DEPARTMENT_CHOICE;
import com.kh.parttimeMVCProject.view.MENU_CHOICE;
import com.kh.parttimeMVCProject.view.MenuViewer;
import com.kh.parttimeMVCProject.view.WORKHOUR_CHOICE;


//System.out.println("======================================알바비 계산 프로그램======================================");
//System.out.println("1.부서 정보메뉴  2.알바생 정보메뉴  3.근무시간 정보메뉴  4.알바비지급내역 정보메뉴  5.전체정보 출력하기  6.프로그램 종료");
//
//System.out.println("-----------------------------------------부서 정보 메뉴-----------------------------------------");
//System.out.println("1.부서정보 보여주기  2.부서정보 추가하기  3.부서정보 수정하기  4.부서정보 삭제하기  5.부서번호순 나열  6.메인메뉴로 나가기");
//
//System.out.println("--------------------------------------------알바생 정보 메뉴--------------------------------------------");
//System.out.println("1.알바생정보 보여주기  2.알바생정보 추가하기  3.알바생정보 수정하기  4.알바생정보 삭제하기  5.알바생 임금순나열  6.메인메뉴로 나가기");
//
//System.out.println("------------------------------------------근무 정보 메뉴------------------------------------------");
//System.out.println("1.근무정보 보여주기  2.근무정보 추가하기  3.근무정보 수정하기  4.근무정보 삭제하기  5.근무 시간순나열  6.메인메뉴로 나가기");
//
//System.out.println("---------------------------------------지급내역 정보 메뉴---------------------------------------");
//System.out.println("1.지급내역정보 보여주기  2.지급내역정보 추가하기  3.지급내역정보 수정하기  4.지급내역정보 삭제하기  5.메인메뉴로 나가기");


public class ParttimeMain {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		int no;
		boolean exitFlag = false;
		
//		while(!exitFlag) {
//			try {
//				MenuViewer.mainMenuView();
//				no = Integer.parseInt(sc.nextLine());
//				
//				switch(no) {
//				case MENU_CHOICE.DEPARTMENT:
//					departmentMenu();
//					break;
//				case MENU_CHOICE.EMPLOYEE:
//					employeeMenu();
//					break;
//				case MENU_CHOICE.WORKHOUR:
//					workhourMenu();
//					break;
//				case MENU_CHOICE.HISTORY:
//					historyMenu();
//					break;	
//				case MENU_CHOICE.VIEWALL:
//					viewAll();
//					break;
//				case MENU_CHOICE.EXIT:
//					System.out.println("프로그램을 종료합니다.");
//					exitFlag = true;
//					break;
//				default:
//					System.out.println("해당 메뉴 번호만 입력하세요.");
//				}
//			} catch (Exception e) {
//				System.out.println("\n입력에 오류가 있습니다.\n프로그램을 다시 시작하세요.");
//			}
//		}
		
		while(!exitFlag) {
			try {
				MenuViewer.mainMenuView();
				no = Integer.parseInt(sc.nextLine());
				
				switch(no) {
				case MENU_CHOICE.DEPARTMENT:
					departmentMenu();
					break;
				case MENU_CHOICE.EMPLOYEE:
					employeeMenu();
					break;
				case MENU_CHOICE.WORKHOUR:
					workhourMenu();
					break;
				case MENU_CHOICE.VIEWALL:
					viewAll();
					break;
//				case MENU_CHOICE.VIEWEMPLOYEE:
//					viewEmployee();
//					break;
				case MENU_CHOICE.EXIT:
					System.out.println("프로그램을 종료합니다.");
					exitFlag = true;
					break;
				default:
					System.out.println("해당 메뉴 번호만 입력하세요.");
				}
			} catch (Exception e) {
				System.out.println("\n입력에 오류가 있습니다.\n프로그램을 다시 시작하세요.");
			}
		}
	}
	
	// 부서 메뉴 
	private static void departmentMenu() throws SQLException {
		int no;
		DepartmentRegisterManager drm = new DepartmentRegisterManager();
		
		MenuViewer.departmentMenuView();
		no = Integer.parseInt(sc.nextLine());
		
		switch (no) {
		case DEPARTMENT_CHOICE.LIST:
			System.out.println("");
			drm.selectManager();
			break;
		case DEPARTMENT_CHOICE.INSERT:
			System.out.println("");
			drm.insertManager();
			break;
		case DEPARTMENT_CHOICE.UPDATE:
			System.out.println("");
			drm.updateManager();
			break;
		case DEPARTMENT_CHOICE.DELETE:
			System.out.println("");
			drm.deleteManager();
			break;	
		// 어차피 depid가 seq로 나열돼서 필요없는 느낌..? 	
		case DEPARTMENT_CHOICE.SORT:
			System.out.println("");
			drm.sortManager();
			break;
		case DEPARTMENT_CHOICE.MAIN:
			return;
		default:
			System.out.println("해당 메뉴 번호만 입력하세요.");
		}
	}
	
	// 알바생 메뉴
	private static void employeeMenu() throws SQLException {
		int no;
		EmployeeRegisterManager erm = new EmployeeRegisterManager();
		
		MenuViewer.employeeMenuView();
		no = Integer.parseInt(sc.nextLine());
		
		switch (no) {
		case EMPLOYEE_CHOICE.LIST:
			System.out.println("");
			erm.selectManager();
			break;
		case EMPLOYEE_CHOICE.INSERT:
			System.out.println("");
			erm.insertManager();
			break;
		case EMPLOYEE_CHOICE.UPDATE:
			System.out.println("");
			erm.updateManager();
			break;
		case EMPLOYEE_CHOICE.DELETE:
			System.out.println("");
			erm.deleteManager();
			break;	
		case EMPLOYEE_CHOICE.SORT:
			System.out.println("");
			erm.sortManager();
			break;
		case EMPLOYEE_CHOICE.MAIN:
			return;
		default:
			System.out.println("해당 메뉴 번호만 입력하세요.");
		}
	}
	
	// 근무 메뉴
	private static void workhourMenu() throws SQLException {
		int no;
		WorkhourRegisterManager wrm = new WorkhourRegisterManager();
		
		MenuViewer.workhourMenuView();
		no = Integer.parseInt(sc.nextLine());
		
		switch (no) {
		case WORKHOUR_CHOICE.LIST:
			System.out.println("");
			wrm.selectManager();
			break;
		case WORKHOUR_CHOICE.INSERT:
			System.out.println("");
			wrm.insertManager();
			break;
		case WORKHOUR_CHOICE.UPDATE:
			System.out.println("");
			wrm.updateManager();
			break;
		case WORKHOUR_CHOICE.DELETE:
			System.out.println("");
			wrm.deleteManager();
			break;	
		case WORKHOUR_CHOICE.SORT:
			System.out.println("");
			wrm.sortManager();
			break;
		case EMPLOYEE_CHOICE.MAIN:
			return;
		default:
			System.out.println("해당 메뉴 번호만 입력하세요.");
		}
	}
	
//	// 지급내역 메뉴
//	private static void historyMenu() {
//		int no;
//		HistoryRegisterManager hrm = new HistoryRegisterManager();
//		
//		MenuViewer.historyMenuView();
//		no = Integer.parseInt(sc.nextLine());
//		
//		switch (no) {
//		case HISTORY_CHOICE.LIST:
//			System.out.println("");
//			hrm.selectManager();
//			break;
//		case HISTORY_CHOICE.INSERT:
//			System.out.println("");
//			hrm.insertManager();
//			break;
//		case HISTORY_CHOICE.UPDATE:
//			System.out.println("");
//			hrm.updateManager();
//			break;
//		case HISTORY_CHOICE.DELETE:
//			System.out.println("");
//			hrm.deleteManager();
//			break;	
//		case HISTORY_CHOICE.MAIN:
//			return;
//		default:
//			System.out.println("해당 메뉴 번호만 입력하세요.");
//		}
//	}
	
	// 전체 내용 출력
	private static void viewAll() {
		
	}

}
