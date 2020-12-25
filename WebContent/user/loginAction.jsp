<%@page import="java.io.PrintWriter" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

			<%
			String msg = null;
				if(session.getAttribute("Msg") != null){
					msg = (String) session.getAttribute("Msg");
				}
				if(msg == "로그인 성공"){
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('로그인 성공!')");
					script.println("location.href='../main.jsp'");
					script.println("</script>");
				}
						
			%>

</body>
</html>