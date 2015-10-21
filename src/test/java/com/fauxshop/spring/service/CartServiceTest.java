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
import com.fauxshop.spring.model.SessionAccount;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/dbHibernate-context.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class })
@Transactional
public class CartServiceTest {	
	private static final Logger log = LoggerFactory.getLogger(CartServiceTest.class);
	Mockery context = new Mockery();	
	
	@Autowired
	private CartService cartService;
	
	private Cart cart;		

/*	We aren't using this at the moment:
	public AccountServiceImplTest() {
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,"com.mysql.jdbc.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,"jdbc:mysql://localhost:3306/fauxleather"); 
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,"root");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,"pass");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA,"fauxleather");
		}	*/
	
	protected static IDataSet getDataSet() throws Exception {
		   return new FlatXmlDataSetBuilder().build(new FileInputStream("cart-dataset.xml"));
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
	   public void saveTest() throws Exception {
		   cart = new Cart();

		   // expectations
		   context.checking(new Expectations() {{
			   /*Assertion is made at the end of this test*/
		   }});		   
		   
	      // test	
		    BigDecimal pricePerItem = new BigDecimal("123.45");
		    BigDecimal shippingCost = new BigDecimal("12.34");
		    BigDecimal tax = new BigDecimal("1.23");
		    cart.setAccountId(-1);
		    cart.setSessionId("ABC123");
		    cart.setInventoryDetailId(-10);
		    cart.setQuantity(2);
		    cart.setPricePerItem(pricePerItem);
		    cart.setShippingCost(shippingCost);
		    cart.setTax(tax);
		    cartService.save(cart);
		   		   
		IDataSet expds = new FlatXmlDataSetBuilder().build(new FileInputStream("cart-dataset.xml"));
		ITable expectedTable = expds.getTable("cart");
		IDatabaseConnection connection = databaseTester.getConnection();
		IDataSet databaseDataSet = connection.createDataSet();
		ITable actualTable = databaseDataSet.getTable("cart");
		String[] ignoredColumns = new String[1];
		ignoredColumns[0] = "cart_id";
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, ignoredColumns);

	    // verify
	    context.assertIsSatisfied();
	   }	 
	   
	   @Test
	   @Transactional
	   public void removeTest() throws Exception {
		   databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		   databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();		   	  

		   // test	
		   cartService.removeCart(-1);

		   // expectations
		   context.checking(new Expectations() {{
			   assertNull(cartService.getCartByIdAndAccountId(-1, -1));
		   }});			    

		   // verify
		   context.assertIsSatisfied();		      
	   }
	   	    	   
	   @Test
	   @Transactional
	   public void getCartByIdAndAccountIdTest() throws Exception {
		   databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		   databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();

		   // expectations
		   context.checking(new Expectations() {{			   
			   assertNotNull(cartService.getCartByIdAndAccountId(-1,-1));
		   }});		
		   
		   // test			   
		   cartService.getCartByIdAndAccountId(-1,-1);				

		   // verify
		   context.assertIsSatisfied();   
	   }	  
	   
	   @Test
	   @Transactional
	   public void getCartByIdAndSessionIdTest() throws Exception {
		   databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		   databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();

		   // expectations
		   context.checking(new Expectations() {{			   
			   assertNotNull(cartService.getCartByIdAndSessionId(-1,"ABC123"));
		   }});		
		   
		   // test			   
		   cartService.getCartByIdAndSessionId(-1,"ABC123");				

		   // verify
		   context.assertIsSatisfied();   
	   }	
	   
	   @Test
	   @Transactional
	   public void getCartByInventoryDetailIdAndAccountIdTest() throws Exception {
		   databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		   databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();

		   // expectations
		   context.checking(new Expectations() {{			   
			   assertNotNull(cartService.getCartByInventoryDetailIdAndAccountId(-10,-1));
		   }});		
		   
		   // test			   
		   cartService.getCartByInventoryDetailIdAndAccountId(-10,-1);				

		   // verify
		   context.assertIsSatisfied();   
	   }	
	   
	   @Test
	   @Transactional
	   public void getCartByInventoryDetailIdAndSessionIdTest() throws Exception {
		   databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		   databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();

		   // expectations
		   context.checking(new Expectations() {{			   
			   assertNotNull(cartService.getCartByInventoryDetailIdAndSessionId(-10,"ABC123"));
		   }});		
		   
		   // test			   
		   cartService.getCartByInventoryDetailIdAndSessionId(-10,"ABC123");				

		   // verify
		   context.assertIsSatisfied();   
	   }	
	   
	   @Test
	   @Transactional
	   public void updateQuantityTest() throws Exception {
		   databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		   databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();
		   
		   // test			   
		   cartService.updateQuantity(-1, 5);

		   // expectations
		   context.checking(new Expectations() {{			
			   assertEquals(5, cartService.getCartByIdAndAccountId(-1, -1).getQuantity());
		   }});				   

		   // verify
		   context.assertIsSatisfied();   
	   }
	   
	   @Test
	   @Transactional
	   public void removeCartFromCartListTest() throws Exception {
		   databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		   databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();

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
		   cartService.removeCartFromCartList(cartList);

		   // expectations
		   context.checking(new Expectations() {{			
			   assertNull(cartService.getCartByIdAndAccountId(-1, -1));
		   }});				   

		   // verify
		   context.assertIsSatisfied();   
	   }
	   
