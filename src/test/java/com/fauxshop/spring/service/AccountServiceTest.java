package com.fauxshop.spring.service;

import static org.junit.Assert.*;

import org.jmock.Mockery;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fauxshop.spring.dao.AccountDAOImpl;
import com.fauxshop.spring.model.Account;

@Ignore
public class AccountServiceTest {	
	private static final Logger log = LoggerFactory.getLogger(AccountServiceTest.class);
	Mockery context = new Mockery();

/*	private AccountDAOImpl accountDAOImpl;	*/
	
	@Test
	public void addAccountAcceptsOnlyAccountModel() {
		// set up
		final AccountDAOImpl accountDAOImpl = context.mock(AccountDAOImpl.class);
		Account a = new Account();
		a.setAccountId(-111);
		a.setAddress("abc");
		a.setAddress2("dfsa");
		a.setCity("asa");
		a.setCountry("ajdfsk");
		a.setEmail("abc@abc.com");
		a.setEnabled(true);
		a.setFirstName("testfirst");
		a.setLastName("testlast");
		a.setPassword("12345");
		a.setPhoneNumber("123");
		a.setState("fdjksl");
		a.setUserLogin("fdskjl");
		a.setZip("12323");
		a.setShipAddress("abc");
		a.setShipAddress2("dfsa");
		a.setShipCity("asa");
		a.setShipCountry("ajdfsk");
		a.setShipName("abc name");
		a.setShipPhone("12345677");
		a.setShipZip("123123");
		accountDAOImpl.addAccount(a);
	}

}
