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
@Table(name="inventory_detail")
public class InventoryDetail {
 
    @Id
    @Column(name="INVENTORY_DETAIL_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int inventoryDetailId;

    @Column(name="INVENTORY_ID")
    private int inventoryId;
    
    @Column(name="PRODUCT_CODE")
    private String productCode;
    
    @Column(name="STOCK_QUANTITY")
    private int stockQuantity;
    
    @Column(name="SIZE")
    private String size;
    
    @Column(name="COLOR")
    private String color;
    
    @Column(name="THUMBNAIL1")
    private String thumbnail1;
    
    @Column(name="THUMBNAIL2")
    private String thumbnail2;

    @Column(name="THUMBNAIL3")
    private String thumbnail3;
    
    @Column(name="THUMBNAIL4")
    private String thumbnail4;
    
    @Column(name="THUMBNAIL5")
    private String thumbnail5;
    
    public int getInventoryDetailId() {
        return inventoryDetailId;
    }
 
    public void setInventoryDetailId(int inventoryDetailId) {
        this.inventoryDetailId = inventoryDetailId;
    }    
 
    public int getInventoryId() {
        return inventoryId;
    }
 
    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
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
    
    public String getThumbnail1() {
    	return thumbnail1;
    }
    
    public void setThumbnail1(String thumbnail1) {
    	this.thumbnail1 = thumbnail1;
    }
    
    public String getThumbnail2() {
    	return thumbnail2;
    }
    
    public void setThumbnail2(String thumbnail2) {
    	this.thumbnail2 = thumbnail2;
    }         
    
    public String getThumbnail3() {
    	return thumbnail3;
    }
    
    public void setThumbnail3(String thumbnail3) {
    	this.thumbnail3 = thumbnail3;
    }         
    
    public String getThumbnail4() {
    	return thumbnail4;
    }
    
    public void setThumbnail4(String thumbnail4) {
    	this.thumbnail4 = thumbnail4;
    }         
    
    public String getThumbnail5() {
    	return thumbnail5;
    }
    
    public void setThumbnail5(String thumbnail5) {
    	this.thumbnail5 = thumbnail5;
    }         
    
    @Override
    public String toString(){
        return "inventoryDetailId="+inventoryDetailId+
        		", inventoryId="+inventoryId+
        		", productCode="+productCode+
        		", stockQuantity="+stockQuantity+
        		", size="+size+
				", color="+color;
    }
}