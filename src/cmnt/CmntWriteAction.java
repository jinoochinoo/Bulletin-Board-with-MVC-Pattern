package cmnt;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import control.Command;
import control.Controller;

public class CmntWriteAction implements Command{

	HttpServletRequest request;
	
	public CmntWriteAction(HttpServletRequest request) {
		this.request = request;
	}
	
	@Override
	public int execute() {
		
		CmntDAO dao = new CmntDAO();
		CmntDTO dto = new CmntDTO();
		
		// 파라미터 값 호출
		int cmnt_bd = Integer.parseInt(request.getParameter("cmnt_bd"));
		String cmnt_id = request.getParameter("cmnt_id");
		String cmnt_content = request.getParameter("cmnt_content");
		
		dto.setCmnt_num(dao.getSeq());
		dto.setCmnt_bd(cmnt_bd);
		dto.setCmnt_id(cmnt_id);
		dto.setCmnt_content(cmnt_content);
		
		int result = dao.insertCmnt(dto);
		
		return result;
	}
}
