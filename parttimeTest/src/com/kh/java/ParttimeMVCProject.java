package com.kh.java;

import java.sql.SQLException;
import java.util.Scanner;

import com.kh.java.controller.ParttimeRegisterManager;
import com.kh.java.view.ParttimeCURDMenu;
import com.kh.java.view.ParttimeMenu;

public class ParttimeMVCProject {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws SQLException {
		boolean exitFlag = false;
		while(!exitFlag) {
			ParttimeMenu.printMenu();
			int num = Integer.parseInt(sc.nextLine());
			
			switch(num) {
				case ParttimeCURDMenu.PRINT: {
					ParttimeRegisterManager.totalSelectManager();
					break;
				}
				case ParttimeCURDMenu.INSERT: {
					ParttimeRegisterManager.insertManager();
					break;
				}
				case ParttimeCURDMenu.UPDATE: {
					ParttimeRegisterManager.updateManager();
					break;
				}
				case ParttimeCURDMenu.DELETE: {
					ParttimeRegisterManager.deleteManager();
					break;
				}
				case ParttimeCURDMenu.SORT: {
					ParttimeRegisterManager.sortManager();
					break;
				}
				case ParttimeCURDMenu.EXIT: {
					exitFlag = true;
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value : " + num);
			}
		}
		System.out.println("The End");
	}
}
