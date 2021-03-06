package com.fauxshop.spring.dao;
 
import java.util.List;
 





import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
 

import com.fauxshop.spring.model.Inventory;
import com.fauxshop.spring.model.InventoryCategoryCode;

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
        Session session = this.sessionFactory.openSession();
        List<Inventory> inventoryList = session.createQuery("from Inventory").list();
        for(Inventory i : inventoryList){
            logger.info("Inventory List::"+i);
        }
        session.close();
        return inventoryList;
    }
 
    /*@Override*/
    public Inventory getInventoryById(int inventoryId) {
        Session session = this.sessionFactory.openSession();      
        Inventory i = (Inventory) session.load(Inventory.class, new Integer(inventoryId));
        logger.info("Inventory loaded successfully, Inventory details="+i);
        session.close();
        return i;
    }       
    
    /*@Override*/
    public Inventory getInventoryByInventoryDetailId(int inventoryDetailId) {        
        Session session = this.sessionFactory.openSession();      
        String hql = "FROM Inventory WHERE inventoryId = ("
        		+ "SELECT inventoryId FROM InventoryDetail WHERE inventoryDetailId = :inventoryDetailId)";
        Query query = session.createQuery(hql);
        query.setParameter("inventoryDetailId", inventoryDetailId);
		Inventory inventory = (Inventory) query.uniqueResult();
        
        logger.info("getInventoryByInventoryDetailId query: " + query.toString());
        logger.info("getInventoryByInventoryDetailId query results (toString()): " + inventory.toString()); 
        session.close();
        return inventory;                
    }      
    
    /*@Override*/
	@SuppressWarnings("unchecked")
    public List<Inventory> getInventoryListByInventoryCatCd(int inventoryCatCd) {        
        Session session = this.sessionFactory.openSession();  
        String hql = "FROM Inventory WHERE inventoryCatCd = :inventoryCatCd";
        Query query = session.createQuery(hql);
        query.setParameter("inventoryCatCd", inventoryCatCd);
		List<Inventory> inventoryList = (List<Inventory>) query.list();
        
        logger.info("getInventoryListByInventoryTypeCd query: " + query.toString());
        logger.info("getInventoryListByInventoryTypeCd query results (toString()): " + inventoryList.toString());
        session.close();
        return inventoryList;                
    }      
	
    /*@Override*/
    public InventoryCategoryCode getInventoryCategoryCode(int inventoryCatCd) {
    	Session session = this.sessionFactory.openSession();
    	String hql = "FROM InventoryCategoryCode WHERE inventoryCatCd = :inventoryCatCd";
    	Query query = session.createQuery(hql);
    	query.setParameter("inventoryCatCd", inventoryCatCd);
    	InventoryCategoryCode result = (InventoryCategoryCode) query.uniqueResult(); 
        
        session.close();
        logger.info("InventoryCategoryCode loaded successfully, InventoryCategoryCode details="+result);
        return result;
    }
    
    // this query doesn't actually return the top sellers, it just limits the results to 4.
	@SuppressWarnings("unchecked")
    public List<Inventory> getBestSellerInventoryList() {
    	Session session = this.sessionFactory.openSession();
    	String hql = "FROM Inventory";
    	Query query = session.createQuery(hql);
    	query.setMaxResults(4);
		List<Inventory> result = (List<Inventory>) query.list();
		session.close();
    	return result;
    }    
}