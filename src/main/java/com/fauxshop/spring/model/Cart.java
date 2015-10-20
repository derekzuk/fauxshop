package com.fauxshop.spring.model;
 
import java.io.Serializable;
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
@Table(name="cart")
public class Cart implements Serializable {
	
	private static final long serialVersionUID = 6182390898687671093L;
 
    @Id
    @Column(name="CART_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int cartId;
     
    @Column(name="ACCOUNT_ID")
    private int accountId;
    
    @Column(name="SESSION_ID")
    private String sessionId;    
    
    @Column(name="INVENTORY_DETAIL_ID")
    private int inventoryDetailId;
    
    @Column(name="QUANTITY")
    private int quantity;
    
    @Column(name="PRICE_PER_ITEM")
    private BigDecimal pricePerItem;
    
    @Column(name="SHIPPING_COST")
    private BigDecimal shippingCost;
    
    @Column(name="TAX")
    private BigDecimal tax;      
    
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
 
    public String getSessionId() {
        return sessionId;
    }
 
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
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
    
    public BigDecimal getPricePerItem() {
    	return pricePerItem;
    }
    
    public void setPricePerItem(BigDecimal pricePerItem) {
    	this.pricePerItem = pricePerItem;
    }
    
    public BigDecimal getShippingCost() {
    	return shippingCost;
    }
    
    public void setShippingCost(BigDecimal shippingCost) {
    	this.shippingCost = shippingCost;
    }
    
    public BigDecimal getTax() {
    	return tax;
    }
    
    public void setTax(BigDecimal tax) {
    	this.tax = tax;
    }
    
    @Override
    public String toString(){
        return "cartId="+cartId+
        		", accountId="+accountId+
        		", sessionId="+sessionId+
        		", inventoryDetailId="+inventoryDetailId+
        		", quantity="+quantity+
        		", pricePerItem="+pricePerItem+
        		", shippingCost="+shippingCost+
        		", tax="+tax;
    }
    
	/*We override the equals and hashCode methods for use in unit and integration testing. This still needs work:*/
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cart))
            return false;
        Cart other = (Cart) o;
        return (other.cartId == cartId &&
               other.accountId == accountId &&
               other.sessionId == sessionId &&
               other.inventoryDetailId == inventoryDetailId &&
               other.quantity == quantity &&
               other.pricePerItem == pricePerItem &&
               other.shippingCost == shippingCost &&
               other.tax == tax);
    }
    
    @Override
     public int hashCode() {
       final int prime = 31;
       int result = 1;
       result = prime * result + (int) (cartId ^ (cartId >>> 32));
       result = prime * result + (int) (accountId ^ (accountId >>> 32));
       result = prime * result
               + ((sessionId == null) ? 0 : sessionId.hashCode());
       result = prime * result + (int) (inventoryDetailId ^ (inventoryDetailId >>> 32));
       result = prime * result + (int) (quantity ^ (quantity >>> 32));
       result = prime * result
               + ((pricePerItem == null) ? 0 : pricePerItem.hashCode());
       result = prime * result
               + ((shippingCost == null) ? 0 : shippingCost.hashCode());
       result = prime * result
               + ((tax == null) ? 0 : tax.hashCode());       
       return result;
     }    
}
