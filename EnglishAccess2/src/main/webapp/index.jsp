<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="CSS/style.css">
<title>English Access</title>
<link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
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

</head>

<body>



<% String error = (String) request.getAttribute("error");
   String errorType = (String) request.getAttribute("errorType");
%>

 <header>
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
	   <button class="login-btn">LOG IN</button>
	 </nav>
</header>
    <div class="blur-bg-overlay"></div>
    <div class="form-popup">
      
      <span class="close-btn material-symbols-rounded">close</span>

      <!-- LOGIN -->
      <div class="form-box login">
        <div class="form-details">
          <h2>Welcome Back</h2>
          <p>Please sign in with your login details to get started.</p>
          <p>"Expand your World, Expand your Mind..."</p>
        </div>
        <div class="form-content">
          <h2>LOGIN</h2>
          <form action="Login" method="post">
            <div class="input-field">
           	  <input type="text" id="username" name="username" required class="username">
              <label for="username">Username</label>
            </div>
            <div class="input-field">
              <input type="password" id="pass" name="password" required>
              <label for="password">Password</label>
              
              <!-- If error parameter of type 'login' exists, toggle on the show-popup class-->
            		  
              <% if (error!= null) {
              		if ("login".equals(errorType)) {%>
						<div class="warning" ><%= error %> </div>
						<script>
							document.body.classList.add("show-popup");
						</script>
			  	  <%}%>
			  <%}%>
			  
            </div>
            <a href="#" class="forgot-pass">Forgot password?</a>
            <button type="submit" value="Login">Log In</button>
          </form>
          <div class="bottom-link">
            Don't have an account?
            <a href="#" id="signup-link">Signup</a>
          </div>
        </div>
      </div>
      
      <!-- SIGNUP -->
      <div class="form-box signup">
        <div class="form-details">
          <h2>Create Account</h2>
          <p>
            To become part of our community, please sign up using your personal
            information.
          </p>
        </div>
        <div class="form-content">
          <h2>SIGNUP</h2>
          <form action="Register" method="post">
            <div class="input-field">
              <input type="text" id="" name="firstname" required>
              <label for="firstname">First Name</label>
            </div>
            <div class="input-field">
              <input type="text" id="lastname" name="lastname" required>
              <label for="lastname">Last Name</label>
            </div>
            <div class="input-field">
              <input type="text" id="email" name="email" required>
              <label for="email">Email Address</label>
            </div>
            <div class="input-field">
              <input type="password"  id="password" name="password" required />
              <label for="password">Password</label>
              
               <!-- If error parameter of type 'signup' exists, toggle on the show-signup class-->
               <!-- "show-popup" reveals the form, "show-signup" reveals specifically the signup form-->
              <% if (error!= null) {
              		if ("signup".equals(errorType)) {%>
						<div class="warning" ><%= error %> </div>
						<script>
							document.body.classList.add("show-popup");
							document.querySelector(".form-popup").classList.add("show-signup");
						</script>
			 	  <%}%>
			  <%}%>
			  
            </div>
            <div class="policy-text">
              <input type="checkbox" id="policy" />
              <label for="policy">
                I agree to the
                <a href="#">Terms &amp; Conditions</a>
              </label>
            </div>
            <button type="submit">Sign Up</button>
          </form>
          <div class="bottom-link">
            Already have an account?
            <a href="#" id="login-link">Login</a>
          </div>
        </div>
      </div>
 
    </div>

	    <div class="stars"></div>

    <section class="home">
      <div class="text-box">
        <h1>Welcome to EnglishAccess!</h1>
        <h1>Begin Your Journey</h1>
        <p>
          Blast off into a universe of knowledge with EnglishAccess, your ultimate
          dictionary companion! Whether you're charting the stars of your
          vocabulary or navigating the cosmic expanse of language, our app is
          here to guide you through the vastness of words and meanings.
        </p>
        <span class="btn-box">
          <button type="button" class="btn">Join Now</button>
          <button type="button" class="btn">Contact Us</button>
        </span>
      </div>
      	<img src="Images/person.png" class="person" alt="" />
      	<img src="Images/rocket.png" class="rocket" alt="" />
    </section>

 	<script type="text/javascript" src="JS/script.js"></script>
 	
</body>

</html>