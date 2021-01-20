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
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <style>
  .type1 {width: 100%;
    height: 100%;

}
  .type2 {top: 50%; position: relative; text-align: center;}
  </style>
</head>
<body>

	<div class="type1"
	style="background-image: 
	url(https://t1.daumcdn.net/cfile/tistory/997015505F51EA5618); height: 200px;">
		<div class="type2">
	<h1 style="color: #FFE400;"><STRONG style="color: #FFFFFF;">JSP Bulletin Board</STRONG>(feat. MVC Pattern)</h1>
		</div>
	</div>


	<nav
		class="navbar navbar-expand-sm bg-dark navbar-dark 
navbar navbar-expand-sm bg-light justify-content-center"
	>
		<!-- Brand/logo -->
		<a class="navbar-brand" href=".user"> <img src="
		https://avatars2.githubusercontent.com/u/73393147?s=460&u=a8db6559fd9b52d0dfc7bfd220431e305502873e&v=4"
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
			<li class="nav-item"><a class="nav-link" href="boardList.board">게시판</a></li>
			<li class="nav-item"><a class="nav-link" href="userInfo.user">회원정보</a></li>
			<li class="nav-item"><a class="nav-link" href="logout.user">로그아웃</a></li>
			<li class="nav-item"><a class="nav-link" href="intro.user">디버깅</a></li>
		</ul>

		<%
			// FALSE 받으면 로그아웃 상태
		} else {
		%>

		<!--  로그인 전 Links -->
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="boardList.board">게시판</a></li>
			<li class="nav-item"><a class="nav-link" href="login.user">로그인</a></li>
			<li class="nav-item"><a class="nav-link" href="signUp.user">회원가입</a></li>
			<li class="nav-item"><a class="nav-link" href="intro.user">디버깅</a></li>
		</ul>
		<%
			}
		%>


	</nav>

</body>
</html>
