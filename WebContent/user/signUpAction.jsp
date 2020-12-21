<%@page import="java.io.Console"%>
<%@page import="java.io.PrintWriter" %>
<%@page import="db.UserDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JINWOO's MVC Board</title>
</head>
<body>
 <%
 	request.setCharacterEncoding("utf-8");
 %>
 
<!-- 자바빈즈 생성 -->
<jsp:useBean id="user" class="db.UserDTO" scope="page"/>
<!-- 자바빈즈에 request 값 저장 -->
<jsp:setProperty name="user" property="userID" />
<jsp:setProperty name="user" property="firstPassword"/>
<jsp:setProperty name="user" property="secondPassword"/>
<jsp:setProperty name="user" property="email"/>

<% System.out.print(request.getAttribute("userID")); %>
<% System.out.print(request.getAttribute("firstPassword")); %>
<% System.out.print(request.getAttribute("secondPassword")); %>
<% System.out.print(request.getAttribute("email")); %>

	<%
	// 입력정보 체크
	if(user.getUserID() == null || user.getFirstPassword() == null ||
			user.getSecondPassword() == null || user.getEmail() == null){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('입력이 안 된 사항이 있습니다')");
		script.println("history.back()");
		script.println("</script>");
	}	
	if(user.getFirstPassword() != user.getSecondPassword()){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('비밀번호가 서로 다릅니다')");
			script.println("history.back()");
			script.println("</script>");
	}

			UserDAO userDAO = new UserDAO();
			int checkID = userDAO.checkID(user);
			if(checkID == 0){
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('이미 존재하는 아이디입니다')");
				script.println("history.back()");
				script.println("</script>");
			}
			
			int result = userDAO.signUP(user);
			if(result == 1){
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('로그인 성공!')");
				script.println("location.href='main.jsp'");
				script.println("</script>");
				
				//로그인 성공시 세션 부여
				session.setAttribute("userID", user.getUserID());
				
			}
	%>
</body>
</html>