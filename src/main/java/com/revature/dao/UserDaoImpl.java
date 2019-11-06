package com.revature.dao;

import static com.revature.util.LoggerUtil.log;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.pojo.Account;
import com.revature.pojo.Role;
import com.revature.pojo.User;

@Component
public class UserDaoImpl implements UserDao {
	private static SessionFactory sf;
	private Session sess;
	private Transaction tx;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sf = sf;
	}
	
	public Session getCurrentSession() {
		return sess;
	}
	
	private void beginSession() {
		sess = sf.openSession();
		tx = sess.beginTransaction();
	}
	
	private void endSession() {
		tx.commit();
		sess.close();
	}

	@Override
	public boolean createUser(User user) {
		if(user == null) {
			throw new NullPointerException();
		}
		
		if (!isValidUser(user)) {
			return false;
		}
		
		beginSession();
		
		
		sess.save(user);
		
		try {
			endSession();
		} catch(Exception e) {
			log.warn("User could not be created. Message: " + e.getMessage());
			return false;
		}
		
		return true;
	}

	@Override
	public User readUser(User user) {
		if (user == null) {
			throw new NullPointerException();
		}
		
		if (!isValidUser(user)) {
			return null;
		}
		
		beginSession();
		
		User newUser = sess.get(User.class, user.getEmail());
		
		endSession();
		return newUser;
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> readAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<User, Role> readUsersWithRoleByAccount(Account account) {
		// TODO Auto-generated method stub
		return null;
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
