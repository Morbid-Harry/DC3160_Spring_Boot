package com.dc3160.DC3160_Spring_Boot.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc3160.DC3160_Spring_Boot.Repository.PreferenceRepository;
import com.dc3160.DC3160_Spring_Boot.beans.Preference;

@Service
public class PreferenceService {
	@Autowired
	PreferenceRepository preferenceRepo;
	
	public void addPreference(Preference preference)
	{
		preferenceRepo.save(preference);
	}
	
	public Preference getPreferenceByUser(int userId)
	{
		return preferenceRepo.findByUserID(userId);
	}
}
