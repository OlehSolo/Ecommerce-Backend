package com.mongale.ecommercecore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mongale.ecommercecore.model.Role;
import com.mongale.ecommercecore.model.RoleName;

public interface RoleRepository extends JpaRepository<Role,Long> {
	Optional<Role> findByName(RoleName roleName);
}
