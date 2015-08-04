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
@Table(name="sizing")
public class Sizing {
	
    @Id
    @Column(name="SIZING_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int sizingId;
     
    @Column(name="INVENTORY_ID")
    private int inventoryId;
    
    @Column(name="BUST")
    private int bust;
    
    @Column(name="WAIST")
    private int waist;
    
    @Column(name="HIP")
    private int hip;
    
    @Column(name="SHOULDER_WIDTH")
    private int shoulderWidth;
    
    @Column(name="FRONT_BODY_LENGTH")
    private int frontBodyLength;
        
    public int getSizingId() {
        return sizingId;
    }
 
    public void setSizingId(int sizingId) {
        this.sizingId = sizingId;
    }
 
    public int getInventoryId() {
        return inventoryId;
    }
 
    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }
 
    public int getBust() {
        return bust;
    }
 
    public void setBust(int bust) {
        this.bust = bust;
    }
    
    public int getWaist() {
    	return waist;
    }
    
    public void setWaist(int waist) {
    	this.waist = waist;
    }
    
    public int getHip() {
    	return hip;
    }
    
    public void setHip(int hip) {
    	this.hip = hip;
    }
    
    public int getShoulderWidth() {
    	return shoulderWidth;
    }
    
    public void setShoulderWidth(int shoulderWidth) {
    	this.shoulderWidth = shoulderWidth;
    }
    
    public int getFrontBodyLength() {
    	return frontBodyLength;
    }
    
    public void setFrontBodyLength(int frontBodyLength) {
    	this.frontBodyLength = frontBodyLength;
    }
    
    @Override
    public String toString(){
        return "sizingId="+sizingId+
        		", inventoryId="+inventoryId+
        		", bust="+bust+
        		", waist="+waist+
        		", hip="+hip+
        		", shoulderWidth="+shoulderWidth+
        		", frontBodyLength="+frontBodyLength;
   }
}