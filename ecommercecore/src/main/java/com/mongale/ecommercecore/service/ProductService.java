package com.mongale.ecommercecore.service;

import javax.validation.constraints.Min;

import org.springframework.validation.annotation.Validated;

import com.mongale.ecommercecore.model.Product;

@Validated
public interface ProductService {
	
	
	Product getProduct(@Min(value = 1L, message = "Invalid product ID.") long id);
	
	Product findOne(String productId);

    void increaseStock(String productId, int amount);

    //decrease stock
    void decreaseStock(String productId, int amount);

    Product offSale(String productId);

    Product onSale(String productId);

    Product update(Product productInfo);
    Product save(Product productInfo);

    void delete(String productId);

}
