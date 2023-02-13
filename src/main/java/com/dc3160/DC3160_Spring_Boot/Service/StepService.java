package com.dc3160.DC3160_Spring_Boot.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc3160.DC3160_Spring_Boot.Repository.StepRepository;
import com.dc3160.DC3160_Spring_Boot.beans.StepRecord;

@Service
public class StepService {
	@Autowired
	private StepRepository stepRepo;
	
	public void addStep(StepRecord step)
	{
		stepRepo.save(step);
	}
	
	public List<StepRecord> getStepByUser(int userId)
	{
		return stepRepo.findByUserID(userId);
	}
}
