package user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import control.Command;
import control.Controller;
import db.UserDAO;
import db.UserDTO;

public class userUpdate implements Command{
	
	HttpServletRequest request;
	
	public userUpdate(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public int execute() {
		
		// request 전달된 데이터로 세션 호출;
		HttpSession session = request.getSession();

		//UserDTO dto = (UserDTO) session.getAttribute("userInfo");
		//System.out.println(dto.getUserID());
		
			if(session.getAttribute("userID") != null) {
				UserDAO dao = new UserDAO();
				int result = dao.userUpdate(request);
			
				session.setAttribute("Msg", "회원정보 수정 성공");
				return result;
			}
			else {
				session.setAttribute("Msg","회원정보 수정 오류");
				return Controller.FALSE;
			}
			
	}
}
