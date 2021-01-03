package board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cmnt.CmntDAO;
import cmnt.CmntDTO;
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
		CmntDAO cmntDAO = new CmntDAO();
		ArrayList<CmntDTO> cmntList = cmntDAO.getCmntList(bd_num);
		
		// session 부분에 cmntList 세팅
		HttpSession session = request.getSession();
		
		session.setAttribute("cmntList", cmntList);
		session.setAttribute("pageNum", pageNum);
		session.setAttribute("bd_num",bd_num);
		session.setAttribute("dto", dto);

		if(result) {
			return Controller.TRUE;
		} else {
			return Controller.FALSE;
		}
	}
}
