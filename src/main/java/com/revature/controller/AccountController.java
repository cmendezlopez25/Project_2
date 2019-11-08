package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.pojo.Account;
import com.revature.pojo.User;
import com.revature.service.AccountService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/account")
public class AccountController {

	private AccountService accountService;
	
	@Autowired
	private void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@PostMapping
	public Account createAccount(@RequestBody Account account, HttpSession sess) {
		User user = (User)sess.getAttribute("user");
		return accountService.createAccount(user, account);
	}
	
	@GetMapping("/{accountId}")
	public Account getAccount(@PathVariable int accountId) {
		return accountService.readAccount(accountId);
	}
	
	@PutMapping
	public Account updateAccount(@RequestBody Account account, HttpSession sess) {
		User user = (User)sess.getAttribute("user");
		return accountService.updateAccount(user, account);
	}
	
	@DeleteMapping("/{accountId}")
	public String deleteAccount(@PathVariable int accountId, HttpSession sess) {
		User user = (User)sess.getAttribute("user");
		accountService.deleteAccount(user, accountId);
		return "Deleted account with id " + accountId;
	}
	
	@GetMapping
	public List<Account> getAllAccountsByUser(HttpSession sess) {
		User user = (User)sess.getAttribute("user");
		return accountService.readAllAccountsByUser(user);
	}
}
