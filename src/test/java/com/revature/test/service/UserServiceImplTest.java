package com.revature.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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

import com.revature.dao.UserDao;
import com.revature.pojo.Account;
import com.revature.pojo.Role;
import com.revature.pojo.User;
import com.revature.service.AccountService;
import com.revature.service.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
	private UserServiceImpl userService;
	private User existingUser;
	private User newUser;
	private User badFormatUser;
	
	@Mock
	private UserDao userDao;
	
	@Mock
	private AccountService accountService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		userService = new UserServiceImpl();
		
		existingUser = new User();
		existingUser.setEmail("rickywang@email.com");
		existingUser.setPassword("password");
		existingUser.setFirstName("Ricky");
		existingUser.setLastName("Wang");
		
		newUser = new User();
		newUser.setEmail("notexist@email.com");
		newUser.setPassword("password");
		newUser.setFirstName("Not");
		newUser.setLastName("Exist");
		
		badFormatUser = new User();
		badFormatUser.setEmail("Ah");
		badFormatUser.setPassword("");
		badFormatUser.setFirstName("");
		badFormatUser.setLastName("");
		
		when(userDao.createUser(newUser)).thenReturn(true);
		when(userDao.createUser(existingUser)).thenReturn(false);
		when(userDao.readUser(newUser)).thenReturn(null);
		when(userDao.readUser(existingUser)).thenReturn(existingUser);
		when(userDao.updateUser(newUser)).thenReturn(false);
		when(userDao.updateUser(existingUser)).thenReturn(true);
		when(userDao.deleteUser(newUser)).thenReturn(false);
		when(userDao.deleteUser(existingUser)).thenReturn(true);
		
		userService.setUserDao(userDao);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = NullPointerException.class)
	public void createUserNull() {
		userService.createUser(null);
	}
	
	@Test
	public void createUserExisting() {
		assertFalse(userService.createUser(existingUser));
		Mockito.verify(userDao).createUser(existingUser);
	}
	
	@Test
	public void createUserNew() {
		Account account = new Account();
		account.setAccountName("Default Account");
		when(accountService.createAccount(newUser, account)).thenReturn(account);
		userService.setAccountService(accountService);
		
		assertTrue(userService.createUser(newUser));
		Mockito.verify(userDao).createUser(newUser);
		Mockito.verify(accountService).createAccount(newUser, account);
	}
	
	@Test
	public void createUserIncorrectFormat() {
		assertFalse(userService.createUser(badFormatUser));
	}
	
	@Test
	public void createUserAccountFail() {
		Account account = new Account();
		account.setAccountName("Default Account");
		when(accountService.createAccount(newUser, account)).thenReturn(null);
		userService.setAccountService(accountService);
		
		assertFalse(userService.createUser(newUser));
		Mockito.verify(accountService).createAccount(newUser, account);
	}
	
	@Test(expected = NullPointerException.class)
	public void loginUserNull() {
		userService.loginUser(null);
	}
	
	@Test
	public void loginUserFail() {
		assertEquals("Should return null", null, userService.loginUser(newUser));
		Mockito.verify(userDao).readUser(newUser);
	}
	
	@Test
	public void loginUserSuccess() {
		assertEquals(existingUser, userService.loginUser(existingUser));
		Mockito.verify(userDao).readUser(existingUser);
	}
	
	@Test(expected = NullPointerException.class)
	public void updateUserNull() {
		userService.updateUser(null);
	}
	
	@Test
	public void updateUserFail() {
		assertFalse(userService.updateUser(newUser));
		Mockito.verify(userDao).updateUser(newUser);
	}
	
	@Test
	public void updateUserSuccess() {
		assertTrue(userService.updateUser(existingUser));
		Mockito.verify(userDao).updateUser(existingUser);
	}
	
	@Test
	public void updateUserIncorrectFormat() {
		assertFalse(userService.updateUser(badFormatUser));
	}
	
	@Test(expected = NullPointerException.class)
	public void deleteUserNull() {
		userService.deleteUser(null);
	}
	
	@Test
	public void deleteUserFail() {
		assertFalse(userService.deleteUser(newUser));
		Mockito.verify(userDao).deleteUser(newUser);
	}
	
	@Test
	public void deleteUserSuccess() {
		assertTrue(userService.deleteUser(existingUser));
		Mockito.verify(userDao).deleteUser(existingUser);
	}
	
	@Test
	public void deleteUserIncorrectFormat() {
		assertFalse(userService.deleteUser(badFormatUser));
	}
	
	@Test
	public void readAllUsersEmpty() {
		when(userDao.readAllUsers()).thenReturn(new ArrayList<User>());
		assertEquals(new ArrayList<User>(), userService.readAllUsers());
	}
	
	@Test
	public void readAllUsersFull() {
		List<User> userList = new ArrayList<User>();
		userList.add(newUser);
		userList.add(existingUser);
		when(userDao.readAllUsers()).thenReturn(userList);
		assertEquals(userList, userService.readAllUsers());
	}
	
	@Test(expected = NullPointerException.class)
	public void readUsersWithRoleByAccountNull() {
		userService.readUsersWithRoleByAccount(null);
	}
	
	@Test
	public void readUsersWithRoleByAccountFail() {
		Map<User, Role> userMap = new HashMap<User, Role>();
		when(userDao.readUsersWithRoleByAccount(new Account())).thenReturn(userMap);
		assertEquals(userMap, userService.readUsersWithRoleByAccount(new Account()));
		Mockito.verify(userDao).readUsersWithRoleByAccount(new Account());
	}
	
	@Test
	public void readUsersWithRoleByAccountSuccess() {
		Map<User, Role> userMap = new HashMap<User, Role>();
		Account account = new Account();
		account.setAccountId(1);
		Role role = new Role();
		role.setRoleName("Owner");
		userMap.put(newUser, role);
		
		role = new Role();
		role.setRoleName("Admin");
		userMap.put(existingUser, role);

		when(userDao.readUsersWithRoleByAccount(account)).thenReturn(userMap);
		assertEquals(userMap, userService.readUsersWithRoleByAccount(account));
		Mockito.verify(userDao).readUsersWithRoleByAccount(account);
	}
}
