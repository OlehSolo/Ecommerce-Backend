package com.mongale.ecommercecore.service.serviceImpl;

import javax.validation.constraints.Min;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongale.ecommercecore.model.Product;
import com.mongale.ecommercecore.service.ProductService;

public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Product getProduct(@Min(value = 1, message = "Invalid product ID.") long id) {
		
		try {			
			return sessionFactory
					.getCurrentSession()
						.get(Product.class,Long.valueOf(id));			
		}
		catch(Exception ex) {		
			ex.printStackTrace();			
		}
		return null;
	}
	

	@Override
	public Product findOne(String productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void increaseStock(String productId, int amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decreaseStock(String productId, int amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product offSale(String productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product onSale(String productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product update(Product productInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product save(Product productInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String productId) {
		// TODO Auto-generated method stub
		
	}

}
