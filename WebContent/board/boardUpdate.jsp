<%@page import="java.io.PrintWriter"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JINWOO's MVC Board</title>
<script type="text/javascript">
	function back(){
		window.history.back();
	}
</script>

</head>
<body>

	<!-- top 레이아웃 삽입 -->
	<jsp:include page="../layout/topLayout.jsp" flush="false" />
	<br />
	
	
<form action="boardUpdateAction.board?num=${dto.bd_num}&pageNum=${pageNum}" method="post" enctype="multipart/form-data">
<input type="hidden" name="bdID" value="<%=session.getAttribute("userID")%>">
<input type="hidden" name="bd_num" value="${dto.bd_num}">
<input type="hidden" name="existing_file" value="${dto.bd_file}">
<table class="container col-lg-7">

	<tr><td>
	<input type="text" name="bdTitle" class="form-control text-center"
		placeholder="${dto.bd_title}" required>
	</td></tr>
	<tr><td>
		<textarea class="form-control text-center" rows="5" name="bdContent"
	 required>${dto.bd_content}</textarea>
	</td></tr>

	<tr class="form-control" align="center">
		<td id="title">기존 파일 &nbsp </td>
		<td>
			${dto.bd_file}
		</td>
	</tr>

	<tr class="form-control" align="center">
		<td id="title">파일첨부 &nbsp </td>
		<td>
		<input type="file" name="bdFile"/>
		</td>
	</tr>

	<tr class="text-center"><td>
	<input type="submit" class="btn btn-primary mb-3" value="등록">
	<input type="button" class="btn btn-secondary mb-3" value="취소" onclick="back()">
	</td></tr>
	</table>
</form>


	<br />
	<!-- bottom 레이아웃 삽입 -->
	<jsp:include page="../layout/bottomLayout.jsp" flush="false" />

</body>
</html>