package com.mongale.ecommercecore.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {
	
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productId;
	
	@NotBlank
	@Size(max = 100)
	private String productName;
	
	@NotBlank
	@Size(max = 100)
	private String productCategory;
	
	@NotNull
	private double productPrice;
	
	@NotBlank
	@Size(max = 100)
	private String productDescription;
	
	@NotBlank
	@Size(max = 100)
	private String productbarcode;
	
	@NotBlank
	@Size(max = 100)
	private String productBrand;
	
	
	
	@Lob
	private byte[] productImage;
	
	 @Column(columnDefinition = "integer default 25")
	private int cartItemCount;
	
	public Product(){}
	
	public long getProductId() {
		return productId;
	}
	@Column(name = "is_active")	
	private boolean active;
	
	
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Product(String productName,
			 String productCategory, 
			 double productPrice, 
			 String productDescription, 
			 String productbarcode, String productBrand) {
		
		this.productName = productName;
		this.productCategory = productCategory;
		this.productPrice = productPrice;
		this.productDescription = productDescription;
		this.productbarcode = productbarcode;
		this.productBrand = productBrand;
	}


	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getProductbarcode() {
		return productbarcode;
	}
	public void setProductbarcode(String productbarcode) {
		this.productbarcode = productbarcode;
	}
	public String getProductBrand() {
		return productBrand;
	}
	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}
	public byte[] getProductImage() {
		return productImage;
	}
	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	}



	public int getQuantity() {
		return cartItemCount;
	}

	public void setQuantity(int quantity) {
		this.cartItemCount = quantity;
	}

	

}
