package com.fauxshop.spring.dao;
 
import java.util.List;
 

import com.fauxshop.spring.model.Inventory;
import com.fauxshop.spring.model.InventoryCategoryCode;
 
public interface InventoryDAO {
 
	/*Do we need these two?*/
    public void save(Inventory i);    
    public List<Inventory> list();
    
    public List<Inventory> listInventory();
    public Inventory getInventoryById(int inventoryId);
    public Inventory getInventoryByInventoryDetailId(int inventoryDetailId);
    public List<Inventory> getInventoryListByInventoryCatCd(int inventoryCatCd);
    public InventoryCategoryCode getInventoryCategoryCode(int inventoryCatCd);
    public List<Inventory> getBestSellerInventoryList();
}