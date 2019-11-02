package com.revature.dao;

import java.util.List;

import com.revature.pojo.Account;

public interface AccountDao {
	public boolean createAccount(Account account);
	
	public Account readAccount(Account account);
	
	public boolean updateAccount(Account account);
	
	public boolean deleteAccount(Account account);
	
	public List<Account> readAllAccounts();
	
}
