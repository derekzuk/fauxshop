package com.fauxshop.spring.service;
 
import java.util.List;
 

import com.fauxshop.spring.model.InventoryDetail;
 
public interface InventoryDetailService {
 
    public List<InventoryDetail> listInventoryDetail();
    public List<InventoryDetail> getInventoryDetailByInventoryId(int inventoryId);
    public InventoryDetail getInventoryDetailByInventoryDetailId(int inventoryDetailId);
    public InventoryDetail getInventoryDetailByIdColorSize(int inventoryId, String color, String size);
    public List<String> getAvailableSizes(int inventoryId, String color);
     
}