package ch02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
			
			// 3. SQL 실행 (PreparedStatement 객체 사용해보기)
			
			// 3 - 1 쿼리 만들어 보기
			String query = "insert into employee values (?, ?, ?, ?, now())";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, 7);			
			preparedStatement.setString(2, "이순신");
			preparedStatement.setString(3, "IT");
			preparedStatement.setString(4, "5000000.00");
			
			// 실행에 호출은  executeQuery --> select만 사용 
			int rowCount = preparedStatement.executeUpdate(); // -> insert, update, delete, 등 사용		
			System.out.println("rountCount : " + rowCount);
			
			
		} catch (ClassNotFoundException e) { // ClassNotFoundException : 클래스를 못찾았을 때
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	} // end of main

} // end of class
