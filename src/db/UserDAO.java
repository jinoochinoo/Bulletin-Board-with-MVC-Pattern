package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import control.Controller;

public class UserDAO {
	
	private Context context;
	private DataSource datasource;
	private Connection conn; // DB 연결
	
	private Statement stmt; // 일반 쿼리
	private PreparedStatement pstmt; // 쿼리문 대기 및 설정
	private ResultSet rs; // 결과값 저장
	private HttpServletRequest request;
	
	// 기본 생성자
	// UserDAO 실행될 때 자동으로 DB 연결
	public UserDAO() {
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
/*
	public UserDAO(HttpServletRequest request) {
		this.request = request;
	}
*/
	/*  / / / / / / / / / / / / / / / / / / / / / 회원가입 부분   / / / / / / / / / / / / / / / / / */
	// 회원가입 절차 진행
	public int signUp(UserDTO dto) {
		
		try { // 아이디 중복 체크부터 진행
			int check = checkID(dto);
			// 이미 아이디가 존재할 때
			if(check == Controller.TRUE) {
				return Controller.FALSE;
				
			// 아이디 존재하지 않을 때 등록절차 진행
			} else if(check == Controller.FALSE) {
		String sql = "insert into MVC_Board_User values (?, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserID());
			pstmt.setString(2, dto.getFirstPassword());
			pstmt.setString(3, dto.getEmail());
			
			pstmt.executeUpdate();
			conn.commit();
			return Controller.TRUE;
			} 
			// DB 접속 및 쿼리 과정에서 예외 발생 시 롤백 
		} catch(Exception e) {
			try {
				conn.rollback();
			} catch(Exception rollbackEx) {
				System.out.println("rollback Exception");
				rollbackEx.printStackTrace();
			}
			System.out.println("DB Connect Exception");
			e.printStackTrace();
			
		} finally {
			dbClose();
			// 끝으로 자원 해제
		} return Controller.EXCEPT;
	}
	
	// 중복 ID 체크
	public int checkID(UserDTO dto) throws Exception{
		
		String sql = "select userID from MVC_Board_User where userID = ?";
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserID());
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) { // userID 존재하면
				return Controller.TRUE;
			} else { //userID 없으면
				return Controller.FALSE;
			} 
	}

	/*  / / / / / / / / / / / / / / / / / / / / / 로그인 부분   / / / / / / / / / / / / / / / / / */

	public int login(HttpServletRequest request) {
		
		String sql = "select userID, firstPassword from MVC_Board_user where userID = ?";

		try{
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, request.getParameter("userID"));
		rs = pstmt.executeQuery();
		
		// 데이터가 있으며 TRUE
		while(rs.next()) {
			if(rs.getString("userID").equals(request.getParameter("userID")) &&
					rs.getString("firstPassword").equals(request.getParameter("firstPassword"))){
				return Controller.TRUE;
			}
		}
		// 없으면 FALSE
		return Controller.FALSE;
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return Controller.EXCEPT;
	}
	
	/*  / / / / / / / / / / / / / / / / / / / / / 회원정보 부분   / / / / / / / / / / / / / / / / / */
	
	public int userInfo(HttpServletRequest request) {
		
		// 세션에 저장된 데이터 활용 // HttpServletRequest request 통해 전달 받아야 함
		HttpSession session = request.getSession();
		
		String sql = "select * from MVC_Board_user where userID = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String) session.getAttribute("userID"));			
			rs = pstmt.executeQuery();
			
			// 데이터 담아줄 그릇 생성
			UserDTO dto = new UserDTO();
			
			while(rs.next()) {
				dto.setUserID(rs.getString("userID"));
				dto.setFirstPassword(replaceStar(rs.getString("firstPassword"), 2));
				dto.setEmail(rs.getString("email"));
			}
			// 세션에 dto 객체 추가하고 마무리
			session.setAttribute("userInfo",dto);
			return Controller.TRUE;
			
		} catch(Exception e) {
			session.setAttribute("Msg", "회원정보 오류");
			e.printStackTrace();
			return Controller.FALSE;
		} finally {
			dbClose();
		}
	}

	// 비밀번호 앞 2자리만 노출하게 만듬
	 public static String replaceStar(String str, int len){  
		   String returnStr="";
		   for(int i=0; i<str.length(); i++){
		    if(i<len) {
		    	returnStr=returnStr+str.substring(i,i+1);
		    }
		    else returnStr=returnStr+"*";
		   }
		   return returnStr;
		 }
	
	/*  / / / / / / / / / / / / / / / / / / / / / DB 관련 부분   / / / / / / / / / / / / / / / / / */
/*
	// DB 연결 
	private void dbConnect() throws Exception {

		context = new InitialContext();
		datasource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle12c");
		conn = datasource.getConnection();
		conn.setAutoCommit(false);
	}
*/
	
	// DB 해제
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

