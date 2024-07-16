<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="CSS/style.css">
<title>Login</title>
</head>
<body>
<div class="loginSection">
<img src="Images/english access.png" id="logo" alt="English Access Logo">
<h2 class="login-head">Login</h2>
   <div class="loginform">
	<form action="Login" method="post" class="login">
		<label for="username">Username:</label> 
		<input type="text" id="username" name="username">
		<label for="password">Password: </label>
		<input type="password" id="pass" name="password">
		<div class="button-container">
		<input type="submit" value="Login" class="login-button">
		<button type="button" onclick="location.href='Register.jsp'" class="register-button">Register</button>
		</div>
	</form>
	   
	<% if (request.getParameter("error") != null) {%>
	<p class="warning" style="color:red;">Invalid User-name or password. </p>
	
	<%} %>
	</div>
	
</div>
 	<script type="text/javascript" src="JS/script.js"></script>
</body>
</html>