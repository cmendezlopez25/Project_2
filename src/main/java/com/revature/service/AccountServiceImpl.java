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
	public Account createAccount(User ownerUser, Account account) {
		if (account == null || ownerUser == null) {
			throw new NullPointerException();
		}
		if (!isValidAccount(account)) {
			return null;
		}
		
		Set<UserRoleAccount> userRoleAccounts = new HashSet<>();
		
		// To prevent setting userRoleAccounts to null
		if (account.getUserRoleAccounts() != null) {
			userRoleAccounts = account.getUserRoleAccounts();
		}
		
		for (UserRoleAccount ura : userRoleAccounts) {
			if (ura.getUser() == null || ura.getRole() == null || ura.getAccount() == null) {
				userRoleAccounts.remove(ura);
			}
			// This is to make sure not just anyone can be user
			else if (ura.getUser() != ownerUser && ura.getRole().getRoleName().equals("Owner")) {
				userRoleAccounts.remove(ura);
			}
			// This is to make sure owner is not any other role
			else if (ura.getUser() == ownerUser && ura.getRole() != new Role(1, "Owner")) {
				userRoleAccounts.remove(ura);
			}
		}
		
		// Making sure that userRoleAccounts has a link with ownerUser as actually an owner
		UserRoleAccount ownerUra = new UserRoleAccount(ownerUser, new Role(1, "Owner"), account);
		
		if (!userRoleAccounts.contains(ownerUra)) {
			userRoleAccounts.add(ownerUra);
		}
		
		account.setUserRoleAccounts(userRoleAccounts);
		
		Account newAccount = accountDao.createAccount(account);
		
		for (UserRoleAccount ura : userRoleAccounts) {
			userRoleAccountService.createUserRoleAccount(ura.getUser(),
					ura.getRole(), ura.getAccount());
		}
		
		return newAccount;
	}

	@Override
	public Account readAccount(int accountId) {
		return accountDao.readAccount(accountId);
	}

	@Override
	public Account updateAccount(User ownerUser, Account account) {
		if (account == null || ownerUser == null) {
			throw new NullPointerException();
		}
		if (!isValidAccount(account)) {
			return null;
		}
	
		
		
		
		return accountDao.updateAccount(account);
	}

	@Override
	public void deleteAccount(User user, int accountId) {
		if (user == null) {
			throw new NullPointerException();
		}
		
		Account account = readAccount(accountId);
		
		if (account == null) {
			throw new IllegalArgumentException();
		}
		
		Set<UserRoleAccount> userRoleAccounts = new HashSet<>();
		
		if (account.getUserRoleAccounts() != null) {
			userRoleAccounts = account.getUserRoleAccounts();
		}
		
		UserRoleAccount ura = new UserRoleAccount(user, new Role(1, "Owner"), account);
		
		if (userRoleAccounts.contains(ura)) {
			accountDao.deleteAccount(account);
		}
		else {
			throw new IllegalArgumentException();
		}
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
