package com.journaldev.spring.dao;
 
import java.util.List;
 

import com.journaldev.spring.model.Cart;
 
public interface CartDAO {
 
    public void save(Cart c);    
    public List<Cart> list();
    public void addCart(Cart c);
    public void updateCart(Cart c);
    public List<Cart> listCarts();
    public Cart getCartById(int id);
    public void removeCart(int id);
    public List<Cart> getCartByUserLogin(String name);
}