package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.pojo.Account;
import com.revature.pojo.Transaction;
import com.revature.service.AccountService;
import com.revature.service.TransactionService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class TransactionController {
	private TransactionService transactionService;
	private AccountService accountService;
	
	@Autowired
	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	
	@Autowired
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@GetMapping("/account/{accountId}/transaction")
	public List<Transaction> getAllTransactionsByAccount(@PathVariable int accountId) {
		Account account = accountService.readAccount(accountId);
		
		if (account == null) {
			return null;
		}
		
		List<Transaction> transactions = transactionService.readAllTransactionsByAccount(account);
		return transactions;
	}
	
	@PostMapping("/account/{accountId}/transaction")
	public void transactionPost(@RequestBody Transaction transaction, @PathVariable int accountId) {
		if(transaction == null) {
			throw new NullPointerException();
		}
		transactionService.createTransaction(transaction);		
	}
	
	@PutMapping("/transaction")
	public void transactionPut(@RequestBody Transaction transaction) {
		
	}
	
	@DeleteMapping("/transaction/{transactionId}")
	public void deleteTransaction(@RequestBody Transaction transaction, @PathVariable int transactionId) {
		if(transaction == null) {
			throw new NullPointerException();
		}
		transactionService.deleteTransaction(transaction);
	}
}
