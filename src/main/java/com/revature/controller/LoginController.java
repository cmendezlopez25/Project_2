package com.revature.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.revature.pojo.User;

@Controller
public class LoginController {
	//private UserService userService;
	
	@PostMapping("/login")
	public String loginPost(User user) {
		System.out.println("I've made it.");
		return "Testing";
	}
}
