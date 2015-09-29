package com.fauxshop.spring.dao;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fauxshop.spring.dao.AccountDAOImpl;
import com.fauxshop.spring.model.Account;

/*I don't know how to write JUnit tests.
*/@Ignore
public class DaoImplTest {	
	private static final Logger log = LoggerFactory.getLogger(DaoImplTest.class);

	private AccountDAOImpl accountDAOImpl;	
	
	@Test
	public void addAccountAcceptsOnlyAccountModel() {
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
		log.error("a: " + a);
		log.error("this.accountService: " + this.accountDAOImpl);
		/*this.accountDAOImpl.addAccount(a);*/
		fail("Not yet implemented");
	}

}
