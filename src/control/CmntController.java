package control;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cmnt.CmntReplyAction;
import cmnt.CmntReplyForm;
import cmnt.CmntWriteAction;


@WebServlet("*.cmnt")
public class CmntController extends HttpServlet {

	private static final long serialVersionUID = 1L;
    private HashMap<String, Command> commandMap;
	
	public CmntController(){
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
		
		// / / / / / / / / / / / / / / 댓글 작성 / / / / / / / / / / / //		
		if(URI.equals("CmntWriteAction.cmnt")) {
			
			command = new CmntWriteAction(request);
			int result = command.execute();
			
			if(result == Controller.TRUE) {
				page = "/boardList.board";
			} else {
				page = "/exception/exception.jsp";
			}
		}
		
		else if(URI.equals("CmntReplyForm.cmnt")) {
			command = new CmntReplyForm(request);
			int result = command.execute();
			
			if(result == Controller.TRUE) {
				page = "/board/CmntReplyForm.jsp";
			} else {
				page = "/exception/exception.jsp";
			}
		}
		
		else if(URI.equals("CmntReplyAction.cmnt")) {
			
			command = new CmntReplyAction(request, response);
			command.execute();
			return;
			
	//		if(result == Controller.TRUE) {
	//			page = "/boardList.board";
	//		} else {
	//			page = "/exception/exception.jsp";
	//		}
		}
		
		
		// 나머지 URI 모두 첫화면으로 전송
		else {
			page = "/main.jsp";
		}
		
		response.sendRedirect("/MVC_Board"+page);

	}
}
