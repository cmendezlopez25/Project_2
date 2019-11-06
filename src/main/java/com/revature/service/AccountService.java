package com.revature.service;

import java.util.List;

import com.revature.pojo.Account;
import com.revature.pojo.User;

public interface AccountService {
	public Account createAccount(User user, Account account);
	
	public Account readAccount(int accountId);
	
	public Account updateAccount(Account account);
	
	public void deleteAccount(Account account);
	
	public List<Account> readAllAccountsByUser(User user);
}
