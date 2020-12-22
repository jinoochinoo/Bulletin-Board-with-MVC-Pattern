package user;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Command;
import control.Controller;

public class logoutAction implements Command{

	HttpServletRequest request;
	HttpServletResponse response;
	
	public logoutAction(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	@Override
	public int execute() {
		
		// 쿠키 정보 받기
		Cookie[] cookie = request.getCookies();
		
		// 쿠키 검색 및 삭제 과정
		if(cookie != null) {
			for(int i=0; i<cookie.length; i++) {
				if(cookie[i].getName().equals("userID")){
					cookie[i].setMaxAge(0); // 저장시간 초기화
					cookie[i].setPath("/"); // 주소값 초기화
					response.addCookie(cookie[i]); // 초기화된 쿠키 정보 저장
					break;
			}
		}
		
		// 세션 정보 받아서 삭제
			request.getSession().invalidate();
		// 쿠키, 세션 다 삭제되면 TRUE 리턴
			return Controller.TRUE;
		} 
		// 과정에서 오류 나면 FALSE 리턴
		return Controller.FALSE;
	}
}