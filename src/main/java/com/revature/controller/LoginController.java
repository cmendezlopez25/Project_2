package com.revature.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.pojo.User;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {
	//private UserService userService;
	@GetMapping("/login")
	public String loginGet() {
		System.out.println("ah");
		return "ah";
	}
	
	@PostMapping("/login")
	public String loginPost(User user) {
		System.out.println("I've made it.");
		return "Testing";
	}
}
