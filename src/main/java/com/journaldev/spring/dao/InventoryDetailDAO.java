package com.journaldev.spring.dao;
 
import java.util.List;
 

import com.journaldev.spring.model.InventoryDetail;
 
public interface InventoryDetailDAO { 
    
    public List<InventoryDetail> listInventoryDetail();
    public List<InventoryDetail> getInventoryDetailByInventoryId(int inventoryId);
    public InventoryDetail getInventoryDetailByInventoryDetailId(int inventoryDetailId);
    public InventoryDetail getInventoryDetailByIdColorSize(int inventoryId, String color, String size);
    public List<String> getAvailableSizes(int inventoryId, String color);
}