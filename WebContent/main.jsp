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

	<!-- 메인 페이지 영역 시작 -->
	<div class="container">
		<div class="jumbotron">
			<div class="container">
				<h1 style="text-align: center;"><strong>JSP 게시판 소개</strong></h1>
				<p class="text-center">JSP 게시판입니다.</p>
				<p class="text-center">DB 연결은 ORACLE, JDBC 이용해서 제작했습니다.</p>
				<p class="text-center">디자인 템플릿은 부트스트랩을 사용했고, javascript·css 부분은 인터넷에서 상당 부분 참고했습니다.</p>
				<p class="text-center">게시판 구현에 필요한 로직을 이해하고 익히기에 좋은 시간이었습니다.</p>
				<p class="text-center">감사합니다.</p>
				
			</div>
		</div>
	</div>

	<table class="container col-lg-7">
		<tr class="text-center">
			<td>
				<a style="text-align: center;"class="btn btn-primary btn-pull text-center" href="intro.user" role="button">자세히 알아보기</a>
			</td>
		</tr>
	</table>

		<br />
		<!-- bottom 레이아웃 삽입 -->
		<jsp:include page="layout/bottomLayout.jsp" flush="false" />
</body>
</html>