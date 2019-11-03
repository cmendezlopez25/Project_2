package com.revature.test.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import com.revature.dao.UserDaoImpl;
import com.revature.pojo.User;
import com.revature.util.SessionFactoryUtil;

@ContextConfiguration(classes=SessionFactoryUtil.class)
@RunWith(MockitoJUnitRunner.class)
public class UserDaoImplTest {
	private UserDaoImpl userDao;
	private User existingUser;
	private User newUser;
	private User badFormatUser;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		userDao = new UserDaoImpl();
		
		existingUser = new User();
		existingUser.setEmail("rickywang@gmail.com");
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
		
		//when(sf.openSession()).thenReturn(sess);
		
		//userDao.setSessionFactory(sf);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = NullPointerException.class)
	public void createUserNull() {
		userDao.createUser(null);
	}

	@Test
	public void createExistingUser() {
		assertFalse(userDao.createUser(existingUser));
		Mockito.verify(userDao.getCurrentSession()).save(existingUser);
	}
	
	@Test
	public void createNewUser() {
		assertTrue(userDao.createUser(newUser));
		//Mockito.verify(sess).save(newUser);
	}
	
	@Test
	public void createUserIncorrectFormat() {
		assertFalse(userDao.createUser(badFormatUser));
		//Mockito.verifyZeroInteractions(sess);
	}
}
