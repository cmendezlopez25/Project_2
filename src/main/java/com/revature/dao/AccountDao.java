package com.revature.dao;

import java.util.List;

import com.revature.pojo.Account;
import com.revature.pojo.User;

public interface AccountDao {
	public Account createAccount(Account account);
	
	public Account readAccount(Account account);
	
	public boolean updateAccount(Account account);
	
	public boolean deleteAccount(Account account);
	
	public List<Account> readAllAccountsByUser(User user);
	
}
