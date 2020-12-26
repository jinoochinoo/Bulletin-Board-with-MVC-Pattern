package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import control.Controller;

public class BoardDAO {

	private Context context;
	private DataSource datasource;
	private Connection conn; // DB 연결
	
	private Statement stmt; // 일반 쿼리
	private PreparedStatement pstmt; // 쿼리문 대기 및 설정
	private ResultSet rs; // 결과값 저장
	private HttpServletRequest request;
	
	// 기본 생성자
	// UserDAO 실행될 때 자동으로 DB 연결 
	public BoardDAO() {
		
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
	
	// - - - - - - - - 시퀀스 값 세팅 !! - - - - - - - - //
	
	public int setSeq() {
		
		int result = 0;
		
		try {
			String sql = "select bd_num.nextval from dual";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch(Exception e) {
			System.out.println("- - - 글쓰기 DAO 과정 실패 - - - ");
			e.printStackTrace();
		} return result;
	}
	
	// - - - - - - - - 글쓰기 !! - - - - - - - - //
	
	public int boardWriteAction(BoardDTO dto) {
		
		int result;
		
		try {
			String sql = "insert into MVC_board_board"
					+ "(bd_num, bd_id, bd_title, bd_content, bd_file, "
					+ "bd_re_ref, bd_re_lev, bd_re_seq, bd_cnt, bd_date)"
					+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,  dto.getBd_num());
			pstmt.setString(2,  dto.getBd_id());
			pstmt.setString(3,  dto.getBd_title());
			pstmt.setString(4, dto.getBd_content());
			pstmt.setString(5,  dto.getBd_file());
			pstmt.setInt(6, dto.getBd_num());
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);
			
			int flag = pstmt.executeUpdate();
			if(flag > 0) {
				result = Controller.TRUE;
				conn.commit();
				return result;
			}
		} catch(Exception e) {
			System.out.println(" - - - 글쓰기 DAO 실행 오류 - - - - ");
			e.printStackTrace();
		} finally {
			dbClose();
		} return Controller.EXCEPT;
	}
	
	// - - - - - - - - 글 목록 가져오기 !! - - - - - - - - //
	
