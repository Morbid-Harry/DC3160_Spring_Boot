package com.dc3160.DC3160_Spring_Boot.Repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dc3160.DC3160_Spring_Boot.beans.StepRecord;

public interface StepRepository extends CrudRepository<StepRecord, Integer>{
	
	List<StepRecord> findByUserID(int userID);
	
	List<StepRecord> findByUserIDAndStepDate(int userID, Date stepDate);
}
