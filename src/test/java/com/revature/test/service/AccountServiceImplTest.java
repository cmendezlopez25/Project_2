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
	private AccountDao accountDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		accountService = new AccountServiceImpl();
		existingAccount = new Account();
		existingAccount.setAccountId(1);
		existingAccount.setAccountName("Mock Account");
		existingAccount.setTransactions(null);
		existingAccount.setUserRoleAccounts(null);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
