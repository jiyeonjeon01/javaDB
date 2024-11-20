package bookTest_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConnection {
	
	public static Connection dbCon() {
		// 객체참조변수 선언
		Connection con = null;

		// 1. jdbc driver load  2. connection
		try {
			// 오라클 드라이버 클래스를 찾아서 메모리에 로딩시킨다 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 오라클서버(데이터베이스 서버)에 접속요청을 진행한다. 
			// con: 오라클을 접속하는 객체 참조 변수 
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/xe", "hr", "hr");
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return con;
	}
	
	//sql 객체 close
	public static void dbClose(Connection con, Statement stmt, ResultSet rs) {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
	}
	
	public static void dbClose(Connection con, Statement stmt) {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
	}

}