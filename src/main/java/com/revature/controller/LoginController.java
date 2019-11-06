package com.revature.controller;

import javax.servlet.http.HttpSession;

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
	public User loginPost(@RequestBody User user, HttpSession sess) {
		User loginUser = userService.loginUser(user);
		
		if (loginUser != null) {
			sess.setAttribute("user", loginUser);
		}

		return loginUser;
	}
}
