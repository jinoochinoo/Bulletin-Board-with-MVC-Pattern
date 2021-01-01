<%@page import="java.io.PrintWriter"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
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
<script type="text/javascript">
	function doAction(value) {
		if (value == 0) { // 수정
			location.href = "boardUpdateAsk.board"
		} 
		else if (value == 1) { // 삭제
			if (confirm("정말 삭제하시겠습니까?") == true) {
				location.href = "boardDeleteAction.board?bd_num=${dto.bd_num}"
			} else {
				return false;
			}
		} 
		else if (value == 2) { // 답글 추가
			location.href = "boardPut.jsp";
		}
	}
	
	function cmntReplyOpen(cmnt_num){

			window.name = "parentForm";
			window.open("CmntReplyForm.cmnt?num="+cmnt_num, 
								  	"replyForm", "width=570, heigh=350, resizable = no, scrollbars = no");
		
	}
</script>
</head>
<body>

	<!-- top 레이아웃 삽입 -->
	<jsp:include page="../layout/topLayout.jsp" flush="false" />
	<br />



	<form action="boardWriteAction.board" method="post"
		name="boardWriteAction" enctype="multipart/form-data"
	>

		<table class="container col-lg-9">
			<tr class="d-flex mb-3 text-center ">
				<td class="p-2 flex-fill bg-info">${dto.bd_date}</td>
				<td class="p-2 flex-fill bg-warning">${dto.bd_id}</td>
				<td class="p-2 flex-fill bg-primary">${dto.bd_cnt}hit</td>
			</tr>
			<tr>
				<td class="form-control text-center">${dto.bd_title}</td>
			</tr>
			<tr>
				<td class="form-control text-center">${dto.bd_content}</td>
			</tr>

			<tr class="form-control" align="center">
				<td id="title">첨부&nbsp; &nbsp;파일 &nbsp; &nbsp;</td>
				<td>
					<a href='FileDownload.board?file_name=${dto.bd_file}'>${dto.bd_file}</a>
				</td>
			</tr>
		</table>
	</form>

	<br />

	<!-- 댓글 부분 -->

	<div>

		<c:forEach var="reply" items="${sessionScope.cmntList}">
			<div class="container col-lg-8 input-group">

				<div class="form-control">
					<c:forEach begin="1" end="${reply.cmnt_level}"> &nbsp;  &nbsp; </c:forEach>
					${reply.cmnt_content}
				</div>
				<div class="input-group-prpend">
					<span class="input-group-text"  style="display: inline-block; width: 95%; text-align: center;">${reply.cmnt_id}	 &nbsp; 	<font size="2"  color="lightgray" style="display: inline-block; width: 95%; text-align: left;">${reply.cmnt_date}</font></span>
				</div>

				<div class="input-group-prpend">
				<c:if test="${sessionScope.userID != null}">
				<a href="#" class="badge badge-dark" onclick="cmntReplyOpen(${reply.cmnt_num})">&nbsp; 답변</a>
				</c:if>
				<!-- 댓글 작성자만 수정, 삭제 가능 (추후 추가) -->
				<c:if test="${sessionScope.userID == reply.cmnt_id}">
				<a href="#" class="badge badge-light">&nbsp; 수정</a>
				<a href="#" class="badge badge-info">&nbsp; 삭제</a>
				</c:if>

				</div>
			</div>
		</c:forEach>
	</div>

	<br />
	<form action="CmntWriteAction.cmnt" method="post">
	<c:if test="${sessionScope.userID != null}">

		<!-- 댓글 작성시 같이 넘겨줄 정보 -->
		<input type="hidden" name="cmnt_bd" value="${dto.bd_num}">
		<input type="hidden" name="cmnt_id" value="${sessionScope.userID}">

		<c:if test="${sessionScope.userID != null}">
			<div class="container col-lg-7 mt-2 input-group">
				<input type="text" class="form-control" placeholder="댓글"
					name="cmnt_content"
				>
				<div class="input-group-append">
					<button class="badge badge-success" type="submit" >작성</button>
				</div>
			</div>
		</c:if>
		
	</c:if>
	
	</form>
	<br />
	<table class="container col-lg-7">
		<tr class="text-center">
			<td>
				<c:if test="${sessionScope.userID != null}">
					<input type="submit" class="btn btn-dark mb-3" value="답글"
						onclick="doAction(2)"
					>
				</c:if>
				<c:if test="${sessionScope.userID == dto.bd_id}">
					<input type="submit" class="btn btn-primary mb-3" value="수정"
						onclick="doAction(0)"
					>
					<input type="button" class="btn btn-warning mb-3" value="삭제"
						onclick="doAction(1)"
					>
				</c:if>
				<input type="button" class="btn btn-info mb-3" value="목록"
					onclick="javascript:location.href='boardList.board?page=${pageNum}'"
				>
			</td>
		</tr>
	</table>


	<br />
	<!-- bottom 레이아웃 삽입 -->
	<jsp:include page="../layout/bottomLayout.jsp" flush="false" />

</body>
</html>