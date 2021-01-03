package cmnt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import control.Command;
import control.Controller;

public class CmntUpdateForm implements Command {

	HttpServletRequest request;
	
	public CmntUpdateForm(HttpServletRequest request) {
		this.request = request;
	}
	
	@Override
	public int execute() {
		
		int cmnt_num = Integer.parseInt(request.getParameter("num"));
		
		// 부모글 글 번호 저장
		CmntDAO dao = new CmntDAO();
		CmntDTO dto = dao.getCmnt(cmnt_num);
		
		// 댓글 정보 session 세팅
		HttpSession session = request.getSession();
		session.setAttribute("cmnt", dto);

		return Controller.TRUE;
	}

}