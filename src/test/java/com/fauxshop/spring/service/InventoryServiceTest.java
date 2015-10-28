package com.fauxshop.spring.service;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.hibernate.Query;
import org.hibernate.Session;
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
import com.fauxshop.spring.model.InventoryCategoryCode;
import com.fauxshop.spring.model.InventoryDetail;
import com.fauxshop.spring.model.SessionAccount;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/spring/applicationContext-hibernate.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class })
@Transactional
public class InventoryServiceTest {	
	private static final Logger log = LoggerFactory.getLogger(InventoryServiceTest.class);
	Mockery context = new Mockery();	

	@Autowired
	private InventoryService inventoryService;

	private Inventory inventory;		

	/*	We aren't using this at the moment:
	public AccountServiceImplTest() {
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,"com.mysql.jdbc.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,"jdbc:mysql://localhost:3306/fauxleather"); 
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,"root");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,"pass");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA,"fauxleather");
		}	*/

	protected static IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream("inventory-dataset.xml"));
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
	public void listInventoryTest() throws Exception {
		databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();			

		// test	
		final List<Inventory> expectedInventoryList = new ArrayList<Inventory>();
		Inventory expectedInventory = new Inventory();
		BigDecimal priceUsd = new BigDecimal("500.00");
		expectedInventory.setInventoryId(-111);
		expectedInventory.setInventoryTypeCd(10);
		expectedInventory.setInventoryCatCd(100);
		expectedInventory.setInventoryTxt("Leather Jacket");
		expectedInventory.setInventoryDescription("This is the description of the leather jacket.");
		expectedInventory.setInventoryCare("This is an explanation of how to care for the product.");
		expectedInventory.setInventorySizeDesc("This is the inventory size description.");
		expectedInventory.setBrand("Dereks Jackets and Clothing");
		expectedInventory.setPriceUsd(priceUsd);
		expectedInventory.setInStock(true);
		expectedInventory.setImg("/resources/img/product1.jpg");
		expectedInventoryList.add(expectedInventory);

		final List<Inventory> actualInventoryList = inventoryService.listInventory();		

		// expectations
		context.checking(new Expectations() {{
			assertEquals(expectedInventoryList.toString(), actualInventoryList.toString());
		}});		   
		// verify
		context.assertIsSatisfied();
	}

	@Test
	@Transactional
	public void getInventoryByIdTest() throws Exception {
		databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();			

		// test	
		final Inventory expectedInventory = new Inventory();
		BigDecimal priceUsd = new BigDecimal("500.00");
		expectedInventory.setInventoryId(-111);
		expectedInventory.setInventoryTypeCd(10);
		expectedInventory.setInventoryCatCd(100);
		expectedInventory.setInventoryTxt("Leather Jacket");
		expectedInventory.setInventoryDescription("This is the description of the leather jacket.");
		expectedInventory.setInventoryCare("This is an explanation of how to care for the product.");
		expectedInventory.setInventorySizeDesc("This is the inventory size description.");
		expectedInventory.setBrand("Dereks Jackets and Clothing");
		expectedInventory.setPriceUsd(priceUsd);
		expectedInventory.setInStock(true);
		expectedInventory.setImg("/resources/img/product1.jpg");

		final Inventory actualInventory = inventoryService.getInventoryById(-111);		

		// expectations
		context.checking(new Expectations() {{
			assertEquals(expectedInventory.toString(), actualInventory.toString());
		}});		   
		// verify
		context.assertIsSatisfied();
	}		

	@Test
	@Transactional
	public void getInventoryByInventoryDetailIdTest() throws Exception {
		databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();			

		// test	
		final Inventory expectedInventory = new Inventory();
		BigDecimal priceUsd = new BigDecimal("500.00");
		expectedInventory.setInventoryId(-111);
		expectedInventory.setInventoryTypeCd(10);
		expectedInventory.setInventoryCatCd(100);
		expectedInventory.setInventoryTxt("Leather Jacket");
		expectedInventory.setInventoryDescription("This is the description of the leather jacket.");
		expectedInventory.setInventoryCare("This is an explanation of how to care for the product.");
		expectedInventory.setInventorySizeDesc("This is the inventory size description.");
		expectedInventory.setBrand("Dereks Jackets and Clothing");
		expectedInventory.setPriceUsd(priceUsd);
		expectedInventory.setInStock(true);
		expectedInventory.setImg("/resources/img/product1.jpg");

		final Inventory actualInventory = inventoryService.getInventoryByInventoryDetailId(-1);		

		// expectations
		context.checking(new Expectations() {{
			assertEquals(expectedInventory.toString(), actualInventory.toString());
		}});		   
		// verify
		context.assertIsSatisfied();
	}		

	@Test
	@Transactional
	public void getInventoryListByInventoryCatCdTest() throws Exception {
		databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();			

		// test	
		final List<Inventory> expectedInventoryList = new ArrayList<Inventory>();
		Inventory expectedInventory = new Inventory();
		BigDecimal priceUsd = new BigDecimal("500.00");
		expectedInventory.setInventoryId(-111);
		expectedInventory.setInventoryTypeCd(10);
		expectedInventory.setInventoryCatCd(100);
		expectedInventory.setInventoryTxt("Leather Jacket");
		expectedInventory.setInventoryDescription("This is the description of the leather jacket.");
		expectedInventory.setInventoryCare("This is an explanation of how to care for the product.");
		expectedInventory.setInventorySizeDesc("This is the inventory size description.");
		expectedInventory.setBrand("Dereks Jackets and Clothing");
		expectedInventory.setPriceUsd(priceUsd);
		expectedInventory.setInStock(true);
		expectedInventory.setImg("/resources/img/product1.jpg");
		expectedInventoryList.add(expectedInventory);

		final List<Inventory> actualInventoryList = inventoryService.getInventoryListByInventoryCatCd(100);		

		// expectations
		context.checking(new Expectations() {{
			assertEquals(expectedInventoryList.toString(), actualInventoryList.toString());
		}});		   
		// verify
		context.assertIsSatisfied();
	}		
	
	@Test
	@Transactional
	public void getInventoryCategoryCodeTest() throws Exception {
		databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();			

		// test	
		final InventoryCategoryCode expectedInventoryCategoryCode = new InventoryCategoryCode();
		expectedInventoryCategoryCode.setInventoryCatCd(100);
		expectedInventoryCategoryCode.setInventoryCatTxt("Jackets");
		expectedInventoryCategoryCode.setInventoryCatDescription("Inventory of the jackets that we sell at the FauxShop");
		
		final InventoryCategoryCode actualInventoryCategoryCode = inventoryService.getInventoryCategoryCode(100);		

		// expectations
		context.checking(new Expectations() {{
			assertEquals(expectedInventoryCategoryCode.toString(), actualInventoryCategoryCode.toString());
		}});		   
		// verify
		context.assertIsSatisfied();
	}	
	
	@Test
	@Transactional
	public void getBestSellerInventoryListTest() throws Exception {
		databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();			

		// execute
		final List<Inventory> inventoryList = inventoryService.getBestSellerInventoryList();		

		// expectations
		context.checking(new Expectations() {{
			assertTrue(!inventoryList.isEmpty());
		}});		   
		
		// verify
		context.assertIsSatisfied();
	}			

}
