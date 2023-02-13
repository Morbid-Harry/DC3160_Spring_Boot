package com.dc3160.DC3160_Spring_Boot.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dc3160.DC3160_Spring_Boot.beans.SleepRecord;

public interface SleepRepository extends CrudRepository<SleepRecord, Integer>{
	
	List<SleepRecord> findByUserID(int userID);
}
