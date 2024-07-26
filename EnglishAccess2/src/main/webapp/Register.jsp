<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="CSS/style.css">
<title>Register</title>
</head>
<body>
<div class="loginSection">
<img src="Images/english access.png" id="logo" alt="English Access Logo">
<h2 class="login-head">Register</h2>
	<form action="Register" method="post" class="register-form">
	<label for="firstname">First Name:</label>
	<input type="text" id="firstname" name="firstname" required>
	<label for="lastname">Last Name:</label>
	<input type="text" id="lastname" name="lastname" required>
	<label for="email">Email Address:</label>
	<input type="text" id="email" name="email" required>
	<label for="password">Password:</label>
	<input type="password" id="password" name="password" required>
	<div class="register-button-container">
	<input type="submit" value="Register" id="register-button-1">
	<button type="button" onclick="location.href='index.jsp'" id="login-redirect">Login</button>
	</div>
	<p>${message}</p>
	
	</form>
	
</div>
 	<script type="text/javascript" src="JS/script.js"></script>
</body>
</html>