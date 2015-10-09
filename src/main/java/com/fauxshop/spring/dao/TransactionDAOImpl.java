package com.fauxshop.spring.dao;
 
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateError;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.fauxshop.spring.model.Cart;
import com.fauxshop.spring.model.TransactionLog;
import com.fauxshop.spring.service.CartService;

@Repository
public class TransactionDAOImpl implements TransactionDAO {
     
    private static final Logger logger = LoggerFactory.getLogger(TransactionDAOImpl.class);
    
    private CartService cartService;
    private SessionFactory sessionFactory;
    
    public CartService getCartService(CartService cs){
    	return cartService;
    }
     
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
    public void createTransaction(int cartId, String sessionId, long trackingNumber){
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
        
        TransactionLog transactionLog = new TransactionLog();
        transactionLog.setAccountId(accountId);
        transactionLog.setSessionId(sessionId);
        transactionLog.setCartId(cartId);
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
    
    /*@Override*/
    public void createTransactionFromSessionId(int cartId, String sessionId, long trackingNumber){
    	logger.error("in createTransactionFromSessionId");
    	Session session = this.sessionFactory.openSession();
    	Transaction tx = session.beginTransaction();
    	Calendar calendar = Calendar.getInstance();
    	
    	try {
    	/*Create queries:*/
        String orderQuantityHql = "SELECT quantity FROM Cart WHERE cartId = :cartId";
        String shipNameHql = "SELECT shipName FROM SessionAccount where sessionId = :sessionId";
        String shipAddressHql = "SELECT shipAddress from SessionAccount where sessionId = :sessionId";
        String shipAddress2Hql = "SELECT shipAddress2 from SessionAccount where sessionId = :sessionId";
        String cityHql = "SELECT shipCity from SessionAccount where sessionId = :sessionId";
        String stateHql = "SELECT shipState from SessionAccount where sessionId = :sessionId";
        String zipHql = "SELECT shipZip from SessionAccount where sessionId = :sessionId";
        String countryHql = "SELECT shipCountry from SessionAccount where sessionId = :sessionId";
        String phoneHql = "SELECT shipPhone from SessionAccount where sessionId = :sessionId";
        String shippingCostHql = "SELECT shippingCost from Cart where cartId = :cartId";
        String taxHql = "SELECT tax from Cart where cartId = :cartId";
        String orderEmailHql = "SELECT email from SessionAccount where sessionId = :sessionId";
        String inventoryDetailIdHql = "SELECT inventoryDetailId from Cart where cartId = :cartId";
        String orderCostHql = "SELECT ((pricePerItem + shippingCost + tax) * quantity) from Cart where cartId = :cartId";     
     			
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

        int orderQuantity = (int) orderQuantityQuery.uniqueResult();
        BigDecimal shippingCost = (BigDecimal) shippingCostQuery.uniqueResult();
        BigDecimal tax = (BigDecimal) taxQuery.uniqueResult();
        int inventoryDetailId = (int) inventoryDetailQuery.uniqueResult();
        BigDecimal orderCost = (BigDecimal) orderCostQuery.uniqueResult();    	
        
    	Query shipNameQuery = session.createQuery(shipNameHql);
    	shipNameQuery.setParameter("sessionId", sessionId); 
    	Query shipAddressQuery = session.createQuery(shipAddressHql);
        shipAddressQuery.setParameter("sessionId", sessionId);
    	Query shipAddress2Query = session.createQuery(shipAddress2Hql);
        shipAddress2Query.setParameter("sessionId", sessionId);
    	Query cityQuery = session.createQuery(cityHql);
        cityQuery.setParameter("sessionId", sessionId);
    	Query stateQuery = session.createQuery(stateHql);
        stateQuery.setParameter("sessionId", sessionId);
    	Query zipQuery = session.createQuery(zipHql);
        zipQuery.setParameter("sessionId", sessionId);
    	Query countryQuery = session.createQuery(countryHql);
    	countryQuery.setParameter("sessionId", sessionId);
    	Query phoneQuery = session.createQuery(phoneHql);
    	phoneQuery.setParameter("sessionId", sessionId);
    	Query orderEmailQuery = session.createQuery(orderEmailHql);
    	orderEmailQuery.setParameter("sessionId", sessionId);
    	
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
        
        TransactionLog transactionLog = new TransactionLog();
        transactionLog.setSessionId(sessionId);
        transactionLog.setCartId(cartId);
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
		
	} catch (final HibernateError he) {
		logger.error("Error in method: " + he);
	}
    	session.close();
    }   
    
    
    /*@Override*/
    public void createTransactionsFromCartList(List<Cart> cartList, String sessionId){
/*    	Random randomGenerator = new Random();
    	int trackingNumber = randomGenerator.nextInt(1000000000);*/
    	long trackingNumber = (long) Math.floor(Math.random() * 9000000000L) + 1000000000L;
    	for (Cart cartRow : cartList) {
    		/*We assign a random number for the tracking number to each TransactionLog record we created:*/
    		createTransaction(cartRow.getCartId(),sessionId,trackingNumber);
    	}     	    	
    }     
    
    /*@Override*/
    public void createTransactionsFromSessionCartList(List<Cart> cartList, String sessionId){
/*    	Random randomGenerator = new Random();
    	int trackingNumber = randomGenerator.nextInt(1000000000);*/
    	long trackingNumber = (long) Math.floor(Math.random() * 9000000000L) + 1000000000L;
    	for (Cart cartRow : cartList) {
    		/*We assign a random number for the tracking number to each TransactionLog record we created:*/
    		createTransactionFromSessionId(cartRow.getCartId(),sessionId,trackingNumber);
    	}     	    	
    }       
        
    public TransactionLog getLastTransactionByAccountId(int accountId) {
        Session session = this.sessionFactory.openSession();
        String hql = "FROM TransactionLog WHERE transactionId = (SELECT MAX(transactionId) FROM TransactionLog WHERE accountId = :accountId)";
        Query query = session.createQuery(hql);
        query.setParameter("accountId", accountId);
        TransactionLog transaction = (TransactionLog) query.uniqueResult();      
        session.close();
        return transaction;   
    }
    
    public TransactionLog getLastTransactionBySessionId(String sessionId) {
        Session session = this.sessionFactory.openSession();
        String hql = "FROM TransactionLog WHERE transactionId = (SELECT MAX(transactionId) FROM TransactionLog WHERE sessionId = :sessionId)";
        Query query = session.createQuery(hql);
        query.setParameter("sessionId", sessionId);
        TransactionLog transaction = (TransactionLog) query.uniqueResult();      
        session.close();
        return transaction;   
    }    
    
    public void setTransactionToConfirmed(long trackingNumber) {
    	Session session = this.sessionFactory.openSession();
    	String hql = "UPDATE TransactionLog SET confirmed = 1 where trackingNumber = :trackingNumber";
    	Query query = session.createQuery(hql);
    	query.setParameter("trackingNumber", trackingNumber);
    	query.executeUpdate();
    	session.close();   
    }
    
    /*    @Override*/
    public void updateMessage(TransactionLog transaction, String message) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        transaction.setMessage(message);
        session.saveOrUpdate(transaction);
        tx.commit();
        session.close();
    }    
    
}