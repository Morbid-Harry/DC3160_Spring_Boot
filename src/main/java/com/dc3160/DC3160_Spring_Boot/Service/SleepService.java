package com.dc3160.DC3160_Spring_Boot.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc3160.DC3160_Spring_Boot.Repository.SleepRepository;
import com.dc3160.DC3160_Spring_Boot.beans.SleepRecord;

@Service
public class SleepService {
	
	@Autowired
	private SleepRepository sleepRepo;
	
	public void addSleep(SleepRecord sleep)
	{
		sleepRepo.save(sleep);
	}
	
	public List<SleepRecord> getSleepByUser(int userId)
	{
		return sleepRepo.findByUserID(userId);
	}
}
