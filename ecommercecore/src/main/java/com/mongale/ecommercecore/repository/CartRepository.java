package com.mongale.ecommercecore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mongale.ecommercecore.model.Cart;
import com.mongale.ecommercecore.model.CartLine;

@Repository
public interface CartRepository extends JpaRepository<CartLine, Long>{

}
