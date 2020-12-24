package visit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VisitCountDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// 싱글톤 패턴
	public VisitCountDAO() {
		
		try {
			
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String dbURL = "jdbc:oracle:thin:@localhost:1521:orcl";
		String dbID = "scott";
		String dbPassword = "tiger";
		conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		conn.setAutoCommit(false); // 오토 커밋 해제
		} catch(Exception e) {
		e.printStackTrace();
		}
		
	}

	// * *  / // / /  / / / / / / / /  총 방문자 증가  / / / / / / / / / / / / * * //
	
	public void setTotalCount() {
	
		try {
			
			String sql = "insert into visit(V_date) values (sysdate)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			conn.commit();
			
		} catch(Exception e) {

			e.printStackTrace();
		} 
	}
	
	// / / / / / / / / / / / 총 방문자수 호출 / / / / / / / / / / / 
	
	public int getTotalCount() {
		
		int totalCount = 0;

		try {
			String sql = "select count(*) as TotalCnt from visit"; 
			pstmt = conn.prepareStatement(sql); 
			rs = pstmt.executeQuery(); 

			if(rs.next()) 
				totalCount = rs.getInt("TotalCnt");

			return totalCount;
				
		} catch(Exception e) {
			e.printStackTrace();
		} 
		return -3;
	}
	
	// / / / / / / / / / / 오늘 방문자 수  / / / / / / / / //
	
	public int getTodayCount() {
		
		int todayCount = 0;
		
		try {
		
		String sql = "select count(*) as TodayCnt from visit where "
				+ "To_date(V_date, 'yyyy-mm-dd') = To_date(sysdate, 'yyyy-mm-dd')";
		
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		if(rs.next())
			todayCount = rs.getInt("TodayCnt");
		return todayCount;
		
	} catch(Exception e) {
		e.printStackTrace();
	} finally {
		dbClose();
	} return -3;
}
	
	
	// - - - - - - - - DB 해제  - - - - - - - //
	
	private void dbClose() {
		if(rs != null) {
			try {
				rs.close();
			} catch(Exception resultCloseEx) {
				resultCloseEx.printStackTrace();
			}
		}
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch(Exception resultCloseEx) {
				resultCloseEx.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch(Exception resultCloseEx) {
				resultCloseEx.printStackTrace();
			}
		}
	}
}
