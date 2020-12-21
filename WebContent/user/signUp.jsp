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
		<h2>회원가입</h2>
			<div class="row">
				<div class="col-lg-6">
					<form action="signUpAction" method="post">
						
					<div class="input-group mt-3 mb-1">
						<div class="input-group-prpend">
							<span class="input-group-text">아이디</span>
						</div>
						<input type="text" name="userID" class="form-control"
							placeholder="아이디를 입력하세요" required>
					</div>
					
						<!-- 아이디 중복확인 구현 -->
						
					<div class="input-group mt-1 mb-1">
						<div class="input-group-prpend">
							<span class="input-group-text">비밀번호</span>
						</div>
						<input type="password" name="firstPassword" class="form-control"
							placeholder="비밀번호를 입력하세요" required>
					</div>
					
					<div class="input-group mt-1 mb-1">
						<div class="input-group-prpend">
							<span class="input-group-text">비밀번호 확인</span>
						</div>
						<input type="password" name="secondPassword" class="form-control"
							placeholder="비밀번호를 다시 입력하세요" required>
					</div>
					
					<div class="input-group mt-1 mb-1">
						<div class="input-group-prpend">
							<span class="input-group-text">이메일</span>
						</div>
						<input type="email" name="email" class="form-control"
							placeholder="이메일을 입력하세요" required>
					</div>

		
			<button type="submit" class="btn btn-primary form-control">회원가입</button>
		</form>
	</div>
</div>
</div>

			<br />
			<!-- bottom 레이아웃 삽입 -->
			<jsp:include page="/layout/bottomLayout.jsp" flush="false" />
</body>
</html>