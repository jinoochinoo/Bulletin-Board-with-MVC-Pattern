package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.loginAction;
import user.logoutAction;
import user.signupAction;
import user.userDeleteAction;
import user.userInfo;
import user.userUpdate;


@WebServlet("/")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	// command에서 리턴받을 종류
	public static final int TRUE = 0;
	public static final int FALSE = 1;
	public static final int EXCEPT = 2;
	
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
		Command command = null; // 실행 커멘드 클래스 담당할 인터페이스
		
		// 로그인 화면
		if(URI.equals("login")) {
			page = "/user/login.jsp";
		}

		// 회원가입 화면
		else if(URI.equals("signUp")){
			page = "/user/signUp.jsp";
		}
		
		// 회원정보 수정
		else if(URI.equals("userUpdate")) {
			page = "/user/userUpdate.jsp";
		}
		
		// 게시판 화면

		
		/*  / / / / / / / / / / / / / / / / / / / / / 회원가입 부분   / / / / / / / / / / / / / / / / / */
		// 회원가입 실행
		else if(URI.equals("signUpAction")){
			
			command = new signupAction(request);
			int result = command.execute();
			
			// TRUE 뜨면 메인 화면으로
			if(result == Controller.TRUE) {
				page = "/user/login.jsp";
			}
			// FLASE 뜨면 로그인 페이지로
			if(result == Controller.FALSE) {
				page = "/user/signUp.jsp";
			}
			// EXCEPT 뜨면 에러 페이지로
			if(result == Controller.EXCEPT)
			page = "/exception/exception.jsp";
			}
		
		else if(URI.equals("/exception")) {
			page = "/exception/exception.jsp";
		}
		
		/*  / / / / / / / / / / / / / / / / / / / / / 로그인 부분   / / / / / / / / / / / / / / / / / */

		else if(URI.equals("loginAction")) {
			command = new loginAction(request, response);
			int result = command.execute();
			
			// 성공
			if(result == Controller.TRUE) {
				page = "/user/loginAction.jsp";
			}
			// 실패
			else if(result == Controller.FALSE) {
				page = "/user/login.jsp";
			}
			else {
				page = "/exception/exception.jsp";
			}
		}
		
		/*  / / / / / / / / / / / / / / / / / / / / / 로그아웃 부분   / / / / / / / / / / / / / / / / / */

		else if(URI.equals("logout")) {
			command = new logoutAction(request, response);
			int result = command.execute();
			if(result == Controller.TRUE) {
				page = "/layout/main.jsp";
			} else {
				page = "/exception/exception.jsp";
			}
		}
		
		/*  / / / / / / / / / / / / / / / / / / / / / 회원정보 조회  / / / / / / / / / / / / / / / / / */
		
		else if(URI.equals("userInfo")) {
			command = new userInfo(request);
			int result = command.execute();
			
			if(result == Controller.TRUE) {
				page = "/user/userInfo.jsp";
			} else {
				page = "/exception/exception.jsp";
			}
		}
		
		/*  / / / / / / / / / / / / / / / / / / / / / 회원정보 수정  / / / / / / / / / / / / / / / / / */
		
		else if(URI.equals("userUpdateAction")) {

			command = new userUpdate(request);
			int result = command.execute();
			
			if(result == Controller.TRUE) {
				page = "/user/updateAction.jsp";
			} else {
				page = "exception/exception.jsp";
			}
		}
		
		/*  / / / / / / / / / / / / / / / / / / / / / 회원정보 삭제  / / / / / / / / / / / / / / / / / */
		
		else if(URI.equals("userDelete")) {
			
			command = new userDeleteAction(request);
			int result = command.execute();
			
			if(result == Controller.TRUE) {
				page = "/layout/main.jsp";
			} else {
				page = "exception/exception.jsp";
			}
		}
		
		// 나머지 URI 전부 첫화면으로 전송
		else {
			page = "/layout/main.jsp";
		}
		
		response.sendRedirect("/MVC_Board"+page);
	}
}
