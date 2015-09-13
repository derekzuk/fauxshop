package com.fauxshop.spring.dao;
 
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
 









import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
 








import com.fauxshop.spring.model.Account;
import com.fauxshop.spring.model.TransactionLog;

@Repository
public class TransactionDAOImpl implements TransactionDAO {
     
    private static final Logger logger = LoggerFactory.getLogger(TransactionDAOImpl.class);
    
    private SessionFactory sessionFactory;
     
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    
    /*    @Override*/
    public void save(TransactionLog t) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(t);
        tx.commit();
        session.close();
    }
 
    @SuppressWarnings("unchecked")
/*    @Override*/
    public List<TransactionLog> list() {
        Session session = this.sessionFactory.openSession();
        List<TransactionLog> transactionList = session.createQuery("from Transaction").list();
        session.close();
        return transactionList;
    }    
 
    /*@Override*/
    public void addTransaction(TransactionLog t) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(t);
        logger.info("Transaction saved successfully, Transaction Details="+t);
    }
 
    /*@Override*/
    public void updateTransaction(TransactionLog t) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(t);
        logger.info("Transaction updated successfully, Transaction Details="+t);
    }
 
    @SuppressWarnings("unchecked")
    /*@Override*/
    public List<TransactionLog> listTransactions() {
        Session session = this.sessionFactory.getCurrentSession();
        List<TransactionLog> transactionsList = session.createQuery("from Transaction").list();
        for(TransactionLog t : transactionsList){
            logger.info("Transaction List::"+t);
        }
        return transactionsList;
    }
 
    /*@Override*/
    public TransactionLog getTransactionById(int id) {
        Session session = this.sessionFactory.getCurrentSession();      
        TransactionLog t = (TransactionLog) session.load(TransactionLog.class, new Integer(id));
        logger.info("Transaction loaded successfully, Transaction details="+t);
        return t;
    }
 
    /*@Override*/
    public void removeTransaction(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        TransactionLog t = (TransactionLog) session.load(TransactionLog.class, new Integer(id));
        if(null != t){
            session.delete(t);
        }
        logger.info("Transaction deleted successfully, transaction details="+t);
    }
    
    /*@Override*/
    public void createTransaction(int cartId){
    	Session session = this.sessionFactory.openSession();
    	Transaction tx = session.beginTransaction();
    	Calendar calendar = Calendar.getInstance();

    	/*Create queries:*/
        String accountIdHql = "SELECT accountId FROM Cart WHERE cartId = :cartId";
        String orderQuantityHql = "SELECT quantity FROM Cart WHERE cartId = :cartId";
        String shipNameHql = "SELECT CONCAT(firstName,' ',lastName) FROM Account where accountId = :accountId";
        String shipAddressHql = "SELECT address from Account where accountId = :accountId";
        String shipAddress2Hql = "SELECT address2 from Account where accountId = :accountId";
        String cityHql = "SELECT city from Account where accountId = :accountId";
        String stateHql = "SELECT state from Account where accountId = :accountId";
        String zipHql = "SELECT zip from Account where accountId = :accountId";
        String countryHql = "SELECT country from Account where accountId = :accountId";
        String phoneHql = "SELECT phoneNumber from Account where accountId = :accountId";
        String shippingCostHql = "SELECT shippingCost from Cart where cartId = :cartId";
        String taxHql = "SELECT tax from Cart where cartId = :cartId";
        String orderEmailHql = "SELECT email from Account where accountId = :accountId";
        String inventoryDetailIdHql = "SELECT inventoryDetailId from Cart where cartId = :cartId";
        String orderCostHql = "SELECT ((pricePerItem + shippingCost + tax) * quantity) from Cart where cartId = :cartId";     
     			
    	Query accountIdQuery = session.createQuery(accountIdHql);
    	accountIdQuery.setParameter("cartId", cartId);    	
    	Query orderQuantityQuery = session.createQuery(orderQuantityHql);
    	orderQuantityQuery.setParameter("cartId", cartId);    	    	   
    	Query shippingCostQuery = session.createQuery(shippingCostHql);
        shippingCostQuery.setParameter("cartId", cartId);
    	Query taxQuery = session.createQuery(taxHql);
        taxQuery.setParameter("cartId", cartId);
    	Query inventoryDetailQuery = session.createQuery(inventoryDetailIdHql);
    	inventoryDetailQuery.setParameter("cartId", cartId);    	
    	Query orderCostQuery = session.createQuery(orderCostHql);
    	orderCostQuery.setParameter("cartId", cartId);        

        int accountId = (int) accountIdQuery.uniqueResult();
        int orderQuantity = (int) orderQuantityQuery.uniqueResult();
        BigDecimal shippingCost = (BigDecimal) shippingCostQuery.uniqueResult();
        BigDecimal tax = (BigDecimal) taxQuery.uniqueResult();
        int inventoryDetailId = (int) inventoryDetailQuery.uniqueResult();
        BigDecimal orderCost = (BigDecimal) orderCostQuery.uniqueResult();    	
        
    	Query shipNameQuery = session.createQuery(shipNameHql);
    	shipNameQuery.setParameter("accountId", accountId); 
    	Query shipAddressQuery = session.createQuery(shipAddressHql);
        shipAddressQuery.setParameter("accountId", accountId);
    	Query shipAddress2Query = session.createQuery(shipAddress2Hql);
        shipAddress2Query.setParameter("accountId", accountId);
    	Query cityQuery = session.createQuery(cityHql);
        cityQuery.setParameter("accountId", accountId);
    	Query stateQuery = session.createQuery(stateHql);
        stateQuery.setParameter("accountId", accountId);
    	Query zipQuery = session.createQuery(zipHql);
        zipQuery.setParameter("accountId", accountId);
    	Query countryQuery = session.createQuery(countryHql);
    	countryQuery.setParameter("accountId", accountId);
    	Query phoneQuery = session.createQuery(phoneHql);
    	phoneQuery.setParameter("accountId", accountId);
    	Query orderEmailQuery = session.createQuery(orderEmailHql);
    	orderEmailQuery.setParameter("accountId", accountId);
    	
    	/*Define variables:*/
        String shipName = (String) shipNameQuery.uniqueResult();
        String shipAddress = (String) shipAddressQuery.uniqueResult();
        String shipAddress2 = (String) shipAddress2Query.uniqueResult();
        String city = (String) cityQuery.uniqueResult();
        String state = (String) stateQuery.uniqueResult();
        String zip = (String) zipQuery.uniqueResult();
        String country = (String) countryQuery.uniqueResult();
        String phone = (String) phoneQuery.uniqueResult();
        String orderEmail = (String) orderEmailQuery.uniqueResult();
        Date date = calendar.getTime();
        boolean shipped = false;
        String trackingNumber = "This is the Tracking Number 1234";
        
        TransactionLog transactionLog = new TransactionLog();
        transactionLog.setAccountId(accountId);
        transactionLog.setOrderQuantity(orderQuantity);
        transactionLog.setShipName(shipName);
        transactionLog.setShipAddress(shipAddress);
        transactionLog.setShipAddress2(shipAddress2);
        transactionLog.setCity(city);
        transactionLog.setState(state);
        transactionLog.setZip(zip);
        transactionLog.setCountry(country);
        transactionLog.setPhone(phone);
        transactionLog.setShippingCost(shippingCost);
        transactionLog.setTax(tax);
        transactionLog.setOrderEmail(orderEmail);
        transactionLog.setDate(date);
        transactionLog.setShipped(shipped);
        transactionLog.setTrackingNumber(trackingNumber);
        transactionLog.setInventoryDetailId(inventoryDetailId);
        transactionLog.setOrderCost(orderCost);            	    	
        
        session.save(transactionLog);
        tx.commit();
    	session.close();
    }    
 
}