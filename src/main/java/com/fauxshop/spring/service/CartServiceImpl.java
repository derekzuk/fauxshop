package com.fauxshop.spring.service;
 
import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fauxshop.spring.dao.CartDAO;
import com.fauxshop.spring.model.Cart;
 
@Service
public class CartServiceImpl implements CartService {
     
    private CartDAO cartDAO;
    
    public void setCartDAO(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }
    
    /*@Override*/
    @Transactional
    public void save(Cart c) {
        this.cartDAO.save(c);
    }           
    
    /*@Override*/
    @Transactional
    public Cart getCartByIdAndAccountId(int cartId, int accountId) {
        return this.cartDAO.getCartByIdAndAccountId(cartId, accountId);
    }
    
    /*@Override*/
    @Transactional
    public Cart getCartByIdAndSessionId(int cartId, String sessionId) {
        return this.cartDAO.getCartByIdAndSessionId(cartId, sessionId);
    }    
    
    /*@Override*/
    @Transactional
    public Cart getCartByInventoryDetailIdAndAccountId(int inventoryDetailId, int accountId) {
        return this.cartDAO.getCartByInventoryDetailIdAndAccountId(inventoryDetailId, accountId);
    }
    
    /*@Override*/
    @Transactional
    public Cart getCartByInventoryDetailIdAndSessionId(int inventoryDetailId, String sessionId) {
        return this.cartDAO.getCartByInventoryDetailIdAndSessionId(inventoryDetailId, sessionId);
    }        
    
    /*@Override*/
    public void addToCart(int accountId,int inventoryId,int quantity, String pricePerItem,String shippingCost, String tax) {
    	this.cartDAO.addToCart(accountId,inventoryId,quantity,pricePerItem,shippingCost,tax);
    }
 
    /*@Override*/
    @Transactional
    public void removeCart(int cartId) {
        this.cartDAO.removeCart(cartId);
    }
    
    /*@Override*/
    @Transactional
    public void updateQuantity(int cartId, int quantity) {
        this.cartDAO.updateQuantity(cartId, quantity);
    }

    /*@Override*/
    @Transactional
    public boolean removeCartFromCartList(List<Cart> cartList) {
        this.cartDAO.removeCartFromCartList(cartList);
        return true;
    }
    
    /*@Override*/
    @Transactional
    public List<Cart> getCartByUserLogin(String name) {
        return this.cartDAO.getCartByUserLogin(name);
    }   
    
    /*@Override*/
    @Transactional
    public BigDecimal getCartItemCostByUserLogin(String name) {
        return this.cartDAO.getCartItemCostByUserLogin(name);
    }
    
    /*@Override*/
    @Transactional
    public List<Cart> getCartBySessionId(String sessionId) {
        return this.cartDAO.getCartBySessionId(sessionId);
    }    
    
    /*@Override*/
    @Transactional
    public BigDecimal getCartShippingCostByUserLogin(String name) {
        return this.cartDAO.getCartShippingCostByUserLogin(name);
    }  
    
    /*@Override*/
    @Transactional
    public BigDecimal getCartTaxCostByUserLogin(String name) {
        return this.cartDAO.getCartTaxCostByUserLogin(name);
    }         
    
    /*@Override*/
    @Transactional
    public BigDecimal getCartTotalByUserLogin(String name) {
        return this.cartDAO.getCartTotalByUserLogin(name);
    }      
    
    /*@Override*/
    public Long getCartQuantityByUserLogin(String name) {
        return this.cartDAO.getCartQuantityByUserLogin(name);
    }                 
    
    /*@Override*/
    @Transactional
    public BigDecimal getCartItemCostBySessionId(String sessionId) {
        return this.cartDAO.getCartItemCostBySessionId(sessionId);
    }
    
    /*@Override*/
    @Transactional
    public BigDecimal getCartShippingCostBySessionId(String sessionId) {
        return this.cartDAO.getCartShippingCostBySessionId(sessionId);
    }  
    
    /*@Override*/
    @Transactional
    public BigDecimal getCartTaxCostBySessionId(String sessionId) {
        return this.cartDAO.getCartTaxCostBySessionId(sessionId);
    }         
    
    /*@Override*/
    @Transactional
    public BigDecimal getCartTotalBySessionId(String sessionId) {
        return this.cartDAO.getCartTotalBySessionId(sessionId);
    }       
    
    /*@Override*/
    public Long getCartQuantityBySessionId(String name) {
        return this.cartDAO.getCartQuantityBySessionId(name);
    }     
        
}