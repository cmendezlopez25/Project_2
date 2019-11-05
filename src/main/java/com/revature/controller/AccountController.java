package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.pojo.Account;
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
		Account newAccount = accountService.createAccount(account);
		System.out.println(account.getAccountName());
		return newAccount;
	}
	
	@GetMapping
	public Account getAccount(@RequestBody Account account) {
		Account retAccount = accountService.readAccount(account);
		System.out.println(account.getAccountName());
		System.out.println(retAccount.getAccountName());
		return retAccount;
	}
}
