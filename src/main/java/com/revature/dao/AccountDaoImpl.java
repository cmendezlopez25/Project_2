package com.revature.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.pojo.Account;
import com.revature.pojo.User;

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
	public Account readAccount(int accountId) {
		beginSession();
		
		Account retAccount = sess.get(Account.class, accountId);
		
		endSession();
		return retAccount;
	}

	@Override
	public Account updateAccount(Account account) {
		if (account == null) {
			throw new NullPointerException();
		}
		beginSession();
		sess.update(account);
		endSession();
		return account;
	}

	@Override
	public void deleteAccount(Account account) {
		if (account == null) {
			throw new NullPointerException();
		}
		beginSession();
		sess.delete(account);
		endSession();
	}

	@Override
	public List<Account> readAllAccountsByUser(User user) {
		if (user == null) {
			throw new NullPointerException();
		}
		beginSession();
		String hql = "SELECT ura.account FROM UserRoleAccount ura WHERE ura.user.email=:email";
		Query query = sess.createQuery(hql);
		query.setParameter("email", user.getEmail());
		
		List<Account> accounts = query.list();
		
		return accounts;
	}

}
