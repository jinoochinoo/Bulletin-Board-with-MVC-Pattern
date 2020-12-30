package board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import control.Command;
import control.Controller;
import db.BoardDAO;
import db.BoardDTO;

public class boardDetail implements Command{

	HttpServletRequest request;
	
	public boardDetail(HttpServletRequest request) {
		this.request = request;
	}
	
	@Override
	public int execute() {
		
		// 파라미터로 받은 글 번호 저장
		String num = request.getParameter("num");
		int bd_num = Integer.parseInt(num);
		String pageNum = request.getParameter("pageNum");
		
		// 저장한 값으로 DAO 메서드 실행, 결과값 저장
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.getDatil(bd_num);
		boolean result = dao.updateCnt(bd_num);
		
		// reply 정보 DB 처리해서 배열에 담기
		ArrayList<BoardDTO> reply = dao.getReplyList(dto);
		
		// 상세보기 때 사용할 dto, 되돌리기 때 필요한 pageNum 세션에 저장
		HttpSession session = request.getSession();
		session.setAttribute("dto", dto);
		session.setAttribute("pageNum", pageNum);
		session.setAttribute("reply", reply);
		
		if(result) {
			return Controller.TRUE;
		} else {
			return Controller.FALSE;
		}
	}
}
