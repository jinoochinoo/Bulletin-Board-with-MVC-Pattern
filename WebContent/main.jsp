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
	<jsp:include page="layout/topLayout.jsp" flush="false" />
	<br />

	<!-- 메인 페이지 영역 시작 -->
	<div class="container">
		<div class="jumbotron">

				<table class="table" style="text-align: center;" >
					<thead>
					<tr>
						<th colspan="2" ><h2 style="text-align: center;"><strong>Front-End</strong></h2></th>
						<th colspan="3" ><h2 style="text-align: center;"><strong>Back-End</strong></h2></th>						
					</tr>
					</thead>
					<tbody>
					<tr>

						<td colspan="1" ><img src="https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F2370C63B5694884912" width=100 height=100></td>
						<td colspan="1" ><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/b/b2/Bootstrap_logo.svg/1200px-Bootstrap_logo.svg.png" width=100 height=100></td>					
						<td colspan="1" ><img src="https://perfectacle.github.io/images/Java-study-014day/thumb.png" width=100 height=100></td>
						<td colspan="1" ><img src="https://javatutorial.net/wp-content/uploads/2016/06/servlet-featured-image-1280x720.png" width=100 height=100></td>
						<td colspan="1" ><img src="https://t1.daumcdn.net/cfile/tistory/9912DA485AD6EF041E" width=100 height=100></td>					
				
					</tr>
					</tbody>
				</table>

		</div>
	</div>

	<table class="container col-lg-7">
		<tr class="text-center">
			<td>
				<a style="text-align: center;"class="btn btn-danger btn-pull text-center" href="https://github.com/jinoochinoo/JSP-MVC-BOARD" role="button">GitHub 소스코드</a>
			</td>
		</tr>
	</table>

		<br />
		<!-- bottom 레이아웃 삽입 -->
		<jsp:include page="layout/bottomLayout.jsp" flush="false" />
</body>
</html>
