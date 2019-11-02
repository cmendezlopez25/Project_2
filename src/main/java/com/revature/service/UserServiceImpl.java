package com.revature.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.dao.UserDao;
import com.revature.pojo.Account;
import com.revature.pojo.Role;
import com.revature.pojo.User;

@Component
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean createUser(User user) {
		if (user == null) {
			throw new NullPointerException();
		}
		
		if (!isValidUser(user)) {
			return false;
		}
		
		return userDao.createUser(user);
	}

	@Override
	public User loginUser(User user) {
		if (user == null) {
			throw new NullPointerException();
		}
		
		if (!isValidUser(user)) {
			return null;
		}
		
		return userDao.readUser(user);
	}

	@Override
	public boolean updateUser(User user) {
		if (user == null) {
			throw new NullPointerException();
		}
		
		if (!isValidUser(user)) {
			return false;
		}
		
		return userDao.updateUser(user);
	}

	@Override
	public boolean deleteUser(User user) {
		if (user == null) {
			throw new NullPointerException();
		}
		
		if (!isValidUser(user)) {
			return false;
		}
		
		return userDao.deleteUser(user);
	}
	
	@Override
	public List<User> readAllUsers() {
		return userDao.readAllUsers();
	}
	
	public Map<User, Role> readUsersWithRoleByAccount(Account account) {
		if (account == null) {
			throw new NullPointerException();
		}
		
		return userDao.readUsersWithRoleByAccount(account);
	}

	private boolean isValidUser(User user) {
		if (user.getEmail() == null || user.getEmail().isEmpty()) {
			return false;
		}
		
		if (user.getPassword() == null || user.getPassword().isEmpty()) {
			return false;
		}
		
		String email = user.getEmail();
		
		if (!email.contains("@")) {
			return false;
		}
		
		return true;
	}
}
