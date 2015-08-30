package com.journaldev.spring.model;
 
import java.io.Serializable;

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
@Table(name="cart")
public class Cart implements Serializable {
	
	private static final long serialVersionUID = 6182390898687671093L;
 
    @Id
    @Column(name="CART_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int cartId;
     
    @Column(name="ACCOUNT_ID")
    private int accountId;
    
    @Column(name="INVENTORY_DETAIL_ID")
    private int inventoryDetailId;
    
    @Column(name="QUANTITY")
    private int quantity;
    
    @Column(name="PRICE_PER_ITEM")
    private String pricePerItem;
    
    @Column(name="SHIPPING_COST")
    private String shippingCost;
    
    @Column(name="TAX")
    private String tax;
    
    public int getCartId() {
        return cartId;
    }
 
    public void setCartId(int cartId) {
        this.cartId = cartId;
    }
 
    public int getAccountId() {
        return accountId;
    }
 
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
 
    public int getInventoryDetailId() {
        return inventoryDetailId;
    }
 
    public void setInventoryDetailId(int inventoryDetailId) {
        this.inventoryDetailId = inventoryDetailId;
    }
    
    public int getQuantity() {
    	return quantity;
    }
    
    public void setQuantity(int quantity) {
    	this.quantity = quantity;
    }
    
    public String getPricePerItem() {
    	return pricePerItem;
    }
    
    public void setPricePerItem(String pricePerItem) {
    	this.pricePerItem = pricePerItem;
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
    
    @Override
    public String toString(){
        return "cartId="+cartId+
        		", accountId="+accountId+
        		", inventoryDetailId="+inventoryDetailId+
        		", quantity="+quantity+
        		", pricePerItem="+pricePerItem+
        		", shippingCost="+shippingCost+
        		", tax="+tax;
    }
}