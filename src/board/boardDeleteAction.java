package board;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import control.Command;
import db.BoardDAO;

public class boardDeleteAction implements Command{

	HttpServletRequest  request;
	
	public boardDeleteAction(HttpServletRequest  request) {
		this.request = request;
	}
	
	@Override
	public int execute() {
		// 글 번호 호출
		int bd_num = Integer.parseInt(request.getParameter("bd_num"));
		
		// 글 삭제 // 연동된 댓글도 삭제
		BoardDAO dao = new BoardDAO();
		int result = dao.deleteBoard(bd_num);
		
		// 첨부된 파일 삭제
		String fileName = dao.getFileName(bd_num);
		
		if(fileName != null) {
			
			// 파일 위치한 절대경로 호출
			String folder = request.getServletContext().getRealPath("UploadFolder");
			// 절대경로 생성
			String filePath = folder + "/" + fileName;
			
			// 파일 지정, 존재하면 삭제 
			File file = new File(filePath);
			
			if(file.exists()) {
				file.delete();
			}
		}
		return result;
	}
}
