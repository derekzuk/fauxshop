package com.fauxshop.spring.controller;
 
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;

import com.fauxshop.spring.model.InventoryDetail;
import com.fauxshop.spring.model.Inventory;
import com.fauxshop.spring.model.TransactionLog;
import com.fauxshop.spring.service.InventoryService;
import com.fauxshop.spring.service.InventoryDetailService;
import com.fauxshop.spring.service.TransactionService;
import com.fauxshop.spring.model.Account;
import com.fauxshop.spring.service.AccountService;
import com.fauxshop.spring.model.Cart;
import com.fauxshop.spring.service.CartService;
 
@Controller
public class ViewController {

    private InventoryService inventoryService;
    private InventoryDetailService inventoryDetailService;
    private AccountService accountService;
    private CartService cartService;
    private TransactionService transactionService;
    
	private static final Logger logger = LoggerFactory.getLogger(ViewController.class);
    
    @Autowired(required=true)
    @Qualifier(value="inventoryService")
    public void setInventoryService(InventoryService is){
        this.inventoryService = is;
    }
    
    @Autowired(required=true)
    @Qualifier(value="inventoryDetailService")
    public void setInventoryDetailService(InventoryDetailService ids){
        this.inventoryDetailService = ids;
    }       
    
    @Autowired(required=true)
    @Qualifier(value="accountService")
    public void setAccountService(AccountService as){
        this.accountService = as;
    } 
    
    @Autowired(required=true)
    @Qualifier(value="cartService")
    public void setCartService(CartService cs){
        this.cartService = cs;
    }  
    
    @Autowired(required=true)
    @Qualifier(value="transactionService")
    public void setTransactionService(TransactionService ts){
        this.transactionService = ts;
    }         
    
    /*Maps to welcome page*/
    @RequestMapping(value={"/", "index"}, method = RequestMethod.GET)
    public String listWelcomePage(Model model) {
    	List<Inventory> bestSellerInventoryList = this.inventoryService.getBestSellerInventoryList();
    	model.addAttribute("cartService", this.cartService);
    	model.addAttribute("inventoryService", this.inventoryService);
    	model.addAttribute("inventoryDetailService", this.inventoryDetailService);
    	model.addAttribute("bestSellerInventoryList", this.inventoryService.getBestSellerInventoryList());
    	model.addAttribute("inventory", new Inventory());    	
    	for (int i = 0; i < bestSellerInventoryList.size(); i++) {
    		String inventoryAttributeName = "inventory" + i;
    		Inventory inventoryAttributeValue = this.inventoryService.getInventoryById(bestSellerInventoryList.get(i).getInventoryId());
    		model.addAttribute(inventoryAttributeName,inventoryAttributeValue);
    		String inventoryDetailAttributeName = "inventoryDetail" + i;
    		List<InventoryDetail> inventoryDetailAttributeValue = this.inventoryDetailService.getInventoryDetailByInventoryId(bestSellerInventoryList.get(i).getInventoryId());
    		model.addAttribute(inventoryDetailAttributeName,inventoryDetailAttributeValue);
    	}

    	// If no user is logged in, then the view will display accordingly.
    	if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString() != "anonymousUser") {
    		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		String name = user.getUsername(); //get logged in username
    		model.addAttribute("account", new Account());
    		model.addAttribute("currentUser", this.accountService.getAccountByName(name));
    	} else {
    		model.addAttribute("currentUser", "No User Logged In");
    	}
    	
