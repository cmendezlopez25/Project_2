package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.dao.AccountDao;
import com.revature.pojo.Account;
import com.revature.pojo.User;

@Component
public class AccountServiceImpl implements AccountService {
	
	private AccountDao accountDao;
	
	@Autowired
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	
	@Override
	public Account createAccount(Account account) {
		if (account == null) {
			throw new NullPointerException();
		}
		if (!isValidAccount(account)) {
			return null;
		}
		return accountDao.createAccount(account);
	}

	@Override
	public Account readAccount(int accountId) {
		
		return accountDao.readAccount(accountId);
	}

	@Override
	public Account updateAccount(Account account) {
		if (account == null) {
			throw new NullPointerException();
		}
		if (!isValidAccount(account)) {
			return null;
		}
		return accountDao.updateAccount(account);
	}

	@Override
	public void deleteAccount(Account account) {
		if (account == null) {
			throw new NullPointerException();
		}
		accountDao.deleteAccount(account);
	}

	@Override
	public List<Account> readAllAccountsByUser(User user) {
		if (user == null) {
			throw new NullPointerException();
		}
		return accountDao.readAllAccountsByUser(user);
	}

	private boolean isValidAccount(Account account) {
		String name = account.getAccountName();
		if (name == null) {
			return false;
		}
		if ("".contentEquals(name)) {
			return false;
		}
		if (name.length() > 100) {
			return false;
		}
		return true;
	}
}
