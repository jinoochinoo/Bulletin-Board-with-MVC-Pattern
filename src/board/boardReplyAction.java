package board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import control.Command;
import control.bdController;
import db.BoardDAO;
import db.BoardDTO;

public class boardReplyAction implements Command{

	HttpServletRequest request;

	public boardReplyAction(HttpServletRequest request) {
		this.request = request;
	}
	
	@Override
	public int execute() {

		BoardDAO dao = new BoardDAO();
		BoardDTO dto = new BoardDTO();
		
		// 작성 답성 후 원래 페이지로 돌아가기 위해 필요한 페이지 번호
		String pageNum = request.getParameter("page");
		
		// 파라미터 값 호출
		String id = request.getParameter("bd_id");
		String reContent = request.getParameter("bd_re_content");
		int ref = Integer.parseInt(request.getParameter("bd_re_ref"));
		int lev = Integer.parseInt(request.getParameter("bd_re_lev"));
		int seq = Integer.parseInt(request.getParameter("bd_re_seq")); 
		
		// 가장 최근 답글이 상위에 노출되게 답글 순서 seq + 1
		dto.setBd_re_ref(ref);
		dto.setBd_re_seq(seq);
		dao.updateReSeq(dto);
		
		// 답글 저장
		dto.setBd_num(dao.setSeq()); // 댓글 번호 seq 값 새로 가져와 세팅
		dto.setBd_id(id);
		dto.setBd_content(reContent);
		dto.setBd_re_ref(ref);
		dto.setBd_re_lev(lev+1);
		dto.setBd_re_seq(seq+1);
		
		int result = dao.boardWriteAction(dto);
		
		if(result == bdController.TRUE) { // 댓글 쓰기 완료되면
		
		// reply 정보 DB 처리해서 배열에 담기
	//	ArrayList<BoardDTO> reply = dao.getReplyList(dto);
		
		// 뷰단 전송 위해 세션에 reply 정보 저장
	//	HttpSession session = request.getSession();
	//	session.setAttribute("reply", reply);
		
		return bdController.TRUE;
		}else {
			return bdController.FALSE;
		}
	}
}
