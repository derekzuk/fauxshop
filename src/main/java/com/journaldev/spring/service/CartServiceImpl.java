package com.journaldev.spring.service;
 
import java.util.List;
 


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 


import com.journaldev.spring.dao.CartDAO;
import com.journaldev.spring.model.Account;
import com.journaldev.spring.model.Cart;
 
@Service
public class CartServiceImpl implements CartService {
     
    private CartDAO cartDAO;
    
    public void setCartDAO(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }
 
    /*@Override*/
    @Transactional
    public void addCart(Cart c) {
        this.cartDAO.addCart(c);
    }
 
    /*@Override*/
    @Transactional
    public void updateCart(Cart c) {
        this.cartDAO.updateCart(c);
    }
 
    /*@Override*/
    @Transactional
    public List<Cart> listCarts() {
        return this.cartDAO.listCarts();
    }
 
    /*@Override*/
    @Transactional
    public Cart getCartById(int id) {
        return this.cartDAO.getCartById(id);
    }
 
    /*@Override*/
    @Transactional
    public void removeCart(int cartId) {
        this.cartDAO.removeCart(cartId);
    }
    
    /*@Override*/
    @Transactional
    public List<Cart> getCartByUserLogin(String name) {
        return this.cartDAO.getCartByUserLogin(name);
    }   
    
    /*@Override*/
    @Transactional
    public int getCartItemCostByUserLogin(String name) {
        return this.cartDAO.getCartItemCostByUserLogin(name);
    }
    
    /*@Override*/
    @Transactional
    public int getCartShippingCostByUserLogin(String name) {
        return this.cartDAO.getCartShippingCostByUserLogin(name);
    }  
    
    /*@Override*/
    @Transactional
    public int getCartTaxCostByUserLogin(String name) {
        return this.cartDAO.getCartTaxCostByUserLogin(name);
    }         
    
    /*@Override*/
    @Transactional
    public int getCartTotalByUserLogin(String name) {
        return this.cartDAO.getCartTotalByUserLogin(name);
    }         
        
}