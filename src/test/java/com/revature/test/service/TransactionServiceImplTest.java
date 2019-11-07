package com.revature.test.service;

import java.time.LocalDate;
import java.util.HashSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.revature.dao.TransactionDaoImpl;
import com.revature.pojo.Account;
import com.revature.pojo.Category;
import com.revature.pojo.Transaction;
import com.revature.service.TransactionService;
import com.revature.service.TransactionServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceImplTest {
	private TransactionService transactionService;
	private Transaction existingTrans;
	private Transaction badFormatTrans;
	private Transaction newTrans;

	@Mock
	private TransactionDaoImpl transactionDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		transactionService = new TransactionServiceImpl();

		existingTrans = new Transaction(1, new Account(), 1.0, LocalDate.now(), "breakfast", "I had a yummy breakfast",
				"no", new HashSet<Category>());

		badFormatTrans = new Transaction(2, new Account(), 0, LocalDate.now(), "", "", "no", new HashSet<Category>());

		newTrans = new Transaction(3, new Account(), 4.56, LocalDate.now(), "new transaction", " no note", "no",
				new HashSet<Category>());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createTransactionNew() {
		assertFalse(trans)
	}

}
