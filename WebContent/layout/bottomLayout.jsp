<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JINWOO's MVC Board</title>

</head>
<body>

<footer class="py-5 bg-dark">
	<div class="container">
		<br/>
			<label class="btn btn-primary">
		  		총 방문자   <span class="badge badge-light"><%=session.getAttribute("totalCount") %></span>
			</label>
	    <br/>
	    	<label class="btn btn-warning">
		  		오늘 방문자   <span class="badge badge-light"><%=session.getAttribute("todayCount") %></span>
			</label>
	    <br/>
		<p class="m-0 text-center text-white">Copyright &copy; JINWOO 2021</p>
	</div>
</footer>

</body>
</html>