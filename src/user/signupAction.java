package user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import control.Command;
import control.Controller;
import db.UserDAO;
import db.UserDTO;

public class signupAction implements Command{

	UserDTO dto;
	HttpServletRequest request;
	
	// 생성자
	public signupAction(HttpServletRequest request) {
		this.request = request;
		
		dto = new UserDTO();
		dto.setUserID(request.getParameter("userID"));
		dto.setFirstPassword(request.getParameter("firstPassword"));
		dto.setSecondPassword(request.getParameter("secondPassword"));
		dto.setEmail(request.getParameter("email"));
	}
	
	@Override
	public int execute() {

		// 결과값 담아줄 세션
		HttpSession session = request.getSession();
		
		if(!dto.getFirstPassword().equals(dto.getSecondPassword())) {
			
			session.setAttribute("Msg", "비밀번호 오류");
			return Controller.FALSE;
		}
		else if(dto.getUserID() == null || dto.getFirstPassword() == null ||
				dto.getSecondPassword() == null || dto.getEmail() == null){
				session.setAttribute("Msg", "빈칸 오류");
				return Controller.FALSE;
			}
		 else {
			
			//회원가입 절차 시작
			UserDAO dao = new UserDAO();
			int result = dao.signUp(dto);
			
			if(result == Controller.TRUE) {
				session.setAttribute("Msg", "회원가입 성공");
				return Controller.TRUE;
			} else if(result == Controller.FALSE) {
				session.setAttribute("Msg", "아이디 오류");
				return Controller.FALSE;
			} else {
				return Controller.EXCEPT;
			}
		}
	}
}
