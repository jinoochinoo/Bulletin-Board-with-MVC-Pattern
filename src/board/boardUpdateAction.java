package board;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import control.Command;
import db.BoardDAO;
import db.BoardDTO;

public class boardUpdateAction implements Command{

	HttpServletRequest request;
	
	public boardUpdateAction(HttpServletRequest request) {
		this.request = request;
	}
	
	@Override
	public int execute() {
		
		int result = 0;
		
		// 게시글, 페이지 넘버 호출 // 원래 페이지로 돌아갈 때 필요
		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num"));
		
		// 새로 업로드할 파일 사이즈, 절대경로
		int fileSize = 5*1024*1024;
		String uploadPath = request.getServletContext().getRealPath("/UploadFolder");
		
		// 디렉토리 없으면 새로 생성
		File newFile = new File(uploadPath); 
		if (!newFile.isDirectory()) { 
			 newFile.mkdir();
			}
		
		try {
			MultipartRequest multi = new MultipartRequest
					(request, uploadPath, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			
			// 파라미터 값 호출 및 세팅
			String title = multi.getParameter("bdTitle");
			String content = multi.getParameter("bdContent");
			String existFile = multi.getParameter("existing_file");
			
			// 파라미터 값 자바빈 저장
			BoardDTO dto = new BoardDTO();
			dto.setBd_num(num);
			dto.setBd_title(title);
			dto.setBd_content(content);
			
			// 글 수정할 때 업로드된 파일 호출
			Enumeration<String> fileNames = multi.getFileNames();
			if(fileNames.hasMoreElements()) {
				String fileName = fileNames.nextElement();
				String updateFile = multi.getFilesystemName(fileName);
				
				if(updateFile == null) { // 수정할 때 새로운 파일 첨부 안 했으면 기존 파일 세팅
					dto.setBd_file(existFile);
				} else { // 새로 파일 첨부했을 경우
					dto.setBd_file(updateFile);
				}
			}
			
			// 저장한 객체로 DB 접근 ㄱㄱ
			BoardDAO dao = new BoardDAO();
			result = dao.updateBoard(dto);
					
			return result;
		} catch(Exception e) {
			System.out.println(" - - - 글 수정 오류 Action - - - - ");
			e.printStackTrace();
		}
		return result; 
	}
}
