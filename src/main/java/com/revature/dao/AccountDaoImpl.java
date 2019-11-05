package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.pojo.Account;
import com.revature.pojo.User;
import com.revature.util.SessionFactoryUtil;

@Component
public class AccountDaoImpl implements AccountDao {
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
	public Account createAccount(Account account) {
		beginSession();
		sess.save(account);
		endSession();
		return account;
	}

	@Override
	public Account readAccount(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateAccount(Account account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAccount(Account account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Account> readAllAccountsByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