	public ArrayList<BoardDTO> getBoardList(HashMap<String, Object> listOpt){
		
		// DB 저장된 게시글 담을 그릇
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		
		String opt = (String)listOpt.get("opt"); // 검색 옵션(제목, 내용, 글쓴이 등..)
		String condition = (String)listOpt.get("condition"); // 검색 내용
		int start = (Integer)listOpt.get("start"); // 현재 페이지번호
		
		try {
			
			// 글목록 전체 보여줄 때
			if(opt == null) {
				//bd_re_ref(그룹번호) 내림차순 정렬 후 
				//bd_re_seq(답변글 순서) 오름차순 정렬 후
				//글 10개 한 화면에 보여주도록 쿼리 설정
				
				String sql = "select * from "
						+ "(select rownum rnum, bd_num, bd_id, bd_title, bd_content, "
						+ "bd_file, bd_cnt, bd_re_ref, bd_re_lev, bd_re_seq, bd_date "
						+ "from "
						+ "(select * from MVC_board_board "
						+ "order by bd_re_ref desc, bd_re_seq asc)) "
						+ "where rnum >= ? and rnum <=?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,  start);
				pstmt.setInt(2,  start+9);
			}
			else if(opt.equals("제목")) { // 제목으로 검색
				
				String sql = "select * from "
						+ "(select rownum rnum, bd_num, bd_id, bd_title, bd_content, "
						+ "bd_file, bd_cnt, bd_re_ref, bd_re_lev, bd_re_seq, bd_date) "
						+ "from "
						+ "(select * from MVC_board_board where bd_title like ? "
						+ "order by bd_re_ref desc, bd_re_seq asc "
						+ "where rnum >= ? and rnum <=?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,  "%"+condition+"%");
				pstmt.setInt(2, start);
				pstmt.setInt(3,  start+9);
				
			}
			else if(opt.equals("내용")) { // 내용으로 검색
				
				String sql = "select * from "
						+ "(select rownum rnum, bd_num, bd_id, bd_title, bd_content, "
						+ "bd_file, bd_cnt, bd_re_ref, bd_re_lev, bd_re_seq, bd_date) "
						+ "from "
						+ "(select * from MVC_board_board where bd_content like ? "
						+ "order by bd_re_ref desc, bd_re_seq asc "
						+ "where rnum >= ? and rnum <=?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,  "%"+condition+"%");
				pstmt.setInt(2, start);
				pstmt.setInt(3,  start+9);
				
			}
			else if(opt.equals("제목+내용")) {
				
				String sql = "select * from "
						+ "(select rownum rnum, bd_num, bd_id, bd_title, bd_content, "
						+ "bd_file, bd_cnt, bd_re_ref, bd_re_lev, bd_re_seq, bd_date) "
						+ "from "
						+ "(select * from MVC_board_board where bd_title like ? or bd_content like ? "
						+ "order by bd_re_ref desc, bd_re_seq asc "
						+ "where rnum >= ? and rnum <=?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,  "%"+condition+"%"); // 제목에 검색 내용이 있거나
				pstmt.setString(2,  "%"+condition+"%"); // 내용에 검색 내용이 있거나
				pstmt.setInt(3, start);
				pstmt.setInt(4,  start+9);
				
			}
			else if(opt.equals("글쓴이")) {
				
				String sql = "select * from "
						+ "(select rownum rnum, bd_num, bd_id, bd_title, bd_content, "
						+ "bd_file, bd_cnt, bd_re_ref, bd_re_lev, bd_re_seq, bd_date) "
						+ "from "
						+ "(select * from MVC_board_board where bd_id like ? "
						+ "order by bd_re_ref desc, bd_re_seq asc "
						+ "where rnum >= ? and rnum <=?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,  "%"+condition+"%");
				pstmt.setInt(2, start);
				pstmt.setInt(3,  start+9);
			}
			
			// 쫙 ~ 검색한 것 담기
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBd_num(rs.getInt("bd_num"));
				dto.setBd_id(rs.getString("bd_id"));
				dto.setBd_title(rs.getString("bd_title"));
				dto.setBd_content(rs.getString("bd_content"));
				dto.setBd_file(rs.getString("bd_file"));
				dto.setBd_cnt(rs.getInt("bd_cnt"));
				dto.setBd_re_ref(rs.getInt("bd_re_ref"));
				dto.setBd_re_lev(rs.getInt("bd_re_lev"));
				dto.setBd_re_seq(rs.getInt("bd_re_seq"));
				dto.setBd_date(rs.getDate("bd_date"));
				list.add(dto);
				
			}
		} catch(Exception e) {
			System.out.println(" - - - - - 게시글 조회 DAO 오류 - - - - - ");
			e.printStackTrace();
		} finally {
			dbClose();
		} return list;
	}
	
	// - - - - - - - - 총 게시글 수 가져오기 !! - - - - - - - - //
	
	public int getBoardListCnt(HashMap<String, Object> listOpt) {
		
		int result = 0;
		String opt = (String)listOpt.get("opt"); // 검색옵션(제목, 내용, 글쓴이 등..)
		String condition = (String)listOpt.get("condition"); // 검색내용
		
		try {
			if(opt == null) { // 전체 게시글 호출
				
				String sql = "select count(*) from MVC_Board_Board";
				pstmt = conn.prepareStatement(sql);
				
			} else if(opt.equals("제목")) { // 제목 검색
				String sql = "select count(*) from MVC_Board_Board where bd_title like ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+condition+"%");
			} else if(opt.equals("내용")) { // 내용 검색
				String sql = "select count(*) from MVC_Board_Board where bd_content like ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+condition+"%");
			} else if(opt.equals("제목+내용")) {
				String sql = "select count(*) from MVC_Board_Board where "
						+ "bd_title like ? or bd_content like ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+condition+"%");
				pstmt.setString(2, "%"+condition+"%");
			} else if(opt.equals("글쓴이")) {
				String sql = "select count(*) from MVC_Board_Board where bd_id like ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+condition+"%");
			}
			
			rs = pstmt.executeQuery(); // SQL 실행 및 조건에 따른 게시글 수 담기
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch(Exception e) {
			System.out.println(" - - - - 전체 게시글 수 호출 오류 DAO  - - - - - ");
			e.printStackTrace();
		} return result;
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
