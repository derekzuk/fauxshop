package com.fauxshop.spring.model;
 
import java.math.BigDecimal;

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
@Table(name="inventory")
public class Inventory {
 
    @Id
    @Column(name="INVENTORY_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int inventoryId;
     
    @Column(name="INVENTORY_TYPE_CD")
    private int inventoryTypeCd;
    
    @Column(name="INVENTORY_TXT")
    private String inventoryTxt;
    
    @Column(name="INVENTORY_DESCRIPTION")
    private String inventoryDescription;
    
    @Column(name="INVENTORY_CARE")
    private String inventoryCare;
    
    @Column(name="INVENTORY_SIZE_DESC")
    private String inventorySizeDesc;    
    
    @Column(name="BRAND")
    private String brand;
    
    @Column(name="PRICE_USD")
    private BigDecimal priceUsd;
    
    @Column(name="IN_STOCK")
	private boolean inStock;
    
    @Column(name="IMG")
    private String img;    
 
    public int getInventoryId() {
        return inventoryId;
    }
 
    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }
 
    public int getInventoryTypeCd() {
        return inventoryTypeCd;
    }
 
    public void setInventoryTypeCd(int inventoryTypeCd) {
        this.inventoryTypeCd = inventoryTypeCd;
    }
 
    public String getInventoryTxt() {
        return inventoryTxt;
    }
 
    public void setInventoryTxt(String inventoryTxt) {
        this.inventoryTxt = inventoryTxt;
    }
    
    public String getInventoryDescription() {
    	return inventoryDescription;
    }
    
    public void setInventoryDescription(String inventoryDescription) {
    	this.inventoryDescription = inventoryDescription;
    }
    
    public String getInventoryCare() {
    	return inventoryCare;
    }
    
    public void setInventoryCare(String inventoryCare) {
    	this.inventoryCare = inventoryCare;
    }
    
    public String getInventorySizeDesc() {
    	return inventorySizeDesc;
    }
    
    public void setInventorySizeDesc(String inventorySizeDesc) {
    	this.inventorySizeDesc = inventorySizeDesc;
    }    
    
    public String getBrand() {
    	return brand;
    }
    
    public void setBrand(String brand) {
    	this.brand = brand;
    }
        
    public BigDecimal getPriceUsd() {
    	return priceUsd;
    }
    
    public void setPriceUsd(BigDecimal priceUsd) {
    	this.priceUsd = priceUsd;
    }
    
    public boolean getInStock() {
    	return inStock;
    }
    
    public void setInStock(boolean inStock) {
    	this.inStock = inStock;
    }    
    
    public String getImg() {
    	return img;
    }
    
    public void setImg(String img) {
    	this.img = img;
    }         
    
    @Override
    public String toString(){
        return "inventoryId="+inventoryId+
        		", inventoryTypeCd="+inventoryTypeCd+
        		", inventoryTxt="+inventoryTxt+
        		", inventoryDescription="+inventoryDescription+
        		", inventoryCare="+inventoryCare;
    }
}