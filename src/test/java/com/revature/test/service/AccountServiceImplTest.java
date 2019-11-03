package com.revature.test.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.revature.pojo.Role;
import com.revature.pojo.User;
import com.revature.pojo.UserRoleAccount;
import com.revature.service.AccountServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {

	private AccountServiceImpl accountService;
	
	private Account existingAccount;
	private Account newAccount;
	private Account badAccount;
	private List<Account> accList;
	
	private User emptyUser;
	private User existingUser;
	
	Set<UserRoleAccount> userRoleAccounts;
	
	Role r = new Role(1, "Owner");
	UserRoleAccount ura;
	
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
		
		emptyUser = new User();
		existingUser = new User("rickywang@email.com","password", "Ricky", "Wang", null);
		
		existingAccount = new Account(1, "existing Account", null, null);
		newAccount = new Account(2, "new account", null, null);
		badAccount = new Account(3, "", null, null);
		
		accList = new ArrayList<Account>();
		accList.add(new Account(4, "account 1", null, null));
		accList.add(new Account(5, "account 2", null, null));
		accList.add(new Account(6, "account 3", null, null));
		accList.add(new Account(7, "account 4", null, null));
		
		
		when(accDao.createAccount(existingAccount)).thenReturn(existingAccount);
		when(accDao.readAccount(existingAccount)).thenReturn(existingAccount);
		when(accDao.updateAccount(existingAccount)).thenReturn(true);
		when(accDao.deleteAccount(existingAccount)).thenReturn(true);
		
		when(accDao.createAccount(newAccount)).thenReturn(newAccount);
		when(accDao.readAccount(newAccount)).thenReturn(null);
		when(accDao.updateAccount(newAccount)).thenReturn(false);
		when(accDao.deleteAccount(newAccount)).thenReturn(false);
		
		when(accDao.readAccount(badAccount)).thenReturn(null);
		when(accDao.deleteAccount(badAccount)).thenReturn(false);
		
		when(accDao.readAllAccountsByUser(emptyUser)).thenReturn(null);
		when(accDao.readAllAccountsByUser(existingUser)).thenReturn(accList);
		
		accountService.setAccountDao(accDao);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void createAccountExisting() {
		assertEquals(existingAccount, accountService.createAccount(existingAccount));
		Mockito.verify(accDao).createAccount(existingAccount);
	}
	
	@Test
	public void readAccountExisting() {
		Account expect = new Account(1, "existing Account", null, null);
		assertEquals(expect, accountService.readAccount(existingAccount));
		Mockito.verify(accDao).readAccount(existingAccount);
	}
	
	@Test
	public void updateAccountExisting() {
		assertTrue(accountService.updateAccount(existingAccount));
		Mockito.verify(accDao).updateAccount(existingAccount);
	}
	
	@Test
	public void deleteAccountExisting() {
		assertTrue(accountService.deleteAccount(existingAccount));
		Mockito.verify(accDao).deleteAccount(existingAccount);
	}
	
	@Test
	public void createAccountNew() {
		assertEquals(newAccount, accountService.createAccount(newAccount));
		Mockito.verify(accDao).createAccount(newAccount);
	}
	
	@Test
	public void readAccountNew() {
		assertEquals(null, accountService.readAccount(newAccount));
		Mockito.verify(accDao).readAccount(newAccount);
	}
	
	@Test
	public void updateAccountNew() {
		assertFalse(accountService.updateAccount(newAccount));
		Mockito.verify(accDao).updateAccount(newAccount);
	}
	
	@Test
	public void deleteAccountNew() {
		assertFalse(accountService.deleteAccount(newAccount));
		Mockito.verify(accDao).deleteAccount(newAccount);
	}
	
	@Test
	public void createAccountBad() {
		assertEquals(null, accountService.createAccount(badAccount));
		Mockito.verifyZeroInteractions(accDao);
	}
	
	@Test
	public void readAccountBad() {
		assertEquals(null, accountService.readAccount(badAccount));
		Mockito.verify(accDao).readAccount(badAccount);
	}
	
	@Test
	public void updateAccountBad() {
		assertFalse(accountService.updateAccount(badAccount));
		Mockito.verifyZeroInteractions(accDao);
	}
	
	@Test
	public void deleteAccountBad() {
		assertFalse(accountService.deleteAccount(badAccount));
		Mockito.verify(accDao).deleteAccount(badAccount);
	}

	@Test(expected=NullPointerException.class)
	public void createAccountNull() {
		accountService.createAccount(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void updateAccountNull() {
		accountService.createAccount(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void deleteAccountNull() {
		accountService.createAccount(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void readAccountNull() {
		accountService.createAccount(null);
	}
	
	@Test
	public void readAllAccountsByUserEmpty() {
		assertEquals(null, accountService.readAllAccountsByUser(emptyUser));
	}
	
	@Test
	public void readAllAccountsByUserExisting() {
		assertEquals(accList, accountService.readAllAccountsByUser(existingUser));
	}
}
