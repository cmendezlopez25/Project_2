package com.revature.test.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.dao.AccountDao;
import com.revature.pojo.Account;
import com.revature.service.AccountServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {

	private AccountServiceImpl accountService;
	
	private Account existingAccount;
	private Account newAccount;
	private Account badAccount;
	
	@Mock
	private AccountDao accDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		accountService = new AccountServiceImpl();
		existingAccount = new Account(1, "existing Account", null, null);
//		existingAccount.setAccountId(1);
//		existingAccount.setAccountName("existing Account");
//		existingAccount.setTransactions(null);
//		existingAccount.setUserRoleAccounts(null);
		
		when(accDao.createAccount(existingAccount)).thenReturn(false);
		when(accDao.readAccount(existingAccount)).thenReturn(existingAccount);
		when(accDao.updateAccount(existingAccount)).thenReturn(true);
		when(accDao.deleteAccount(existingAccount)).thenReturn(true);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=NullPointerException.class)
	public void createAccountNull() {
		accountService.createAccount(null);
	}
	
	@Test
	public void createAccountExisting() {
		assertFalse(accountService.createAccount(existingAccount));
		Mockito.verify(accDao).createAccount(existingAccount);
	}
	

}
