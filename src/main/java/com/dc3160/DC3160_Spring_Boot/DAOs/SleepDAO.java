package com.dc3160.DC3160_Spring_Boot.DAOs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dc3160.DC3160_Spring_Boot.Database.DatabaseConnection;
import com.dc3160.DC3160_Spring_Boot.beans.*;

public class SleepDAO {
	
	Connection connection = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public SleepDAO()
	{
		
	}
	
	private Connection getConnection()
	{
		Connection newConnection = DatabaseConnection.getInstance().createConnection();
		return newConnection;
	}
	
	public void insert(int userID, Double hours, Date date)
	{
		
		String sqlStatement = "INSERT INTO sleep_table (fk_user_id, sleep_hours, sleep_date) VALUES (?,?,?)";
		
		connection = getConnection();
		
		try {
			
			ps = connection.prepareStatement(sqlStatement);
			ps.setInt(1, userID);
			ps.setDouble(2, hours);
			ps.setDate(3, date);
			
			ps.executeUpdate();
			

			
			//Close ps and connection
			ps.close();
			connection.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
	
	public ArrayList<SleepRecord> getUserSleepRecords(int userID)
	{
		String sqlStatement = "SELECT * FROM sleep_table WHERE fk_user_id = ? order by sleep_date desc";
		
		connection = getConnection();
		
		ArrayList<SleepRecord> records = new ArrayList<>();
		try {
			
			ps = connection.prepareStatement(sqlStatement);
			ps.setInt(1, userID);

			
			rs = ps.executeQuery();
		
			
			while(rs.next())
			{
				SleepRecord record = new SleepRecord();
				record.setSleepID(rs.getInt(1));
				record.setUserID(rs.getInt(2));
				record.setSleepHours(rs.getDouble(3));
				record.setSleepDate(rs.getDate(4));
				
				records.add(record);
			}
			
			//Close ps, rs and connection
			rs.close();
			ps.close();
			connection.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return records;
	}
	
}
