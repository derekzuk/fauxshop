package com.journaldev.spring.model;
 
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
    
    @Column(name="BRAND")
    private String brand;
    
    @Column(name="PRODUCT_CODE")
    private String productCode;
    
    @Column(name="STOCK_QUANTITY")
    private int stockQuantity;
    
    @Column(name="SIZE")
    private String size;
    
    @Column(name="COLOR")
    private String color;
    
    @Column(name="PRICE_USD")
    private String priceUsd;
    
    @Column(name="THUMBNAIL")
    private String thumbnail;    
 
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
    
    public String getBrand() {
    	return brand;
    }
    
    public void setBrand(String brand) {
    	this.brand = brand;
    }
    
    public String getProductCode() {
    	return productCode;
    }
    
    public void setProductCode(String productCode) {
    	this.productCode = productCode;
    }
    
    public int getStockQuantity() {
    	return stockQuantity;
    }
    
    public void setStockQuantity(int stockQuantity) {
    	this.stockQuantity = stockQuantity;
    }     

    public String getSize() {
    	return size;
    }
    
    public void setSize(String size) {
    	this.size = size;
    }
    
    public String getColor() {
    	return color;
    }
    
    public void setColor(String color) {
    	this.color = color;
    } 
    
    public String getPriceUsd() {
    	return priceUsd;
    }
    
    public void setPriceUsd(String priceUsd) {
    	this.priceUsd = priceUsd;
    }
    
    public String getThumbnail() {
    	return thumbnail;
    }
    
    public void setThumbnail(String thumbnail) {
    	this.thumbnail = thumbnail;
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