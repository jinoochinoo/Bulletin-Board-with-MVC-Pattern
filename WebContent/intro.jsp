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
				
				<h4 class="bg-light text-dark">MVC 모델1, 모델2 방식 섞어서 구현하기</h4>

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

				<h4 class="bg-dark text-white">MVC 모델1, 모델2 방식 섞어서 구현하기</h4>

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
			</div>
		</div>
	</div>
	<br />
	<!-- bottom 레이아웃 삽입 -->
	<jsp:include page="layout/bottomLayout.jsp" flush="false" />
</body>
</html>