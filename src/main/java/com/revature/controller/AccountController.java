package com.revature.controller;

import java.util.List;

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
@CrossOrigin(origins="*")
@RequestMapping("/account")
public class AccountController {

	private AccountService accountService;
	
	@Autowired
	private void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@PostMapping
	public Account createAccount(@RequestBody Account account) {
		return accountService.createAccount(account);
	}
	
	@GetMapping("/{accountId}")
	public Account getAccount(@PathVariable int accountId) {
		return accountService.readAccount(accountId);
	}
	
	@PutMapping
	public Account updateAccount(@RequestBody Account account) {
		return accountService.updateAccount(account);
	}
	
	@DeleteMapping
	public String deleteAccount(@RequestBody Account account) {
		accountService.deleteAccount(account);
		// Might need to redo return type for deletemapping
		return "Deleted account";
	}
	
	@GetMapping
	public List<Account> getAllAccountsByUser(@RequestBody User user) {
		return accountService.readAllAccountsByUser(user);
	}
}
