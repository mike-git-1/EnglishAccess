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


@WebServlet("/add_note")
public class add_note extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DataSource dataSource; // we set this value to private the object

    @Override
    public void init() throws ServletException { // initialization constructor
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()); //to interact with the bean
        dataSource = (DataSource) context.getBean("dataSource"); // object we want to retrieve
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		if(username == null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not logged in .");
			return;
		}
		
		String notecontent1 = request.getParameter("content");
		
		try (Connection connection = dataSource.getConnection()){
			String sqlusercheck = "SELECT UserID FROM users WHERE Email = ?";
			PreparedStatement statement = connection.prepareStatement(sqlusercheck);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			int user_id = 0;
			
			if(rs.next()) {
				user_id = rs.getInt("UserID");
				
				
			} 
			else {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "USER not found. ");
				return;
			}
			
			
			
			String sqlnotecheck = "SELECT COUNT(*) FROM user_notes WHERE user_id = ? AND note_text = ?";
			PreparedStatement statement2 = connection.prepareStatement(sqlnotecheck);
			statement2.setInt(1, user_id);
			statement2.setString(2, notecontent1);
			ResultSet rs2 = statement2.executeQuery();
			if (rs2.next() && rs2.getInt(1) > 0) {
				response.setContentType("text/plain");
				response.getWriter().write("note already exists :" + notecontent1);
			}
			else {
			
					String insertnote = "INSERT INTO user_notes(note_text, user_id) VALUES (?, ?)";
					PreparedStatement statement3 = connection.prepareStatement(insertnote);
					statement3.setString(1, notecontent1 );
					statement3.setInt(2, user_id);
				    int rowsInserted = statement3.executeUpdate();
				    
				    if (rowsInserted > 0 && rowsInserted < 2) {
				    	response.setContentType("text/plain");
				    	response.getWriter().write(notecontent1);
				    	
				    }
				
					
				
				
			}
			
			rs.close();
			rs2.close();
			statement.close();
			statement2.close();
			
			
		 }  catch (SQLException e) {
	            throw new ServletException(e); 
	            }
			
			
			
			
			
		}
	}
		
	

