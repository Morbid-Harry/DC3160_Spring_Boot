package com.dc3160.DC3160_Spring_Boot.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc3160.DC3160_Spring_Boot.Repository.UserRepository;
import com.dc3160.DC3160_Spring_Boot.beans.User;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	public void addUser(User user)
	{
		userRepo.save(user);
	}
	
	public User getUserByEmailAndPassword(String email, String password)
	{
		return userRepo.findUserByEmailAndPassword(email, password);
	}
	
	public User getUserByEmail(String email)
	{
		return userRepo.findUserByEmail(email);
	}
	
}
