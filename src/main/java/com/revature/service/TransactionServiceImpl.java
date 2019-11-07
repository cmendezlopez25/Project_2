package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.dao.TransactionDao;
import com.revature.pojo.Account;
import com.revature.pojo.Transaction;

@Component
public class TransactionServiceImpl implements TransactionService{
	
	private TransactionDao transactionDao;
	
	@Autowired
	public void setTransactionDao(TransactionDao transactionDao) {
		this.transactionDao = transactionDao;
	}

	@Override
	public boolean createTransaction(Transaction transaction) {
		transactionDao.createTransaction(transaction);
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
		Transaction newTransaction = transactionDao.readTransaction(transaction);
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
		
		return transactionDao.updateTransaction(transaction);
	}

	@Override
	public boolean deleteTransaction(Transaction transaction) {
		if(transaction == null) {
			return false;
		}
		if(!isValidTransaction(transaction)) {
			return false;
		}
		return transactionDao.deleteTransaction(transaction);
		
	}

	@Override
	public List<Transaction> readAllTransactionsByAccount(Account account) {
		if(account == null) {
			throw new NullPointerException();
		}
		
		return transactionDao.readAllTransactionsByAccount(account);
	}
	
	private boolean isValidTransaction(Transaction transaction) {
		if(transaction == null || transaction.getAmount() == 0.0) {
			return false;
		}
		if(transaction.getTransactionName() == "" || transaction.getTransactionName() == null) {
			transaction.setTransactionName("My Transaction");
		}
		
		return true;
	}
	
}
