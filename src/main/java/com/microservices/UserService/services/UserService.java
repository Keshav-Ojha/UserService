package com.microservices.UserService.services;

import java.util.List;

import com.microservices.UserService.entities.User;

public interface UserService {

	User saveUser(User user);
	
	List<User> getAllUser();
	
	User getUser(String userId);
}
