package user;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

	// 세션 및 쿠키 있는지 확인하기 위한 클래스
public class SessionCheck {

	public static boolean loginCheck(HttpSession session, Cookie[] cookie) {
		
		// userID 세션 등록됐으면 바로 TRUE 반환
		if(session.getAttribute("userID") != null) {
			return true;
			// 세션 없으면 쿠키 확인
		} else if(cookie != null) {
			for(int i=0; i<cookie.length; i++) {
				// userID 쿠키 있으면 TRUE
				if(cookie[i].getName().equals("userID")) {
					System.out.println(cookie[i].getPath()); // 이거 왜 함?
					// 쿠키 정보를 세션에 추가
					session.setAttribute("userID", cookie[i].getValue());
					return true;
				}
			}
		}
		//세션, 쿠키 둘 다 없을 때 FALSE
		return false;
	}
}
