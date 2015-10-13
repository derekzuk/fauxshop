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
        session.saveOrUpdate(c);
        tx.commit();
        session.close();
    } 
    
    /*@Override*/
    public Cart getCartByIdAndAccountId(int cartId, int accountId) {
    	Session session = this.sessionFactory.openSession();
    	String hql = "FROM Cart WHERE cartId = :cartId AND accountId = :accountId";
    	Query query = session.createQuery(hql);
    	query.setParameter("cartId", cartId);
    	query.setParameter("accountId", accountId);    	
    	Cart result = (Cart) query.uniqueResult();  
    	session.close();
    	return result;
    }    
    
    /*@Override*/
    public Cart getCartByIdAndSessionId(int cartId, String sessionId) {
    	Session session = this.sessionFactory.openSession();
    	String hql = "FROM Cart WHERE cartId = :cartId AND sessionId = :sessionId";
    	Query query = session.createQuery(hql);
    	query.setParameter("cartId", cartId);
    	query.setParameter("sessionId", sessionId);    	
    	Cart result = (Cart) query.uniqueResult();  
    	session.close();
    	return result;
    }     
    
    /*@Override*/
    public Cart getCartByInventoryDetailIdAndAccountId(int inventoryDetailId, int accountId) {
    	Session session = this.sessionFactory.openSession();
    	String hql = "FROM Cart WHERE inventoryDetailId = :inventoryDetailId AND accountId = :accountId";
    	Query query = session.createQuery(hql);
    	query.setParameter("inventoryDetailId", inventoryDetailId);
    	query.setParameter("accountId", accountId);    	
    	Cart result = (Cart) query.uniqueResult(); 
    	session.close();
    	return result;
    }    
    
    /*@Override*/
    public Cart getCartByInventoryDetailIdAndSessionId(int inventoryDetailId, String sessionId) {
    	Session session = this.sessionFactory.openSession();
    	String hql = "FROM Cart WHERE inventoryDetailId = :inventoryDetailId AND sessionId = :sessionId";
    	Query query = session.createQuery(hql);
    	query.setParameter("inventoryDetailId", inventoryDetailId);
    	query.setParameter("sessionId", sessionId);    	
    	Cart result = (Cart) query.uniqueResult();  
    	session.close();
    	return result;
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
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Cart c = (Cart) session.load(Cart.class, new Integer(cartId));
        if(null != c){
            session.delete(c);
        }
        tx.commit();
        session.close();
        logger.debug("Cart deleted successfully, cart details="+c);
    } 
    
    /*@Override*/
    public void updateQuantity(int cartId, int quantity) {
    	Session session = this.sessionFactory.openSession();
    	Transaction tx = session.beginTransaction();
    	
        logger.debug("updateQuantity quantity variable value: " + quantity); 
    	
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
    	logger.debug("in removeCartFromCartList.");
    	for (Cart cartRow : cartList) {
    		removeCart(cartRow.getCartId());
    	}   
    	logger.debug("Cart removed.");
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
            logger.debug("Cart List::"+c);
        }
        
        logger.debug("getCartByUserLogin query: " + query.toString());
        logger.debug("getCartByUserLogin query results (toString()): " + cartList.toString());        
        return cartList;        
    }
    
    /*@Override*/
    @SuppressWarnings("unchecked")
	public List<Cart> getCartBySessionId(String sessionId) {
    	logger.error("in getCartBySessionId");
    	logger.error("sessionId: " + sessionId);
        Session session = this.sessionFactory.getCurrentSession();      
        String hql = "FROM Cart WHERE sessionId = :sessionId";
        Query query = session.createQuery(hql);
        query.setParameter("sessionId", sessionId);
        List<Cart> cartList = (List<Cart>) query.list();
        logger.error("cartList: " + cartList);
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

        logger.debug("getCartByUserLogin query: " + query.toString());
        logger.debug("getCartByUserLogin query results (toString()): " + cartSum);        
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

        logger.debug("getCartByUserLogin query: " + query.toString());
        logger.debug("getCartByUserLogin query results (toString()): " + cartSum);        
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

        logger.debug("getCartByUserLogin query: " + query.toString());
        logger.debug("getCartByUserLogin query results (toString()): " + cartSum);        
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

        logger.debug("getCartByUserLogin query: " + query.toString());
        logger.debug("getCartByUserLogin query results (toString()): " + cartSum);        
        return cartSum;        
    }
    
    /*@Override*/
    public Long getCartQuantityByUserLogin(String name) {
        Session session = this.sessionFactory.openSession();      
        String hql = "SELECT SUM(quantity) FROM Cart WHERE accountId = ("
        		+ "SELECT accountId FROM Account where userLogin = :userLogin)";
        Query query = session.createQuery(hql);
        query.setParameter("userLogin", name);
        Long cartQuantity = (Long) query.uniqueResult();
      
        session.close();
        return cartQuantity;        
    }    
    
    
    /*@Override*/
    public BigDecimal getCartItemCostBySessionId(String sessionId) {
        Session session = this.sessionFactory.getCurrentSession();      
        String hql = "SELECT quantity * SUM(pricePerItem) FROM Cart WHERE sessionId = :sessionId";
        Query query = session.createQuery(hql);
        query.setParameter("sessionId", sessionId);
        BigDecimal cartSum = (BigDecimal) query.uniqueResult();
        
        return cartSum;        
    }
    
    /*@Override*/
    public BigDecimal getCartShippingCostBySessionId(String sessionId) {
        Session session = this.sessionFactory.getCurrentSession();      
        String hql = "SELECT quantity * SUM(shippingCost) FROM Cart WHERE sessionId = :sessionId";
        Query query = session.createQuery(hql);
        query.setParameter("sessionId", sessionId);
        BigDecimal cartSum = (BigDecimal) query.uniqueResult();
        
        return cartSum;        
    }         
    
    /*@Override*/
    public BigDecimal getCartTaxCostBySessionId(String sessionId) {
        Session session = this.sessionFactory.getCurrentSession();      
        String hql = "SELECT quantity * SUM(tax) FROM Cart WHERE sessionId = :sessionId";
        Query query = session.createQuery(hql);
        query.setParameter("sessionId", sessionId);
        BigDecimal cartSum = (BigDecimal) query.uniqueResult();
   
        return cartSum;        
    }      
    
    /*@Override*/
    public BigDecimal getCartTotalBySessionId(String sessionId) {
        Session session = this.sessionFactory.getCurrentSession();      
        String hql = "SELECT quantity * (SUM(pricePerItem) + SUM(shippingCost) + SUM(tax)) FROM Cart WHERE sessionId = :sessionId";
        Query query = session.createQuery(hql);
        query.setParameter("sessionId", sessionId);
        BigDecimal cartSum = (BigDecimal) query.uniqueResult();
        
        return cartSum;        
    }          
    
    /*@Override*/
    public Long getCartQuantityBySessionId(String sessionId) {
        Session session = this.sessionFactory.openSession();      
        String hql = "SELECT SUM(quantity) FROM Cart WHERE sessionId = :sessionId";
        Query query = session.createQuery(hql);
        query.setParameter("sessionId", sessionId);
        Long cartQuantity = (Long) query.uniqueResult();
      
        session.close();
        return cartQuantity;        
    }        
}