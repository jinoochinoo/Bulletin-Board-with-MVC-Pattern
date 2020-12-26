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
    <c:forEach var="board" items="${sessionScope.list}">
      <tr class="text-center">
        <td>${board.bd_num}</td>
        <td>
        <a href="BoardDetailAction.board?num=${board.bd_num}&pageNum=${pageNum}">${board.bd_title}</a>
        </td>
        <td>${board.bd_id}</td>
        <td>${board.bd_date}</td>
        <td>${board.bd_cnt}</td>
      </tr>
      </c:forEach>
    </tbody>
  </table>
</div>
<br/>

	<c:if test="${sessionScope.userID!=null}">
  		<div id="topForm" class="text-center">
  			<input type="button" class="btn btn-primary" value="글쓰기" onclick="writeForm()">
  		</div>
 	 </c:if>
  
<br/>
<div id="pageForm" class="container">
	<ul class="pagination justify-content-center">

	 <c:if test="${startPage != 1}">
            <li class="page-item"><a class="page-link" href='boardList.board?page=${startPage-1}'>Prev</a></li>
        </c:if>
        
        <c:forEach var="pageNum" begin="${firstPage}" end="${fifthPage}">
            <c:if test="${pageNum == startPage}">
                <li class="page-item page-link text-dark" >${pageNum}&nbsp;</li>
            </c:if>
            <c:if test="${pageNum != startPage}">
                <li class="page-item"><a class="page-link" href='boardList.board?page=${pageNum}'>${pageNum}&nbsp;</a></li>
            </c:if>
        </c:forEach>
        
        <c:if test="${fifthPage != maxPage }">
           <li class="page-item"><a class="page-link" href='boardList.board?page=${fifthPage+1 }'>Next</a></li>
        </c:if>

	   </ul>
</div>

	<br />
	<!-- bottom 레이아웃 삽입 -->
	<jsp:include page="../layout/bottomLayout.jsp" flush="false" />

</body>
</html>