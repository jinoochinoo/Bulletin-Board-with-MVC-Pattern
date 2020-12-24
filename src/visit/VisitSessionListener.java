package visit;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class VisitSessionListener implements HttpSessionListener{

	public void sessionCreated(HttpSessionEvent sessionE) {
		if(sessionE.getSession().isNew()){
			execute(sessionE);
		}
	}
	
	private void execute(HttpSessionEvent sessionE) {
		
		VisitCountDAO dao = new VisitCountDAO();
		try {
			// 전체 방문자 수 증가
			dao.setTotalCount();
			// 총 방문자 수
			int totalCount = dao.getTotalCount();
			//오늘 방문자 수
			int todayCount = dao.getTodayCount();
			
			HttpSession session = sessionE.getSession();
			
			//세션에 방문자 수 담기
			session.setAttribute("totalCount", totalCount);
			session.setAttribute("todayCount", todayCount);
			
		} catch (Exception e) {
			System.out.println("------ 방문자 카운터 오류 ----");
			e.printStackTrace();
		}
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {}
}
