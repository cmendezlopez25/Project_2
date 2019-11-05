package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.pojo.User;
import com.revature.service.UserService;

@RestController
@CrossOrigin(origins="*")
public class LoginController {
	private UserService userService;
	
	@Autowired
	private void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping(value="/login", consumes = "application/json", produces = "application/json")
	public User loginPost(@RequestBody User user) {
		User loginUser = userService.loginUser(user);
		System.out.println("NOPLEASE");
		return loginUser;
	}
}
