package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.pojo.User;
import com.revature.service.UserService;

@RestController
@RequestMapping("/register")
@CrossOrigin(origins="*")
public class RegisterController {
	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public void registerGet() {
		
	}
	
	@PostMapping(consumes="application/json")
	public User registerPost(@RequestBody User user) {
		if (!userService.createUser(user)) {
			return null;
		}
		return user;
	}
}
