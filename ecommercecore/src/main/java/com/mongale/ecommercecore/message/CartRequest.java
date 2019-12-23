package com.mongale.ecommercecore.message;

import java.util.List;

public class CartRequest {
	
	private List<Long> productId;
	private long userId;
	public List<Long> getProductId() {
		return productId;
	}
	public void setProductId(List<Long> productId) {
		this.productId = productId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	

}
