package com.journaldev.spring.dao;
 
import java.util.List;
 



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
 


import com.journaldev.spring.model.Account;
import com.journaldev.spring.model.Cart;

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
    public void addCart(Cart c) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(c);
        logger.info("Cart saved successfully, Cart Details="+c);
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
    public void removeCart(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Cart c = (Cart) session.load(Cart.class, new Integer(id));
        if(null != c){
            session.delete(c);
        }
        logger.info("Cart deleted successfully, cart details="+c);
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
 
}