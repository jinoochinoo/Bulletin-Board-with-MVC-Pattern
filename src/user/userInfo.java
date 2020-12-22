package user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import control.Command;
import control.Controller;
import db.UserDAO;

public class userInfo implements Command{
	
	HttpServletRequest request;
	
	public userInfo(HttpServletRequest request) {
		this.request = request;
	}
	
	
	@Override
	public int execute() {
		
		// 세션에 등록된 데이터 호출
		HttpSession session = request.getSession();
		
		// 이미 세션에 userInfo 정보가 담겨있다면 바로 RETURN
		if(session.getAttribute("userInfo") != null) {
			return Controller.TRUE;
		}
		
		// 로그인된 세션 이용해서 DB 자료 추출 ㄱㄱ싱
		else if(session.getAttribute("userID") != null) {
			UserDAO dao = new UserDAO();
			int result = dao.userInfo(request);
		
			// 결과값 그대로 리턴
			return result;
		}
		// 오류 발생
		return Controller.EXCEPT;
	}
}
