package com.englishaccess.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.englishaccess.beans.User;
import com.englishaccess.dao.UserDao;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private UserDao userDao;
	

    @Override
    public void init() throws ServletException { // initialization constructor
    	//to interact with the bean
    	WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()); 
    	// object we want to retrieve. "userDao" is the ID for the `UserDao` bean in the Spring app context (e.g. defined in the xml file). 
    	// we cast it to (UserDao) type because context.getBean("String name") returns type `Object`
    	userDao = (UserDao) context.getBean("userDao"); 
    }


	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Register.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// collect form data
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String username = request.getParameter("email");
		String password = request.getParameter("password");
		
		// fill user data in a user bean
		User user = new User (firstName, lastName, username, password);
		
		try {
			if (userDao.isEmailUsed(username)){
				String message = "Error: This email is already in use.";
				request.setAttribute("errorType", "signup");
				request.setAttribute("error", message);
				request.getRequestDispatcher("index.jsp").forward(request, response);
				// return to exit out of doPost method after redirecting user.
				return;
			}
			if (userDao.registerUser(user)) {
				System.out.println("User successfully inserted.");
	            HttpSession session = request.getSession();
	            session.setAttribute("username", username);
	            session.setAttribute("userId", user.getUserID().toString());
	            response.sendRedirect("welcome.jsp");
			} else {
				String message = "Error: Failed to register user.";
				request.setAttribute("errorType", "signup");
				request.setAttribute("error", message);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			
		} catch (SQLException e) {
			  throw new ServletException(e); // sql exception aka error
		}
	}
	
}