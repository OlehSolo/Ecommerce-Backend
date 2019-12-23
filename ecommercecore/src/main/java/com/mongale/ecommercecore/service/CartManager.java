package com.mongale.ecommercecore.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongale.ecommercecore.model.Cart;
import com.mongale.ecommercecore.model.CartLine;
import com.mongale.ecommercecore.model.Product;
import com.mongale.ecommercecore.model.User;

@Service
public class CartManager {
	
	@Autowired
	private CartService cartLineService;
	
	//@Autowired
	private ProductService productService;
		
	@Autowired
	private HttpSession session;
	
	public List<CartLine> getCartLines() {

		return cartLineService.list(getCart().getCartId());

	}
public String manageCartLine(int cartLineId, int count) {
		
		CartLine cartLine = cartLineService.get(cartLineId);		

		double oldTotal = cartLine.getTotal();

		
		Product product = cartLine.getProduct();
		
		// check if that much quantity is available or not
		if(product.getQuantity() < count) {
			return "result=unavailable";		
		}	
		
		// update the cart line
		cartLine.setProductCount(count);
		cartLine.setBuyingPrice(product.getProductPrice());
		cartLine.setTotal(product.getProductPrice() * count);
		cartLineService.update(cartLine);

	
		// update the cart
		Cart cart = this.getCart();
		cart.setTotalPrice(cart.getTotalPrice() - oldTotal + cartLine.getTotal());
		cartLineService.updateCart(cart);
		
		return "result=updated";
	}

public String addCartLine(int productId) {		
	Cart cart = this.getCart();
	String response = null;
	CartLine cartLine = cartLineService.getByCartAndProduct(cart.getCartId(), productId);
	if(cartLine==null) {
		
		cartLine = new CartLine();
		Product product = productService.getProduct(productId);
		
		cartLine.setCartId(cart.getCartId());
		cartLine.setProduct(product);
		cartLine.setProductCount(1);
		cartLine.setBuyingPrice(product.getProductPrice());
		cartLine.setTotal(product.getProductPrice());
		
		
		cartLineService.add(cartLine);
		
		
		cart.setTotalPrice(cart.getTotalPrice() + cartLine.getTotal());
		cart.setCartLines(cart.getCartLines() + 1);
		cartLineService.updateCart(cart);

		response = "result=added";						
	} 
	else {
		
		if(cartLine.getProductCount() < 3) {
			
			response = this.manageCartLine(cartLine.getId(), cartLine.getProductCount() + 1);				
		}			
		else {				
			response = "result=maximum";				
		}						
	}		
	return response;
}
private Cart getCart() {
	return ((User)session.getAttribute("user")).getCart();
}

public String removeCartLine(int cartLineId) {
	
	CartLine cartLine = cartLineService.get(cartLineId);
	
	Cart cart = this.getCart();	
	cart.setTotalPrice(cart.getTotalPrice() - cartLine.getTotal());
	cart.setCartLines(cart.getCartLines() - 1);		
	cartLineService.updateCart(cart);
	
	
	cartLineService.remove(cartLine);
			
	return "result=deleted";
}

public String validateCartLine() {
	Cart cart = this.getCart();
	List<CartLine> cartLines = cartLineService.list(cart.getCartId());
	double grandTotal = 0.0;
	int lineCount = 0;
	String response = "result=success";
	boolean changed = false;
	Product product = null;
	for(CartLine cartLine : cartLines) {					
		product = cartLine.getProduct();
		changed = false;
		
		if((!product.isActive() && product.getQuantity() == 0) && cartLine.isAvailable()) {
			cartLine.setAvailable(false);
			changed = true;
		}			
		
		if((product.isActive() && product.getQuantity() > 0) && !(cartLine.isAvailable())) {
				cartLine.setAvailable(true);
				changed = true;
		}
		
	
		if(cartLine.getBuyingPrice() != product.getProductPrice()) {
		
			cartLine.setBuyingPrice(product.getProductPrice());
		
			cartLine.setTotal(cartLine.getProductCount() * product.getProductPrice());
			changed = true;				
		}
		
		
		if(cartLine.getProductCount() > product.getQuantity()) {
			cartLine.setProductCount(product.getQuantity());										
			cartLine.setTotal(cartLine.getProductCount() * product.getProductPrice());
			changed = true;
			
		}
		
		
		if(changed) {				
			
			cartLineService.update(cartLine);
			
			response = "result=modified";
		}
		
		grandTotal += cartLine.getTotal();
		lineCount++;
	}
	
	cart.setCartLines(lineCount++);
	cart.setTotalPrice(grandTotal);
	cartLineService.updateCart(cart);

	return response;
}	
	
	

}
