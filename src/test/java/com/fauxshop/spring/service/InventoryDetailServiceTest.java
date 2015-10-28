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
import com.fauxshop.spring.model.InventoryDetail;
import com.fauxshop.spring.model.SessionAccount;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/spring/applicationContext-hibernate.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class })
@Transactional
public class InventoryDetailServiceTest {	
	private static final Logger log = LoggerFactory.getLogger(InventoryDetailServiceTest.class);
	Mockery context = new Mockery();	

	@Autowired
	private InventoryDetailService inventoryDetailService;

	private InventoryDetail inventoryDetail;		

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
	public void getInventoryDetailByInventoryIdTest() throws Exception {
		databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();			
		inventoryDetail = new InventoryDetail();

		// test	
		final List<InventoryDetail> expectedInventoryDetailList = new ArrayList<InventoryDetail>();
		InventoryDetail expectedInventoryDetail = new InventoryDetail();
		expectedInventoryDetail.setInventoryDetailId(-1);
		expectedInventoryDetail.setInventoryId(-111);
		expectedInventoryDetail.setProductCode("ABP30203CX");
		expectedInventoryDetail.setStockQuantity(129);
		expectedInventoryDetail.setSize("XS");
		expectedInventoryDetail.setColor("Black");
		expectedInventoryDetail.setThumbnail1("/resources/img/product1.jpg");
		expectedInventoryDetail.setThumbnail2("/resources/img/product11.jpg");
		expectedInventoryDetail.setThumbnail3("/resources/img/product12.jpg");
		expectedInventoryDetailList.add(expectedInventoryDetail);

		final List<InventoryDetail> actualInventoryDetailList = inventoryDetailService.getInventoryDetailByInventoryId(-111);		

		// expectations
		context.checking(new Expectations() {{
			assertEquals(expectedInventoryDetailList.toString(), actualInventoryDetailList.toString());
		}});		   
		// verify
		context.assertIsSatisfied();
	}

	@Test
	@Transactional
	public void getInventoryDetailByInventoryDetailIdTest() throws Exception {
		databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();			
		inventoryDetail = new InventoryDetail();

		// test	
		final InventoryDetail expectedInventoryDetail = new InventoryDetail();
		expectedInventoryDetail.setInventoryDetailId(-1);
		expectedInventoryDetail.setInventoryId(-111);
		expectedInventoryDetail.setProductCode("ABP30203CX");
		expectedInventoryDetail.setStockQuantity(129);
		expectedInventoryDetail.setSize("XS");
		expectedInventoryDetail.setColor("Black");
		expectedInventoryDetail.setThumbnail1("/resources/img/product1.jpg");
		expectedInventoryDetail.setThumbnail2("/resources/img/product11.jpg");
		expectedInventoryDetail.setThumbnail3("/resources/img/product12.jpg");

		final InventoryDetail actualInventoryDetail = inventoryDetailService.getInventoryDetailByInventoryDetailId(-1);		

		// expectations
		context.checking(new Expectations() {{
			assertEquals(expectedInventoryDetail.toString(), actualInventoryDetail.toString());
		}});		   
		// verify
		context.assertIsSatisfied();
	}		

	@Test
	@Transactional
	public void getInventoryDetailByIdColorTest() throws Exception {
		databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();			
		inventoryDetail = new InventoryDetail();

		// test	
		final List<InventoryDetail> expectedInventoryDetailList = new ArrayList<InventoryDetail>();
		InventoryDetail expectedInventoryDetail = new InventoryDetail();
		expectedInventoryDetail.setInventoryDetailId(-1);
		expectedInventoryDetail.setInventoryId(-111);
		expectedInventoryDetail.setProductCode("ABP30203CX");
		expectedInventoryDetail.setStockQuantity(129);
		expectedInventoryDetail.setSize("XS");
		expectedInventoryDetail.setColor("Black");
		expectedInventoryDetail.setThumbnail1("/resources/img/product1.jpg");
		expectedInventoryDetail.setThumbnail2("/resources/img/product11.jpg");
		expectedInventoryDetail.setThumbnail3("/resources/img/product12.jpg");
		expectedInventoryDetailList.add(expectedInventoryDetail);

		final List<InventoryDetail> actualInventoryDetailList = inventoryDetailService.getInventoryDetailByIdColor(-111, "Black");		

		// expectations
		context.checking(new Expectations() {{
			assertEquals(expectedInventoryDetailList.toString(), actualInventoryDetailList.toString());
		}});		   
		// verify
		context.assertIsSatisfied();
	}		

	@Test
	@Transactional
	public void getInventoryDetailByIdColorSizeTest() throws Exception {
		databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();			
		inventoryDetail = new InventoryDetail();

		// test	
		final InventoryDetail expectedInventoryDetail = new InventoryDetail();
		expectedInventoryDetail.setInventoryDetailId(-1);
		expectedInventoryDetail.setInventoryId(-111);
		expectedInventoryDetail.setProductCode("ABP30203CX");
		expectedInventoryDetail.setStockQuantity(129);
		expectedInventoryDetail.setSize("XS");
		expectedInventoryDetail.setColor("Black");
		expectedInventoryDetail.setThumbnail1("/resources/img/product1.jpg");
		expectedInventoryDetail.setThumbnail2("/resources/img/product11.jpg");
		expectedInventoryDetail.setThumbnail3("/resources/img/product12.jpg");

		final InventoryDetail actualInventoryDetail = inventoryDetailService.getInventoryDetailByIdColorSize(-111, "Black", "XS");		

		// expectations
		context.checking(new Expectations() {{
			assertEquals(expectedInventoryDetail.toString(), actualInventoryDetail.toString());
		}});		   
		// verify
		context.assertIsSatisfied();
	}		
	
	@Test
	@Transactional
	public void getAvailableSizesTest() throws Exception {
		databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();			
		inventoryDetail = new InventoryDetail();

		// test	
		final List<String> expectedList = new ArrayList<String>();
		expectedList.add("XS");

		final List<String> actualList = inventoryDetailService.getAvailableSizes(-111, "Black");		

		// expectations
		context.checking(new Expectations() {{
			assertEquals(expectedList.toString(), actualList.toString());
		}});		   
		// verify
		context.assertIsSatisfied();
	}			

}
