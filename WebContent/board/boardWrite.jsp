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
	
	
<form action="boardWriteAction.board" method="post" enctype="multipart/form-data">
<input type="hidden" name="bdID" value="<%=session.getAttribute("userID")%>">
<table class="container col-lg-7">

	<tr><td>
	<input type="text" name="bdTitle" class="form-control text-center"
		placeholder="제목을 입력해주세요." required>
	</td></tr>
	<tr><td>
		<textarea class="form-control text-center" rows="5" name="bdContent"
			placeholder="내용을 입력해주세요" required 
		></textarea>
	</td></tr>

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