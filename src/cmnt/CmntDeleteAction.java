package cmnt;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Command;
import control.Controller;

public class CmntDeleteAction implements Command{

	HttpServletRequest request;
	HttpServletResponse response;
	
	public CmntDeleteAction(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	@Override
	public int execute() {
		
		int cmnt_num = Integer.parseInt(request.getParameter("cmnt_num"));
		
		CmntDAO dao = new CmntDAO();
		int result = dao.deleteCmnt(cmnt_num);
		
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			// 정상적으로 댓글 삭제됐으면 0 반환
			if(result == Controller.TRUE) {
				out.println("0");
				out.close();
			} 
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
