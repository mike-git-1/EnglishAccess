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
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DataSource dataSource; // we set this value to private the object

    @Override
    public void init() throws ServletException { // initialization constructor
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()); //to interact with the bean
        dataSource = (DataSource) context.getBean("dataSource"); // object we want to retrieve
    }


	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Register.jsp");
	}
		

		

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String username = request.getParameter("email");
		String password = request.getParameter("password");
		
		   try (Connection connection = dataSource.getConnection()) {
	            System.out.println("Database connection established.");
		   String checkQuery = "SELECT COUNT(*) FROM users WHERE Email = ?";
           try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
               checkStmt.setString(1, username);
               try (ResultSet rs = checkStmt.executeQuery()) {
                   if (rs.next() && rs.getInt(1) > 0) {
                       String message = "Error: This email is already in use";
                       request.setAttribute("message", message);
                       request.getRequestDispatcher("Register.jsp").forward(request, response);
                       return;
                   }
               }
           }
           String sql = "INSERT INTO users (FirstName, LastName, Email, Password) VALUES(?, ?, ?, ?)";
           try (PreparedStatement statement = connection.prepareStatement(sql)) {
               statement.setString(1, firstname);
               statement.setString(2, lastname);
               statement.setString(3, username);
               statement.setString(4, password);
               int rowsInserted = statement.executeUpdate();

               if (rowsInserted > 0) {
                   System.out.println("User successfully inserted.");
                   HttpSession session = request.getSession();
                   session.setAttribute("username", username);
                   response.sendRedirect("welcome.jsp");
               } else {
                   System.out.println("No rows inserted.");
                   request.setAttribute("message", "Failed to register user.");
                   request.getRequestDispatcher("Register.jsp").forward(request, response);
               }
           }
       } catch (SQLException e) {
           e.printStackTrace();
           request.setAttribute("message", "SQL Error: " + e.getMessage());
           request.getRequestDispatcher("Register.jsp").forward(request, response);
       }
   }

   
}
		


