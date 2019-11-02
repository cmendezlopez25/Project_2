package com.revature.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.pojo.User;

@RestController
@CrossOrigin(origins="*")
public class LoginController {
	//private UserService userService;
	@GetMapping("/login")
	public String loginGet() {
		System.out.println("ah");
		return "ah";
	}
	
	@GetMapping("/login")
	public String loginGet(User user) {
		System.out.println("Wee");
		return "Testing";
	}
	
	@PostMapping(consumes="application/json", value="/login")
	public String loginPost(@RequestBody User user) {
		System.out.println("username: " + user.getEmail() + ", password: " + user.getPassword());
		return "success";
	}
}
