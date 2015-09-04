package com.journaldev.spring.dao;
 
import java.util.List;
 



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
 


import com.journaldev.spring.model.Cart;
import com.journaldev.spring.model.InventoryDetail;

@Repository
public class InventoryDetailDAOImpl implements InventoryDetailDAO {
     
    private static final Logger logger = LoggerFactory.getLogger(InventoryDAOImpl.class);
 
    private SessionFactory sessionFactory;
     
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }       
 
    @SuppressWarnings("unchecked")
    /*@Override*/
    public List<InventoryDetail> listInventoryDetail() {
        Session session = this.sessionFactory.getCurrentSession();
        List<InventoryDetail> inventoryDetailList = session.createQuery("from InventoryDetail").list();
        for(InventoryDetail i : inventoryDetailList){
            logger.info("InventoryDetail List::"+i);
        }
        return inventoryDetailList;
    }
 
    @SuppressWarnings("unchecked")    
    /*@Override*/
    public List<InventoryDetail> getInventoryDetailByInventoryId(int inventoryId) {
        Session session = this.sessionFactory.getCurrentSession();      
        String hql = "FROM InventoryDetail WHERE inventoryId = :inventoryId";
        Query query = session.createQuery(hql);
        query.setParameter("inventoryId", inventoryId);
		List<InventoryDetail> inventoryDetailList = (List<InventoryDetail>) query.list();
        
        for(InventoryDetail id : inventoryDetailList){
        	logger.info("Inventory Detail List::"+id);
        }
        
        logger.info("getInventoryDetailByInventoryId query: " + query.toString());
        logger.info("getInventoryDetailByInventoryId query results (toString()): " + inventoryDetailList.toString());        
        return inventoryDetailList;               
    }    
       
    /*@Override*/
    public InventoryDetail getInventoryDetailByInventoryDetailId(int inventoryDetailId) {
        Session session = this.sessionFactory.getCurrentSession();      
        String hql = "FROM InventoryDetail WHERE inventoryDetailId = :inventoryDetailId";
        Query query = session.createQuery(hql);
        query.setParameter("inventoryDetailId", inventoryDetailId);
		InventoryDetail inventoryDetail = (InventoryDetail) query.uniqueResult();
        
        logger.info("getInventoryDetailByInventoryDetailId query: " + query.toString());
        logger.info("getInventoryDetailByInventoryDetailId query results (toString()): " + inventoryDetail.toString());        
        return inventoryDetail;               
    }         
    
    /*@Override*/
    public InventoryDetail getInventoryDetailByIdColorSize(int inventoryId, String color, String size) {
        Session session = this.sessionFactory.getCurrentSession();      
        String hql = "FROM InventoryDetail WHERE inventoryId = :inventoryId AND color = :color AND size = :size";
        Query query = session.createQuery(hql);
        query.setParameter("inventoryId", inventoryId);
        query.setParameter("color", color);
        query.setParameter("size", size);
		InventoryDetail inventoryDetail = (InventoryDetail) query.uniqueResult();
        
        logger.info("getInventoryDetailByInventoryDetailId query: " + query.toString());
        logger.info("getInventoryDetailByInventoryDetailId query results (toString()): " + inventoryDetail.toString());        
        return inventoryDetail;               
    }       
 
}