package com.dc3160.DC3160_Spring_Boot.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dc3160.DC3160_Spring_Boot.Database.DatabaseConnection;
import com.dc3160.DC3160_Spring_Boot.beans.User;

public class UserDAO {

	Connection connection = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public UserDAO()
	{
		
	}
	
	private Connection getConnection()
	{
		Connection newConnection = DatabaseConnection.getInstance().createConnection();
		return newConnection;
	}
	
	//Attempts to inserts a new user and returns the user
	public User insert(String firstName, String lastName, String email, String password)
	{
		User user = new User();
		
		String sqlStatement = "INSERT INTO user_table (user_first_name, user_last_name, user_email, user_password) VALUES (?,?,?,?)";
		
		connection = getConnection();
		
		try {
			
			ps = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, email);
			ps.setString(4, password);
			
			ps.executeUpdate();
			
			//Get the returned generated ID
			rs = ps.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			
			//Create user bean and return to servlet to add to session
			user.setUserID(id);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email);
			user.setPassword(password);
			
			//Close ps, rs and connection
			ps.close();
			rs.close();
			connection.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if(user.getEmail() == null)
			{
				return null;
			}
		}
		
		
		return user;
	}
	
	public User getUserByEmailAndPassword(String email, String password)
	{
		User user = new User();
		
		String sqlStatement = "SELECT * FROM user_table WHERE user_email = ? AND user_password = ?";
		
		connection = getConnection();
		
		try {
			
			ps = connection.prepareStatement(sqlStatement);

			ps.setString(1, email);
			ps.setString(2, password);
			
			rs = ps.executeQuery();
			
			//Get the result of the query and create user bean
			//If rs has no next then return null
			if(rs.next())
			{
				//rs.next();
				user.setUserID(rs.getInt(1));
				user.setFirstName(rs.getString(2));
				user.setLastName(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setPassword(rs.getString(5));
				
				//Close ps, rs and connection
				ps.close();
				rs.close();
				connection.close();
				
				return user;
			}
			else {
				//Close ps, rs and connection
				ps.close();
				rs.close();
				connection.close();
				
				return null;
			}
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	public void update(String firstName, String lastName, int userID)
	{
		String sqlStatement = "UPDATE user_table SET user_first_name = ?, user_last_name = ? WHERE user_id = ?";
		
		connection = getConnection();
		
		try {
			
			ps = connection.prepareStatement(sqlStatement);
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setInt(3, userID);
			
			ps.executeUpdate();
			
			//Close ps and connection
			ps.close();
			connection.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
