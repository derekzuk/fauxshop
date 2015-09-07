package com.fauxshop.spring.service;
 
import java.util.List;
 
import com.fauxshop.spring.model.Inventory;
 
public interface InventoryService {
 
    public List<Inventory> listInventory();
    public Inventory getInventoryById(int inventoryId);
    public Inventory getInventoryByInventoryDetailId(int inventoryDetailId);
     
}