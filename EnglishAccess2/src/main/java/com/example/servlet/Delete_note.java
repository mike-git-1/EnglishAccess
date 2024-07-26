package com.example.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet implementation class Delete_note
 */
@WebServlet("/Delete_note")
public class Delete_note extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	private DataSource dataSource; // we set this value to private the object

    @Override
    public void init() throws ServletException { // initialization constructor
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()); //to interact with the bean
        dataSource = (DataSource) context.getBean("dataSource"); // object we want to retrieve
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		if(username == null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not logged in .");
			return;
		}
		
		String removeNote = request.getParameter("NoteText");
		try (Connection connection = dataSource.getConnection()){
			String deleteQuery = "SELECT UserID FROM users WHERE Email = ?";
			PreparedStatement statement = connection.prepareStatement(deleteQuery);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			int user_id = 0;
			if (rs.next()) {
				user_id = rs.getInt("UserID");
				
			}
			
			
			
			else {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "USER not found. ");
				session.invalidate();
				return;
				
			}
			
			System.out.println("Deleting note for user_id: " + user_id + ", note_text: " + removeNote);

			
			String deleteNote = "DELETE FROM user_notes WHERE user_id = ? AND note_text = ?";
			PreparedStatement statement1 = connection.prepareStatement(deleteNote);
			statement1.setInt(1, user_id);
			statement1.setString(2, removeNote);
			
			int rowsAffected = statement1.executeUpdate();
			
			if (rowsAffected > 0) {
				response.getWriter().write("delete");
				
			}
			else {
				response.getWriter().write("sorry there was an issue on our side");
			}
			
			statement.close();
			connection.close();
			statement1.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().write("error");
		}
		
		
		
	}

}
