package com.fauxshop.spring.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.fauxshop.spring.model.Account;
import com.fauxshop.spring.model.Roles;
import com.fauxshop.spring.model.SessionAccount;

@Repository
public class AccountDAOImpl implements AccountDAO {

	private static final Logger logger = LoggerFactory.getLogger(AccountDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	/*    @Override*/
	public void saveAccount(Account a) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(a);
		tx.commit();
		session.close();
	}
	
	public void saveSessionAccount(SessionAccount sa) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		sa.setSessionAccountId(getSessionAccountBySessionId(sa.getSessionId()).getSessionAccountId());
		session.saveOrUpdate(sa);
		tx.commit();
		session.close();		
	}

	@SuppressWarnings("unchecked")
	/*    @Override*/
	public List<Account> list() {
		Session session = this.sessionFactory.openSession();
		List<Account> accountList = session.createQuery("from Account").list();
		session.close();
		return accountList;
	}    

	/*@Override*/
	public void addAccount(Account a) {	
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		a.setEnabled(true);
		if (a.getShipName().isEmpty()) {
			a.setShipName((a.getFirstName() + " " + a.getLastName()));
		}
		if (a.getShipCity().isEmpty()){
			a.setShipCity(a.getCity());	
		}
		if (a.getShipState().isEmpty()){
			a.setShipState(a.getState());	
		}
		if (a.getShipZip().isEmpty()) {
			a.setShipZip(a.getZip());
		}		
		if (a.getShipPhone().isEmpty()) {
			a.setShipPhone(a.getPhoneNumber());
		}
		if (a.getShipCountry().isEmpty()) {
			a.setShipCountry(a.getCountry());
		}
		if (a.getShipAddress().isEmpty()) {
			a.setShipAddress(a.getAddress());
		}
		if (a.getShipAddress2().isEmpty()) {
			a.setShipAddress2(a.getAddress2());
		}		
		session.saveOrUpdate(a);  
		tx.commit();
		session.close();
		logger.debug("Account saved successfully, Account Details="+a);
	}   

	/*@Override*/
	public void addSessionAccount(SessionAccount sa) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(sa);  
		tx.commit();
		session.close();
	}   	

	@SuppressWarnings("unchecked")
	/*@Override*/
	public List<Account> listAccounts() {
		Session session = this.sessionFactory.openSession();
		List<Account> accountsList = session.createQuery("from Account").list();
		for(Account a : accountsList){
			logger.debug("Account List::"+a);
		}
		session.close();
		return accountsList;
	}

	/*@Override*/
	public Account getAccountByName(String name) {
		Session session = this.sessionFactory.openSession();      
		String hql = "FROM Account WHERE userLogin = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", name);
		Account result = (Account) query.uniqueResult();
		session.close();
		return result;
	}    

	/*@Override*/
	public void removeAccount(int id) {
		Session session = this.sessionFactory.openSession();
		Account a = (Account) session.load(Account.class, new Integer(id));
		if(null != a){
			session.delete(a);
		}
		logger.debug("Account deleted successfully, account details="+a);
		session.close();
	}

	/*@Override*/
	public void createUserRole(String name) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Roles roles = new Roles();
		roles.setRole("User");
		roles.setUserLogin(name);
		session.saveOrUpdate(roles);
		tx.commit();
		session.close();
	}        
	
	public boolean isUserLoginUnique(String name) {
		Session session = this.sessionFactory.openSession();      
		String hql = "FROM Account WHERE userLogin = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", name);
		Account result = (Account) query.uniqueResult();
		session.close();

		return (null == result);
	}
	
	public boolean isSessionAccountAlreadyRegistered(String sessionId) {
		Session session = this.sessionFactory.openSession();      
		String hql = "FROM SessionAccount WHERE sessionId = :sessionId";
		Query query = session.createQuery(hql);
		query.setParameter("sessionId", sessionId);
		SessionAccount result = (SessionAccount) query.uniqueResult();
		session.close();			

		return (null != result);		
	}

	/*@Override*/
	public SessionAccount getSessionAccountBySessionId(String sessionId) {
		Session session = this.sessionFactory.openSession();      
		String hql = "FROM SessionAccount WHERE sessionId = :sessionId";
		Query query = session.createQuery(hql);
		query.setParameter("sessionId", sessionId);
		SessionAccount result = (SessionAccount) query.uniqueResult();
		session.close();
		
		return result;
	}    	

}