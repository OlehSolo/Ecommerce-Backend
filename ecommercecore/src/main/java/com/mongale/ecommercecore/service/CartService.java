package com.mongale.ecommercecore.service;

import java.util.List;
import java.util.Map;

import org.springframework.validation.annotation.Validated;

import com.mongale.ecommercecore.model.Cart;
import com.mongale.ecommercecore.model.CartLine;
import com.mongale.ecommercecore.model.Order;
import com.mongale.ecommercecore.model.Product;

@Validated
public interface CartService {
	
	public List<CartLine> list(long cartId);
	public CartLine get(int id);	
	public boolean add(CartLine cartLine);
	public boolean update(CartLine cartLine);
	public boolean remove(CartLine cartLine);
	

	public CartLine getByCartAndProduct(long cartId, long productId);		
		

	boolean updateCart(Cart cart);
	
	
	public List listAvailable(int cartId);
	
	
	boolean addOrder(Order order);

}
