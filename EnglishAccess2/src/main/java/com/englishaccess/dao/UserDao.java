package com.englishaccess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.sql.DataSource;




import com.englishaccess.beans.User;

public class UserDao {
	

	private DataSource dataSource; // we set this value to private the object
	private static final String checkEmailQuery = "SELECT COUNT(*) FROM users WHERE Email = ?";
	private static final String checkUserQuery = "SELECT COUNT(*) FROM users WHERE email = ? AND password = ?";
	private static final String insertUser = "INSERT INTO users (UserId, FirstName, LastName, Email, Password) VALUES(?, ?, ?, ?, ?)";
	private static final String selectUser = "SELECT userid FROM users WHERE Email = ?";
	
	public UserDao(DataSource dataSource) {
		this.dataSource = dataSource;

	}
	
	public boolean isEmailUsed (String email) throws SQLException {
		try (
			Connection connection = dataSource.getConnection();
			PreparedStatement checkStmt = connection.prepareStatement(checkEmailQuery)) {
		
			System.out.println("Database connection established.");
			checkStmt.setString(1, email);
			try (ResultSet rs = checkStmt.executeQuery()) {
				// Email already exists if returns true
				return rs.next() && rs.getInt(1) > 0;
			}    	
		}       
		
	}
	
	public boolean isUserExists (String username, String password) throws SQLException {
		try (
			Connection connection = dataSource.getConnection();
			PreparedStatement checkStmt = connection.prepareStatement(checkUserQuery)) {
		
			System.out.println("Database connection established.");
			checkStmt.setString(1, username);
			checkStmt.setString(2, password);
			try (ResultSet rs = checkStmt.executeQuery()) {
				// User exists if returns true
				return rs.next() && rs.getInt(1) > 0;
			}    	
		}       
		
	}
	
	public UUID selectUser (String username) throws SQLException {
		
		UUID userId = null;
		
		try (
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(selectUser)) {
			
			
			statement.setString(1, username);
			
			ResultSet rs = statement.executeQuery();
			
			//Retrieve the userid (should be just one)
			while (rs.next()) {
				userId = UUID.fromString(rs.getString("userid"));
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userId;
	}
	
	
	public boolean registerUser (User user) throws SQLException{
	    try (
	    	Connection connection = dataSource.getConnection();
	    	PreparedStatement statement = connection.prepareStatement(insertUser)) {
	    	
	    	statement.setString(1, user.getUserID().toString());
	    	statement.setString(2, user.getFirstName());
	    	statement.setString(3, user.getLastName());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getPassword());
			// Registration successful if rowsInserted > 0
			return statement.executeUpdate() > 0;
	
	    } 
		
	}
	
}
