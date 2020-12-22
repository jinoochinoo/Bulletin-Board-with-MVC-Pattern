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
	<jsp:include page="/layout/topLayout.jsp" flush="false" />
	<br />

	<div class="container">
		<h2>로그인</h2>
		<form action="loginAction" method="post">
			<div class="form-group">
				<label for="userID">아이디</label>
				<input type="text" class="form-control" 
					placeholder="아이디를 입력하세요" name="userID">
			</div>
			<div class="form-group">
				<label for="password">비밀번호</label>
				<input type="password" class="form-control" 
					placeholder="비밀번호를 입력하세요" name="firstPassword">
			</div>
			<div class="form-group form-check">
				<label class="form-check-label"> <input
						class="form-check-input" type="checkbox" name="remember"> 로그인 상태 유지
				</label>
			</div>
			<button type="submit" class="btn btn-primary">로그인</button>
		</form>
	</div>

			<%
			String msg = null;
			if(session.getAttribute("Msg") != null){
				msg = (String)session.getAttribute("Msg");
			}
			
			if(msg == "회원가입 성공"){
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('회원가입 성공! 로그인 해주세요')");
				script.println("</script>");
				session.invalidate();
			}
			
			if(msg == "아이디 혹은 비밀번호 오류"){
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('아이디 혹은 비밀번호가 맞지 않습니다!')");
				script.println("</script>");
				session.invalidate();
			}
			
			%>
	<br />
	<!-- bottom 레이아웃 삽입 -->
	<jsp:include page="/layout/bottomLayout.jsp" flush="false" />
</body>
</html>