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
@Table(name="inventory_category_code")
public class InventoryCategoryCode {	
	
    @Id
    @Column(name="INVENTORY_CAT_CD")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int inventoryCatCd;
     
    @Column(name="INVENTORY_CAT_TXT")
    private String inventoryCatTxt;
    
    @Column(name="INVENTORY_CAT_DESCRIPTION")
    private String inventoryCatDescription;
        
    public int getInventoryCatCd() {
        return inventoryCatCd;
    }
 
    public void setInventoryCatCd(int inventoryCatCd) {
        this.inventoryCatCd = inventoryCatCd;
    }
 
    public String getInventoryCatTxt() {
        return inventoryCatTxt;
    }
 
    public void setInventoryCatTxt(String inventoryCatTxt) {
        this.inventoryCatTxt = inventoryCatTxt;
    }
 
    public String getInventoryCatDescription() {
        return inventoryCatDescription;
    }
 
    public void setInventoryCatDescription(String inventoryCatDescription) {
        this.inventoryCatDescription = inventoryCatDescription;
    }
        
    @Override
    public String toString(){
        return "inventoryCatCd="+inventoryCatCd+
        		", inventoryCatTxt="+inventoryCatTxt+
        		", inventoryTypeDescription="+inventoryCatDescription;
    }
}