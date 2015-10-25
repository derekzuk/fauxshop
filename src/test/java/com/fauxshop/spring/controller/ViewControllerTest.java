package com.fauxshop.spring.controller;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.test.context.support.*;
import org.springframework.test.context.transaction.*;
import com.fauxshop.spring.model.Account;
import com.fauxshop.spring.service.AccountService;
import com.fauxshop.spring.service.CartService;
import com.fauxshop.spring.service.InventoryDetailService;
import com.fauxshop.spring.service.InventoryService;
import com.github.springtestdbunit.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/servlet-context.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class })
@Transactional
public class ViewControllerTest {	
//	Model views left to test:
/*	"/", "index"
	/account
	/cart
	/cartRemove/{cartId}
	/categories/{inventoryCatCd}
	/contact
	/invoice
	/login
	/payment
	/product_detail/{id}**
	/product_detail/add/{inventoryId}
	/shipping							*/
	
	Mockery context = new Mockery();

	protected static IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream("controller-dataset.xml"));
	}	

	protected static IDataSet getEmptyDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream("empty-dataset.xml"));
	}	

	static IDatabaseTester databaseTester;	

	@Autowired
	private RequestMappingHandlerAdapter handlerAdapter;

	@Autowired
	private RequestMappingHandlerMapping handlerMapping;

	@Autowired
	private AccountService accountService;

	@Autowired
	private CartService cartService;

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private InventoryDetailService inventoryDetailService;

	@Autowired
	private ViewController viewController;
	
	private Account getExpectedAccount() {
		final Account account = new Account();
		account.setAccountId(-1);
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
		return account;
	}

	// This test would probably work if the test data was set up to handle it.
	// Probably the smartest thing to do would be to change how the index page loads the inventory data, though.
	@SuppressWarnings("deprecation")
	@Test
	@Ignore
	public void listWelcomePageTest() throws Exception {
		databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();	

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setMethod("GET");
		request.setRequestURI("/");
		MockHttpServletResponse response = new MockHttpServletResponse();
		Object handler = handlerMapping.getHandler(request).getHandler();		
		final SecurityContext securityContext = context.mock(SecurityContext.class);
		final Authentication authentication = context.mock(Authentication.class);
		
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new GrantedAuthorityImpl("User"));		
		final User user = new User("password", "userlogintest", authorities);

		context.checking(new Expectations() {{	
			allowing(authentication).setAuthenticated(true);
			allowing(securityContext).setAuthentication(authentication);
			allowing(securityContext).getAuthentication(); will(returnValue(authentication));
			allowing(authentication).getPrincipal(); will(returnValue(user));
		}});				

		authentication.setAuthenticated(true);
		securityContext.setAuthentication(authentication);
		SecurityContextHolder.setContext(securityContext);

		// execute
		final ModelAndView modelAndView = handlerAdapter.handle(request, response, handler);		

		// modelAndView and/or response asserts here
		// expectations
		context.checking(new Expectations() {{
			assertEquals(modelAndView.getViewName(), "/");
		}});		   		   	        	
	}	

	@SuppressWarnings("deprecation")
	@Test
	public void aboutTest() throws Exception {
		databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();	

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setMethod("GET");
		request.setRequestURI("/about");
		MockHttpServletResponse response = new MockHttpServletResponse();
		Object handler = handlerMapping.getHandler(request).getHandler();		
		final SecurityContext securityContext = context.mock(SecurityContext.class);
		final Authentication authentication = context.mock(Authentication.class);
		
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new GrantedAuthorityImpl("User"));		
		final User user = new User("userlogintest", "password", authorities);

		context.checking(new Expectations() {{	
			allowing(authentication).setAuthenticated(true);
			allowing(securityContext).setAuthentication(authentication);
			allowing(securityContext).getAuthentication(); will(returnValue(authentication));
			allowing(authentication).getPrincipal(); will(returnValue(user));
		}});				

		authentication.setAuthenticated(true);
		securityContext.setAuthentication(authentication);
		SecurityContextHolder.setContext(securityContext);

		final Account account = getExpectedAccount();
		
		// execute
		final ModelAndView modelAndView = handlerAdapter.handle(request, response, handler);

		// modelAndView and/or response asserts here
		// expectations
		context.checking(new Expectations() {{
			assertEquals("about", modelAndView.getViewName());
			assertEquals(modelAndView.getModelMap().get("currentUser").toString(), account.toString());			
		}});		   	        	
	}
    
	@SuppressWarnings("deprecation")
	@Test
	public void confirmTest() throws Exception {
		databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/fauxleather","root", "pass");		   	
		databaseTester.setDataSet(getDataSet()); databaseTester.onSetup();	

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setMethod("GET");
		request.setRequestURI("/confirm");
		MockHttpServletResponse response = new MockHttpServletResponse();
		Object handler = handlerMapping.getHandler(request).getHandler();		
		final SecurityContext securityContext = context.mock(SecurityContext.class);
		final Authentication authentication = context.mock(Authentication.class);
		
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new GrantedAuthorityImpl("User"));		
		final User user = new User("userlogintest", "password", authorities);

		context.checking(new Expectations() {{	
			allowing(authentication).setAuthenticated(true);
			allowing(securityContext).setAuthentication(authentication);
			allowing(securityContext).getAuthentication(); will(returnValue(authentication));
			allowing(authentication).getPrincipal(); will(returnValue(user));
		}});				

		authentication.setAuthenticated(true);
		securityContext.setAuthentication(authentication);
		SecurityContextHolder.setContext(securityContext);

		final Account account = getExpectedAccount();

		// execute
		final ModelAndView modelAndView = handlerAdapter.handle(request, response, handler);

		// modelAndView and/or response asserts here
		// expectations
		context.checking(new Expectations() {{
			assertEquals("confirm", modelAndView.getViewName());
			assertEquals(modelAndView.getModelMap().get("currentUser").toString(), account.toString());			
			assertNotNull(modelAndView.getModelMap().get("inventoryService").toString());
			assertNotNull(modelAndView.getModelMap().get("inventoryDetailService").toString());
		}});		   	        	
	}    
	
}
