package com.fauxshop.spring.service;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.*;
import org.springframework.test.context.junit4.*;
import org.springframework.test.context.transaction.*;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.*;
import com.fauxshop.spring.model.Account;
import com.fauxshop.spring.model.Cart;
import com.fauxshop.spring.model.Inventory;
import com.fauxshop.spring.model.SessionAccount;
import com.fauxshop.spring.model.TransactionLog;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/dbHibernate-context.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class })
@Transactional
public class TransactionServiceTest {	
	private static final Logger log = LoggerFactory.getLogger(TransactionServiceTest.class);
	Mockery context = new Mockery();	

	@Autowired
	private TransactionService transactionService;

	private TransactionLog transactionLog;	

	/*	We aren't using this at the moment:
	public AccountServiceImplTest() {
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,"com.mysql.jdbc.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,"jdbc:mysql://localhost:3306/fauxleather"); 
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,"root");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,"pass");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA,"fauxleather");
		}	*/

	protected static IDataSet getDataSet() throws Exception {
		log.error("in getDataSet()");
		return new FlatXmlDataSetBuilder().build(new FileInputStream("transaction-dataset.xml"));
	}	

	protected static IDataSet getEmptyDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream("empty-dataset.xml"));
	}	

	static IDatabaseTester databaseTester;

	@Before
	public void setup() throws Exception{
		databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		databaseTester.setDataSet(getEmptyDataSet()); databaseTester.onSetup();
	}		   

	@AfterClass
	public static void oneTimeTearDown() throws Exception {
		databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		databaseTester.setDataSet(getEmptyDataSet()); databaseTester.onSetup();
	}

	// methods left to test:
	///*	
	//	    public List<TransactionLog> listTransactions();


	//	    public void createTransaction(int cartId, String sessionId, long trackingNumber);
	//	    public void createTransactionFromSessionId(int cartId, String sessionId, long trackingNumber);
	//	    public void createTransactionsFromCartList(List<Cart> cartList, String sessionId);
	//	    public boolean createTransactionsFromSessionCartList(List<Cart> cartList, String sessionId);
	//	    public TransactionLog getLastTransactionByAccountId(int accountId);
	//	    public TransactionLog getLastTransactionBySessionId(String sessionId);
	//	    public void setTransactionToConfirmed(long trackingNumber);
	//	    public void updateMessage(TransactionLog transaction, String message);*/	   

	@Test
	@Transactional
	public void addTransactionTest() throws Exception {
		transactionLog = new TransactionLog();

		// expectations
		context.checking(new Expectations() {{
			/*Assertion is made at the end of this test*/
		}});		   

		// test	
		BigDecimal shippingCost = new BigDecimal("12.34");
		BigDecimal tax = new BigDecimal("1.23");
		BigDecimal orderCost = new BigDecimal("274.04");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = sdf.parse("01/01/2010");
		transactionLog.setAccountId(-1);
		transactionLog.setSessionId("ABC123");
		transactionLog.setCartId(-1);
		transactionLog.setOrderQuantity(2);
		transactionLog.setShipName("shipnametest");
		transactionLog.setShipAddress("shipaddresstest");
		transactionLog.setShipAddress2("shipaddress2test");
		transactionLog.setCity("citytest");
		transactionLog.setState("statetest");
		transactionLog.setZip("12345");
		transactionLog.setCountry("countrytest");
		transactionLog.setPhone("phonenumbertest");
		transactionLog.setShippingCost(shippingCost);
		transactionLog.setTax(tax);
		transactionLog.setOrderEmail("emailtest@emailtest.com");
		transactionLog.setDate(date);
		transactionLog.setShipped(false);
		transactionLog.setTrackingNumber(1234567890);
		transactionLog.setInventoryDetailId(-1);
		transactionLog.setOrderCost(orderCost);
		transactionLog.setMessage("messagetest");
		transactionLog.setConfirmed(false);
		transactionService.addTransaction(transactionLog);

		IDataSet expds = new FlatXmlDataSetBuilder().build(new FileInputStream("transaction-dataset.xml"));
		ITable expectedTable = expds.getTable("transaction");
		IDatabaseConnection connection = databaseTester.getConnection();
		IDataSet databaseDataSet = connection.createDataSet();
		ITable actualTable = databaseDataSet.getTable("transaction");
		String[] ignoredColumns = new String[1];
		ignoredColumns[0] = "transaction_id";
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, ignoredColumns);

		// verify
		context.assertIsSatisfied();
	}	   

	@Test
	@Transactional
	public void updateTransactionTest() throws Exception {
		transactionLog = new TransactionLog();

		// expectations
		context.checking(new Expectations() {{
			/*Assertion is made at the end of this test*/
		}});		   

		// test	
		BigDecimal shippingCost = new BigDecimal("12.34");
		BigDecimal tax = new BigDecimal("1.23");
		BigDecimal orderCost = new BigDecimal("274.04");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = sdf.parse("01/01/2010");
		transactionLog.setAccountId(-1);
		transactionLog.setSessionId("ABC123");
		transactionLog.setCartId(-1);
		transactionLog.setOrderQuantity(2);
		transactionLog.setShipName("shipnametest");
		transactionLog.setShipAddress("shipaddresstest");
		transactionLog.setShipAddress2("shipaddress2test");
		transactionLog.setCity("citytest");
		transactionLog.setState("statetest");
		transactionLog.setZip("12345");
		transactionLog.setCountry("countrytest");
		transactionLog.setPhone("phonenumbertest");
		transactionLog.setShippingCost(shippingCost);
		transactionLog.setTax(tax);
		transactionLog.setOrderEmail("emailtest@emailtest.com");
		transactionLog.setDate(date);
		transactionLog.setShipped(false);
		transactionLog.setTrackingNumber(1234567890);
		transactionLog.setInventoryDetailId(-1);
		transactionLog.setOrderCost(orderCost);
		transactionLog.setMessage("messagetest");
		transactionLog.setConfirmed(false);
		transactionService.updateTransaction(transactionLog);

		IDataSet expds = new FlatXmlDataSetBuilder().build(new FileInputStream("transaction-dataset.xml"));
		ITable expectedTable = expds.getTable("transaction");
		IDatabaseConnection connection = databaseTester.getConnection();
		IDataSet databaseDataSet = connection.createDataSet();
		ITable actualTable = databaseDataSet.getTable("transaction");
		String[] ignoredColumns = new String[1];
		ignoredColumns[0] = "transaction_id";
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, ignoredColumns);

		// verify
		context.assertIsSatisfied();
	}

	@Test
	@Transactional
	public void getTransactionByIdTest() throws Exception {	
		databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();			

		// test	
		final TransactionLog transactionLog = new TransactionLog();
		BigDecimal shippingCost = new BigDecimal("12.34");
		BigDecimal tax = new BigDecimal("1.23");
		BigDecimal orderCost = new BigDecimal("274.04");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss.S", Locale.ENGLISH);
		Date date = dateFormat.parse("2010-01-01 00:00:00.0");
		transactionLog.setTransactionId(-1);
		transactionLog.setAccountId(-1);
		transactionLog.setSessionId("ABC123");
		transactionLog.setCartId(-1);
		transactionLog.setOrderQuantity(2);
		transactionLog.setShipName("shipnametest");
		transactionLog.setShipAddress("shipaddresstest");
		transactionLog.setShipAddress2("shipaddress2test");
		transactionLog.setCity("citytest");
		transactionLog.setState("statetest");
		transactionLog.setZip("12345");
		transactionLog.setCountry("countrytest");
		transactionLog.setPhone("phonenumbertest");
		transactionLog.setShippingCost(shippingCost);
		transactionLog.setTax(tax);
		transactionLog.setOrderEmail("emailtest@emailtest.com");
		transactionLog.setDate(date);
		transactionLog.setShipped(false);
		transactionLog.setTrackingNumber(1234567890);
		transactionLog.setInventoryDetailId(-1);
		transactionLog.setOrderCost(orderCost);
		transactionLog.setMessage("messagetest");
		transactionLog.setConfirmed(false);

		final TransactionLog actualTransactionLog = transactionService.getTransactionById(-1);	

		// expectations
		context.checking(new Expectations() {{
			assertEquals(transactionLog.toString(), actualTransactionLog.toString());
		}});		   
		// verify
		context.assertIsSatisfied();	
	}

	@Test
	@Transactional
	public void removeTransactionTest() throws Exception {	
		databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();			

		// test
		transactionService.removeTransaction(-1);	

		// expectations
		IDataSet expds = new FlatXmlDataSetBuilder().build(new FileInputStream("empty-dataset.xml"));
		ITable expectedTable = expds.getTable("transaction");
		IDatabaseConnection connection = databaseTester.getConnection();
		IDataSet databaseDataSet = connection.createDataSet();
		ITable actualTable = databaseDataSet.getTable("transaction");
		Assertion.assertEquals(expectedTable, actualTable);

		// verify
		context.assertIsSatisfied();
	}	

	@Test
	@Transactional
	public void createTransactionTest() throws Exception {		
		databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();		
		
		// first, we have to remove the existing transaction from the test database:
		transactionService.removeTransaction(-1);

		// test	
		List<Cart> cartList = new ArrayList<Cart>();
		Cart cart = new Cart();
		BigDecimal pricePerItem = new BigDecimal("123.45");
		BigDecimal shippingCost = new BigDecimal("12.34");
		BigDecimal tax = new BigDecimal("1.23");
		cart.setCartId(-1);
		cart.setAccountId(-1);
		cart.setSessionId("ABC123");
		cart.setInventoryDetailId(-10);
		cart.setQuantity(2);
		cart.setPricePerItem(pricePerItem);
		cart.setShippingCost(shippingCost);
		cart.setTax(tax);
		cartList.add(cart);
		transactionService.createTransactionsFromCartList(cartList, "ABC123"); 		

		// expectations
		IDataSet expds = new FlatXmlDataSetBuilder().build(new FileInputStream("transaction-dataset.xml"));
		ITable expectedTable = expds.getTable("transaction");
		IDatabaseConnection connection = databaseTester.getConnection();
		IDataSet databaseDataSet = connection.createDataSet();
		ITable actualTable = databaseDataSet.getTable("transaction");
		String[] ignoredColumns = new String[4];
		ignoredColumns[0] = "transaction_id";
		ignoredColumns[1] = "date";
		ignoredColumns[2] = "message";
		ignoredColumns[3] = "tracking_number";
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, ignoredColumns);

		// verify
		context.assertIsSatisfied();	
	}
}