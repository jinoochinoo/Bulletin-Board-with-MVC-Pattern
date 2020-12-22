<%@ page import="db.UserDTO" %>
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
  <h2>회원정보</h2>
  <p>아이디, 비밀번호, 이메일이 노출됩니다. 비밀번호 부분은 앞 2자리만 노출됩니다. 센스있게 :)</p>
  <table class="table">
    <thead class="thead-dark">
      <tr>
        <th>아이디</th>
        <th>비밀번호</th>
        <th>이메일</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td><%=dto.getUserID() %></td>
        <td><%=dto.getFirstPassword() %></td>
        <td><%=dto.getEmail() %></td>
      </tr>
    </tbody>
  </table>
</div>


	<br />
	<!-- bottom 레이아웃 삽입 -->
	<jsp:include page="/layout/bottomLayout.jsp" flush="false" />
</body>
</html>