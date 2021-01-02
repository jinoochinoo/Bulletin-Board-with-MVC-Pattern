package cmnt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Command;
import control.Controller;

public class CmntReplyAction implements Command{

	HttpServletRequest request;
	HttpServletResponse response;
	
	public CmntReplyAction(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
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
		
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			// 정상적으로 댓글 저장했으면 1 전달
			if(result == Controller.TRUE) {
				out.println("0");
			} else {
				System.out.println(" - - - result 값 오류 터짐 - - - - ");
				out.println("1");
			}
			
			out.close();
			
		} catch (IOException e) {
			System.out.println(" - - - - getWrtier 호출 실패 - - - - - ");
			e.printStackTrace();
		}
		
		return result;
		
	}

}
