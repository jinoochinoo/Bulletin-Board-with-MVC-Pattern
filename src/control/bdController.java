package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.boardDetail;
import board.boardList;
import board.boardWriteAction;
import board.fileDownload;


@WebServlet("*.board")
public class bdController extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
	// command에서 리턴받을 종류
	public static final int TRUE = 0;
	public static final int FALSE = 1;
	public static final int EXCEPT = 2;
	
	public bdController(){
		super();
	}
	
    // Get, Post 전달 모두 doAction 부분에서 받아서 처리
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doAction(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doAction(request, response);
	}
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 전송된 주소 '/' 단위로 잘라서 각 배열에 배치
		String[] TempURI = request.getRequestURI().split("/");
		// 마지막 파일명만 URI 넣기
		String URI = TempURI[TempURI.length-1];


		String page = null; // 넘겨줄 페이지 문자열
		Command command = null; // 실행 커멘드 클래스 담당할 인터페이스
		
		// / / / / / / / / / / / / / / / / / / / / / 게시판리스트 /  / / / / / / / / / / / / / / / 
		if(URI.equals("boardList.board")) {
			
			command = new boardList(request);
			int result = command.execute();
			
			if(result == bdController.TRUE) {
				page = "/board/boardList.jsp";
			} else {
				page = "/exception.exception.jsp";
			}
		}
		
		
		// / / / / / / / / / / / / / / / 글쓰기 화면 이동 / / / / / / / / / / / / / / / /  
		else if(URI.equals("boardWrite.board")) {
			page = "/board/boardWrite.jsp";
		}
		
		// / / / / / / / / / / / / / / / / / / 글쓰기 등록  / / / / / / / / / / / / / / / / / 
		else if(URI.equals("boardWriteAction.board")) {
			
			command = new boardWriteAction(request);
			int result = command.execute();
			
			if(result == Controller.TRUE) {
				page="/boardList.board";
			} else {
				page = "/exception/exception.jsp";
			}
		}
		
		// / / / / / / / / / / / / / / / / / / 게시글 상세보기  / / / / / / / / / / / / / / / / / 
		
		else if(URI.equals("BoardDetail.board")) {
			
			command = new boardDetail(request);
			int result = command.execute();
			
			if(result == Controller.TRUE) {
				page = "/board/boardDetail.jsp";
			} else {
				page = "/exception/exception.jsp";
			}
		}
		
		// / / / / / / / / / / / / / / / / / / 파일 다운로드  / / / / / / / / / / / / / / / / / 
		
		else if(URI.equals("FileDownload.board")) {
			
			fileDownload fileDownload = new fileDownload(request, response);
			fileDownload.download();
			return;
		}
		
		// 나머지 URI 전부 첫화면으로 전송
		else {
			page = "/main.jsp";
		}

		response.sendRedirect("/MVC_Board"+page);

	}
}
