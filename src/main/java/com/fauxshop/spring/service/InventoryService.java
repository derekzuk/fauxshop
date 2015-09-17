package com.fauxshop.spring.service;
 
import java.util.List;
 

import com.fauxshop.spring.model.Inventory;
import com.fauxshop.spring.model.InventoryCategoryCode;
 
public interface InventoryService {
 
    public List<Inventory> listInventory();
    public Inventory getInventoryById(int inventoryId);
    public Inventory getInventoryByInventoryDetailId(int inventoryDetailId);
    public List<Inventory> getInventoryListByInventoryCatCd(int inventoryCatCd);
    public InventoryCategoryCode getInventoryCategoryCode(int inventoryCatCd);
     
}