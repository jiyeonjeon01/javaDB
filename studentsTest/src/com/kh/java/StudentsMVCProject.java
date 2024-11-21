package com.kh.java;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.java.controller.StudentsDAO;
import com.kh.java.controller.StudentsRegisterManager;
import com.kh.java.model.StudentsVO;
import com.kh.java.view.StudentsCURDMenu;
import com.kh.java.view.StudentsMenu;

public class StudentsMVCProject {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		boolean exitFlag = false;
		while (!exitFlag) {
			StudentsMenu.printMenu();
			int num = Integer.parseInt(sc.nextLine());
			switch (num) {
			case StudentsCURDMenu.PRINT: {
				StudentsRegisterManager.totalSelectManager(); // 전체 출력하기
				break;
			}
			case StudentsCURDMenu.INSERT: {
				StudentsRegisterManager.insertManager(); // 데이터 삽입하기
				break;
			}
			case StudentsCURDMenu.UPDATE: {
				StudentsRegisterManager.updateManager(); // 데이터 수정하기
				break;
			}
			case StudentsCURDMenu.DELETE: {
				StudentsRegisterManager.deleteManager(); // 데이터 삭제하기
				break;
			}
			case StudentsCURDMenu.SORT: {
				StudentsRegisterManager.sortManager(); // 데이터 순위매기기
				break;
			}
			case StudentsCURDMenu.EXIT: {
				exitFlag = true; // 프로그램 종료하기
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + num);
			}
		}
		System.out.println("The end");
	}
}
