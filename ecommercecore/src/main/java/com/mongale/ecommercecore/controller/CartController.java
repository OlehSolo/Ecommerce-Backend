package com.mongale.ecommercecore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongale.ecommercecore.message.ApiResponse;
import com.mongale.ecommercecore.message.CartRequest;
import com.mongale.ecommercecore.model.CartLine;
import com.mongale.ecommercecore.model.Product;
import com.mongale.ecommercecore.repository.CartRepository;
import com.mongale.ecommercecore.service.CartManager;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	
	CartRepository cartRepository;
	
	@Autowired
	CartManager cartManager;
	
	@PostMapping("/add")
	public ResponseEntity<?> addItem(@Valid @RequestBody CartLine cartLine){
		
		CartLine line = new  CartLine();
		line.setProduct(cartLine.getProduct());
		line.setCartId(cartLine.getCartId());
		line.setProductCount(cartLine.getProductCount());
		line.setBuyingPrice(cartLine.getBuyingPrice());
		line.setTotal(cartLine.getTotal());
		
		CartLine results = cartRepository.save(line);
		
		return new ResponseEntity<>(new ApiResponse(true, "Product added to cart successfully"), HttpStatus.OK);
		
	}

}
