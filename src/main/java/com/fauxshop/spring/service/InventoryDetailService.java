package com.fauxshop.spring.service;
 
import java.util.List;
 

import com.fauxshop.spring.model.InventoryDetail;
 
public interface InventoryDetailService {
 
    public List<InventoryDetail> getInventoryDetailByInventoryId(int inventoryId);
    public InventoryDetail getInventoryDetailByInventoryDetailId(int inventoryDetailId);
    public List<InventoryDetail> getInventoryDetailByIdColor(int inventoryId, String color);
    public InventoryDetail getInventoryDetailByIdColorSize(int inventoryId, String color, String size);
    public List<String> getAvailableSizes(int inventoryId, String color);
     
}