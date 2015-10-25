package com.fauxshop.spring.swf;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.message.MessageResolver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.*;
import org.springframework.test.context.junit4.*;
import org.springframework.test.context.transaction.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.webflow.action.EventFactorySupport;
import org.springframework.webflow.execution.Event;

import com.github.springtestdbunit.*;
import com.fauxshop.spring.model.Account;
import com.fauxshop.spring.model.Cart;
import com.fauxshop.spring.model.SessionAccount;

public class AccountValidationTest {	
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(AccountValidationTest.class);
	Mockery context = new Mockery();	

	private AccountValidation accountValidation;	

	@Test
	public void validateLoginFormNullUserLoginTest() throws Exception {	
		this.accountValidation = new AccountValidation();

		final MessageContext messageContext = context.mock(MessageContext.class);
		String userLogin = null;
		String password = "password";

		// expectations
		context.checking(new Expectations() {{
			allowing (messageContext).addMessage(with(any(MessageResolver.class)));               
		}});		

		// execute
		final Event event = accountValidation.validateLoginForm(userLogin, password, messageContext);
		
		// expectations
		context.checking(new Expectations() {{
			assertEquals(event.toString(), new EventFactorySupport().error(this).toString());
		}});			

		// verify
		context.assertIsSatisfied();		   
	}	  
	
	@Test
	public void validateLoginFormNullPasswordTest() throws Exception {	
		this.accountValidation = new AccountValidation();

		final MessageContext messageContext = context.mock(MessageContext.class);
		String userLogin = "userLogin";
		String password = null;

		// expectations
		context.checking(new Expectations() {{
			allowing (messageContext).addMessage(with(any(MessageResolver.class)));               
		}});		

		// execute
		final Event event = accountValidation.validateLoginForm(userLogin, password, messageContext);
		
		// expectations
		context.checking(new Expectations() {{
			assertEquals(event.toString(), new EventFactorySupport().error(this).toString());
		}});			

		// verify
		context.assertIsSatisfied();		   
	}	  	
	
	@Test
	public void validateRegistrationEmailTestSuccess() throws Exception {
		this.accountValidation = new AccountValidation();
		
		final String email = "testemail@test.com";
		final MessageContext messageContext = context.mock(MessageContext.class);						
		
		// execute
		final Event event = accountValidation.validateRegistrationEmail(email, messageContext);
		
		// expectations
		context.checking(new Expectations() {{
			assertEquals(event.toString(), new EventFactorySupport().success(this).toString());
		}});		
		
		// verify
		context.assertIsSatisfied();							
	}
	
	@Test
	public void validateRegistrationEmailTestFail() throws Exception {
		this.accountValidation = new AccountValidation();
		
		final String email = "testemailfailure";
		final MessageContext messageContext = context.mock(MessageContext.class);								
		
		// expectations
		context.checking(new Expectations() {{
			allowing (messageContext).addMessage(with(any(MessageResolver.class)));
		}});		
		
		// execute
		final Event event = accountValidation.validateRegistrationEmail(email, messageContext);
		
		// expectations
		context.checking(new Expectations() {{
			assertEquals(event.toString(), new EventFactorySupport().error(this).toString());
		}});			
		
		// verify
		context.assertIsSatisfied();							
	}	
	
}
