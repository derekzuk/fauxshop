package com.fauxshop.spring.model;
 
import java.io.Serializable;
import java.sql.Timestamp;

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
@Table(name="transaction")
public class TransactionLog implements Serializable {
	
	private static final long serialVersionUID = 6182390898687671093L;
 
    @Id
    @Column(name="TRANSACTION_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int transactionId;
     
    @Column(name="ACCOUNT_ID")
    private int accountId;
    
    @Column(name="ORDER_QUANTITY")
    private String orderQuantity;
    
    @Column(name="SHIP_NAME")
    private String shipName;
    
    @Column(name="SHIP_ADDRESS")
    private String shipAddress;
    
    @Column(name="SHIP_ADDRESS2")
    private String shipAddress2;
    
    @Column(name="CITY")
    private String city;
    
    @Column(name="STATE")
    private String state;
    
    @Column(name="ZIP")
    private String zip;
    
    @Column(name="COUNTRY")
    private String country;
    
    @Column(name="PHONE")
    private String phone;

    @Column(name="SHIPPING_COST")
    private String shippingCost;
    
    @Column(name="TAX")
    private String tax;    

    @Column(name="ORDER_EMAIL")
    private String orderEmail;
    
    @Column(name="DATE")
    private Timestamp date;
    
    @Column(name="SHIPPED")
    private boolean shipped;
    
    @Column(name="TRACKING_NUMBER")
    private String trackingNumber;
    
    @Column(name="INVENTORY_ID")
    private int inventoryId;
    
    @Column(name="ORDER_COST")
    private String orderCost;    
    
    public int getTransactionId() {
        return transactionId;
    }
 
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
 
    public int getAccountId() {
        return accountId;
    }
 
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
 
    public String getOrderQuantity() {
        return orderQuantity;
    }
 
    public void setOrderQuantity(String orderQuantity) {
        this.orderQuantity = orderQuantity;
    }
    
    public String getShipName() {
    	return shipName;
    }
    
    public void setShipName(String shipName) {
    	this.shipName = shipName;
    }
    
    public String getShipAddress() {
    	return shipAddress;
    }
    
    public void setShipAddress(String shipAddress) {
    	this.shipAddress = shipAddress;
    }
    
    public String getShipAddress2() {
    	return shipAddress2;
    }
    
    public void setShipAddress2(String shipAddress2) {
    	this.shipAddress2 = shipAddress2;
    }
    
    public String getCity() {
    	return city;
    }
    
    public void setCity(String city) {
    	this.city = city;
    }
    
    public String getState() {
    	return state;
    }
    
    public void setState(String state) {
    	this.state = state;
    }
    
    public String getZip() {
    	return zip;
    }
    
    public void setZip(String zip) {
    	this.zip = zip;
    }    

    public String getCountry() {
    	return country;
    }
    
    public void setCountry(String country) {
    	this.country = country;
    }
    
    public String getPhone() {
    	return phone;
    }
    
    public void setPhone(String phone) {
    	this.phone = phone;
    } 
    
    public String getShippingCost() {
    	return shippingCost;
    }
    
    public void setShippingCost(String shippingCost) {
    	this.shippingCost = shippingCost;
    }
    
    public String getTax() {
    	return tax;
    }
    
    public void setTax(String tax) {
    	this.tax = tax;
    }    
    
    public String getOrderEmail() {
    	return orderEmail;
    }
    
    public void setOrderEmail(String orderEmail) {
    	this.orderEmail = orderEmail;
    }
    
    public Timestamp getDate() {
    	return date;
    }
    
    public void setDate(Timestamp date) {
    	this.date = date;
    }
    
    public boolean getShipped() {
    	return shipped;
    }
    
    public void setShipped(boolean shipped) {
    	this.shipped = shipped;
    }
    
    public String getTrackingNumber() {
    	return trackingNumber;
    }
    
    public void setTrackingNumber(String trackingNumber) {
    	this.trackingNumber = trackingNumber;
    }
    
    public int getInventoryId() {
    	return inventoryId;
    }
    
    public void setInventoryId(int inventoryId) {
    	this.inventoryId = inventoryId;
    }
    
    public String getOrderCost() {
    	return orderCost;
    }
    
    public void setOrderCost(String orderCost) {
    	this.orderCost = orderCost;
    }     
    
    @Override
    public String toString(){
        return "transactionId="+transactionId+
        		", accountId="+accountId+
        		", orderQuantity="+orderQuantity+
        		", shipName="+shipName+
        		", shipAddress="+shipAddress+
        		", city="+city+
        		", state="+state+
        		", zip="+zip+
        		", country="+country+
        		", phone="+phone+
        		", tax="+tax+
        		", orderEmail="+orderEmail+
        		", date="+date+
        		", shipped="+shipped+
        		", trackingNumber="+trackingNumber+
        		", inventoryId="+inventoryId+
        		", orderCost="+orderCost;
        		
    }
}