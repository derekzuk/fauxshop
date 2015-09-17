package com.fauxshop.spring.service;
 
import java.util.List;
 

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 

import com.fauxshop.spring.dao.InventoryDAO;
import com.fauxshop.spring.model.Inventory;
import com.fauxshop.spring.model.InventoryCategoryCode;
 
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
    
    /*@Override*/
    public List<Inventory> getInventoryListByInventoryCatCd(int inventoryCatCd) {
        return this.inventoryDAO.getInventoryListByInventoryCatCd(inventoryCatCd);
    }      
    
    public InventoryCategoryCode getInventoryCategoryCode(int inventoryCatCd) {
    	return this.inventoryDAO.getInventoryCategoryCode(inventoryCatCd);
    }
    
}