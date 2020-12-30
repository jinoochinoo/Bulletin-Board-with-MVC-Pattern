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
	function doAction(value){
		if(value == 0){
			location.href="boardUpdateAsk.board"
		} // 수정
		else if(value == 1){
			if(confirm("정말 삭제하시겠습니까?") == true){
				location.href="boardDeleteAction.board?bd_num=${dto.bd_num}"
			} else{
				return false;
			}
		} // 삭제
		else if(value == 2){
			location.href="boardPut.jsp";
		}
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
	<div>
		<c:forEach var="reply" items="${sessionScope.reply}">
			<div class="container col-lg-8 input-group">
				<c:if test="${reply.bd_re_lev > 0}">
					<div class="form-control">
						<c:forEach begin="1" end="${reply.bd_re_lev}"> &nbsp;  &nbsp; </c:forEach>
						${reply.bd_content}
					</div>
				</c:if>
				<div class="input-group-prpend">
					<span class="input-group-text">${reply.bd_id}</span>
				</div>

			</div>
		</c:forEach>
	</div>

	<br />


	<form
		action="BoardReplyAction.board?page=${pageNum}&bdNum=${dto.bd_num}"
		method="post"
	>

		<!-- 댓글 작성시 같이 넘겨줄 정보 -->
		<input type="hidden" name="bd_id" value="${sessionScope.userID}">
		<input type="hidden" name="bd_num" value="${dto.bd_num}">
		<input type="hidden" name="bd_re_ref" value="${dto.bd_re_ref}">
		<input type="hidden" name="bd_re_lev" value="${dto.bd_re_lev}">
		<input type="hidden" name="bd_re_seq" value="${dto.bd_re_seq}">

		<c:if test="${sessionScope.userID != null}">
			<div class="container col-lg-7 mt-2 input-group">
				<input type="text" class="form-control" placeholder="댓글"
					name="bd_re_content"
				>
				<div class="input-group-append">
					<button class="badge badge-success" type="submit">작성</button>
				</div>
			</div>
		</c:if>

	</form>

	<br />
	<table class="container col-lg-7">
		<tr class="text-center">
			<td>
			<c:if test="${sessionScope.userID == dto.bd_id}">
				<input type="submit" class="btn btn-dark mb-3" value="답글"
					onclick="doAction(2)"
				>
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