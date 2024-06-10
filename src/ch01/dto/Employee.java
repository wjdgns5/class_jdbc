package ch01.dto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// DTO 설계 하고
// 값을 담아서. 연산자를 사용해 보세요.
public class Employee {

	public static void main(String[] args) {
		// timeszone 뜻 : 국가별로 각자의 고유한 타임존 (개념)
		// "jdbc:mysql://아이피:포트/테이블명?국가의 서버시간=아시아/서울"
		String url = "jdbc:mysql://localhost:3306/mydb2?serverTimezone=Asia/Seoul";
		String user = "root"; // 상용서비스에서 절대 root 계정으로 사용 금지
		String password = "asd123"; // 결과를 받는다.
		
		// 필요 데이터 타입
		// JDBC API의 레벨(자바 개발자들이 개념화 시켜놓은 클래스들 이다.)
		Connection connection = null; // 연결을 관리하는 데이터타입
		Statement statement = null; // 문자열 기반으로 쿼리를 실행시킨다.
		ResultSet resultSet = null; 
		
		// 1. MySQL 구현체를 사용하겠다는 설정을 해야한다.
		// JDBC 드라이버 로드 (MySQL 드라이버)
		try {
			// 1. 메모리에 사용하는 드라이버(JDB API를 구현한 클래스) 클래스를 띄운다.
			// Class.forName() JVM에게 해당 클래스의 정보를 로드
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			
			// 2. 데이터베이스 연결 설정
			// DriverManager 어플리케이션에서 사용할 수 있는 JDBC 드라이버를 관리
			// JDBC : 자바에서 데이터베이스에 접속할 수 있도록 하는 자바 API
			connection = DriverManager.getConnection(url, user, password);
			
			// 3. SQL 실행 (쿼리 실행)
			// createStatement : SQL문을 DB로 보내기 위한 Statement 개체를 만든다.
			statement = connection.createStatement();
			
			// 딱 2가지는 기억하자
			// executeQuery : SELECT 문과 같은 쿼리문을 실행할 때 사용
			resultSet = statement.executeQuery("SELECT * FROM NEW_EMPLOYEES");// select 실행시 사용한다.
			// statement.executeUpdate(password); --> Insert, Update, delete 사용
			
			// 구문 분석 -- 파싱
			// resultSet : Statement의 executeQuery() 메서드가 반환하는 결과로 얻은 데이터를 의미
			while(resultSet.next()) {
				System.out.println("USER ID : " + resultSet.getInt("emp_no"));
				System.out.println("First_Name : " + resultSet.getString("first_name"));
				System.out.println("Last Name : " + resultSet.getString("last_name"));
				System.out.println("----------------------------");
			}
			
		} catch (ClassNotFoundException e) { // // ClassNotFoundException : 클래스를 못찾았을 때
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	} // end of main

} // end of class
