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

import javax.sql.DataSource;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.WebApplicationContext;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DataSource dataSource; // we set this value to private the object

    @Override
    public void init() throws ServletException { // initialization constructor
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()); //to interact with the bean
        dataSource = (DataSource) context.getBean("dataSource"); // object we want to retrieve
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username"); // values we want to retrieve from the index.jsp file
        String password = request.getParameter("password");

        try (Connection connection = dataSource.getConnection()) { //connector object interacting with the bean return value
            String sql = "SELECT * FROM users WHERE email = ? AND password = ?"; //query
            try (PreparedStatement statement = connection.prepareStatement(sql)) { // prepared statement aka execution
                statement.setString(1, username); // the first ?
                statement.setString(2, password); // the second ?
                try (ResultSet resultSet = statement.executeQuery()) { //values retrieved
                    if (resultSet.next()) {
                        request.getSession().setAttribute("username", username); //session attributes
                        response.sendRedirect("welcome.jsp"); //redirect
                    } else {
                        response.sendRedirect("index.jsp?error=1"); //faulty redirect with the parameter - error = 1 - meaning true;
                    }
                }
            }
        } catch (SQLException e) {
            throw new ServletException(e); // sql exception aka error
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
