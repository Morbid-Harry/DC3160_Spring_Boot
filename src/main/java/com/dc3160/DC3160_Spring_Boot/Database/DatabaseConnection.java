package com.dc3160.DC3160_Spring_Boot.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private final String userName = "root";
	private final String userPass = "SpitFire22!";
	private final String connUrl = "jdbc:mysql://localhost:3306/hlsp_app";
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	
	private static DatabaseConnection dbConnection = null;
	
	private DatabaseConnection()
	{
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			System.out.println("Driver not Found");
		}
	}
	
	
	public Connection createConnection() {
		
		Connection connection = null;
		try {
			
			connection = DriverManager.getConnection(connUrl, userName, userPass);
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("Connection to: " + connUrl + " failed" );
		}
		
		return connection;
	}
	
	
	public static DatabaseConnection getInstance()
	{
		if(dbConnection == null) {
			dbConnection = new DatabaseConnection();
		}
		
		return dbConnection;
	}
	
}
