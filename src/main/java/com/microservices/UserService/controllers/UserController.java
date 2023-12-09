package com.microservices.UserService.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.UserService.entities.User;
import com.microservices.UserService.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public User createUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@GetMapping("/")
	public List<User> getAllUsers(){
		return userService.getAllUser();
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") String userId) {
		return userService.getUser(userId);
	}
}
