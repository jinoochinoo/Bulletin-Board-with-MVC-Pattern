package cmnt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Command;
import control.Controller;

public class CmntUpdateAction implements Command{

	HttpServletRequest request;
	HttpServletResponse response;
	
	public CmntUpdateAction(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	@Override
	public int execute() {

		CmntDAO dao = new CmntDAO();
		CmntDTO dto = new CmntDTO();
		
		// 파라미터 값 호출
		int cmnt_num = Integer.parseInt(request.getParameter("cmnt_num"));
		String cmnt_content = request.getParameter("cmnt_content");
		
		dto.setCmnt_num(cmnt_num);
		dto.setCmnt_content(cmnt_content);
		
		int result = dao.updateCmnt(dto);
		
		// 결과값 전송
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();

			// 정상적으로 댓글 저장됐으면 0 전달
			if(result == Controller.TRUE) {
				out.println("0");
			} else {
				out.println("1");
			}
			} catch(IOException e) {
				System.out.println(" - - - - getWrtier 호출 실패 - - - - - ");
				e.printStackTrace();
			}
		
		return result;
	}
}