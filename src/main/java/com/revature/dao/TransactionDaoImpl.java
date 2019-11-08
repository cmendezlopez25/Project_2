package com.revature.dao;

import static com.revature.util.LoggerUtil.log;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.pojo.Account;
import com.revature.pojo.Transaction;

@Component
public class TransactionDaoImpl implements TransactionDao {
	
	private static SessionFactory sf;
	private Session sess;
	private org.hibernate.Transaction tx;
	//need to be specific here because the default transaction is our pojo transaction
	
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
	public boolean createTransaction(Transaction transaction) {		
		if(transaction == null) {
			throw new NullPointerException();
		}
		if(!isValidTransaction(transaction)) {
			return false;
		}
		beginSession();
		
		sess.save(transaction);
		
		try {
			endSession();
		} catch(Exception e){
			log.warn("There was an exception trying to save " + transaction.toString() + " to the database");
		}
		return true;
	}

	@Override
	public Transaction readTransaction(Transaction transaction) {
		if(transaction == null) {
			throw new NullPointerException();
		}
		if(!isValidTransaction(transaction)) {
			return null;
		}
		beginSession();
		
		Transaction newTransaction = sess.get(Transaction.class, transaction.getTransactionId());
		endSession();
		return newTransaction;
	}

	@Override
	public boolean updateTransaction(Transaction transaction) {
		if(transaction == null) {
			throw new NullPointerException();
		}
		if(!isValidTransaction(transaction)) {
			return false;
		}
		beginSession();
		
		sess.update(transaction);
		
		endSession();
		return true;
	}

	@Override
	public boolean deleteTransaction(Transaction transaction) {
		if(transaction == null) {
			throw new NullPointerException();
		}
		if(!isValidTransaction(transaction)) {
			return false;
		}
		beginSession();
		
		sess.delete(transaction);
		
		endSession();
		return true;
	}

	@Override
	public List<Transaction> readAllTransactions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> readAllTransactionsByAccount(Account account) {
		if(account == null) {
			throw new NullPointerException();
		}
		beginSession();
		
		String hql = "SELECT t FROM Transaction t WHERE t.account.accountId=:accountId";
		Query query = sess.createQuery(hql);
		query.setParameter("accountId", account.getAccountId());
		
		List<Transaction> transactions = query.list();
		
		return transactions;
	}
	
	private boolean isValidTransaction(Transaction transaction) {
		
		if(transaction.getTransactionName() == "" || transaction.getTransactionName() == null) {
			transaction.setTransactionName("My Transaction");
		}
		if(transaction.getAmount() == 0.0) {
			return false;
		}
		return true;
	}

}
