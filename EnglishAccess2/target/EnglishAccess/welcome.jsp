<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="javax.servlet.http.HttpSession" %>
<%
	HttpSession session1 = request.getSession(false);
	if (session == null || session.getAttribute("username") == null){
		response.sendRedirect("index.jsp");
		return;
	}
	


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>
	<h2>Welcome, <%= session.getAttribute("username") %></h2>
    <form action="Logout">
    
    
    </form>

</body>
</html>