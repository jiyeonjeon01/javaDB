package studentTest_2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentsMain {
	public static Scanner scan = new Scanner(System.in);
	public static final int PRINT = 1, INSERT = 2, UPDATE = 3, DELETE = 4, EXIT = 5;
	
	public static void main(String[] args) throws SQLException {
		boolean exitFlag = false;
		// 사용자로부터 Students의 출력, 입력, 수정, 삭제, 종료 요청 받기 
		while(!exitFlag) {
			printMenu();
			int num = Integer.parseInt(scan.nextLine());
			switch (num) {
			case PRINT:
				studentsPrint();
				break;
			case INSERT:
				studentsInsert();
				break;
			case UPDATE:
				studentsUpdate();
				break;
			case DELETE:
				studentsDelete();
				break;
			case EXIT:
				exitFlag = true;
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + num);
			}
		}
		System.out.println("The End");	
	}
	
	// 메뉴
	private static void printMenu() {
		System.out.println("Students Menu(1.출력, 2.입력, 3.수정  4.삭제  5.종료)");
		System.out.println(">>");
	}
			
	// 출력
	public static void studentsPrint() throws SQLException {
		// Connection
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Students> studentsList = new ArrayList<Students>();
		
		// 1. Load, 2. Connect
		con = DBConnection.dbCon();
		// 3. statement
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT * FROM STUDENTS");
		// 4. 테이블 내용 가져오기
		while (rs.next()) {
			int id = rs.getInt("ID");
			String name = rs.getString("NAME");
			Date birth = rs.getDate("BIRTH");
			String addr = rs.getString("ADDR");
			
			Students students = new Students(id, name, birth, addr);
			studentsList.add(students);
		}
		
		// 5. 출력하기
		studentsListPrint(studentsList);
		// 6. sql 객체 반납
		DBConnection.dbClose(con, stmt, rs);
	}
			
	// 입력
	private static void studentsInsert() throws SQLException {
	    // Connection
	    Connection con = null;
	    Statement stmt = null;

	    // 1. Load, Connect
	    con = DBConnection.dbCon();

	    // 2. Scanner를 사용하여 사용자로부터 입력 받기
	    System.out.println("학생 정보를 입력하세요.");
	    
	    System.out.print("이름: ");
	    String name = scan.nextLine();
	    
	    System.out.print("생일 (YYYY-MM-DD): ");
	    String birthStr = scan.nextLine();
	    Date birth = Date.valueOf(birthStr); // 입력받은 문자열을 Date로 변환
	    
	    System.out.print("주소: ");
	    String addr = scan.nextLine();

	    // 3. Students 객체 생성
	    Students students = new Students(0, name, birth, addr); // ID는 자동으로 증가하므로 0으로 설정

	    // 4. Statement
	    stmt = con.createStatement();

	    // 5. INSERT INTO 구문 작성 (ID는 시퀀스로 자동 생성)
	    int result = stmt.executeUpdate("INSERT INTO STUDENTS (ID, NAME, BIRTH, ADDR) VALUES (" +
	            "STUDENTS_ID_SEQ.NEXTVAL, '" +
	            students.getName() + "', '" +
	            students.getBirth() + "', '" +
	            students.getAddr() + "')");

	    // 6. 결과 확인
	    System.out.println((result != 0) ? "입력성공" : "입력실패");

	    // 7. SQL 객체 반납
	    DBConnection.dbClose(con, stmt);
	}


	
	// 수정
	private static void studentsUpdate() throws SQLException {
	    // Connection
	    Connection con = null;
	    Statement stmt = null;

	    // 1. Load, Connect
	    con = DBConnection.dbCon();

	    // 2. 수정할 학생 ID 입력받기
	    System.out.print("수정할 학생의 ID를 입력하세요: ");
	    int studentId = Integer.parseInt(scan.nextLine());

	    // 3. 수정할 학생 정보 입력받기
	    System.out.print("새로운 이름: ");
	    String name = scan.nextLine();

	    System.out.print("새로운 생일 (YYYY-MM-DD): ");
	    String birthStr = scan.nextLine();
	    Date birth = Date.valueOf(birthStr); // 문자열을 Date로 변환

	    System.out.print("새로운 주소: ");
	    String addr = scan.nextLine();

	    // 4. SQL 구문 작성
	    String sql = "UPDATE STUDENTS SET " +
	                 "NAME = '" + name + "', " +
	                 "BIRTH = '" + birth + "', " +
	                 "ADDR = '" + addr + "' " +
	                 "WHERE ID = " + studentId;

	    // 디버깅용 출력
	    System.out.println("Executing SQL: " + sql);

	    // 5. 데이터 업데이트 실행
	    stmt = con.createStatement();
	    int result = stmt.executeUpdate(sql);

	    // 6. 결과 확인
	    System.out.println((result != 0) ? "수정 성공" : "수정 실패");

	    // 7. SQL 객체 반납
	    DBConnection.dbClose(con, stmt);
	}




			
	// 삭제 
	private static void studentsDelete() throws SQLException {
		// Connection
		Connection con = null;
		Statement stmt = null;
		
		// 1. Load, 2. Connect
		con = DBConnection.dbCon();
		// 3. statement
		System.out.println("삭제할 번호 >> ");
		int no = Integer.parseInt(scan.nextLine());
		stmt = con.createStatement();
		int result = stmt.executeUpdate("DELETE FROM STUDENTS WHERE ID = " + no);
		// 4. 내용이 잘 입력되었는지 check 
		System.out.println((result != 0) ? "삭제 성공" : "삭제 실패");
		// 6. sql 객체 반납
		DBConnection.dbClose(con, stmt);
	}

	// 종료 는 스위치 문에서 구현
	
	private static void studentsListPrint (ArrayList<Students> studentsList) {
		for(Students students : studentsList) {
			System.out.println(students.toString());
		}
	}
}
