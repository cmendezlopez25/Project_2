package com.revature.controller;

import static com.revature.util.LoggerUtil.log;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logout")
@CrossOrigin(origins="*")
public class LogoutController {
	@GetMapping
	public void logoutGet(HttpSession sess) {
		log.debug("Logging out");
		sess.invalidate();
	}
}
