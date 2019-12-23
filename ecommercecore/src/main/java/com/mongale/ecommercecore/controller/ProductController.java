package com.mongale.ecommercecore.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mongale.ecommercecore.exception.ResourceNotFoundException;
import com.mongale.ecommercecore.message.AddProductRequest;
import com.mongale.ecommercecore.message.ApiResponse;
import com.mongale.ecommercecore.model.Product;
import com.mongale.ecommercecore.model.User;
import com.mongale.ecommercecore.repository.ProductRepository;
import com.mongale.ecommercecore.service.UploadImageFileServ;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	@Autowired
	ProductRepository repository;

	@PostMapping("/insert")
	public ResponseEntity<?> insertProduct(@Valid @RequestBody AddProductRequest request) 
	{
		Product product = new Product(request.getProductName(), request.getProductCategory(),
				request.getProductPrice(), request.getProductDescription(), request.getProductbarcode()
				, request.getProductBrand());
		
		product.setQuantity(1);
		
		UploadImageFileServ service = new UploadImageFileServ();
		
		
		
		
		Product results = repository.save(product);
		return new ResponseEntity<>(new ApiResponse(true, "Product is inserted successfully"), HttpStatus.OK);
		}
	
	@GetMapping("/products")
	public List<Product> listProducts()
	{
		return repository.findAll();
	}
	
	 @CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/modify/{id}")
	public ResponseEntity<Product> modifyProduct( @PathVariable(value = "id") 
	Long productId, @Valid @RequestBody Product productDetails) throws ResourceNotFoundException
	{
		Product product = repository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found on :: " + productId));
		
		product.setProductName(productDetails.getProductName());
		product.setProductCategory(productDetails.getProductCategory());
		product.setProductPrice(productDetails.getProductPrice());
		product.setProductDescription(productDetails.getProductDescription());
		product.setProductBrand(productDetails.getProductBrand());
		product.setProductbarcode(productDetails.getProductbarcode());
		
		final Product updatedProduct = repository.save(product);
		return ResponseEntity.ok(updatedProduct);
	}
	
	 @CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/remove/{id}")
	public Map<String, Boolean> removeProduct(@PathVariable(value = "id") Long productId) throws Exception
	{
		Product product =
		        repository
		            .findById(productId)
		            .orElseThrow(() -> new ResourceNotFoundException("Product not found on :: " + productId));
		    repository.delete(product);
		    Map<String, Boolean> response = new HashMap<>();
		    response.put("deleted", Boolean.TRUE);
		    return response;
	}
}
