package com.journaldev.spring.service;
 
import java.util.List;
 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.journaldev.spring.dao.InventoryDAO;
import com.journaldev.spring.model.Inventory;
 
@Service
public class InventoryServiceImpl implements InventoryService {
     
    private InventoryDAO inventoryDAO;
 
    public void setInventoryDAO(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }
 
    /*@Override*/
    @Transactional
    public List<Inventory> listInventory() {
        return this.inventoryDAO.listInventory();
    }
 
    /*@Override*/
    @Transactional
    public Inventory getInventoryById(int inventoryId) {
        return this.inventoryDAO.getInventoryById(inventoryId);
    }
    
    /*@Override*/
    @Transactional
    public Inventory getInventoryByInventoryDetailId(int inventoryDetailId) {
        return this.inventoryDAO.getInventoryByInventoryDetailId(inventoryDetailId);
    }        
 
}