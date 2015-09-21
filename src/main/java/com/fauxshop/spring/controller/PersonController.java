package com.fauxshop.spring.controller;
 
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.context.spi.CurrentSessionContext;
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
 

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fauxshop.spring.model.InventoryDetail;
import com.fauxshop.spring.model.Inventory;
import com.fauxshop.spring.service.InventoryService;
import com.fauxshop.spring.service.InventoryDetailService;
import com.fauxshop.spring.model.Account;
import com.fauxshop.spring.service.AccountService;
import com.fauxshop.spring.model.Cart;
import com.fauxshop.spring.service.CartService;
 
@Controller
public class PersonController {

    private InventoryService inventoryService;
    private InventoryDetailService inventoryDetailService;
    private AccountService accountService;
    private CartService cartService;
    
	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
    
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
    
    /*Maps to welcome page*/
    @RequestMapping(value={"/", "index"}, method = RequestMethod.GET)
    public String listWelcomePage(Model model) {
    	model.addAttribute("cartService", this.cartService);
    	model.addAttribute("inventory", new Inventory());
    	model.addAttribute("listInventory", this.inventoryService.listInventory());
    	model.addAttribute("leatherJacket", this.inventoryService.getInventoryById(-111));
    	model.addAttribute("pleatherShirt", this.inventoryService.getInventoryById(-112));  
    	model.addAttribute("pleatherPants", this.inventoryService.getInventoryById(-113));
    	model.addAttribute("hempShirt", this.inventoryService.getInventoryById(-114));  

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
    	model.addAttribute("cartService", this.cartService);
    	
    	if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString() != "anonymousUser") {
    		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		String name = user.getUsername(); //get logged in username
    		model.addAttribute("account", new Account());
    		model.addAttribute("currentUser", this.accountService.getAccountByName(name));
    	} else {
    		model.addAttribute("currentUser", "No User Logged In");
    	}    	
        return "about";
    } 

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String listAccount(Model model) {
    	model.addAttribute("cartService", this.cartService);
    	model.addAttribute("inventoryService", this.inventoryService);
    	model.addAttribute("inventoryDetailService", this.inventoryDetailService);
    	
    	// If no user is logged in, then the view will display accordingly.
    	if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString() != "anonymousUser") {
    		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		String name = user.getUsername(); //get logged in username
    		model.addAttribute("account", new Account());
    		model.addAttribute("accountService", this.accountService);
    		model.addAttribute("currentUser", this.accountService.getAccountByName(name));
    	} else {
    		model.addAttribute("currentUser", "No User Logged In");
    	}
    	return "account";
    }    
    
    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String listCart(Model model) {   	
        return "redirect:cart.do";
    }
    
    @RequestMapping("/cartRemove/{cartId}")
    public String removeFromCart(@PathVariable(value="cartId") int cartId) {   	        	
    		this.cartService.removeCart(cartId);	    	
        return "redirect:/cart";
    }         
    
    @RequestMapping(value = "/categories/{inventoryCatCd}", method = RequestMethod.GET)
    public String listCategories(@PathVariable("inventoryCatCd") int inventoryCatCd,
    		Model model) {
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
    	} else {
    		model.addAttribute("currentUser", "No User Logged In");
    	}    	    	
        return "categories";
    }
    
    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public String listConfirm(Model model) {
    	model.addAttribute("cartService", this.cartService);
    	model.addAttribute("inventoryService", this.inventoryService);
    	model.addAttribute("inventoryDetailService", this.inventoryDetailService);
    	
    	if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString() != "anonymousUser") {
    		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		String name = user.getUsername(); //get logged in username
    		model.addAttribute("account", new Account());
    		model.addAttribute("currentUser", this.accountService.getAccountByName(name));
    	} else {
    		model.addAttribute("currentUser", "No User Logged In");
    	}    	    	
        return "confirm";
    }
    
    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String listContact(Model model) {
    	model.addAttribute("cartService", this.cartService);
    	
    	if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString() != "anonymousUser") {
    		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		String name = user.getUsername(); //get logged in username
    		model.addAttribute("account", new Account());
    		model.addAttribute("currentUser", this.accountService.getAccountByName(name));
    	} else {
    		model.addAttribute("currentUser", "No User Logged In");
    	}    	    	
        return "contact";
    }
    
    @RequestMapping(value = "/invoice", method = RequestMethod.GET)
    public String listInvoice(Model model) {
    	model.addAttribute("cartService", this.cartService);
    	model.addAttribute("inventoryService", this.inventoryService);
    	model.addAttribute("inventoryDetailService", this.inventoryDetailService);
    	
    	if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString() != "anonymousUser") {
    		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		String name = user.getUsername(); //get logged in username
    		model.addAttribute("account", new Account());
    		model.addAttribute("currentUser", this.accountService.getAccountByName(name));
    	} else {
    		model.addAttribute("currentUser", "No User Logged In");
    	}    	    	
        return "invoice";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String listLogin(Model model) {   	
        return "redirect:login.do";
    }   
    
    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    public String listPayment(Model model) {
    	model.addAttribute("cartService", this.cartService);
    	model.addAttribute("inventoryService", this.inventoryService);
    	model.addAttribute("inventoryDetailService", this.inventoryDetailService);
    	
    	if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString() != "anonymousUser") {
    		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		String name = user.getUsername(); //get logged in username
    		model.addAttribute("account", new Account());
    		model.addAttribute("currentUser", this.accountService.getAccountByName(name));
    	} else {
    		model.addAttribute("currentUser", "No User Logged In");
    	}    	            
        return "payment";
    }      
    
    /*@RequestMapping(value = "/product_detail/${id}", method = {RequestMethod.POST,RequestMethod.GET})*/
    @RequestMapping("/product_detail/{id}")
    public String listProductDetail(@PathVariable("id") int id, 
    		@ModelAttribute("InventoryDetail") InventoryDetail invdet,
    		Model model,
    		HttpServletRequest request) {
    	
    	/*We get a unique list of colors and sizes for the inventoryId:*/
    	List<InventoryDetail> inventoryList = this.inventoryDetailService.getInventoryDetailByInventoryId(id);
   	 	List<String> colorList = new ArrayList<String>();
   	 	List<String> sizeList = new ArrayList<String>();    
    	
    	int i = 0;
    	do{
    	 String color = inventoryList.get(i).getColor();
    	 String size = inventoryList.get(i).getSize();
    	 colorList.add(color);
    	 sizeList.add(size);
    	 i++;
    	}
    	while (i < inventoryList.size());
    	/*LinkedHashSet does not allow for duplicate values, so we run the list through a LinkedHashSet:*/
    	colorList = new ArrayList<String>(new LinkedHashSet<String>(colorList));
    	sizeList = new ArrayList<String>(new LinkedHashSet<String>(sizeList));    
    	
    	model.addAttribute("cartService", this.cartService);
    	model.addAttribute("inventoryService", this.inventoryService);
    	model.addAttribute("inventoryDetailService", this.inventoryDetailService);
    	model.addAttribute("inventory", new Inventory());
    	model.addAttribute("leatherJacket", this.inventoryService.getInventoryById(id));
    	model.addAttribute("leatherJacketDetail", this.inventoryDetailService.getInventoryDetailByInventoryId(id));
    	model.addAttribute("colorList", colorList);
    	model.addAttribute("sizeList", sizeList);
    	
    	if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString() != "anonymousUser") {
    		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		String name = user.getUsername(); //get logged in username
    		model.addAttribute("account", new Account());
    		model.addAttribute("currentUser", this.accountService.getAccountByName(name));
    	} else {
    		model.addAttribute("currentUser", "No User Logged In");
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
    	} else {
    		return "redirect:/login";
    	}    	    	
        return "redirect:/cart";
    }    
    
    @RequestMapping(value = "/shipping", method = RequestMethod.GET)
    public String listShipping(Model model) {
    	model.addAttribute("cartService", this.cartService);
    	model.addAttribute("inventoryService", this.inventoryService);
    	model.addAttribute("inventoryDetailService", this.inventoryDetailService);
    	
    	if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString() != "anonymousUser") {
    		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		String name = user.getUsername(); //get logged in username
    		model.addAttribute("account", new Account());
    		model.addAttribute("currentUser", this.accountService.getAccountByName(name));
    	} else {
    		model.addAttribute("currentUser", "No User Logged In");
    	}    	    	
        return "shipping";
    }
     
}