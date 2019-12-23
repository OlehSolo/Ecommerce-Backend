package com.mongale.ecommercecore.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cart")
public class Cart implements Serializable {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	   @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private long cartId;
	   
	   @Column(name="total_price")
	   private double totalPrice;
	   
	   @Column(name="cart_lines")
	   private int cartLines;
	   
	   @OneToOne
		private User user;
	   
	   
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getCartLines() {
		return cartLines;
	}

	public void setCartLines(int cartLines) {
		this.cartLines = cartLines;
	}
	   
	   
	   
	   

	   
}
