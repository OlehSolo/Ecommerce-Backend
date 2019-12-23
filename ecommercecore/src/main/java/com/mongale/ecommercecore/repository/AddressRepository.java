package com.mongale.ecommercecore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mongale.ecommercecore.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
