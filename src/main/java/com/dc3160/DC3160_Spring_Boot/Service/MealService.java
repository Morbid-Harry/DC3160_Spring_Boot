package com.dc3160.DC3160_Spring_Boot.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc3160.DC3160_Spring_Boot.Repository.MealRepository;
import com.dc3160.DC3160_Spring_Boot.beans.MealRecord;

@Service
public class MealService {
	
	@Autowired
	private MealRepository mealRepo;
	
	public void addMeal(MealRecord meal)
	{
		mealRepo.save(meal);
	}
	
	public List<MealRecord> getMealsByUser(int userID)
	{
		return mealRepo.findByUserID(userID);
	}
}
