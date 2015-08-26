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
 
    /*@Override*/
    public InventoryDetail getInventoryDetailByInventoryId(int inventoryId) {
        Session session = this.sessionFactory.getCurrentSession();      
        String hql = "FROM InventoryDetail WHERE inventoryId = :inventoryId";
        Query query = session.createQuery(hql);
        query.setParameter("inventoryId", inventoryId);
        InventoryDetail inventoryDetail = (InventoryDetail) query.uniqueResult();
        
        logger.info("getCartByUserLogin query: " + query.toString());
        logger.info("getCartByUserLogin query results (toString()): " + inventoryDetail.toString());        
        return inventoryDetail;         
    }          
 
}