<%@page import="java.io.PrintWriter" %>
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
	<jsp:include page="topLayout.jsp" flush="false" />
	<br />
	
	
	<div class="container" style="margin-top: 30px">
		<div class="row">
			<div class="col-sm-4">
				<h3>Some Links</h3>
				<p>Github & Youtube URL for Portfolio</p>
				<ul class="nav nav-pills flex-column">
					<li class="nav-item"><a class="nav-link active" href="#">GitHub</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">Youtube</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Disable</a></li>
				</ul>
				<hr class="d-sm-none">
			</div>


	<div class="col-sm-8">
			<div class="container">
				<div class="jumbotron">
					<h1>MVC 패턴 게시판 포트폴리오 소개</h1>
					<h5>게시글 및 회원정보 CRUD 기능 구현에 초점을 맞춘 기본형 게시판.</h5>
				</div>
			</div>
		</div>
	</div>
</div>

			<%
			String msg = null;
				if(session.getAttribute("Msg") != null){
					msg = (String) session.getAttribute("Msg");
				}
				if(msg == "로그인 성공"){
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('로그인 성공!')");
					script.println("</script>");
				}
						
			%>
			
			<br />
			<!-- bottom 레이아웃 삽입 -->
			<jsp:include page="bottomLayout.jsp" flush="false" />
			
</body>
</html>