package cmnt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import control.Controller;

public class CmntDAO {

		private Context context;
		private DataSource datasource;
		private Connection conn; // DB 연결
		
		private Statement stmt; // 일반 쿼리
		private PreparedStatement pstmt; // 쿼리문 대기 및 설정
		private ResultSet rs; // 결과값 저장
		private HttpServletRequest request;
		
		// 기본 생성자
		// UserDAO 실행될 때 자동으로 DB 연결 
		public CmntDAO() {
			
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
		
		//  / / / / //   시퀀스 값 호출 		//  / / / / //   
		
		public int getSeq() {
			
			int result = 1;
			try {
				String sql = "select cmnt_seq.nextval from dual";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) result = rs.getInt(1);
				
			} catch(Exception e) {
				System.out.println(" - - - - 시퀀스 값 호출 에러 DAO - - - -  ");
				e.printStackTrace();
			}
			return result;
		}
		
		//  / / / / //   댓글 등록 		//  / / / / //   
		public int insertCmnt(CmntDTO dto) {
			
			int result = 0;
			
			try {
				String sql = "insert into MVC_board_comment(cmnt_num, cmnt_bd, cmnt_id, "
						+ "cmnt_date, cmnt_parent, cmnt_content) "
						+ "values(?, ?, ?, sysdate, ?, ?)";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getCmnt_num());
				pstmt.setInt(2, dto.getCmnt_bd());
				pstmt.setString(3,  dto.getCmnt_id());
				pstmt.setInt(4, dto.getCmnt_parent());
				pstmt.setString(5, dto.getCmnt_content());
				
				int flag = pstmt.executeUpdate();
				if(flag > 0) {
					result = Controller.TRUE;
					conn.commit();
				} else{
					result = Controller.FALSE;
				}
				
				
				
			} catch(Exception e) {
				try {
					conn.rollback();
				} catch(Exception rollbackEx) {
					System.out.println(" - - - - 롤백 에러  - - - - ");
					rollbackEx.printStackTrace();
				}
				System.out.println(" - - - - 댓글 등록 오류 DAO - - - - - ");
				e.printStackTrace();
			} return result;
		}
		
		//  / / / / //   댓글 목록 가져오기	//  / / / / //   
		public ArrayList<CmntDTO> getCmntList(int bd_num){
			
			ArrayList<CmntDTO> list = new ArrayList<CmntDTO>();
			
			try {
				String sql = "select level, cmnt_num, cmnt_bd, cmnt_id, cmnt_date, "
						+ "cmnt_parent, cmnt_content from MVC_board_comment "
						+ "where cmnt_bd = ? "
						+ "start with cmnt_parent = 0 "
						+ "connect by prior cmnt_num = cmnt_parent ";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,  bd_num);
				
				rs = pstmt.executeQuery();
				while(rs.next()) {
					
					CmntDTO dto = new CmntDTO();
					dto.setCmnt_level(rs.getInt("level"));
					dto.setCmnt_num(rs.getInt("cmnt_num"));
					dto.setCmnt_bd(rs.getInt("cmnt_bd"));
					dto.setCmnt_id(rs.getString("cmnt_id"));
					dto.setCmnt_date(rs.getDate("cmnt_date"));
					dto.setCmnt_parent(rs.getInt("cmnt_parent"));
					dto.setCmnt_content(rs.getString("cmnt_content"));
					list.add(dto);
					
				}
				
			} catch(Exception e) {
				System.out.println(" - - - 댓글 목록 가져오기 오류 DAO - - - -");
				e.printStackTrace();
			} 
			dbClose();
			return list;
		} 
		
		//  / / / / //   댓글 정보 가져오기 		//  / / / / //   
		public CmntDTO getCmnt(int cmnt_num) {
			CmntDTO cmnt = null;
			
			try {
				String sql = "select * from MVC_board_comment where cmnt_num = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,  cmnt_num);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					cmnt = new CmntDTO();
					cmnt.setCmnt_num(rs.getInt("cmnt_num"));
					cmnt.setCmnt_bd(rs.getInt("cmnt_bd"));
					cmnt.setCmnt_id(rs.getString("cmnt_id"));
					cmnt.setCmnt_date(rs.getDate("cmnt_date"));
					cmnt.setCmnt_parent(rs.getInt("cmnt_parent"));
					cmnt.setCmnt_content(rs.getString("cmnt_content"));
				}
				
			} catch(Exception e) {
				System.out.println(" - - - - - 댓글 정보 호출 오류 DAO - - - - - - ");
				e.printStackTrace();
			} 
			dbClose();
			return cmnt;
		}
		
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
