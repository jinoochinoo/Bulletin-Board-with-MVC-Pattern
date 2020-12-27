<%@page import="java.io.PrintWriter"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JINWOO's MVC Board</title>
<style>
      td {
        text-align: center;
      }
</style>
</head>
<body>

	<!-- top 레이아웃 삽입 -->
	<jsp:include page="../layout/topLayout.jsp" flush="false" />
	<br />
	


<form action="boardWriteAction.board" method="post" name="boardWriteAction"  enctype="multipart/form-data">

<table class="container col-lg-7">
 	<tr class="d-flex mb-3 text-center ">
	    <td class="p-2 flex-fill bg-info">${dto.bd_date}</td>
	    <td class="p-2 flex-fill bg-warning">${dto.bd_id}</td>
	    <td class="p-2 flex-fill bg-primary">${dto.bd_cnt} hit</td>
  	</tr>
	<tr><td class="form-control text-center">dfdf
			${dto.bd_title}
	</td></tr>
	<tr><td class="form-control text-center">dfdf
			${dto.bd_content}
	</td></tr>
	
	<tr class="form-control" align="center">
		<td id="title">파일첨부 &nbsp </td>
		<td>
			<a href='FileDownload.board?file_name=${dto.bd_file}'>${dto.bd_file}</a>
		</td>
	</tr>
	
	<tr class="text-center"><td>
	<input type="submit" class="btn btn-primary mb-3" value="수정">	
	<input type="button" class="btn btn-secondary mb-3" value="삭제">
	<input type="button" class="btn btn-secondary mb-3" value="답글">
	<input type="button" class="btn btn-secondary mb-3" value="목록"
		onclick="javascript:location.href='boardList.board?page=${pageNum}'">
	</td></tr>
	</table>
</form>


	<br />
	<!-- bottom 레이아웃 삽입 -->
	<jsp:include page="../layout/bottomLayout.jsp" flush="false" />

</body>
</html>