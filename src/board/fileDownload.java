package board;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Command;
import control.Controller;

public class fileDownload {

	HttpServletRequest request;
	HttpServletResponse response;
	
	public fileDownload(HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response = response;
	}

	public void download() {
	
		// 파라미터로 전달된 파일명 받기
		String fileName = request.getParameter("file_name");

		// 파일 저장된 서버의 물리적 경로 호출
		String folder = request.getServletContext().getRealPath("UploadFolder");
		
		// 파일 절대경로 만들기
		String filePath = folder + "/" + fileName;
		
		try {
			// 파일 크기 얻기 위한 파일객체 생성(다운로드시 프로그레스바 표시하기 위함)
			File file = new File(filePath);
			byte b[] = new byte[(int) file.length()];
			
			// 웹 브라우저가 인식하지 못하는 ContentType(MIME) 설정
			response.setContentType("application/octet-stream");

			// 파일명 한글 인코딩
			String encoding = new String(fileName.getBytes("UTF-8"), "8859_1");
			
			// 파일 링크 클릭하면 헤더에 설정한 다운로드 저장 화면 호출
			response.setHeader("Content-Disposition", "attachment;filename="+ encoding);
			response.setHeader("Content-Length", String.valueOf(file.length()));
			
			if(file.isFile()) { // 파일 있으면
				
				// 서버 속 파일에 연결할 입력 스트림 생성
				FileInputStream fileInputStream = new FileInputStream(file);
				// 웹 브라우저에 연결할 출력 스트림 생성
				ServletOutputStream servletOutputStream = response.getOutputStream();
				
				// 파일 읽어서 클라이언트 쪽으로 저장
				int readNum = 0;
				while((readNum = fileInputStream.read(b)) != -1) {
					servletOutputStream.write(b, 0, readNum);
				}

				// 연결 해제
				servletOutputStream.close();
				fileInputStream.close();
			}
		} catch(Exception e) {
			System.out.println(" - - - - - 파일 업로드 오류 Action - - - - - ");
			e.printStackTrace();
		} 
	}
}
