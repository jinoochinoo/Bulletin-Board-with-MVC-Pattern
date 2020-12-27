package board;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import control.Command;
import control.bdController;
import db.BoardDAO;
import db.BoardDTO;

public class boardList implements Command{

	HttpServletRequest request;
	
	public boardList(HttpServletRequest request) {
		this.request = request;
	}
	
	@Override
	public int execute() {
		
		// 페이지 번호 세팅
		int startPage = 1;
		
		// request 통한 방식은 string 등록밖에 안 돼서 parsing 해야 댐
		String page = request.getParameter("page");
		if(page != null) {
			startPage = Integer.parseInt(page);
		}
		
		// 검색 조건과 내용 호출
		String opt = request.getParameter("opt");
		String condition = request.getParameter("condition");

		// 검색 조건과 내용 HashMap 담기
		// 이름-값 호출로 훨씬 호출 쉽고, 따로 넘기는 게 아니라 Map 담아서 한꺼번에 옮겨서 편함
		HashMap<String, Object> listOpt = new HashMap<String, Object>();
		listOpt.put("opt", opt);
		listOpt.put("condition", condition);
		listOpt.put("start",  startPage * 10 - 9);
		
		//검색값은 세션에 추가해서 list.jsp 뷰단에서 활용
		HttpSession session = request.getSession();
		session.setAttribute("opt", opt);
		session.setAttribute("condition", condition);
		
		// DB 연결, 메소드 실행, 목록 및 게시글 수 받기
		BoardDAO dao = new BoardDAO();
		int listCnt = dao.getBoardListCnt(listOpt);
		ArrayList<BoardDTO> list = dao.getBoardList(listOpt);
		
		// 한 화면에 게시글 10개
		// 페이징 5개, 다음 화살표 별도 표시
		
		// 전체 페이지 수
		int maxPage = (int)(listCnt/10.0 + 0.9);
		// 마지막 페이지
		int fifthPage = startPage + 4;
		if(fifthPage > maxPage) fifthPage = maxPage;
		// 시작 페이지
		int firstPage = fifthPage - 4;
		if(firstPage<0) firstPage = 1;
		
		// JSP 뷰단까지 전달할 객체 세션에 저장
		// 각 페이지 번호 저장
		session.setAttribute("startPage", startPage);
		session.setAttribute("maxPage", maxPage);
		session.setAttribute("firstPage", firstPage);
		session.setAttribute("fifthPage", fifthPage);
	
		// 게시글 총 수, 게시글 목록 저장
		session.setAttribute("listCnt", listCnt);
		session.setAttribute("list", list);
		
		return bdController.TRUE;
	}

}
