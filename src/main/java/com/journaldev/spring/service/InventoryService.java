package com.journaldev.spring.service;
 
import java.util.List;
 
import com.journaldev.spring.model.Inventory;
 
public interface InventoryService {
 
    public List<Inventory> listInventory();
    public Inventory getInventoryById(int inventoryId);
     
}