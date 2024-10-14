package com.englishaccess.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


import com.englishaccess.beans.Note;
import com.englishaccess.dao.NotesDao;



@WebServlet("/fetch_notes")
public class FetchNotes extends HttpServlet{
	

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
	
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		  
		try {
			
			Map<UUID, Note> notes = notesDao.readAllNotes();
			
			// used to convert java objects into JSON
			Gson gson = new Gson();
			//  returns a Collection<Note> containing only the Note objects from the Map<UUID, Note>. This excludes the keys (UUIDs) and gives you a collection of the values alone.
	        String jsonNotes = gson.toJson(notes.values());
	        
           
	        // obtain printwriter object
            PrintWriter out = response.getWriter();
            
            // use printwriter to Write JSON to HTTP response body to the client
            out.print(jsonNotes);
            
            // Ensure all buffered data is sent to the client immediately
            out.flush();
            
		}catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to fetch notes"); 
		}
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
	}
	
	
}