	   @Test
	   public void getCartByUserLoginTest() throws Exception {
		   databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		   databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();

		   // test			   
		   String name = "userlogintest";
		   final List<Cart> actualCartList = (List<Cart>) cartService.getCartByUserLogin(name);		   
	       Cart expectedCart = new Cart();
				   BigDecimal pricePerItem = new BigDecimal("123.45");
				   BigDecimal shippingCost = new BigDecimal("12.34");
				   BigDecimal tax = new BigDecimal("1.23");				   
				   expectedCart.setCartId(-1);
				   expectedCart.setAccountId(-1);
				   expectedCart.setSessionId("ABC123");
				   expectedCart.setInventoryDetailId(-10);
				   expectedCart.setQuantity(2);
				   expectedCart.setPricePerItem(pricePerItem);
				   expectedCart.setShippingCost(shippingCost);
				   expectedCart.setTax(tax);
		   final List<Cart> expectedCartList = new ArrayList<Cart>();
		   		   expectedCartList.add(expectedCart);
		   		   
		   // expectations
		   context.checking(new Expectations() {{
			   assertEquals(expectedCartList.get(0).toString(), actualCartList.get(0).toString());
		   }});

		   // verify
		   context.assertIsSatisfied();  		   
	   }	  
	   
	   @Test
	   public void getCartBySessionIdTest() throws Exception {
		   databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		   databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();

		   // test			   
		   String sessionId = "ABC123";
		   final List<Cart> actualCartList = (List<Cart>) cartService.getCartBySessionId(sessionId);		   
	       Cart expectedCart = new Cart();
				   BigDecimal pricePerItem = new BigDecimal("123.45");
				   BigDecimal shippingCost = new BigDecimal("12.34");
				   BigDecimal tax = new BigDecimal("1.23");				   
				   expectedCart.setCartId(-1);
				   expectedCart.setAccountId(-1);
				   expectedCart.setSessionId("ABC123");
				   expectedCart.setInventoryDetailId(-10);
				   expectedCart.setQuantity(2);
				   expectedCart.setPricePerItem(pricePerItem);
				   expectedCart.setShippingCost(shippingCost);
				   expectedCart.setTax(tax);
		   final List<Cart> expectedCartList = new ArrayList<Cart>();
		   		   expectedCartList.add(expectedCart);
		   		   
		   // expectations
		   context.checking(new Expectations() {{
			   assertEquals(expectedCartList.get(0).toString(), actualCartList.get(0).toString());
		   }});

		   // verify
		   context.assertIsSatisfied();  		   
	   }	  	   
	    
	    @Test
	    public void userLogginCartCostTests() throws Exception {
			   databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
			   databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();

			   // test			   
			   final BigDecimal expectedCartItemCost = new BigDecimal("246.90");
			   final BigDecimal actualCartItemCost = cartService.getCartItemCostByUserLogin("userlogintest");
			   final BigDecimal expectedCartShippingCost = new BigDecimal("24.68");
			   final BigDecimal actualCartShippingCost = cartService.getCartShippingCostByUserLogin("userlogintest");
			   final BigDecimal expectedCartTaxCost = new BigDecimal("2.46");
			   final BigDecimal actualCartTaxCost = cartService.getCartTaxCostByUserLogin("userlogintest");
			   final BigDecimal expectedCartTotal = new BigDecimal("274.04");
			   final BigDecimal actualCartTotal = cartService.getCartTotalByUserLogin("userlogintest");
			   		   
			   // expectations
			   context.checking(new Expectations() {{
				   assertEquals(expectedCartItemCost, actualCartItemCost);
				   assertEquals(expectedCartShippingCost, actualCartShippingCost);
				   assertEquals(expectedCartTaxCost, actualCartTaxCost);
				   assertEquals(expectedCartTotal, actualCartTotal);
			   }});

			   // verify
			   context.assertIsSatisfied();  
	    }
	    
	    @Test
	    public void userLogginCartCostSessionIdTests() throws Exception {
			   databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
			   databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();

			   // test			   
			   final BigDecimal expectedCartItemCost = new BigDecimal("246.90");
			   final BigDecimal actualCartItemCost = cartService.getCartItemCostBySessionId("ABC123");
			   final BigDecimal expectedCartShippingCost = new BigDecimal("24.68");
			   final BigDecimal actualCartShippingCost = cartService.getCartShippingCostBySessionId("ABC123");
			   final BigDecimal expectedCartTaxCost = new BigDecimal("2.46");
			   final BigDecimal actualCartTaxCost = cartService.getCartTaxCostBySessionId("ABC123");
			   final BigDecimal expectedCartTotal = new BigDecimal("274.04");
			   final BigDecimal actualCartTotal = cartService.getCartTotalBySessionId("ABC123");
			   		   
			   // expectations
			   context.checking(new Expectations() {{
				   assertEquals(expectedCartItemCost, actualCartItemCost);
				   assertEquals(expectedCartShippingCost, actualCartShippingCost);
				   assertEquals(expectedCartTaxCost, actualCartTaxCost);
				   assertEquals(expectedCartTotal, actualCartTotal);
			   }});

			   // verify
			   context.assertIsSatisfied();  
	    }	    
	    
	    @Test
	    public void getCartQuantityTests() throws Exception {
			   databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
			   databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();
			   
			   // test
			   final long expectedCartQuantity = 2;
			   final long actualCartQuantityUserLogin = cartService.getCartQuantityByUserLogin("userlogintest");			   
			   final long actualCartQuantitySessionId = cartService.getCartQuantityBySessionId("ABC123");
			   
			   // expectations
			   context.checking(new Expectations() {{
				   assertEquals(expectedCartQuantity, actualCartQuantityUserLogin);
				   assertEquals(expectedCartQuantity, actualCartQuantitySessionId);
			   }});
			   
	    }
	   
}