    	return "index";
    }
    
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String listAbout(Model model) {
    	String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();    
    	model.addAttribute("cartService", this.cartService);
    	
    	if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString() != "anonymousUser") {
    		
    		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		String name = user.getUsername(); //get logged in username
    		model.addAttribute("currentUser", this.accountService.getAccountByName(name));
    		model.addAttribute("cart", cartService.getCartByUserLogin(name));
    		model.addAttribute("cartItemList", cartService.getCartItemCostByUserLogin(name));
    		model.addAttribute("cartShippingCost", cartService.getCartShippingCostByUserLogin(name));
    		model.addAttribute("cartTaxCost", cartService.getCartTaxCostByUserLogin(name));
    		model.addAttribute("cartTotalCost", cartService.getCartTotalByUserLogin(name));
    	} else {
    		model.addAttribute("currentUser", "No User Logged In");
    		model.addAttribute("cartSession", cartService.getCartBySessionId(sessionId));
    		model.addAttribute("cartItemList", cartService.getCartItemCostBySessionId(sessionId));    		
    		model.addAttribute("cartShippingCost", cartService.getCartShippingCostBySessionId(sessionId));
    		model.addAttribute("cartTaxCost", cartService.getCartTaxCostBySessionId(sessionId));
    		model.addAttribute("cartTotalCost", cartService.getCartTotalBySessionId(sessionId));    		
    	}    	
        return "about";
    } 

    @RequestMapping(value = "/account", method = {RequestMethod.POST,RequestMethod.GET})
    public String listAccount(Model model) {
        return "redirect:account.do";
    }    
    
    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String listCart(Model model) {   	
        return "redirect:cart.do";
    }
    
    @RequestMapping(value = "/cart/updateQuantity/{cartId}", method = {RequestMethod.POST,RequestMethod.GET})
    public String updateQuantity(@PathVariable("cartId") int cartId,
    		HttpServletRequest request) { 

	    this.cartService.updateQuantity(cartId, Integer.parseInt(request.getParameter("quantity")));
    	
	    return "redirect:/cart";
    }    
    
    @RequestMapping("/cartRemove/{cartId}")
    public String removeFromCart(@PathVariable(value="cartId") int cartId,
    		Model model) {
    	if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString() != "anonymousUser") {
    		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		String name = user.getUsername(); //get logged in username
    		int accountId = this.accountService.getAccountByName(name).getAccountId();    
    		Cart cartItemToBeRemoved = this.cartService.getCartByIdAndAccountId(cartId, accountId);
    		
    		/*The item is only removed if both the cartId and accountId provided are valid.*/
        	if (null != cartItemToBeRemoved) {
        		this.cartService.removeCart(cartId);
        	}
    	} else {
    		String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();    
    		Cart cartItemToBeRemoved = this.cartService.getCartByIdAndSessionId(cartId, sessionId);
    		
    		/*The item is only removed if both the cartId and sessionId provided are valid.*/
        	if (null != cartItemToBeRemoved) {
        		this.cartService.removeCart(cartId);
        	};    		
    	}   
    	return "redirect:/cart";    		
    }         
    
    @RequestMapping(value = "/categories/{inventoryCatCd}", method = RequestMethod.GET)
    public String listCategories(@PathVariable("inventoryCatCd") int inventoryCatCd,
    		Model model) {
    	String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();    	
    	model.addAttribute("cartService", this.cartService);
    	model.addAttribute("inventoryService", this.inventoryService);
    	model.addAttribute("inventoryDetailService", this.inventoryDetailService);
    	model.addAttribute("inventoryCatCd", inventoryCatCd);
    	model.addAttribute("inventoryList", this.inventoryService.getInventoryListByInventoryCatCd(inventoryCatCd));
    	model.addAttribute("inventoryCategoryCode", this.inventoryService.getInventoryCategoryCode(inventoryCatCd));
    	
    	if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString() != "anonymousUser") {
    		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		String name = user.getUsername(); //get logged in username
    		model.addAttribute("account", new Account());
    		model.addAttribute("currentUser", this.accountService.getAccountByName(name));
    		model.addAttribute("cart", cartService.getCartByUserLogin(name));    		
    	} else {
    		model.addAttribute("currentUser", "No User Logged In");
    		model.addAttribute("cartSession", cartService.getCartBySessionId(sessionId));
    	}    	    	
        return "categories";
    }
    
    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public String listConfirm(Model model) {
    	String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
    	model.addAttribute("cartService", this.cartService);
    	model.addAttribute("inventoryService", this.inventoryService);
    	model.addAttribute("inventoryDetailService", this.inventoryDetailService);
    	
    	if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString() != "anonymousUser") {
    		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		String name = user.getUsername(); //get logged in username
    		model.addAttribute("account", new Account());
    		model.addAttribute("currentUser", this.accountService.getAccountByName(name));
    		model.addAttribute("cart", cartService.getCartByUserLogin(name));    		
    		model.addAttribute("cartItemList", cartService.getCartItemCostByUserLogin(name));
    		model.addAttribute("cartShippingCost", cartService.getCartShippingCostByUserLogin(name));
    		model.addAttribute("cartTaxCost", cartService.getCartTaxCostByUserLogin(name));
    		model.addAttribute("cartTotalCost", cartService.getCartTotalByUserLogin(name));
    	} else {
    		model.addAttribute("currentUser", "No User Logged In");
    		model.addAttribute("cartSession", cartService.getCartBySessionId(sessionId));
    		model.addAttribute("cartItemList", cartService.getCartItemCostBySessionId(sessionId));    		
    		model.addAttribute("cartShippingCost", cartService.getCartShippingCostBySessionId(sessionId));
    		model.addAttribute("cartTaxCost", cartService.getCartTaxCostBySessionId(sessionId));
    		model.addAttribute("cartTotalCost", cartService.getCartTotalBySessionId(sessionId));
    	}    	    	
        return "confirm";
    }
    
    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String listContact(Model model) {
    	String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();    	
    	model.addAttribute("cartService", this.cartService);
    	
    	if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString() != "anonymousUser") {
    		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		String name = user.getUsername(); //get logged in username
    		model.addAttribute("account", new Account());
    		model.addAttribute("currentUser", this.accountService.getAccountByName(name));
    		model.addAttribute("cart", cartService.getCartByUserLogin(name));    		
    		model.addAttribute("cartItemList", cartService.getCartItemCostByUserLogin(name));
    		model.addAttribute("cartShippingCost", cartService.getCartShippingCostByUserLogin(name));
    		model.addAttribute("cartTaxCost", cartService.getCartTaxCostByUserLogin(name));
    		model.addAttribute("cartTotalCost", cartService.getCartTotalByUserLogin(name));
    	} else {
    		model.addAttribute("currentUser", "No User Logged In");
    		model.addAttribute("cartSession", cartService.getCartBySessionId(sessionId));
    		model.addAttribute("cartItemList", cartService.getCartItemCostBySessionId(sessionId));    		
    		model.addAttribute("cartShippingCost", cartService.getCartShippingCostBySessionId(sessionId));
    		model.addAttribute("cartTaxCost", cartService.getCartTaxCostBySessionId(sessionId));
    		model.addAttribute("cartTotalCost", cartService.getCartTotalBySessionId(sessionId));
    	}    	    	
        return "contact";
    }
    
    @RequestMapping(value = "/invoice", method = RequestMethod.GET)
    public String listInvoice(Model model) {
    	String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();    	
    	model.addAttribute("cartService", this.cartService);
    	model.addAttribute("inventoryService", this.inventoryService);
    	model.addAttribute("inventoryDetailService", this.inventoryDetailService);
    	
    	if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString() != "anonymousUser") {
    		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		String name = user.getUsername(); //get logged in username
    		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
    		Account currentUser = this.accountService.getAccountByName(name);
    		TransactionLog lastTransaction = this.transactionService.getLastTransactionByAccountId(this.accountService.getAccountByName(name).getAccountId());
    		String date = dt.format(lastTransaction.getDate());
    		
    		model.addAttribute("account", new Account());
    		model.addAttribute("currentUser", currentUser);
        	model.addAttribute("lastTransaction", lastTransaction);
        	model.addAttribute("date", date);
    		model.addAttribute("cart", cartService.getCartByUserLogin(name));    		
    		model.addAttribute("cartItemList", cartService.getCartItemCostByUserLogin(name));
    		model.addAttribute("cartShippingCost", cartService.getCartShippingCostByUserLogin(name));
    		model.addAttribute("cartTaxCost", cartService.getCartTaxCostByUserLogin(name));
    		model.addAttribute("cartTotalCost", cartService.getCartTotalByUserLogin(name));
    	} else {
    		model.addAttribute("currentUser", "No User Logged In");
    		model.addAttribute("cartSession", cartService.getCartBySessionId(sessionId));
    		model.addAttribute("cartItemList", cartService.getCartItemCostBySessionId(sessionId));    		
    		model.addAttribute("cartShippingCost", cartService.getCartShippingCostBySessionId(sessionId));
    		model.addAttribute("cartTaxCost", cartService.getCartTaxCostBySessionId(sessionId));
    		model.addAttribute("cartTotalCost", cartService.getCartTotalBySessionId(sessionId));
    	}    	    	
        return "invoice";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String listLogin(Model model) {   	
        return "redirect:login.do";
    }   
    
    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    public String listPayment(Model model) {
    	String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();    	
    	model.addAttribute("cartService", this.cartService);
    	model.addAttribute("inventoryService", this.inventoryService);
    	model.addAttribute("inventoryDetailService", this.inventoryDetailService);
    	
    	if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString() != "anonymousUser") {
    		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		String name = user.getUsername(); //get logged in username
    		model.addAttribute("account", new Account());
    		model.addAttribute("currentUser", this.accountService.getAccountByName(name));
    		model.addAttribute("cart", cartService.getCartByUserLogin(name));    		
    		model.addAttribute("cartItemList", cartService.getCartItemCostByUserLogin(name));
    		model.addAttribute("cartShippingCost", cartService.getCartShippingCostByUserLogin(name));
    		model.addAttribute("cartTaxCost", cartService.getCartTaxCostByUserLogin(name));
    		model.addAttribute("cartTotalCost", cartService.getCartTotalByUserLogin(name));
    	} else {
    		model.addAttribute("currentUser", "No User Logged In");
    		model.addAttribute("cartSession", cartService.getCartBySessionId(sessionId));
    		model.addAttribute("cartItemList", cartService.getCartItemCostBySessionId(sessionId));    		
    		model.addAttribute("cartShippingCost", cartService.getCartShippingCostBySessionId(sessionId));
    		model.addAttribute("cartTaxCost", cartService.getCartTaxCostBySessionId(sessionId));
    		model.addAttribute("cartTotalCost", cartService.getCartTotalBySessionId(sessionId));
    	}    	            
        return "payment";
    }          
    
	@RequestMapping("/product_detail/{id}**")
    public String listProductDetail(@PathVariable("id") int id,    		
    		@ModelAttribute("InventoryDetail") InventoryDetail invdet,
    		Model model,
    		HttpServletRequest request) {
    	String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();		
		
		String queryString = (String) request.getQueryString();
	    String colorPath = queryString.replace("color=","");     	    
	        	
    	/*We get a unique list of colors and sizes for the inventoryId:*/
    	List<InventoryDetail> fullInventoryList = this.inventoryDetailService.getInventoryDetailByInventoryId(id); 
    	List<InventoryDetail> inventoryList =  this.inventoryDetailService.getInventoryDetailByIdColor(id, colorPath);
   	 	List<String> sizeList = new ArrayList<String>();    
   	 	List<String> colorList = new ArrayList<String>();
    	
   	 	/*Loops to populate the size and color list:*/
    	int a = 0;
    	do{
    	 String size = inventoryList.get(a).getSize();
    	 sizeList.add(size);
    	 a++;
    	}
    	while (a < inventoryList.size());
    	/*LinkedHashSet does not allow for duplicate values, so we run the list through a LinkedHashSet:*/
    	sizeList = new ArrayList<String>(new LinkedHashSet<String>(sizeList)); 

    	int b = 0;
    	do{
    	 String color = fullInventoryList.get(b).getColor();
    	 colorList.add(color);
    	 b++;
    	}	
    	while (b < fullInventoryList.size());
    	colorList = new ArrayList<String>(new LinkedHashSet<String>(colorList)); 
    	
    	model.addAttribute("cartService", this.cartService);
    	model.addAttribute("inventoryService", this.inventoryService);
    	model.addAttribute("inventoryDetailService", this.inventoryDetailService);
    	model.addAttribute("inventory", new Inventory());
    	model.addAttribute("pleatherJacket", this.inventoryService.getInventoryById(id));
    	model.addAttribute("pleatherJacketDetail", this.inventoryDetailService.getInventoryDetailByInventoryId(id));
    	model.addAttribute("colorList", colorList);
    	model.addAttribute("sizeList", sizeList);
    	
    	if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString() != "anonymousUser") {
    		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		String name = user.getUsername(); //get logged in username
    		model.addAttribute("account", new Account());
    		model.addAttribute("currentUser", this.accountService.getAccountByName(name));
    		model.addAttribute("cart", cartService.getCartByUserLogin(name));    		
    		model.addAttribute("cartItemList", cartService.getCartItemCostByUserLogin(name));
    		model.addAttribute("cartShippingCost", cartService.getCartShippingCostByUserLogin(name));
    		model.addAttribute("cartTaxCost", cartService.getCartTaxCostByUserLogin(name));
    		model.addAttribute("cartTotalCost", cartService.getCartTotalByUserLogin(name));
    	} else {
    		model.addAttribute("currentUser", "No User Logged In");
    		model.addAttribute("cartSession", cartService.getCartBySessionId(sessionId));
    		model.addAttribute("cartItemList", cartService.getCartItemCostBySessionId(sessionId));    		
    		model.addAttribute("cartShippingCost", cartService.getCartShippingCostBySessionId(sessionId));
    		model.addAttribute("cartTaxCost", cartService.getCartTaxCostBySessionId(sessionId));
    		model.addAttribute("cartTotalCost", cartService.getCartTotalBySessionId(sessionId));
    	}    	    	
        return "product_detail";
    }       
    
	@RequestMapping(value = "/product_detail/add/{inventoryId}", method = {RequestMethod.POST,RequestMethod.GET})
    public String addProduct(@PathVariable("inventoryId") int inventoryId,
    		@ModelAttribute("InventoryDetail") InventoryDetail invdet,
    		Model model,
    		HttpServletRequest request) {    	
    	    	
    	/*We need to check if an inventory detail record already exists in the cart for this user. If so, we will add to the quantity.*/   	
    	if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString() != "anonymousUser") {
    		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		String name = user.getUsername(); //get logged in username    		
    		model.addAttribute("account", new Account());
    		model.addAttribute("currentUser", this.accountService.getAccountByName(name));
    		
    		List<Cart> userCartList = this.cartService.getCartByUserLogin(name);    
    		List<Integer> userCartInventoryIdList = new ArrayList<Integer>();    		
    		if (!userCartList.isEmpty()) {
        		int a = 0;
    			do {
    				int inventoryDetailId = userCartList.get(a).getInventoryDetailId();
    				userCartInventoryIdList.add(inventoryDetailId);
    				a++;
    			} 	
    			while (a < userCartList.size());    			
    		}
        	
    		/*If the inventory item already exists in the cart, we add the quantity to the existing record.*/
        	int inventoryDetailId = this.inventoryDetailService.getInventoryDetailByIdColorSize(inventoryId, invdet.getColor(), invdet.getSize()).getInventoryDetailId();
        	int accountId = this.accountService.getAccountByName(name).getAccountId();
    		if (userCartInventoryIdList.contains(inventoryDetailId)) {
    			Cart existingCart = this.cartService.getCartByInventoryDetailIdAndAccountId(inventoryDetailId, accountId);
    			this.cartService.updateQuantity(existingCart.getCartId(), (existingCart.getQuantity() + Integer.parseInt(request.getParameter("quantity"))));
    		/*If the inventory item does not already exist in the user's cart, we create a new cart record.*/    		
    		} else {
    		// We add the selected inventory item to the user's cart.
    		// This method isn't finished. Needs work.
    		Cart newCartRecord = new Cart();
    		Inventory product = this.inventoryService.getInventoryById(inventoryId);
    		newCartRecord.setAccountId(this.accountService.getAccountByName(name).getAccountId());
    		newCartRecord.setInventoryDetailId(inventoryDetailService.getInventoryDetailByIdColorSize(inventoryId, invdet.getColor(), invdet.getSize()).getInventoryDetailId());
    		newCartRecord.setQuantity(Integer.parseInt(request.getParameter("quantity")));
    		newCartRecord.setPricePerItem(product.getPriceUsd());
    		newCartRecord.setShippingCost(BigDecimal.valueOf(22.22));
    		newCartRecord.setTax(BigDecimal.valueOf(11.11));
    		this.cartService.save(newCartRecord);
    		}
    	} else {
    		/*The user is not logged in. In this case, we populate the SESSION_ID column in the CART table with their session id.*/
    		String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
    		
    		List<Cart> userCartList = this.cartService.getCartBySessionId(sessionId);    
    		List<Integer> userCartInventoryIdList = new ArrayList<Integer>();    		
    		if (!userCartList.isEmpty()) {
        		int a = 0;
    			do {
    				int inventoryDetailId = userCartList.get(a).getInventoryDetailId();
    				userCartInventoryIdList.add(inventoryDetailId);
    				a++;
    			} 	
    			while (a < userCartList.size());    			
    		}
        	
    		/*If the inventory item already exists in the cart, we add the quantity to the existing record.*/
        	int inventoryDetailId = this.inventoryDetailService.getInventoryDetailByIdColorSize(inventoryId, invdet.getColor(), invdet.getSize()).getInventoryDetailId();
    		if (userCartInventoryIdList.contains(inventoryDetailId)) {
    			Cart existingCart = this.cartService.getCartByInventoryDetailIdAndSessionId(inventoryDetailId, sessionId);
    			this.cartService.updateQuantity(existingCart.getCartId(), (existingCart.getQuantity() + Integer.parseInt(request.getParameter("quantity"))));
    		/*If the inventory item does not already exist in the user's cart, we create a new cart record.*/    		
    		} else {
    		// We add the selected inventory item to the user's cart.
    		// This method isn't finished. Needs work.
    		Cart newCartRecord = new Cart();
    		Inventory product = this.inventoryService.getInventoryById(inventoryId);
    		newCartRecord.setSessionId(sessionId);
    		newCartRecord.setInventoryDetailId(inventoryDetailService.getInventoryDetailByIdColorSize(inventoryId, invdet.getColor(), invdet.getSize()).getInventoryDetailId());
    		newCartRecord.setQuantity(Integer.parseInt(request.getParameter("quantity")));
    		newCartRecord.setPricePerItem(product.getPriceUsd());
    		newCartRecord.setShippingCost(BigDecimal.valueOf(22.22));
    		newCartRecord.setTax(BigDecimal.valueOf(11.11));
    		this.cartService.save(newCartRecord);
    		}    		    		
    	}    	    	
        return "redirect:/cart";
    }    
    
    @RequestMapping(value = "/shipping", method = RequestMethod.GET)
    public String listShipping(Model model) {
    	String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();    	
    	model.addAttribute("cartService", this.cartService);
    	model.addAttribute("inventoryService", this.inventoryService);
    	model.addAttribute("inventoryDetailService", this.inventoryDetailService);
    	
    	if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString() != "anonymousUser") {
    		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		String name = user.getUsername(); //get logged in username
    		model.addAttribute("account", new Account());
    		model.addAttribute("currentUser", this.accountService.getAccountByName(name));
    		model.addAttribute("cart", cartService.getCartByUserLogin(name));    		
    		model.addAttribute("cartItemList", cartService.getCartItemCostByUserLogin(name));
    		model.addAttribute("cartShippingCost", cartService.getCartShippingCostByUserLogin(name));
    		model.addAttribute("cartTaxCost", cartService.getCartTaxCostByUserLogin(name));
    		model.addAttribute("cartTotalCost", cartService.getCartTotalByUserLogin(name));
    	} else {
    		model.addAttribute("currentUser", "No User Logged In");
    		model.addAttribute("cartSession", cartService.getCartBySessionId(sessionId));
    		model.addAttribute("cartItemList", cartService.getCartItemCostBySessionId(sessionId));    		
    		model.addAttribute("cartShippingCost", cartService.getCartShippingCostBySessionId(sessionId));
    		model.addAttribute("cartTaxCost", cartService.getCartTaxCostBySessionId(sessionId));
    		model.addAttribute("cartTotalCost", cartService.getCartTotalBySessionId(sessionId));
    	}    	    	
        return "shipping";
    }
     
}