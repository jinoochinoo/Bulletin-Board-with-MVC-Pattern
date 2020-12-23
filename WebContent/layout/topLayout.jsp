<%@ page import="user.SessionCheck"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JINWOO's MVC Board</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--  부트스트랩 참조 -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
>
</head>
<body>

	<div class="jumbotron text-center" style="margin-bottom: 0">
		<h1>My First Bulletin Board with MVC Pattern</h1>
		<h5>Thanks to visit and share your precious time!</h5>
	</div>


	<nav
		class="navbar navbar-expand-sm bg-dark navbar-dark 
navbar navbar-expand-sm bg-light justify-content-center"
	>
		<!-- Brand/logo -->
		<a class="navbar-brand" href="#"> <img src="C:\Users\user\eclipse-workspace\MVC_Board\WebContent\images\jinwoo.png"
			alt="logo" style="width: 40px;">
		</a>
		<%
			// 세션 및 쿠키 체크
		Cookie[] cookie = request.getCookies();
		boolean check = SessionCheck.loginCheck(session, cookie); // 저장된 세션, 쿠키 전달
		// TRUE 받으면 로그인 상태
		if (check) {
		%>
		<!-- 로그인 후 Links -->
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="dfdf">소개</a></li>
			<li class="nav-item"><a class="nav-link" href="dfdf">게시판</a></li>
			<li class="nav-item"><a class="nav-link" href="userInfo">회원정보</a></li>
			<li class="nav-item"><a class="nav-link" href="logout">로그아웃</a></li>
		</ul>

		<%
			// FALSE 받으면 로그아웃 상태
		} else {
		%>

		<!--  로그인 전 Links -->
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="dfdf">소개</a></li>
			<li class="nav-item"><a class="nav-link" href="dfdf">게시판</a></li>
			<li class="nav-item"><a class="nav-link" href="login">로그인</a></li>
			<li class="nav-item"><a class="nav-link" href="signUp">회원가입</a></li>
		</ul>
		<%
			}
		%>


	</nav>

</body>
</html>