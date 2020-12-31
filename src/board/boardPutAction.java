package board;

import javax.servlet.http.HttpServletRequest;

import control.Command;
import db.BoardDAO;
import db.BoardDTO;

public class boardPutAction implements Command {

	HttpServletRequest request;
	
	public boardPutAction(HttpServletRequest request) {
		this.request = request;
	};
	
	@Override
	public int execute() {
		// TODO Auto-generated method stub
		
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = new BoardDTO();
		
		// 답글 관련 파라미터 호출
		int bd_num = Integer.parseInt(request.getParameter("bd_num"));
		String bd_id = request.getParameter("bd_id");
		String title = request.getParameter("bd_title");
		String content = request.getParameter("bd_content");
		int ref = Integer.parseInt(request.getParameter("bd_re_ref"));
	

		// 답글 저장
		dto.setBd_num(dao.setSeq()); // 답글 번호 seq 값 새로 가져와 세팅
		dto.setBd_id(bd_id);
		dto.setBd_title(title);
		dto.setBd_content(content);
		dto.setBd_re_ref(ref);
		dto.setBd_parent(bd_num);

		
		int result = dao.boardWriteAction(dto);
		return result;
	}
}
