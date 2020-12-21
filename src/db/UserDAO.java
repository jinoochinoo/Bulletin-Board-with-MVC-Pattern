package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

	private Connection conn; // DB 연결
	private PreparedStatement pstmt; // 쿼리문 대기 및 설정
	private ResultSet rs; // 결과값 저장
	
	// 기본 생성자
	// UserDAO 실행될 때 자동으로 DB 연결
	public UserDAO() {
		try {
		String dbURL = "jdbc:oracle:thin:@localhost:1521:orcl";
		String dbID = "scott";
		String dbPassword = "tiger";
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch(Exception e) {
		e.printStackTrace();
		}
	}
	
	public int signUP(UserDTO dto) {
		
		String sql = "insert into MVC_Board_User values(?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserID());
			pstmt.setString(2, dto.getFirstPassword());
			pstmt.setString(3, dto.getEmail());
			
			pstmt.executeUpdate();
			conn.commit();
			
			return 1;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int checkID(UserDTO dto) {
		String sql = "select userID from MVC_Board_User where userID = '?'";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserID());
			rs = pstmt.executeQuery();
			
			if(rs.next()) return 0;
		} catch(Exception e) {
			e.printStackTrace();
		} 
		return 1; 
	}
}
