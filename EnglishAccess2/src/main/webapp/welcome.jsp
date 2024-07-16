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
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="CSS/Main.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<title>Welcome</title>
</head>
<body>
 <div class="navbar">
	<h2>
		Welcome,
		<%= session.getAttribute("username") %>
		</h2>
		
	<button onclick="logout()" id="logout-button">Logout</button>
 </div>
	<div class="main">
	<div class="main-section">
	
	
	</div>

	<div class="utilities">
		

		<div class="dictionary-box">
			<div class="container">
				<h1 class="welcome" style="color:black;">Dictionary</h1>
				<span style="color:black;">Please enter a word : </span><input type="text" id="define"
					placeholder="Please enter a word">
				<button id="definition">definition</button>
				<section class="meanings-section">
					<section id="parts-of-speech"></section>
				</section>
				<audio controls>
					<source src="" type="audio/mpeg" id="source">
				</audio>

			</div>

		</div>

		<div class="notes-section1">
		<h1 id="notes-title">Enter a note:</h1>
		<textarea id="note-text" placeholder="Please enter a note..."></textarea>
		<div id="add-subtract-note">
		<span class="material-symbols-outlined"><a href="#" onclick="add_note()"  id="add-note">add</a></span>
		</div>
		<div id="notes-section">
			
		
		</div>
		
		</div>
		
		</div>
	</div>
	
	
	
	<div class="Next">
	<button>Next:</button>
	
	
	</div>
	




	<script type="text/javascript" src="JS/script.js"></script>
</body>
</html>