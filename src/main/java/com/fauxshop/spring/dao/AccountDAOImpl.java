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

@Repository
public class AccountDAOImpl implements AccountDAO {
     
    private static final Logger logger = LoggerFactory.getLogger(AccountDAOImpl.class);
    
    private SessionFactory sessionFactory;
     
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    
    /*    @Override*/
    public void save(Account a) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(a);
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
    	session.persist(a);  
    	a.setShipName((a.getFirstName() + " " + a.getLastName()));
    	a.setShipCity(a.getCity());
    	a.setShipState(a.getState());
    	a.setShipZip(a.getZip());
    	a.setShipPhone(a.getPhoneNumber());
    	a.setShipCountry(a.getCountry());
    	a.setShipAddress(a.getAddress());
    	a.setShipAddress2(a.getAddress2());
    	logger.info("Account saved successfully, Account Details="+a);
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
 
}