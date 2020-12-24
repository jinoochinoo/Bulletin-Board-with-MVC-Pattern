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
	
	

<form action="/board/writerAction" method="post">
<table class="container col-lg-7">
	<tr><td>
	<input type="text" name="bdTitle" class="form-control text-center"
		placeholder="제목을 입력해주세요." required>
	</td></tr>
	<tr><td>
		<textarea class="form-control text-center" rows="5" name="bdContent"
			placeholder="내용을 입력해주세요"  style="text-align: center; vertical-align: middle;" required 
		></textarea>
	</td></tr>
	
	<tr class="form-control">
		<td id="title">파일첨부  </td>
		<td>
		<input type="file" name="bdFile"/>
		</td>
	</tr>
	
	<tr class="text-center"><td>
	<input type="submit" class="btn btn-primary mb-3" value="등록">
	<input type="button" class="btn btn-secondary mb-3" value="취소">
	</td></tr>
	</table>
</form>


	<br />
	<!-- bottom 레이아웃 삽입 -->
	<jsp:include page="../layout/bottomLayout.jsp" flush="false" />

</body>
</html>