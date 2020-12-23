package user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import control.Command;
import control.Controller;
import db.UserDAO;

public class userDeleteAction implements Command{

	HttpServletRequest request;
	
	public userDeleteAction(HttpServletRequest request) {
		this.request = request;
	}
	
	@Override
	public int execute() {
		 
			// DB 정보 삭제
			UserDAO dao = new UserDAO();
			int result = dao.userDelete(request);
			return result;
			} 
	}
