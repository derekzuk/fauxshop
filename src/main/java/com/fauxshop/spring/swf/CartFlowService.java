package com.fauxshop.spring.swf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.fauxshop.spring.model.Cart;
import com.fauxshop.spring.model.Inventory;
import com.fauxshop.spring.model.InventoryDetail;
import com.fauxshop.spring.service.AccountService;
import com.fauxshop.spring.service.CartService;
import com.fauxshop.spring.service.InventoryDetailService;
import com.fauxshop.spring.service.InventoryService;

/*At the moment, this class is not used. 
The intention was to use it to remove the inventoryService and inventoryDetailService calls out of the cart views.
My suspicion is there is a better way to do that.*/
public class CartFlowService implements Serializable {
	private static final Logger logger = LoggerFactory.getLogger(AccountValidation.class);

	private static final long serialVersionUID = 1L;

	public HashMap<Cart, HashMap<Inventory, InventoryDetail>> getCartInventoryHashMap(UsernamePasswordAuthenticationToken name, 
			AccountService accountService, 
			CartService cartService, 
			InventoryService inventoryService, 
			InventoryDetailService inventoryDetailService) {

		HashMap<Cart, HashMap<Inventory, InventoryDetail>> cartInventoryHashMap = new HashMap<Cart, HashMap<Inventory, InventoryDetail>>();
		HashMap<Inventory, InventoryDetail> inventoryHashMap = new HashMap<Inventory, InventoryDetail>();
		List<Cart> cartList = new ArrayList<Cart>();


		cartList = cartService.getCartByUserLogin(name.getName());

		for (int i=0; i < cartList.size(); i++) {
			Inventory inventory = inventoryService.getInventoryByInventoryDetailId(cartList.get(i).getInventoryDetailId());			
			InventoryDetail inventoryDetail = inventoryDetailService.getInventoryDetailByInventoryDetailId(cartList.get(i).getInventoryDetailId());
			inventoryHashMap.put(inventory, inventoryDetail);			
			cartInventoryHashMap.put(cartList.get(i), inventoryHashMap);
		}

		return cartInventoryHashMap;
	}

	public HashMap<Cart, HashMap<Inventory, InventoryDetail>> getCartInventoryHashMapBySessionId(String sessionId, 
			AccountService accountService, 
			CartService cartService, 
			InventoryService inventoryService, 
			InventoryDetailService inventoryDetailService) {

		HashMap<Cart, HashMap<Inventory, InventoryDetail>> cartInventoryHashMap = new HashMap<Cart, HashMap<Inventory, InventoryDetail>>();
		HashMap<Inventory, InventoryDetail> inventoryHashMap = new HashMap<Inventory, InventoryDetail>();
		List<Cart> cartList = new ArrayList<Cart>();

		cartList = cartService.getCartBySessionId(sessionId);

		for (int i=0; i < cartList.size(); i++) {
			Inventory inventory = inventoryService.getInventoryByInventoryDetailId(cartList.get(i).getInventoryDetailId());
			InventoryDetail inventoryDetail = inventoryDetailService.getInventoryDetailByInventoryDetailId(cartList.get(i).getInventoryDetailId());
			inventoryHashMap.put(inventory, inventoryDetail);			
			cartInventoryHashMap.put(cartList.get(i), inventoryHashMap);
		}

		return cartInventoryHashMap;
	}

}