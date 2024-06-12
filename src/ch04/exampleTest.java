package ch04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class exampleTest {
	
	public static void main(String[] args) {
		
		String url ="jdbc:mysql://localhost:3306/m_boad?serverTimezone=Asia/Seoul";
		String name ="root";
		String password="asd123";
		
		try {
			// MySQL 드라이버(구현클래스) 메모리에 로드
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			
			try( Connection conn = DriverManager.getConnection(url, name, password) ) {
				conn.setAutoCommit(false); // 자동으로 커밋하는 것을 끈다. (수동)
				
				String sqlUpdate = "  update user set username = ? where email = ? ";
				
				PreparedStatement psmt1 = conn.prepareStatement(sqlUpdate);
				psmt1.setString(1, "세종");
				psmt1.setString(2, "a@nate.com");
				psmt1.executeUpdate();
				
				
				String sqlInsert = " Insert INTO user(username, password, email, userRole, address, createDate) "
						+" values (?, ?, ?, ?, ?, now() ) ";
				
				
				if (true) {
					conn.commit();
				} else {
					conn.rollback();
				}
				
			
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
		
	} // end of main
	

} // end of class
