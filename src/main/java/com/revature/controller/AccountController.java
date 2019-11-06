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
@CrossOrigin(origins="*")
@RequestMapping("/account")
public class AccountController {

	private AccountService accountService;
	
	@Autowired
	private void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@PostMapping
	public Account createAccount(@RequestBody Account account, HttpSession sess) {
		User user = (User)sess.getAttribute("User");
		return accountService.createAccount(user, account);
	}
	
	@GetMapping("/{accountId}")
	public Account getAccount(@PathVariable int accountId) {
		// Should only people who are in account be able to read account?
		return accountService.readAccount(accountId);
	}
	
	@PutMapping
	public Account updateAccount(@RequestBody Account account) {
		// Will have to do role logic in service....
		return accountService.updateAccount(account);
	}
	
	@DeleteMapping
	public String deleteAccount(@RequestBody Account account) {
		// Only owner will be able to delete account
		accountService.deleteAccount(account);
		// Might need to redo return type for deletemapping
		return "Deleted account";
	}
	
	@GetMapping
	public List<Account> getAllAccountsByUser(@RequestBody User user) {
		// should user only be able to see only their logged in accounts?
		return accountService.readAllAccountsByUser(user);
	}
}
