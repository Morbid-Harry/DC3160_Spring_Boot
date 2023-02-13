package com.dc3160.DC3160_Spring_Boot.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc3160.DC3160_Spring_Boot.Repository.ExerciseRepository;
import com.dc3160.DC3160_Spring_Boot.beans.ExerciseRecord;

@Service
public class ExerciseService {
	@Autowired
	private ExerciseRepository exerciseRepo;
	
	public void addExercise(ExerciseRecord exercise)
	{
		exerciseRepo.save(exercise);
	}
	
	public List<ExerciseRecord> getExerciseByUser(int userId)
	{
		return exerciseRepo.findByUserID(userId);
	}
	
	
	
}
