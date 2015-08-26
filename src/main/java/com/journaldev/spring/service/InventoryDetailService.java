package com.journaldev.spring.service;
 
import java.util.List;
 
import com.journaldev.spring.model.InventoryDetail;
 
public interface InventoryDetailService {
 
    public List<InventoryDetail> listInventoryDetail();
    public InventoryDetail getInventoryDetailByInventoryId(int inventoryId);
     
}