<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="javax.servlet.http.HttpSession" %>
<%
	//prevents browser from caching the page. 
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

	HttpSession session1 = request.getSession(false);
	if (session == null || session.getAttribute("userId") == null){
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
	<link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
    />
    <link
      rel="stylesheet"
      href="https://unicons.iconscout.com/release/v4.0.0/css/line.css"
    />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Poppins&display=swap"
      rel="stylesheet"
    />
     <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"
    />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
    />

</head>

<body>
	<header class="main-header">
	  <nav class="navbar">
	    <!-- hamburger icon -->
	    <span class="menu-btn material-symbols-rounded">menu</span>
	    <a href="#" class="logo">
	      <img src="Images/english access.png" alt="logo" />
	      <h1>English Access</h1>
	    </a>
	    <ul class="links">
	      <span class="close-btn material-symbols-rounded">close</span>
	      <li><a href="#">Home</a></li>
	      <li><a href="#">Profile</a></li>
	      <li><a href="#">About Us</a></li>
	      <li><a href="#">Contact Us</a></li>
	    </ul>
	    <button onclick="logout()" class="login-btn">LOG OUT</button>
	  </nav>
	</header>
	
	<div class="intro">
	  <h1>Welcome, <%= session.getAttribute("username") %>!</h1>
	  <!--<button onclick="logout()" id="logout-button">Logout</button>-->
	</div>
	
	<div class="main">
	  <div class="main-section"></div>
	  <div class="utilities">
	    <div class="dictionary-box">
		  <audio class="audioPlayer">
	          <source src="" type="audio/mpeg" id="source" />
	      </audio>
	      <div class="container">
	        <h2 class="welcome" style="color: black">Dictionary</h2>
	        <!--<span style="color: black">Please enter a word : </span>-->
    		<div class="content">
     			<div class="search-box">
		       		<input type="text" placeholder="Type your word here" id="define" />
		        	<button id="definition">Search</button>
      			</div>
 
    		</div>
	        <!-- <input type="text" id="define" placeholder="Please enter a word" />
	        <button id="definition">definition</button>-->
	        <section class="meanings-section" id="meanings-section">
	          <!-- <section id="parts-of-speech"></section> -->
	        </section>
	      </div>
	    </div>
	    
  		<div class="popup-box">
	      <div class="popup">
	        <div class="content">
	          <header class="note-header">
	            <p>Add a new Note</p>
	            <!-- the cancel button  -->
	        	<i class="uil uil-times"></i>
	          </header>
	          <fieldset>
	            <div class="row title">
	              <label for="">Title</label>
	              <input type="text" maxlength="50" required/>
	            </div>
	            <div class="row description">
	              <label for="">Description</label>
	              <textarea required></textarea>
	            </div>
	            <button onclick="add_note()">Add Note</button>
	          </fieldset>
	        </div>
	      </div>
	    </div>	
  
	    <div class="wrapper">
	      <li class="add-box">
	        <div class="icon">
	          <i class="uil uil-plus"></i>
	        </div>
	        <p>Add new note</p>
	      </li>
	    </div>
    
	   <!--<div class="notes-section1">
	      <h1 id="notes-title">Enter a note:</h1>
	      <textarea id="note-text" placeholder="Please enter a note..."></textarea>
	      <div id="add-subtract-note">
	        <span class="material-symbols-outlined"
	          ><a href="#" onclick="add_note()" id="add-note">add</a></span
	        >
	      </div>
	      <div id="notes-section"></div>-->
	      
	    </div>
	  </div>
	<div class="Next">
	  <button>Next:</button>
	</div>
	
	
	



	<script type="text/javascript" src="JS/main.js"></script>
</body>
</html>