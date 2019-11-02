package com.revature.service;

import java.util.List;
import java.util.Map;

import com.revature.pojo.Account;
import com.revature.pojo.Role;
import com.revature.pojo.User;

public interface UserService {
	public boolean createUser(User user);
	public User loginUser(User user);
	public boolean updateUser(User user);
	public boolean deleteUser(User user);
	public List<User> readAllUsers();
	public Map<User, Role> readUsersWithRoleByAccount(Account account);
}
