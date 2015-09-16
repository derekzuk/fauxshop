package com.fauxshop.spring.service;
 
import java.math.BigDecimal;
import java.util.List;
 







import com.fauxshop.spring.model.Cart;
 
public interface CartService {
    
	public void save(Cart c);
    public void updateCart(Cart c);
    public List<Cart> listCarts();
    public Cart getCartById(int id);
    public void addToCart(int accountId,int inventoryId,int quantity, String pricePerItem,String shippingCost,String tax);
    public void removeCart(int cartId);
    public boolean removeCartFromCartList(List<Cart> cartList);
    public List<Cart> getCartByUserLogin(String name);
    public BigDecimal getCartItemCostByUserLogin(String name);
    public BigDecimal getCartShippingCostByUserLogin(String name);
    public BigDecimal getCartTaxCostByUserLogin(String name);    
    public BigDecimal getCartTotalByUserLogin(String name);
     
}