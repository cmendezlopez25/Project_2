package com.revature.test.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.HashSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import com.revature.dao.TransactionDaoImpl;
import com.revature.pojo.Account;
import com.revature.pojo.Category;
import com.revature.pojo.Transaction;
import com.revature.util.SessionFactoryUtil;

@ContextConfiguration(classes=SessionFactoryUtil.class)
@RunWith(MockitoJUnitRunner.class)
public class TransactionDaoImplTest {
	private TransactionDaoImpl transactionDao;
	private Transaction existingTrans;
	private Transaction badFormatTrans;
	private Transaction newTrans;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		transactionDao = new TransactionDaoImpl();
		
		existingTrans = new Transaction(1, new Account(), 1.0, LocalDate.now(),
				"breakfast", "I had a yummy breakfast", "no", new HashSet<Category>());
		
		badFormatTrans = new Transaction(2, new Account(), 0, LocalDate.now(),
				"", "", "no", new HashSet<Category>());
		
		newTrans = new Transaction(3, new Account(), 4.56, LocalDate.now(),
				"new transaction", " no note", "no", new HashSet<Category>());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateExistingTransaction() {
		assertFalse(transactionDao.createTransaction(existingTrans));
		Mockito.verify(transactionDao.getCurrentSession()).save(existingTrans);
	}
	
	@Test(expected = NullPointerException.class)
	public void createTransactionNull() {
		transactionDao.createTransaction(null);
	}
	
	@Test
	public void createNewTransaction() {
		assertTrue(transactionDao.createTransaction(newTrans));
	}

	@Test
	public void createTransactionIncorrectFormat() {
		assertFalse(transactionDao.createTransaction(badFormatTrans));
	}

}
