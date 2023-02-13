package com.dc3160.DC3160_Spring_Boot.Repository;

import org.springframework.data.repository.CrudRepository;

import com.dc3160.DC3160_Spring_Boot.beans.Preference;

public interface PreferenceRepository extends CrudRepository<Preference, Integer>{
	
	Preference findByUserID(int userId);
}
