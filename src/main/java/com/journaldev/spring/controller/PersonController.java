package com.journaldev.spring.controller;
 
import java.math.BigDecimal;

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

import com.journaldev.spring.model.InventoryDetail;
import com.journaldev.spring.model.Person;
import com.journaldev.spring.service.PersonService;
import com.journaldev.spring.model.Inventory;
import com.journaldev.spring.service.InventoryService;
import com.journaldev.spring.service.InventoryDetailService;
import com.journaldev.spring.model.Account;
import com.journaldev.spring.service.AccountService;
import com.journaldev.spring.model.Cart;
import com.journaldev.spring.service.CartService;
 
@Controller
public class PersonController {

	private PersonService personService;
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
    
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String listCategories(Model model) {
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
    	
    	model.addAttribute("cartService", this.cartService);
    	model.addAttribute("inventoryService", this.inventoryService);
    	model.addAttribute("inventoryDetailService", this.inventoryDetailService);
    	model.addAttribute("inventory", new Inventory());
    	model.addAttribute("leatherJacket", this.inventoryService.getInventoryById(id));
    	model.addAttribute("leatherJacketDetail", this.inventoryDetailService.getInventoryDetailByInventoryId(id));
    	
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


    @Autowired(required=true)
    @Qualifier(value="personService")
    public void setPersonService(PersonService ps){
        this.personService = ps;
    }    
     
    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String listPersons(Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("listPersons", this.personService.listPersons());
        return "person";
    }     
     
    //For add and update person both
    @RequestMapping(value= "/person/add", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("person") Person p){
         
        if(p.getId() == 0){
            //new person, add it
            this.personService.addPerson(p);
        }else{
            //existing person, call update
            this.personService.updatePerson(p);
        }
         
        return "redirect:/persons";
         
    }
     
    @RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id){
         
        this.personService.removePerson(id);
        return "redirect:/persons";
    }
  
    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", this.personService.getPersonById(id));
        model.addAttribute("listPersons", this.personService.listPersons());
        return "person";
    }
     
}