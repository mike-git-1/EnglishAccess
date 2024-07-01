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
<div class="loginform">
<img src="Images/english access.png" id="logo" alt="English Access Logo">
<h2>Login</h2>
	<form action="Login" method="post" class="login">
		<label for="username">Username:</label>
		<input type="text" id="username" name="username"><br><br>
		<label for="password">Password: </label>
		<input type="password" id="pass" name="password"><br><br>
		<input type="submit" value="Login" class="login-button">
		<button class="register">Register</button>

		
	</form>
	<form action="Register" method="post" class="Register">
	<label for="firstname">First Name:</label>
	<input type="text" id="firstname" name="firstname"><br><br>
	<label for="lastname">Last Name:</label>
	<input type="text" id="lastname" name="lastname"><br><br>
	<label for="email">Email Address:</label>
	<input type="text" id="email" name="email"><br><br>
	<label for="password">Password:</label>
	<input type="password" id="password" name="firstname"><br><br> <!--  I'm hiding this for now - we will use it with javascript modifications -->
	
	
	
	
	
	</form>
	<% if (request.getParameter("error") != null) {%>
	<p class="warning" style="color:red;">Invalid Username or password. </p>
	
	<%} %>
</div>
 	<script type="text/javascript" src="JS/script.js"></script>
</body>
</html>