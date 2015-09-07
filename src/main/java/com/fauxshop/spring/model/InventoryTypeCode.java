package com.fauxshop.spring.model;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 *
 */
@Entity
@Table(name="inventory_type_code")
public class InventoryTypeCode {	
	
    @Id
    @Column(name="INVENTORY_TYPE_CD")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int inventoryTypeCd;
     
    @Column(name="INVENTORY_TYPE_TXT")
    private String inventoryTypeTxt;
    
    @Column(name="INVENTORY_TYPE_DESCRIPTION")
    private String inventoryTypeDescription;
        
    public int getInventoryTypeCd() {
        return inventoryTypeCd;
    }
 
    public void setInventoryTypeCd(int inventoryTypeCd) {
        this.inventoryTypeCd = inventoryTypeCd;
    }
 
    public String getInventoryTypeTxt() {
        return inventoryTypeTxt;
    }
 
    public void setInventoryTypeTxt(String inventoryTypeTxt) {
        this.inventoryTypeTxt = inventoryTypeTxt;
    }
 
    public String getInventoryTypeDescription() {
        return inventoryTypeDescription;
    }
 
    public void setInventoryTypeDescription(String inventoryTypeDescription) {
        this.inventoryTypeDescription = inventoryTypeDescription;
    }
        
    @Override
    public String toString(){
        return "inventoryTypeCd="+inventoryTypeCd+
        		", inventoryTypeTxt="+inventoryTypeTxt+
        		", inventoryTypeDescription="+inventoryTypeDescription;
    }
}