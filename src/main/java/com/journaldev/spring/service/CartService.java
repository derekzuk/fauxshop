package com.journaldev.spring.service;
 
import java.util.List;
 
import com.journaldev.spring.model.Cart;
 
public interface CartService {
    
    public void addCart(Cart c);
    public void updateCart(Cart c);
    public List<Cart> listCarts();
    public Cart getCartById(int id);
    public void removeCart(int id);
     
}