package cmnt;

import javax.servlet.http.HttpServletRequest;

import control.Command;
import control.Controller;

public class CmntReplyAction implements Command{

	HttpServletRequest request;
	
	public CmntReplyAction(HttpServletRequest request) {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int execute() {
		
		// 파라미터 호출
		int cmnt_num = Integer.parseInt(request.getParameter("cmnt_num"));
		int cmnt_bd = Integer.parseInt(request.getParameter("cmnt_bd"));
		String cmnt_id = request.getParameter("cmnt_id");
		String cmnt_content = request.getParameter("cmnt_content");
		
		CmntDAO dao = new CmntDAO();
		CmntDTO dto = new CmntDTO();
		
		dto.setCmnt_num(dao.getSeq());
		dto.setCmnt_bd(cmnt_bd);
		dto.setCmnt_id(cmnt_id);
		dto.setCmnt_content(cmnt_content);
		dto.setCmnt_parent(cmnt_num);
		
		int result = dao.insertCmnt(dto);
		
		return Controller.TRUE;
		
	}

}
