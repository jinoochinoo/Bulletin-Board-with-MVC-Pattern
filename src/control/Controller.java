package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public Controller(){
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
		// 마지막 파일명만 남겨서 URI 생성
		String URI = TempURI[TempURI.length-1];
		
		String page = null; // 넘겨줄 페이지 문자열
		
		// 로그인 화면
		if(URI.equals("login")) {
			page = "/user/login.jsp";
		}

		// 회원가입 화면
		else if(URI.equals("signUp")){
			page = "/user/signUp.jsp";
		}
		
		// 회원가입 실행
		else if(URI.equals("signUpAction")){
			
			page = "/user/signUpAction.jsp";
			
			System.out.println("컨트롤러 통과함");
	}
		
		else if(URI.equals("/exception")) {
			page = "/exception/exception.jsp";
		}
		
		// 나머지 URI 전부 첫화면으로 전송
		else {
			page = "/layout/main.jsp";
		}
		
		response.sendRedirect("/MVC_Board"+page);
	}
}
