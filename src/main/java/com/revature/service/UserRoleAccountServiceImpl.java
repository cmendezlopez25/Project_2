package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.dao.UserRoleAccountDao;
import com.revature.pojo.Account;
import com.revature.pojo.Role;
import com.revature.pojo.User;
import com.revature.pojo.UserRoleAccount;

@Component
public class UserRoleAccountServiceImpl implements UserRoleAccountService {
	
	private UserRoleAccountDao userRoleAccountDao;
	
	private UserService userService;
	
	private AccountService accountService;
	
	@Autowired
	public void setUserRoleAccountDao(UserRoleAccountDao userRoleAccountDao,
			UserService userService, AccountService accountService) {
		this.userRoleAccountDao = userRoleAccountDao;
		this.userService = userService;
		this.accountService = accountService;
	}
	
	@Override
	public UserRoleAccount createUserRoleAccount(User user, Role role, Account account) {
		if (user == null || role == null || account == null) {
			throw new NullPointerException();
		}
		
		if (userService.readUser(user) == null || accountService.readAccount(account.getAccountId()) == null) {
			return null;
		}
		
		UserRoleAccount userRoleAccount = new UserRoleAccount(user, role, account);
		return userRoleAccountDao.createUserRoleAccount(userRoleAccount);
	}

	@Override
	public void deleteUserRoleAccount(User user, Role role, Account account) {
		if (user == null || role == null || account == null) {
			throw new NullPointerException();
		}
		
		UserRoleAccount userRoleAccount = new UserRoleAccount(user, role, account);
		userRoleAccountDao.deleteUserRoleAccount(userRoleAccount);
	}

}
