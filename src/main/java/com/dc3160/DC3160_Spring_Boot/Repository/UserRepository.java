package com.dc3160.DC3160_Spring_Boot.Repository;

import org.springframework.data.repository.CrudRepository;

import com.dc3160.DC3160_Spring_Boot.beans.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	
	User findUserByEmailAndPassword(String email, String password);
	
	User findUserByEmail(String email);
}
