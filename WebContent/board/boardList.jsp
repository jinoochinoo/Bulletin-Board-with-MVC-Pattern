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
	<jsp:include page="../layout/topLayout.jsp" flush="false" />
	<br />

<div class="container">
  <h2>게시판</h2>
  <table class="table">
    <thead class="table-primary text-center">
      <tr>
        <th style="width: 10%">번호</th>
        <th style="width: 50%">제목</th>
        <th style="width: 15%">작성자</th>
        <th style="width: 15%">작성일</th>
        <th style="width: 10%">조회수</th>
      </tr>
    </thead>
    <tbody>
      <tr class="text-center">
        <td>0</td>
        <td>임시글이라고요! 임시!!</td>
        <td>진우쓰</td>
        <td>2019-10-10</td>
        <td>0</td>
      </tr>      
    </tbody>
  </table>
</div>



	<br />
	<!-- bottom 레이아웃 삽입 -->
	<jsp:include page="../layout/bottomLayout.jsp" flush="false" />

</body>
</html>