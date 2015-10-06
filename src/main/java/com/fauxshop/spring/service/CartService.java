package com.fauxshop.spring.service;
 
import java.math.BigDecimal;
import java.util.List;
 







import com.fauxshop.spring.model.Cart;
 
public interface CartService {
    
	public void save(Cart c);
    public void updateCart(Cart c);
    public List<Cart> listCarts();
    public Cart getCartById(int id);
    public Cart getCartByIdAndAccountId(int cartId, int accountId);
    public Cart getCartByIdAndSessionId(int cartId, String sessionId);
    public Cart getCartByInventoryDetailIdAndAccountId(int inventoryDetailId, int accountId);
    public Cart getCartByInventoryDetailIdAndSessionId(int inventoryDetailId, String sessionId);
    public void addToCart(int accountId,int inventoryId,int quantity, String pricePerItem,String shippingCost,String tax);
    public void removeCart(int cartId);
    public void updateQuantity(int cartId, int quantity);
    public boolean removeCartFromCartList(List<Cart> cartList);
    
    /*By User Login:*/
    public List<Cart> getCartByUserLogin(String name);
    public BigDecimal getCartItemCostByUserLogin(String name);
    public BigDecimal getCartShippingCostByUserLogin(String name);
    public BigDecimal getCartTaxCostByUserLogin(String name);    
    public BigDecimal getCartTotalByUserLogin(String name);
    
    /*By Session Id:*/
    public List<Cart> getCartBySessionId(String sessionId);    
    public BigDecimal getCartItemCostBySessionId(String sessionId);
    public BigDecimal getCartShippingCostBySessionId(String sessionId);
    public BigDecimal getCartTaxCostBySessionId(String sessionId);    
    public BigDecimal getCartTotalBySessionId(String sessionId);    
     
}