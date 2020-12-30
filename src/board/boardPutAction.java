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
		
		// 답글 작성 후 원래 페이지로 돌아가기 위한 페이지 번호
		String pageNum = request.getParameter("pageNum");
		
		// 답글 관련 파라미터 호출
		String bd_id = request.getParameter("bd_id");
		String title = request.getParameter("bd_title");
		String content = request.getParameter("bd_content");
		int ref = Integer.parseInt(request.getParameter("bd_re_ref"));
		int lev = Integer.parseInt(request.getParameter("bd_re_lev"));
		int seq = Integer.parseInt(request.getParameter("bd_re_seq"));
		
		// 가장 최근 답글이 상위에 노출되게 답글 순서 seq + 1
		dto.setBd_re_ref(ref);
		dto.setBd_re_seq(seq);
		dao.updateReSeq(dto);
		
		// 답글 저장
		dto.setBd_num(dao.setSeq()); // 답글 번호 seq 값 새로 가져와 세팅
		dto.setBd_id(bd_id);
		dto.setBd_content(content);
		dto.setBd_re_ref(ref);
		dto.setBd_re_lev(lev+1);
		dto.setBd_re_seq(seq+1);
		
		int result = dao.boardWriteAction(dto);
		
		return result;
	}
}
