package com.fauxshop.spring.dao;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.internal.runners.JUnit38ClassRunner;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fauxshop.spring.dao.AccountDAOImpl;
import com.fauxshop.spring.model.Account;

/*@RunWith(JUnit38ClassRunner.class)*/
/*@ContextConfiguration(locations = {"classpath:spring/myapp-db-beans.xml", "classpath:spring/myapp-db-test-beans.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)*/
/*@Transactional*/
@Ignore
public class AccountDAOImplTest {	
	private static final Logger log = LoggerFactory.getLogger(AccountDAOImplTest.class);
	Mockery context = new Mockery();
	
	@Autowired
	protected Account account;
	
    private AccountDAOImpl accountDAOImpl;
    
    public void setAccountDAOImpl(AccountDAOImpl accountDAOImpl) {
        this.accountDAOImpl = accountDAOImpl;
    }	
		
    @Test
	public void addAccountAcceptsOnlyAccountModel() {
		// set up
		final Account account = context.mock(Account.class);
		/*final AccountDAOImpl accountDAOImpl = context.mock(AccountDAOImpl.class);*/			
		
		account.setAccountId(-111);
		account.setAddress("abc");
		account.setAddress2("dfsa");
		account.setCity("asa");
		account.setCountry("ajdfsk");
		account.setEmail("abc@abc.com");
		account.setEnabled(true);
		account.setFirstName("testfirst");
		account.setLastName("testlast");
		account.setPassword("12345");
		account.setPhoneNumber("123");
		account.setState("fdjksl");
		account.setUserLogin("fdskjl");
		account.setZip("12323");
		account.setShipAddress("abc");
		account.setShipAddress2("dfsa");
		account.setShipCity("asa");
		account.setShipCountry("ajdfsk");
		account.setShipName("abc name");
		account.setShipPhone("12345677");
		account.setShipZip("123123");
		accountDAOImpl.addAccount(account);
		
        // expectations
        context.checking(new Expectations() {{
            oneOf (account.getAccountId()).equals(-111);
        }});

        // execute
        accountDAOImpl.addAccount(account);
        
        // verify
        context.assertIsSatisfied();
	}

}
