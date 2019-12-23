package com.mongale.ecommercecore.service.serviceImpl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongale.ecommercecore.model.Cart;
import com.mongale.ecommercecore.model.CartLine;
import com.mongale.ecommercecore.model.Order;
import com.mongale.ecommercecore.model.Product;
import com.mongale.ecommercecore.repository.CartRepository;
import com.mongale.ecommercecore.service.CartService;

@Service
@Transactional
public class CartServiceImp implements CartService {
	
	//@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<CartLine> list(long cartId) {
	
		String query = "FROM CartLine WHERE cartId = :cartId";
		return sessionFactory.getCurrentSession()
								.createQuery(query, CartLine.class)
									.setParameter("cartId", cartId)
										.getResultList();
	}

	@Override
	public CartLine get(int id) {
		
		return sessionFactory.getCurrentSession().get(CartLine.class, Integer.valueOf(id));
	}

	@Override
	public boolean add(CartLine cartLine) {
		
		try {
			sessionFactory.getCurrentSession().persist(cartLine);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(CartLine cartLine) {
		
		try {
			sessionFactory.getCurrentSession().update(cartLine);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean remove(CartLine cartLine) {
		
		try {			
			sessionFactory.getCurrentSession().delete(cartLine);
			return true;
		}catch(Exception ex) {
			return false;
		}	
	}

	@Override
	public CartLine getByCartAndProduct(long cartId, long productId) {
		
		String query = "FROM CartLine WHERE cartId = :cartId AND product.id = :productId";
		try {
			
			return sessionFactory.getCurrentSession()
									.createQuery(query,CartLine.class)
										.setParameter("cartId", cartId)
										.setParameter("productId", productId)
											.getSingleResult();
			
		}catch(Exception ex) {
			
			return null;	
		}
	}

	@Override
	public boolean updateCart(Cart cart) {
		
		try {			
			sessionFactory.getCurrentSession().update(cart);			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}

	@Override
	public List listAvailable(int cartId) {
		
		String query = "FROM CartLine WHERE cartId = :cartId AND available = :available";
		
		return sessionFactory.getCurrentSession()
								.createQuery(query, CartLine.class)
									.setParameter("cartId", cartId)
									.setParameter("available", true)
										.getResultList();
	}

	@Override
	public boolean addOrder(Order order) {
		
		try {			
			sessionFactory.getCurrentSession().persist(order);			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}
	
	
}
