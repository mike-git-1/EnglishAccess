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

import com.englishaccess.dao.NotesDao;

/**
 * Servlet implementation class Delete_note
 */
@WebServlet("/Delete_note")
public class Delete_note extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private NotesDao notesDao;
    
    @Override
    public void init() throws ServletException { // initialization constructor
    	//to interact with the bean
    	WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()); 
    	// object we want to retrieve. "userDao" is the ID for the `UserDao` bean in the Spring app context (e.g. defined in the xml file). 
    	// we cast it to (UserDao) type because context.getBean("String name") returns type `Object`
    	notesDao = (NotesDao) context.getBean("notesDao"); 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// checking first to see if user is logged in. Redirects user to login form if not.
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		
		if(userId == null) {
			String message = "Error: You must be logged in to use this feature.";
			request.setAttribute("errorType", "login");
			request.setAttribute("error", message);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		
		// collect form data
		String noteid = request.getParameter("noteid");
				
		try {
			int rowsAffected = notesDao.deleteNote(noteid);
			if (rowsAffected > 0) {
				response.getWriter().write("delete");
				
			}
			else {
				response.getWriter().write("sorry there was an issue on our side");
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().write("error");
		}

	}

}
