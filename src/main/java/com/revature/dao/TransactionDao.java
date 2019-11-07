package com.revature.dao;

import java.util.List;

import com.revature.pojo.Account;
import com.revature.pojo.Transaction;

public interface TransactionDao {

	public boolean createTransaction(Transaction transaction);
	
	public Transaction readTransaction(Transaction transaction);
	
	public boolean updateTransaction(Transaction transaction);
	
	public boolean deleteTransaction(Transaction transaction);
	
	public List<Transaction> readAllTransactions();
	
	public List<Transaction> readAllTransactionsByAccount(Account account);
	
}
