<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="webapp/CSS/style.css">
<title>Login</title>
</head>
<body>
<div class="loginform">
<h2>Login</h2>
	<form action="Login" method="post">
		<label for="username">Username:</label>
		<input type="text" id="username" name="username"><br><br>
		<label for="password">Password</label>
		<input type="password" id="pass" name="password"><br><br>
		<input type="submit" value="Login">
		
	</form>
	<% if (request.getParameter("error") != null) {%>
	<p style="color:red;">Invalid UserName or password. </p>
	
	<%} %>
</div>
 	<script type="text/javascript" src="js/script.js"></script>
</body>
</html>