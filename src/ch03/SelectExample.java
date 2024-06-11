package ch03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectExample {
	
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/mydb2?serverTimezone=Asia/Seoul";
		String user = "root"; // 상용서비스에서 절대 root 계정으로 사용 금지
		String password = "asd123";
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection(url, user, password);
			
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("select * from employee");
			
			while(resultSet.next()) {
				System.out.println("ID : " + resultSet.getInt("id"));
				System.out.println("Name : " + resultSet.getString("name"));
				System.out.println("department : " + resultSet.getString("department"));
				System.out.println("salary : " + resultSet.getInt("salary"));
				System.out.println("hire_date : " + resultSet.getDate("hire_date"));
				System.out.println();
			}
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} // 외우기 
 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
