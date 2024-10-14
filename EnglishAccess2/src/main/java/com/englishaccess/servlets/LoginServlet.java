package com.englishaccess.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.web.context.support.WebApplicationContextUtils;

import com.englishaccess.dao.UserDao;

import org.springframework.web.context.WebApplicationContext;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
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
    	
    	// collect form data
        String username = request.getParameter("username"); // values we want to retrieve from the index.jsp file
        String password = request.getParameter("password");
        
      
        try { 
        	if (userDao.isUserExists(username, password)) {
        		request.getSession().setAttribute("username", username); // set session with username
        		UUID userId = userDao.selectUser(username);
        		request.getSession().setAttribute("userId", userId.toString()); // set session with userid
                response.sendRedirect("welcome.jsp"); //redirect;
        	} else {
            	request.setAttribute("errorType", "login");
            	request.setAttribute("error", "Invalid username or password.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        	           
        } catch (SQLException e) {
            throw new ServletException(e); // sql exception aka error
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
