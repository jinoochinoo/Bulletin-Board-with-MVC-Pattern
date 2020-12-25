<%@page import="java.io.PrintWriter"%><%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JINWOO's MVC Board</title>
<script type="text/javascript">
	function writeForm(){
		location.href="boardWrite.board";
	}
</script>
</head>
<body>

	<!-- top 레이아웃 삽입 -->
	<jsp:include page="../layout/topLayout.jsp" flush="false" />
	<br />

<div class="container">
  <h2 class="text-center">게시판</h2>

<div id="searchForm" align="right">
	<form>
		<select name="opt" class="badge badge-pill badge-light">
			<option value="0">제목</option>
			<option value="1">내용</option>
			<option value="2">제목+내용</option>
			<option value="3">글쓴이</option>
		</select>
	  <input type="text" class="btn btn-outline-light text-dark col-sm-3" placeholder="Search">
	  <button class="btn btn-success btn-sm" type="submit">Go</button>
	</form>
</div>

  <table class="table">
    <thead class="table-primary text-center">
      <tr>
        <th style="width: 10%">번호</th>
        <th style="width: 50%">제목</th>
        <th style="width: 15%">글쓴이</th>
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
<br/>
  <div id="topForm" class="text-center">
  		<input type="button" class="btn btn-primary" value="글쓰기" onclick="writeForm()">
  </div>
<br/>
<div id="pageForm" class="text-center">
	페이지 번호
</div>
<br>



	<br />
	<!-- bottom 레이아웃 삽입 -->
	<jsp:include page="../layout/bottomLayout.jsp" flush="false" />

</body>
</html>