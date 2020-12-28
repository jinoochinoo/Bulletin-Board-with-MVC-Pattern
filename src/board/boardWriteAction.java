package board;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import control.Command;
import db.BoardDAO;
import db.BoardDTO;

public class boardWriteAction implements Command{

	HttpServletRequest request;

	public boardWriteAction(HttpServletRequest request) {
		this.request = request;
	}
	
	@Override
	public int execute() {
		
		int result = 0;
		
		// MultipartRequest 객체 생성자의 인자들을 각각 변수로 만들어주기 ㄱㄱ!
		// 업로드 파일 사이즈
		int fileSize = 5*1024*1024;
		// 업로드 폴더 경로
		String uploadPath = request.getServletContext().getRealPath("/UploadFolder");
		// 인코딩 타입 설정
		
		// 디렉토리 없으면 새로 생성
		File newFile = new File(uploadPath); 
		if (!newFile.isDirectory()) { 
			 newFile.mkdir();
			}
		
		try {
			
			// 파일 업로드 // MultipartRequest 객체 생성
			MultipartRequest multi = new MultipartRequest 
					(request, uploadPath, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			// UTF-8 인코딩 타입 설정 // DefaultFileRenamePolicy 중복 파일명 재정의
			
			
			// 파일 이름 가져오기 절차 시작
			String fileName = "";
			Enumeration<String> names = multi.getFileNames();
			
			// 파일 이름 찾기
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
			dto.setBd_file(fileName);
			
			result = dao.boardWriteAction(dto);
			
			return result;
			
		} catch(Exception e) {
			System.out.println(" - - - 글쓰기 Action 오류 - - - -");
			e.printStackTrace();
			
		}  return result;
	} 

}
