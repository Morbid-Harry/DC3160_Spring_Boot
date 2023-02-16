package com.dc3160.DC3160_Spring_Boot.Repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dc3160.DC3160_Spring_Boot.beans.MealRecord;
import com.dc3160.DC3160_Spring_Boot.beans.StepRecord;

public interface MealRepository extends CrudRepository<MealRecord, Integer> {
	
	List<MealRecord> findByUserID(int userID);
	
	List<MealRecord> findByUserIDAndMealDate(int userID, Date mealDate);

}
