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
		Session session = this.sessionFactory.getCurrentSession();
		a.setEnabled(true);
		if (null == a.getShipName()) {
			a.setShipName((a.getFirstName() + " " + a.getLastName()));
		}
		if (null == a.getShipCity()){
			a.setShipCity(a.getCity());	
		}
		if (null == a.getShipState()){
			a.setShipState(a.getState());	
		}
		if (null == a.getShipZip()) {
			a.setShipZip(a.getZip());
		}		
		if (null == a.getShipPhone()) {
			a.setShipPhone(a.getPhoneNumber());
		}
		if (null == a.getShipCountry()) {
			a.setShipCountry(a.getCountry());
		}
		if (null == a.getShipAddress()) {
			a.setShipAddress(a.getAddress());
		}
		if (null == a.getShipAddress2()) {
			a.setShipAddress2(a.getAddress2());
		}		
		session.persist(a);  
		logger.info("Account saved successfully, Account Details="+a);
	}   

	/*@Override*/
	public void addSessionAccount(SessionAccount sa) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(sa);  
		tx.commit();
		session.close();
	}   	
	
	/*@Override*/
	public void updateAccount(Account a) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(a);
		logger.info("Account updated successfully, Account Details="+a);
	}

	@SuppressWarnings("unchecked")
	/*@Override*/
	public List<Account> listAccounts() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Account> accountsList = session.createQuery("from Account").list();
		for(Account a : accountsList){
			logger.info("Account List::"+a);
		}
		return accountsList;
	}

	/*@Override*/
	public Account getAccountById(int id) {
		Session session = this.sessionFactory.getCurrentSession();      
		Account a = (Account) session.load(Account.class, new Integer(id));
		logger.info("Account loaded successfully, Account details="+a);
		return a;
	}

	/*@Override*/
	public Account getAccountByName(String name) {
		Session session = this.sessionFactory.getCurrentSession();      
		String hql = "FROM Account WHERE userLogin = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", name);
		Account result = (Account) query.uniqueResult();

		logger.info("getAccountByName query: " + query.toString());
		logger.info("getAccountByName query results (toString()): " + result.toString());
		return result;
	}    

	/*@Override*/
	public void removeAccount(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Account a = (Account) session.load(Account.class, new Integer(id));
		if(null != a){
			session.delete(a);
		}
		logger.info("Account deleted successfully, account details="+a);
	}

	/*@Override*/
	public void createUserRole(String name) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Roles roles = new Roles();
		roles.setRole("User");
		roles.setUserLogin(name);
		session.save(roles);
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
		Account result = (Account) query.uniqueResult();
		session.close();

		return (null == result);		
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