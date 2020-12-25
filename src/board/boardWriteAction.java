package board;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import control.Command;
import control.Controller;
import db.BoardDAO;
import db.BoardDTO;

public class boardWriteAction implements Command{

	HttpServletRequest request;

	public boardWriteAction(HttpServletRequest request) {
		this.request = request;
	}
	
	@Override
	public int execute() {
		
		// 업로드 파일 사이즈
		int fileSize = 5*1024*1024;
		// 업로드 폴더 경로
		String uploadPath = request.getServletContext().getRealPath("/UploadFolder");
		
		int result = 0;
		try {
			
			//파일 업로드
			MultipartRequest multi = new MultipartRequest 
					(request, uploadPath, fileSize, "UTF-8", new DefaultFileRenamePolicy());

			//파일 이름 가져오기
			String fileName = "";
			Enumeration<String> names = multi.getFileNames();
			if(names.hasMoreElements()) {
				String name = names.nextElement();
				fileName = multi.getFilesystemName(name);
			}
			
			BoardDAO dao = new BoardDAO();
			BoardDTO dto = new BoardDTO();
			
			dto.setBd_num(dao.setSeq());
			dto.setBd_id(multi.getParameter("bdID"));
			dto.setBd_title(multi.getParameter("bdTitle"));
			dto.setBd_content(multi.getParameter("bdContent"));
			dto.setBd_file(multi.getParameter("bdFile"));
			
			result = dao.boardWriteAction(dto);
			
			return result;
			
		} catch(Exception e) {
			System.out.println(" - - - 글쓰기 Action 오류 - - - -");
			e.printStackTrace();
			
		}  return result;
	} 

}
