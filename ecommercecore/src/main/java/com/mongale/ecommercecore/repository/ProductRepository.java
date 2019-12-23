package com.mongale.ecommercecore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mongale.ecommercecore.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
