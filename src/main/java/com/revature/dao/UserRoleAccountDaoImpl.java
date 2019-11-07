package com.revature.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.pojo.UserRoleAccount;

@Component
public class UserRoleAccountDaoImpl implements UserRoleAccountDao {
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
	public UserRoleAccount createUserRoleAccount(UserRoleAccount userRoleAccount) {
		if (userRoleAccount == null) {
			throw new NullPointerException();
		}
		beginSession();
		sess.save(userRoleAccount);
		endSession();
		return userRoleAccount;
	}

	@Override
	public void deleteUserRoleAccount(UserRoleAccount userRoleAccount) {
		if (userRoleAccount == null) {
			throw new NullPointerException();
		}
		beginSession();
		sess.delete(userRoleAccount);
		endSession();
	}

}
