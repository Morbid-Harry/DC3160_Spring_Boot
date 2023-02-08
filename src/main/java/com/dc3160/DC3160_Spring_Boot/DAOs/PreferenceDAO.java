package com.dc3160.DC3160_Spring_Boot.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dc3160.DC3160_Spring_Boot.Database.DatabaseConnection;
import com.dc3160.DC3160_Spring_Boot.beans.*;


public class PreferenceDAO {
	
	Connection connection = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public PreferenceDAO()
	{
		
	}
	
	private Connection getConnection()
	{
		Connection newConnection = DatabaseConnection.getInstance().createConnection();
		return newConnection;
	}
	
	
	public Preference insert(int fkUserID, int age, int weightSt, int weightLbs, int heightFeet, int heightInches, int stepTarget, int calorieTarget, double exerciseTarget)
	{
		Preference preference = new Preference();
		
		String sqlStatement = "INSERT INTO preference_table(fk_user_id, age, weight_stone, weight_pounds, height_feet, height_inches, step_target, calorie_target, exercise_target) VALUES (?,?,?,?,?,?,?,?,?)";
		
		connection = getConnection();
		
		try {
			
			ps = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, fkUserID);
			ps.setInt(2, age);
			ps.setInt(3, weightSt);
			ps.setInt(4, weightLbs);
			ps.setInt(5, heightFeet);
			ps.setInt(6, heightInches);
			ps.setInt(7, stepTarget);
			ps.setInt(8, calorieTarget);
			ps.setBigDecimal(9, java.math.BigDecimal.valueOf(exerciseTarget));
			
			ps.executeUpdate();
			
			//Get the returned generated ID
			rs = ps.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			
			//Create user bean and return to servlet to add to session
			preference.setPreferenceID(id);
			preference.setFkUserID(fkUserID);
			preference.setAge(age);
			preference.setWeightStone(weightSt);
			preference.setWeightPounds(weightLbs);
			preference.setHeightFeet(heightFeet);
			preference.setHeightInches(heightInches);
			preference.setStepTarget(stepTarget);
			preference.setCalorieTarget(calorieTarget);
			preference.setExerciseTarget(exerciseTarget);
			
			//Close ps, rs and connection
			ps.close();
			rs.close();
			connection.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return preference;
	}
	
	
	public void update(int preferenceID, int age, int weightSt, int weightLbs, int heightFeet, int heightInches, int stepTarget, int calorieTarget, double exerciseTarget)
	{
		
		String sqlStatement = "UPDATE preference_table SET age = ?, weight_stone = ?, weight_pounds = ?, height_feet = ?, height_inches = ?, step_target = ?, calorie_target = ?, exercise_target = ? WHERE preference_id = ?";
		
		connection = getConnection();
		
		try {
			
			ps = connection.prepareStatement(sqlStatement);
			ps.setInt(1, age);
			ps.setInt(2, weightSt);
			ps.setInt(3, weightLbs);
			ps.setInt(4, heightFeet);
			ps.setInt(5, heightInches);
			ps.setInt(6, stepTarget);
			ps.setInt(7, calorieTarget);
			ps.setBigDecimal(8, java.math.BigDecimal.valueOf(exerciseTarget));
			ps.setInt(9, preferenceID);
			
			ps.executeUpdate();
			
			//Close ps and connection
			ps.close();
			connection.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Preference getUserPreferences(int userID)
	{
		Preference preference = new Preference();
		
		String sqlStatement = "Select * FROM preference_table WHERE fk_user_id = ?";
		
		connection = getConnection();
		
		try {
			
			ps = connection.prepareStatement(sqlStatement);
			ps.setInt(1, userID);
			
			rs = ps.executeQuery();
			
			//Get the returned generated ID
			rs.next();
			
			//Create user bean and return to servlet to add to session
			preference.setPreferenceID(rs.getInt(1));
			preference.setFkUserID(rs.getInt(2));
			preference.setAge(rs.getInt(3));
			preference.setWeightStone(rs.getInt(4));
			preference.setWeightPounds(rs.getInt(5));
			preference.setHeightFeet(rs.getInt(6));
			preference.setHeightInches(rs.getInt(7));
			preference.setStepTarget(rs.getInt(8));
			preference.setCalorieTarget(rs.getInt(9));
			preference.setExerciseTarget(rs.getBigDecimal(10).doubleValue());
			
			//Close ps, rs and connection
			ps.close();
			rs.close();
			connection.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return preference;
	}
}
