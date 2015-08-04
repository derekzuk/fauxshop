package com.journaldev.spring.dao;
 
import java.util.List;
 
import com.journaldev.spring.model.Inventory;
 
public interface InventoryDAO {
 
	/*Do we need these two?*/
    public void save(Inventory i);    
    public List<Inventory> list();
    
    public List<Inventory> listInventory();
    public Inventory getInventoryById(int id);
}