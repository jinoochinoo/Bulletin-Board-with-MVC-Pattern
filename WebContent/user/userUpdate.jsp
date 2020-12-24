<%@ page import="db.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
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

	<!-- UserDTO 선언 및 세션값 호출 -->
	<%!UserDTO dto;%>
	<%
		dto = (UserDTO) session.getAttribute("userInfo");
	%>

	<div class="container">
		<form action="userUpdateAction" method="post">
			<h2>회원정보 수정</h2>
			<p>비밀번호, 이메일 주소 등을 수정할 수 있습니다.</p>
			<table class="table" class="col-lg-12">
				<thead class="thead-dark text-center">
					<tr>
						<th>아이디</th>
						<th>비밀번호</th>
						<th>비밀번호 확인</th>
						<th>이메일</th>
						<th>성별</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<!-- ID 라벨로 처리하고, hidden 데이터로 전송 -->
							<label><%=dto.getUserID()%></label>
							<input type="hidden" name="userID" value=<%=dto.getUserID()%>>
						</td>
						<td>
							<input type="password" class="form-control"
								placeholder="비밀번호를 입력하세요" name="firstPassword"
							>
						</td>
						<td>
							<input type="password" class="form-control"
								placeholder="확인 비밀번호를 입력하세요" name="secondPassword"
							>
						</td>
						<td>
							<input type="text" class="form-control" placeholder="이메일을 입력하세요"
								name="emailID" value="<%=dto.getEmailID()%>"><h4 class="text-center">@</h4>
								<select name="emailAddress" class="form-control">
									<option>naver.com</option>
									<option>daum.net</option>
									<option>gmail.com</option>
									<option>nate.com</option>
						</select>
						</td>
						
						<td>
						<input type="radio" name="gender" value="male" class="form-control" checked><h6 class="text-center">남자</h6>
						<input type="radio" name="gender" value="female" class="form-control"><h6 class="text-center">여자</h6>
						</td>
						
					</tr>
				</tbody>
			</table>
			<div class="text-center">
				<button type="submit" class="btn btn-primary">수정</button>
				<a class="btn btn-secondary" onclick="history.back()">취소</a>
			</div>		
		</form>
	</div>


	<!-- bottom 레이아웃 삽입 -->
	<br />
	<jsp:include page="/layout/bottomLayout.jsp" flush="false" />
</body>
</html>