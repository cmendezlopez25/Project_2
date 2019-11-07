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
		
		boolean hasOwnerLink = false;
		Set<UserRoleAccount> removedUserRoleAccounts = new HashSet<>();
		
		for (UserRoleAccount ura : userRoleAccounts) {
			if (ura.getUser() == null || ura.getRole() == null) {
				removedUserRoleAccounts.add(ura);
			}
			// This is to make sure not just anyone can be user
			else if (!ura.getUser().getEmail().equals(ownerUser.getEmail()) 
					&& ura.getRole().getRoleName().equals("Owner")) {
				removedUserRoleAccounts.add(ura);
			}
			// This is to make sure owner is not any other role
			else if (ura.getUser().getEmail().equals(ownerUser.getEmail()) 
					&& !ura.getRole().getRoleName().equals("Owner")) {
				removedUserRoleAccounts.add(ura);
			}
			else if (ura.getUser().getEmail().equals(ownerUser.getEmail()) 
					&& ura.getRole().getRoleName().equals("Owner")) {
				hasOwnerLink = true;
				ura.setAccount(account);
			}
			else {
				ura.setAccount(account);
			}
		}
		
		userRoleAccounts.removeAll(removedUserRoleAccounts);
		
		// Making sure that userRoleAccounts has a link with ownerUser as actually an owner
		if (!hasOwnerLink) {
			userRoleAccounts.add(new UserRoleAccount(ownerUser, new Role(1, "Owner"), account));
		}
		
		account.setUserRoleAccounts(userRoleAccounts);
		
		Account newAccount = accountDao.createAccount(account);
		
		for (UserRoleAccount ura : userRoleAccounts) {
			userRoleAccountService.createUserRoleAccount(ura.getUser(),
					ura.getRole(), ura.getAccount());
		}
		
		// This is to make it return correct uraId after userroleaccount link
		// is created in database
		return accountDao.readAccount(newAccount.getAccountId());
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
	
		Set<UserRoleAccount> oldURAs = new HashSet<>();
		Set<UserRoleAccount> newURAs = new HashSet<>();
		
		if (readAccount(account.getAccountId()).getUserRoleAccounts() != null) {
			oldURAs = readAccount(account.getAccountId()).getUserRoleAccounts();
		}
		if (account.getUserRoleAccounts() != null) {
			newURAs = account.getUserRoleAccounts();
		}
		
		// Finding ura's that should be gone after update
		Set<UserRoleAccount> deleteURAs = new HashSet<>();
		
		for (UserRoleAccount ura : oldURAs) {
			deleteURAs.add(ura);
		}
		
		deleteURAs.removeAll(newURAs);
		
		for (UserRoleAccount ura : deleteURAs) {
			userRoleAccountService.deleteUserRoleAccount(ura.getUser(), 
					ura.getRole(), ura.getAccount());
		}
		
		// Finding ura's that should be updated after update
		Set<UserRoleAccount> createURAs = new HashSet<>();
		
		for (UserRoleAccount ura : newURAs) {
			createURAs.add(ura);
		}
		
		createURAs.removeAll(oldURAs);
		
		for (UserRoleAccount ura : newURAs) {
			userRoleAccountService.createUserRoleAccount(ura.getUser(), 
					ura.getRole(), ura.getAccount());
		}
		
		Account updatedAccount = accountDao.updateAccount(account);
		
		return accountDao.readAccount(updatedAccount.getAccountId());
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
