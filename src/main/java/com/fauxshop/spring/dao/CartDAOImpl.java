package com.fauxshop.spring.dao;
 
import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.fauxshop.spring.model.Cart;

@Repository
public class CartDAOImpl implements CartDAO {
     
    private static final Logger logger = LoggerFactory.getLogger(CartDAOImpl.class);
    
    private SessionFactory sessionFactory;
     
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    
    /*    @Override*/
    public void save(Cart c) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(c);
        tx.commit();
        session.close();
    }
 
    @SuppressWarnings("unchecked")
/*    @Override*/
    public List<Cart> list() {
        Session session = this.sessionFactory.openSession();
        List<Cart> cartList = session.createQuery("from Cart").list();
        session.close();
        return cartList;
    }    
 
    /*@Override*/
    public void updateCart(Cart c) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(c);
        logger.info("Cart updated successfully, Cart Details="+c);
    }
 
    @SuppressWarnings("unchecked")
    /*@Override*/
    public List<Cart> listCarts() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Cart> cartsList = session.createQuery("from Cart").list();
        for(Cart c : cartsList){
            logger.info("Cart List::"+c);
        }
        return cartsList;
    }
 
    /*@Override*/
    public Cart getCartById(int id) {
        Session session = this.sessionFactory.getCurrentSession();      
        Cart c = (Cart) session.load(Cart.class, new Integer(id));
        logger.info("Cart loaded successfully, Cart details="+c);
        return c;
    }   
    
    /*@Override*/
    public void addToCart(int accountId,int inventoryId,int quantity, String pricePerItem, String shippingCost, String tax){
    	Session session = this.sessionFactory.openSession();
    	Transaction tx = session.beginTransaction();
    	String hql = "INSERT INTO Cart (accountId,inventoryId,quantity,pricePerItem,shippingCost,tax)"
    			+ " :accountId,:inventoryId,:quantity,:pricePerItem,:shippingCost,:tax";
    	Query query = session.createQuery(hql);
    	query.setParameter("accountId", accountId);
    	query.setParameter("inventoryId", inventoryId);
    	query.setParameter("quantity", quantity);
    	query.setParameter("pricePerItem", pricePerItem);
    	query.setParameter("shippingCost", shippingCost);
    	query.setParameter("tax", tax);
    	
    	query.executeUpdate();    	
    	tx.commit();
    	session.close();
    }
 
    /*@Override*/
    public void removeCart(int cartId) {
        Session session = this.sessionFactory.getCurrentSession();
        Cart c = (Cart) session.load(Cart.class, new Integer(cartId));
        if(null != c){
            session.delete(c);
        }
        logger.info("Cart deleted successfully, cart details="+c);
    } 
    
    /*@Override*/
    public void updateQuantity(int cartId, int quantity) {
    	Session session = this.sessionFactory.openSession();
    	Transaction tx = session.beginTransaction();
    	
        logger.info("updateQuantity quantity variable value: " + quantity); 
    	
    	String hql = "UPDATE Cart SET quantity = :quantity WHERE cartId = :cartId";
    	Query query = session.createQuery(hql);
    	query.setParameter("cartId", cartId);
    	query.setParameter("quantity", quantity);    	
    	query.executeUpdate();    	
    	tx.commit();
    	session.close();
    }     
    
    /*@Override*/
    public void removeCartFromCartList(List<Cart> cartList) {
    	logger.info("in removeCartFromCartList.");
    	for (Cart cartRow : cartList) {
    		removeCart(cartRow.getCartId());
    	}   
    	logger.info("Cart removed.");
    }       
    
    /*@Override*/
    @SuppressWarnings("unchecked")
	public List<Cart> getCartByUserLogin(String name) {
        Session session = this.sessionFactory.getCurrentSession();      
        String hql = "FROM Cart WHERE accountId = ("
        		+ "SELECT accountId FROM Account where userLogin = :userLogin)";
        Query query = session.createQuery(hql);
        query.setParameter("userLogin", name);
        List<Cart> cartList = (List<Cart>) query.list();

        for(Cart c : cartList){
            logger.info("Cart List::"+c);
        }
        
        logger.info("getCartByUserLogin query: " + query.toString());
        logger.info("getCartByUserLogin query results (toString()): " + cartList.toString());        
        return cartList;        
    }
    
    /*@Override*/
    public BigDecimal getCartItemCostByUserLogin(String name) {
        Session session = this.sessionFactory.getCurrentSession();      
        String hql = "SELECT quantity * SUM(pricePerItem) FROM Cart WHERE accountId = ("
        		+ "SELECT accountId FROM Account where userLogin = :userLogin)";
        Query query = session.createQuery(hql);
        query.setParameter("userLogin", name);
        BigDecimal cartSum = (BigDecimal) query.uniqueResult();

        logger.info("getCartByUserLogin query: " + query.toString());
        logger.info("getCartByUserLogin query results (toString()): " + cartSum);        
        return cartSum;        
    }
    
    /*@Override*/
    public BigDecimal getCartShippingCostByUserLogin(String name) {
        Session session = this.sessionFactory.getCurrentSession();      
        String hql = "SELECT quantity * SUM(shippingCost) FROM Cart WHERE accountId = ("
        		+ "SELECT accountId FROM Account where userLogin = :userLogin)";
        Query query = session.createQuery(hql);
        query.setParameter("userLogin", name);
        BigDecimal cartSum = (BigDecimal) query.uniqueResult();

        logger.info("getCartByUserLogin query: " + query.toString());
        logger.info("getCartByUserLogin query results (toString()): " + cartSum);        
        return cartSum;        
    }         
    
    /*@Override*/
    public BigDecimal getCartTaxCostByUserLogin(String name) {
        Session session = this.sessionFactory.getCurrentSession();      
        String hql = "SELECT quantity * SUM(tax) FROM Cart WHERE accountId = ("
        		+ "SELECT accountId FROM Account where userLogin = :userLogin)";
        Query query = session.createQuery(hql);
        query.setParameter("userLogin", name);
        BigDecimal cartSum = (BigDecimal) query.uniqueResult();

        logger.info("getCartByUserLogin query: " + query.toString());
        logger.info("getCartByUserLogin query results (toString()): " + cartSum);        
        return cartSum;        
    }      
    
    /*@Override*/
    public BigDecimal getCartTotalByUserLogin(String name) {
        Session session = this.sessionFactory.getCurrentSession();      
        String hql = "SELECT quantity * (SUM(pricePerItem) + SUM(shippingCost) + SUM(tax)) FROM Cart WHERE accountId = ("
        		+ "SELECT accountId FROM Account where userLogin = :userLogin)";
        Query query = session.createQuery(hql);
        query.setParameter("userLogin", name);
        BigDecimal cartSum = (BigDecimal) query.uniqueResult();

        logger.info("getCartByUserLogin query: " + query.toString());
        logger.info("getCartByUserLogin query results (toString()): " + cartSum);        
        return cartSum;        
    }      
 
}