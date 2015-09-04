package com.journaldev.spring.service;
 
import java.util.List;
 


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 


import com.journaldev.spring.dao.InventoryDetailDAO;
import com.journaldev.spring.model.Inventory;
import com.journaldev.spring.model.InventoryDetail;
 
@Service
public class InventoryDetailServiceImpl implements InventoryDetailService {
     
    private InventoryDetailDAO inventoryDetailDAO;
 
    public void setInventoryDetailDAO(InventoryDetailDAO inventoryDetailDAO) {
        this.inventoryDetailDAO = inventoryDetailDAO;
    }
 
    /*@Override*/
    @Transactional
    public List<InventoryDetail> listInventoryDetail() {
        return this.inventoryDetailDAO.listInventoryDetail();
    }
 
    /*@Override*/
    @Transactional
    public List<InventoryDetail> getInventoryDetailByInventoryId(int inventoryId) {
        return this.inventoryDetailDAO.getInventoryDetailByInventoryId(inventoryId);
    }
    
    /*@Override*/
    @Transactional
    public InventoryDetail getInventoryDetailByInventoryDetailId(int inventoryDetailId) {
        return this.inventoryDetailDAO.getInventoryDetailByInventoryDetailId(inventoryDetailId);
    }    
    
    /*@Override*/
    @Transactional
    public InventoryDetail getInventoryDetailByIdColorSize(int inventoryId, String color, String size) {
        return this.inventoryDetailDAO.getInventoryDetailByIdColorSize(inventoryId, color, size);
    }         
    
    public List<String> getAvailableSizes(int inventoryId, String color){
    	return this.inventoryDetailDAO.getAvailableSizes(inventoryId, color);
    }
 
}