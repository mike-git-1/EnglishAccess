package com.englishaccess.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.englishaccess.beans.Note;
import com.englishaccess.dao.NotesDao;


@WebServlet("/add_note")
public class Add_note extends HttpServlet {
	
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
		String noteTitle = request.getParameter("title");
		String noteContent = request.getParameter("content");
		
		// fill note data in a note bean
		Note note = new Note(UUID.fromString(userId), noteTitle, noteContent);
		
		// interact with db to insert note
		try {
			notesDao.insertNote(note);
		} catch (SQLException e) {
			throw new ServletException("Error inserting note into the database",e); // sql exception aka error
		}
		response.sendRedirect("welcome.jsp");
	}
		
}

