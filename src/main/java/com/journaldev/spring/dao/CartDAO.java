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
    public void removeCart(int cartId);
    public List<Cart> getCartByUserLogin(String name);
    public int getCartItemCostByUserLogin(String name);
    public int getCartShippingCostByUserLogin(String name);
    public int getCartTaxCostByUserLogin(String name);
    public int getCartTotalByUserLogin(String name);
}