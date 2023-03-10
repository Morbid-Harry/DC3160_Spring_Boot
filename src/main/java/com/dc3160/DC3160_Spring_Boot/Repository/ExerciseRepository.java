package com.dc3160.DC3160_Spring_Boot.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dc3160.DC3160_Spring_Boot.beans.ExerciseRecord;

public interface ExerciseRepository extends CrudRepository<ExerciseRecord, Integer> {
	
	List<ExerciseRecord> findByUserID(int userID);
}
