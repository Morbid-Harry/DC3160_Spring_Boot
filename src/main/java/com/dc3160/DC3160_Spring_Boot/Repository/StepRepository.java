package com.dc3160.DC3160_Spring_Boot.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dc3160.DC3160_Spring_Boot.beans.StepRecord;

public interface StepRepository extends CrudRepository<StepRecord, Integer>{
	
	List<StepRecord> findByUserID(int userID);
}
