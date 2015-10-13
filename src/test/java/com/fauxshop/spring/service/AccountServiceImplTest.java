package com.fauxshop.spring.service;

import static org.junit.Assert.*;

import java.io.FileInputStream;
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
import com.fauxshop.spring.model.SessionAccount;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/dbHibernate-context.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class })
@Transactional
public class AccountServiceImplTest {	
	private static final Logger log = LoggerFactory.getLogger(AccountServiceImplTest.class);
	Mockery context = new Mockery();	
	
	@Autowired
	private AccountService accountService;
	
	private Account account;	
	private SessionAccount sessionAccount;

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
		   return new FlatXmlDataSetBuilder().build(new FileInputStream("account-dataset.xml"));
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
	    	   
	   @Test
	   @Transactional
	   public void addAccountTest() throws Exception {
		   account = new Account();

		   // expectations
		   context.checking(new Expectations() {{
			   /*Assertion is made at the end of this test*/
		   }});		   
		   
	      // test	
			account.setAddress("addresstest");
			account.setAddress2("address2test");
			account.setCity("citytest");
			account.setCountry("countrytest");
			account.setEmail("emailtest@emailtest.com");
			account.setEnabled(true);
			account.setFirstName("firstnametest");
			account.setLastName("lastnametest");
			account.setPassword("passwordtest");
			account.setPhoneNumber("phonenumbertest");
			account.setState("statetest");
			account.setUserLogin("userlogintest");
			account.setZip("ziptest");
			account.setShipAddress("shipaddresstest");
			account.setShipAddress2("shipaddress2test");
			account.setShipCity("shipcitytest");
			account.setShipState("shipstatetest");
			account.setShipCountry("shipcountrytest");
			account.setShipName("shipnametest");
			account.setShipPhone("shipphonetest");
			account.setShipZip("shipziptest");
			accountService.addAccount(account);
		   		   
		IDataSet expds = new FlatXmlDataSetBuilder().build(new FileInputStream("account-dataset.xml"));
		ITable expectedTable = expds.getTable("account");
		IDatabaseConnection connection = databaseTester.getConnection();
		IDataSet databaseDataSet = connection.createDataSet();
		ITable actualTable = databaseDataSet.getTable("account");
		String[] ignoredColumns = new String[1];
		ignoredColumns[0] = "account_id";
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, ignoredColumns);

	    // verify
	    context.assertIsSatisfied();
	   }
	   
	   @Test
	   @Transactional
	   public void addSessionAccountTest() throws Exception {
		   sessionAccount = new SessionAccount();

		   // expectations
		   context.checking(new Expectations() {{
			   /*Assertion is made at the end of this test*/
		   }});		   
		   
	      // test	
		   	sessionAccount.setSessionId("ABC123");
		   	sessionAccount.setEmail("emailtest@emailtest.com");
		   	sessionAccount.setShipAddress("shipaddresstest");
		   	sessionAccount.setShipAddress2("shipaddress2test");
		   	sessionAccount.setShipCity("shipcitytest");
		   	sessionAccount.setShipState("shipstatetest");
		   	sessionAccount.setShipCountry("shipcountrytest");
		   	sessionAccount.setShipName("shipnametest");
		   	sessionAccount.setShipPhone("shipphonetest");
		   	sessionAccount.setShipZip("shipziptest");
		   	accountService.addSessionAccount(sessionAccount);		   
		   		   
		IDataSet expds = new FlatXmlDataSetBuilder().build(new FileInputStream("account-dataset.xml"));
		ITable expectedTable = expds.getTable("session_account");
		IDatabaseConnection connection = databaseTester.getConnection();
		IDataSet databaseDataSet = connection.createDataSet();
		ITable actualTable = databaseDataSet.getTable("session_account");
		String[] ignoredColumns = new String[1];
		ignoredColumns[0] = "session_account_id";
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, ignoredColumns);

	    // verify
	    context.assertIsSatisfied();
	   }	   
	   
	   @Test
	   @Transactional
	   public void getAccountByNameTest() throws Exception {
		   databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		   databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();

		   // test			   
		   accountService.getAccountByName("userlogintest");	

		   // expectations
		   context.checking(new Expectations() {{			   
			   assertNotNull(accountService.getAccountByName("userlogintest"));
		   }});					

		   // verify
		   context.assertIsSatisfied();
	   }	 
	   
	   @Test
	   @Transactional
	   public void createUserRoleTest() throws Exception {

		   // expectations
		   context.checking(new Expectations() {{
			   /*Assertion is made at the end of this test*/
		   }});		   
		   
	      // test	
		   accountService.createUserRole("userlogintest");			   
		   		   
		IDataSet expds = new FlatXmlDataSetBuilder().build(new FileInputStream("account-dataset.xml"));
		ITable expectedTable = expds.getTable("roles");
		IDatabaseConnection connection = databaseTester.getConnection();
		IDataSet databaseDataSet = connection.createDataSet();
		ITable actualTable = databaseDataSet.getTable("roles");
		Assertion.assertEquals(expectedTable, actualTable);

	    // verify
	    context.assertIsSatisfied();		   
	   }
	   
	   @Test
	   @Transactional
	   public void isUserLoginUniqueTest() throws Exception {
		   databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		   databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();

		   // test	
		   accountService.isUserLoginUnique("userlogintest");	

		   // expectations
		   context.checking(new Expectations() {{			
			   assertFalse(accountService.isUserLoginUnique("userlogintest"));
		   }});					

		   // verify
		   context.assertIsSatisfied();
	   }	   
	   
	   @Test
	   @Transactional
	   public void isSessionAccountAlreadyRegistered() throws Exception {
		   databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		   databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();

		   // test	
		   accountService.isSessionAccountAlreadyRegistered("testuser");	

		   // expectations
		   context.checking(new Expectations() {{			
			   assertFalse(accountService.isSessionAccountAlreadyRegistered("testuser"));
		   }});					

		   // verify
		   context.assertIsSatisfied();
	   }
	   
	   @Test
	   @Transactional
	   public void getSessionAccountBySessionId() throws Exception {
		   databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		   databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();

		   // test	
		   accountService.getSessionAccountBySessionId("ABC123");	

		   // expectations
		   context.checking(new Expectations() {{			
			   assertNotNull(accountService.getSessionAccountBySessionId("ABC123"));
		   }});					

		   // verify
		   context.assertIsSatisfied();
	   }
	   
}
	