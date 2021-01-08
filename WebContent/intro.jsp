<%@page import="java.io.PrintWriter"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JINWOO's MVC Board</title>

</head>
<body>

	<!-- top 레이아웃 삽입 -->
	<jsp:include page="layout/topLayout.jsp" flush="false" />
	<br />
	<div class="container" style="margin-top: 30px">
		<div class="row">
			<div class="col-sm-4">
				<h2 class="text-center">About Me</h2>
				<div class="text-center">
					<img
						src="C:\Users\user\eclipse-workspace\MVC_Board\WebContent\images\jinwoo.png"
						style="width: 200px;"
					>
				</div>
				<br/>
				<h5 class="btn btn-outline-warning btn-lg btn-block"><strong>URL 이력서</strong></h5>
				<ul class="nav nav-pills flex-column">

					<li class="nav-item text-center"><a class="nav-link" href="https://korean.kw.ac.kr/">국어국문학과, 영어영문학과 졸업</a>
					</li><br/>

					<li class="nav-item text-center"><a class="nav-link active" href="https://www.ekn.kr/web/view.php?key=423166">기자로 일했던 2년 1개월</a>
					</li><br/>
	
					<li class="nav-item text-center"><a class="nav-link" href="http://www.poongin.co.kr/">짧았던 패션회사 해외영업 인턴</a>
					</li><br/>
	
					<li class="nav-item text-center"><a class="nav-link active" href="https://github.com/jinoochinoo">개발자가 되기 위한 my GitHub</a>
					</li>
					</ul>
					<hr class="d-sm-none">
			</div>
			<div class="col-sm-8">
				<h4 class="text-center"><strong>에러, 디버깅, 그리고 해결...!</strong></h4>
				<br/>
				
				<h4 class="bg-primary text-white">MVC 모델1, 모델2 방식 섞어서 구현하기</h4>

				<p>애초 Ajax 통해 로그인, 회원가입 과정에서 경고창 띄우는 방식으로
				아이디 중복체크, 비밀번호 체크 하려했지만 컨트롤러 데이터 전송 과정에서 막힘.
				</p>
				<p>
				Spring 이용하면 구현 가능했지만 이해하지 못한 채 사용하는 게 싫어서 java 파일,
				즉 서버 쪽에서 오류 검사해 결과값만 session 통해 뷰로 전달하기로 결정.
				</p>
				<p>
				이 방식으로 세션 사용할 경우, 바로바로 invaliate() 해줘야 함. 세션 해제 안했다가
			    오류 계속 발생해서 살짝 당황 ;;; Msg 등록한 세션은 실행 이후 즉각 해제되도록 설정!
				</p>
				<br>

				<h4 class="bg-dark text-white">JDBC 통해 DB 연결 과정 중 오류 발생</h4>

				<p>클론코딩 MVC 모델 1, 모델 2 각각 서로 다르게 DB 연결. 첫 번째가 기본 생성자를 통해
				DB 연결했다면, 두 번째 방식은 servers 내부에 저장된 context.xml 파일에 데이터 정보를
				저장한 후 context.lookup 방식을 통해 연결
				</p>
				<p>
				각각 서로 다른 두 방식을 모두 사용하려다 마주한 오류들. mysql, oracle Class.forname
				명칭이 달라서 한참 헤맴. 그리고 Auto Commit 설정 해제하지 않아서 또 삽질.
				</p>
				<p>
				디버깅 통해 모두 해결했지만 소스 코드를 주석으로 처리해 userDAO.java 파일에 남겨두기로 결정.
				이후 같은 문제 방식할 경우, 참고할 계획.
				</p>
				<br>
				
				<h4 class="bg-secondary text-white">img 사진 엑박으로 표시</h4>

				<p>네비게이션 부부네 첨부한 사진 파일이 자꾸 엑박으로 뜸. src 코드 문제로 해당 파일이
				include 될 때마다 실행 위치가 달라서 이런 문제가 발생하는 것으로 파악.
				</p>
				<p>
				다른 폴더에서 실행될 때마다 새로 주소값 설정해야 하는 게 꽤 번거로운 작업. 해결책 찾던 중
				절대 주소값(C:드라이브부터 파일까지 경로) 전부 붙여넣기. 코드가 길어졌지만 엑박 문제 해결.
				</p>
				<br>
				
				<h4 class="bg-danger text-white">회원정보 수정 중 SQL 오류 발생</h4>

				<p>ResultSet rs 부분에 update SQL 실행 및 저장한 다음, userDTO 객체 기반으로 새로 dto 세션
				 설정하려 했지만 자꾸 SQL 관련 에러 마주함. rs.next() 메소드에서 자꾸 막힘.
				</p>
				<p>
				"인출 시퀀스가 틀립니다", "SQL 부적절합니다"
				</p>
				<p>
				뭐가 문제일지 한참 고민하다가 update SQL 명령문은 select 쿼리랑 다르게 따로 저장될 ㄱ ㅔ 없다란
				기본 중의 기본적인 사실을 인지. rs.next() 메소드 지우고 바로 dto 객체에 request.getParameter 값
				넣으니 정상 작동.
				</p>
				<br>
				
				<h4 class="bg-warning text-white">경고문구 띄울 때 여러 방식 적용</h4>

				<p>JSP 게시판 클론코딩을 여러 번 하던 중 서로 다른 방식으로 경고창 문구를 띄우는 것을 발견.
				클론코딩에서 배운 내용 다 적용하고자 코드 충돌 없는 선에서 최대한 다양한 방식으로 경고창 활용.
				</p>
				<p>
				같은 JSP 화면에서 javascript onclick 기능 통해 즉각적으로 점검하기도 했고, DB 데이터 점검 후
				별도 JSP 파일을 거쳐 요청 반환하면서 오류 메시지를 띄워주기도 함. 나중에 다른 사람들 코드를 보거나
				비교할 때 많은 도움이 될 것으로 믿음!
				</p>
				<br>
				
				<h4 class="bg-info text-white">이메일 주소를 @ 기준으로 쪼개서 호출</h4>

				<p>DB 저장된 이메일을 ID, Address 두 부분으로 쪼개서 호출할 때 substring(), StringTokenizer() 함수
				 활용해서 해결. 이전에 코딩테스트 연습했던 게 기억나서 다행. 다만, 코드가 좀 복잡해진 게 흠.
				</p>
				<br>
				
				<h4 class="bg-success text-white">조회수 구현 중 DB 커넥션 종료</h4>

				<p>"SQLRecoverableException: 접속 종료"
				</p>
				<p>
				조회수 호출 로직을 짜던 중 계속해서 DB 커넥션 종료 오류 발생. 새로운 DB 입력, 총 조회수 호출, 
				오늘 조회수 호출 등 한 번에 세 가지 메소드 다 수행하려다 문제 생김.
				</p>
				<p>
				앞서 첫 번째로 수행한 메소드에서 DB 커넥션을 습관처럼 dbClose() 통해 끊어서 다음 DB 접속이
				이뤄질 수 없었던 것. 처음으로 Listener 란 기능을 써봐서 좋은 경험.
				</p>
				<br>
				
				<h4 class="bg-primary text-white">검색값 유지 기능</h4>

				<p>검색, 페이징 기능 모두 구현했지만 두 기능 한 번에 적용하니 문제 발생. 다른 페이지로 넘어갈 때
				검색값이 없어지면서 검색된 페이지가 아니라 일반 페이지로 화면 전환. 당황 ;;;
				</p>
				<p>
				우선 Model 부분에서 세션에 전달된 검색값을 따로 저장. 이후 뷰단에서 세션에 저장된 검색값을 EL
				태그로 호출하면서 페이징 기능과 함께 넘겨주기로 결정. h.r.e.f="page=?&condition=?&opt=?" 식으로
				코드 작성.
				</p>
				<p>
				검색값을 유지하는 것은 option 태그 안에 c:if 태그 추가해 성립할 시 selected 추가되게 설정. 태그가 중복으로 되는 줄 
				모르고 일단 썼는데 자연스럽게 되길래 깜놀. 태그 안에서 select name="opt" 통해 name 값을 기억하게 해야 함.
				</p>
				<p>
				** 이후 기본적인 리스트 조회 부분에서 다시 오류 발생. condition opt 값 별도로 전하다 보니, 없으면 null 처리되는 게 아니라
				"" 빈 공백으로 전달돼서 문제 야기. 서버에서 request 이용해 값 받을 때 "" 공백일 경우, null 설정 별도로 추가해서 해결. 조심!
				</p>
				<br>
				
				<h4 class="bg-light text-dark">다운로드 구현</h4>

				<p>업로드 과정에서 not a directory 에러 자꾸 발생. 현기증 날 뻔;;; 경로 지정한 곳에 UploadFolder
				 생성하면 해결됐지만 서버 재시작 후 매번 다시 생성해야 하는 게 또 다른 문제로 떠오름 ㅠㅠ
				</p>
				<p>
				해결법 찾던 중 디렉토리 없으면 새로 생성 함수를 찾아서 적용. 이후 자동으로 파일 생성하면서
				 업로드 가능해짐. 
				</p>
				<p>
				첨부파일 업로드, 다운로드 부분은 처음한 작업이라서 클론코딩 수준으로 따라치기에 그침. 이후 스프링, 스프링부트 학습
				하면서 다시 차근차근 공부할 것. 진짜로!
				</p>
				<br>
				
				<h4 class="bg-dark text-white">댓글 작성 후 바로 새로고침 기능</h4>

				<p>참고 블로그 따라서 자바스크립트 이용해 window.opener.document.location.reload(); 방식으로
				새로고침 기능 쓰려고 했지만 수정한 결과값이 반영되지 않음. 어떻게 할까, 찾던 중 https://blog.naver.com/jingug1004/221194206790 
				블로그 발견. 여기서 힌트를 얻어서 opener.location.href = url; 방식으로 직접 url 짜집기하니 성공! 싱기방기
				</p>
				<br>
				
			</div>
		</div>
	</div>
	<br />
	<!-- bottom 레이아웃 삽입 -->
	<jsp:include page="layout/bottomLayout.jsp" flush="false" />
</body>
</html>