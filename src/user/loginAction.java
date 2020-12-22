package user;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import control.Command;
import control.Controller;
import db.UserDAO;

public class loginAction implements Command{

	HttpServletRequest request;
	HttpServletResponse response;
	
	public loginAction(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	@Override
	public int execute() {
		// 결과값 담을 세션
		HttpSession session = request.getSession();

						// 로그인 절차 시작 // 
		
		// 기본 생성자 통해 DB 연결 후 login() 메서드 실행
		UserDAO dao = new UserDAO();
		int result = dao.login(request);
		
		// 로그인 성공 시 세션 설정
		if(result == Controller.TRUE) {
			String userID = request.getParameter("userID");
			session.setAttribute("userID", userID);
			session.setAttribute("Msg", "로그인 성공");
			
			// 자동로그인 체크하면 쿠키 추가
			String remember = request.getParameter("remember");
			if(remember != null && remember.equals("true")) {
				Cookie cookie = new Cookie("userID", userID);
				cookie.setMaxAge(60*2); // 120초, 2분으로 쿠키 설정
				cookie.setPath("/"); // 쿠키 저장 위치
				response.addCookie(cookie); // 쿠키 저장
			}
			return Controller.TRUE;
		}	
		else if(result == Controller.FALSE) {
			session.setAttribute("Msg", "아이디 혹은 비밀번호 오류");
			return Controller.FALSE;
		}
		else {
			return Controller.EXCEPT;
		}
	}
}
