package com.mongale.ecommercecore.message;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddProductRequest {

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
	
	
	//private byte[] productImage;

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

	/*public byte[] getProductImage() {
		return productImage;
	}

	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	}*/
}
