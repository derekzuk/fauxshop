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
import com.journaldev.spring.model.Inventory;

@Repository
public class InventoryDAOImpl implements InventoryDAO {
     
    private static final Logger logger = LoggerFactory.getLogger(InventoryDAOImpl.class);
 
    private SessionFactory sessionFactory;
     
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    
    /*    @Override*/
    public void save(Inventory p) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(p);
        tx.commit();
        session.close();
    }
 
    @SuppressWarnings("unchecked")
/*    @Override*/
    public List<Inventory> list() {
        Session session = this.sessionFactory.openSession();
        List<Inventory> inventoryList = session.createQuery("from Inventory").list();
        session.close();
        return inventoryList;
    }    
 
    @SuppressWarnings("unchecked")
    /*@Override*/
    public List<Inventory> listInventory() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Inventory> inventoryList = session.createQuery("from Inventory").list();
        for(Inventory i : inventoryList){
            logger.info("Inventory List::"+i);
        }
        return inventoryList;
    }
 
    /*@Override*/
    public Inventory getInventoryById(int inventoryId) {
        Session session = this.sessionFactory.getCurrentSession();      
        Inventory i = (Inventory) session.load(Inventory.class, new Integer(inventoryId));
        logger.info("Inventory loaded successfully, Inventory details="+i);
        return i;
    }
    
    /*@Override*/
/*    public Inventory getInventoryById(int inventoryId) {
        Session session = this.sessionFactory.getCurrentSession();      
        String hql = "FROM Inventory WHERE inventoryId = :inventoryId";
        Query query = session.createQuery(hql);
        query.setParameter("inventoryId", inventoryId);
        Inventory result = (Inventory) query.uniqueResult();

        logger.info("getInventoryById query: " + query.toString());
        logger.info("getAccountById query results (toString()): " + result.toString());
        return result;
    }*/        
 
}