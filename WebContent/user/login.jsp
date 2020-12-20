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
		<form action="#">
			<div class="form-group">
				<label for="userID">아이디</label>
				<input type="text" class="form-control" id="userID"
					placeholder="아이디를 입력하세요" name="userID">
			</div>
			<div class="form-group">
				<label for="password">비밀번호</label>
				<input type="password" class="form-control" id="password"
					placeholder="비밀번호를 입력하세요" name="password">
			</div>
			<div class="form-group form-check">
				<label class="form-check-label"> <input
						class="form-check-input" type="checkbox" name="remember"> 로그인 상태 유지
				</label>
			</div>
			<button type="submit" class="btn btn-primary">로그인</button>
		</form>
	</div>



	<br />
	<!-- bottom 레이아웃 삽입 -->
	<jsp:include page="/layout/bottomLayout.jsp" flush="false" />
</body>
</html>