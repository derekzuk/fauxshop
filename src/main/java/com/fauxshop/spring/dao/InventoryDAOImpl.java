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
import com.fauxshop.spring.model.Inventory;
import com.fauxshop.spring.model.InventoryDetail;

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
    public Inventory getInventoryByInventoryDetailId(int inventoryDetailId) {        
        Session session = this.sessionFactory.getCurrentSession();      
        String hql = "FROM Inventory WHERE inventoryId = ("
        		+ "SELECT inventoryId FROM InventoryDetail WHERE inventoryDetailId = :inventoryDetailId)";
        Query query = session.createQuery(hql);
        query.setParameter("inventoryDetailId", inventoryDetailId);
		Inventory inventory = (Inventory) query.uniqueResult();
        
        logger.info("getInventoryByInventoryDetailId query: " + query.toString());
        logger.info("getInventoryByInventoryDetailId query results (toString()): " + inventory.toString());        
        return inventory;                
    }       
 
}