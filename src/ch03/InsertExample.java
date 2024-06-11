package ch03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertExample {
	
	public static void main(String[] args) {
		
		// Connection 객체를 얻어서 insert 부문을 직접 만들어 보세요.
		// mydb2 사용, employee 테이블에 값을 넣는 코드를 작성하세요.
		
		String url;
		String user;
		String password;
		
		url = "jdbc:mysql://localhost:3306/mydb2?serverTimezone=Asia/Seoul";
		user = "root";
		password = "asd123";
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			
			String query = "insert into employee values (?, ?, ?, ?, now())";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, 10);
			preparedStatement.setString(2, "세종");
			preparedStatement.setString(3, "IT");
			preparedStatement.setString(4, "4500000.00");
			
			int rowCount = preparedStatement.executeUpdate();
			System.out.println("rountCount : " +rowCount);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} // end of main

} // end of class
