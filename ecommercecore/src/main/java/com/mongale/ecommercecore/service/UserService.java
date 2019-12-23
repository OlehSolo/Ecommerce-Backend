package com.mongale.ecommercecore.service;

import java.util.Collection;

import com.mongale.ecommercecore.model.User;

public interface UserService {
	
	User findOne(String email);

    Collection<User> findByRole(String role);

    User save(User user);

    User update(User user);

}
