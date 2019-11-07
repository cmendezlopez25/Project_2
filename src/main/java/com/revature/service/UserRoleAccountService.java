package com.revature.service;

import com.revature.pojo.Account;
import com.revature.pojo.Role;
import com.revature.pojo.User;
import com.revature.pojo.UserRoleAccount;

public interface UserRoleAccountService {
	public UserRoleAccount createUserRoleAccount(User user, Role role, Account account);
	
	public void deleteUserRoleAccount(User user, Role role, Account account);
}
