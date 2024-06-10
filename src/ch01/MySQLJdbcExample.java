package ch01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLJdbcExample {

	public static void main(String[] args) {
		
		// 준비물
		// mydb2 --> 데이터베이스 이름
		// serverTimezone --> 아시아/ 서울 시간으로 사용
		String url = "jdbc:mysql://localhost:3306/mydb2?serverTimezone=Asia/Seoul";
		String user = "root"; // 상용서비스에서 절대 root 계정으로 사용 금지
		String password = "asd123";
		
		// 필요 데이터 타입
		// JDBC API의 레벨(자바 개발자들이 개념화 시켜놓은 클래스들 이다.)
		Connection connection = null; // 연결을 관리하는 데이터타입
		Statement statement = null; // 문자열 기반으로 쿼리를 실행시킨다.
		ResultSet resultSet = null; // 결과를 받는다.
		
		// 1. MySQL 구현체를 사용하겠다는 설정을 해야한다.
		// JDBC 드라이버 로드 (MySQL 드라이버)
		try {
			// 1. 메모리에 사용하는 드라이버(JDB API를 구현한 클래스) 클래스를 띄운다.
			Class.forName("com.mysql.cj.jdbc.Driver"); // 외우기 
			
			// 2. 데이터베이스 연결 설정
			connection = DriverManager.getConnection(url, user, password);
			
			// 3. SQL 실행
			statement = connection.createStatement();
			// 딱 2가지는 기억하자
			resultSet= statement.executeQuery("SELECT * FROM employee "); // select 실행시 사용한다.
			// statement.executeUpdate(password); --> Insert, Update, delete 사용
			
			// 구문 분석 -- 파싱
			while(resultSet.next()) {
				System.out.println("USER ID : " + resultSet.getInt("id"));
				System.out.println("USER NAME : " + resultSet.getString("name"));
				System.out.println("department : " + resultSet.getString("department"));
				System.out.println("---------------------------------");
			}
			
		} catch (ClassNotFoundException e) { // ClassNotFoundException : 클래스를 못찾았을 때
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	} // end of main

} // end of class
