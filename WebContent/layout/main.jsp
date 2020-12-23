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
				  <h1 class="text-center">MVC 패턴 게시판</h1>
					  <h1 class="text-center mt-4">CRUD 기능만 구현한 <small>기본형 게시판</small></h1>
					  <h2 class="text-center">Model, Controller 부분은 <small>오라클, 서블릿!</small></h2>
					  <h3 class="text-center">View, 이미지 부분은 <small>부트스트랩, 자바스크립트!</small></h3>
					  <h4 class="text-center">자세한 제작 과정은 <small>소개 메뉴에서 확인해주쎄요!</small></h4>
					  
				</div>
			</div>
		</div>
	</div>
</div>
			
			<br />
			<!-- bottom 레이아웃 삽입 -->
			<jsp:include page="bottomLayout.jsp" flush="false" />
			
</body>
</html>