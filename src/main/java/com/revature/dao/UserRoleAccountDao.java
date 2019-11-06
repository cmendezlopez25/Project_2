package com.revature.dao;

import com.revature.pojo.Account;
import com.revature.pojo.Role;
import com.revature.pojo.User;
import com.revature.pojo.UserRoleAccount;

public interface UserRoleAccountDao {
	public UserRoleAccount createUserRoleAccount(UserRoleAccount userRoleAccount);

	public void deleteUserRoleAccount(UserRoleAccount userRoleAccount);
	
}
