package com.fauxshop.spring.service;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;

import junit.framework.TestCase;

import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.hibernate.SessionFactory;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.action.ReturnValueAction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.internal.runners.JUnit38ClassRunner;
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
import org.springframework.test.*;

import com.github.springtestdbunit.*;
import com.fauxshop.spring.dao.AccountDAO;
import com.fauxshop.spring.dao.AccountDAOImpl;
import com.fauxshop.spring.model.Account;

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
	private AccountDAO accountDAO;
	
	private Account account;	

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
			accountDAO.addAccount(account);			   
		   		   
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
	   public void getAccountByNameTest() throws Exception {
		   databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		   databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();

		   // test	
		   accountDAO.getAccountByName("userlogintest");	

		   // expectations
		   context.checking(new Expectations() {{			   
			   assertNotNull(accountDAO.getAccountByName("userlogintest"));
		   }});					

		   // verify
		   context.assertIsSatisfied();
	   }	 
	   
	   @Test
	   @Transactional
	   public void isUserLoginUniqueTest() throws Exception {
		   databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		   databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();

		   // test	
		   accountDAO.isUserLoginUnique("userlogintest");	

		   // expectations
		   context.checking(new Expectations() {{			
			   assertFalse(accountDAO.isUserLoginUnique("userlogintest"));
		   }});					

		   // verify
		   context.assertIsSatisfied();
	   }	   
	   
}
	