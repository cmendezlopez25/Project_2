package com.revature.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.dao.AccountDao;
import com.revature.pojo.Account;
import com.revature.pojo.Role;
import com.revature.pojo.User;
import com.revature.pojo.UserRoleAccount;

@Component
public class AccountServiceImpl implements AccountService {
	
	private AccountDao accountDao;
	
	private UserRoleAccountService userRoleAccountService;
	
	@Autowired
	public void setAccountDao(AccountDao accountDao, UserRoleAccountService userRoleAccountService) {
		this.accountDao = accountDao;
		this.userRoleAccountService = userRoleAccountService;
	}
	
	@Override
	public Account createAccount(User user, Account account) {
		if (account == null || user == null) {
			throw new NullPointerException();
		}
		if (!isValidAccount(account)) {
			return null;
		}
		
		Account newAccount = accountDao.createAccount(account);
		
		UserRoleAccount ura = userRoleAccountService.
				createUserRoleAccount(user, new Role(1, "Owner"), newAccount);
		
		Set<UserRoleAccount> userRoleAccounts = new HashSet<>();
		userRoleAccounts.add(ura);
		
		newAccount.setUserRoleAccounts(userRoleAccounts);
		
		return newAccount;
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
