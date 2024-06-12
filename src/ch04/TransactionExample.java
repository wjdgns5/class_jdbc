package ch04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class TransactionExample {
	
	public static void main(String[] args) {
		
		// 드라이버가 필요하다. --> MySQL 개발자들이 자바 코드로 작성한 클래스의 묶음(.jar)
		// mysql version 8.0
		String url = "jdbc:mysql://localhost:3306/m_boad?serverTimezone=Asia/Seoul"; 
		String id = "root";
		String password = "asd123";
		
		
		// 구현체를 사용하기 위해서
		try {
			// Class.forName 은 클래스 Class <-- 최상위 Object 안에 있음
			// 동적 바인딩 처리 
			Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL 드라이버(구현클래스) 메모리에 로드
			
			// try - catch - resource 문법
			try( Connection conn = DriverManager.getConnection(url, id, password) ) {
				conn.setAutoCommit(false); // 자동 커밋을 수동으로 설정 (수동 커밋 모드로 설정)
				
				String sqlInsert = " Insert INTO user(username, password, email, userRole, address, createDate) "
						+ "	values (?, ?, ?, ?, ?, now() ) ";
				// 공백 때문에 오류나는 경우가 있다. 무조건 맨앞, 맨뒤 한칸씩 띄어서 사용 !!
				
				PreparedStatement psmt1 = conn.prepareStatement(sqlInsert);
				psmt1.setString(1, "김철수");
				psmt1.setString(2, "asd123");
				psmt1.setString(3, "a@nate.com");
				psmt1.setString(4, "user");
				psmt1.setString(5, "부산시진구");
				psmt1.executeUpdate();
				
				// 작업의 단위 다른거
				String sqlUpdate = "UPDATE user SET email = ? Where username = ?";
				PreparedStatement psmt2 = conn.prepareStatement(sqlUpdate);
				psmt2.setString(1, "b@naver.com");
				psmt2.setString(2, "김유신");
				psmt2.executeUpdate();
				
				
				// 수동 커밋 모드를 설정했다면 직접 commit()을 실행해야
				// 물리적인 저장장치에 영구히 반영이 된다.
				if (true) {
					conn.commit();
				} else {
					conn.rollback();
				}
				
				
			} catch (Exception e) {
				// conn.rollback(); // 오류 발생시 뒤로 돌아간다.
				e.printStackTrace();
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	} // end of main

} // end of class
