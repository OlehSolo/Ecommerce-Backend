package com.mongale.ecommercecore.controller;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongale.ecommercecore.exception.AppException;
import com.mongale.ecommercecore.exception.ResourceNotFoundException;
import com.mongale.ecommercecore.message.ApiResponse;
import com.mongale.ecommercecore.message.JwtAuthenticationResponse;
import com.mongale.ecommercecore.message.LoginRequest;
import com.mongale.ecommercecore.message.SignUpRequest;
import com.mongale.ecommercecore.model.Address;
import com.mongale.ecommercecore.model.Role;
import com.mongale.ecommercecore.model.RoleName;
import com.mongale.ecommercecore.model.User;
import com.mongale.ecommercecore.repository.RoleRepository;
import com.mongale.ecommercecore.repository.UserRepository;
import com.mongale.ecommercecore.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/auth")
public class UserAuthController {
	
	    @Autowired
	    AuthenticationManager authenticationManager;

	    @Autowired
	    UserRepository userRepository;

	    @Autowired
	    RoleRepository roleRepository;

	    @Autowired
	    PasswordEncoder passwordEncoder;

	    @Autowired
	    JwtTokenProvider tokenProvider;
	    
	    @GetMapping("/users")
		  public List<User> getAllUsers() {
		    return userRepository.findAll();
		  }
	    
	    @PutMapping("/update/{id}")
		  public ResponseEntity<User> updateUser(
		      @PathVariable(value = "id") Long userId, @Valid @RequestBody User userDetails)
		      throws ResourceNotFoundException {
		    User user =
		        userRepository
		            .findById(userId)
		            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
		    user.setName(userDetails.getName());
		    user.setLastName(userDetails.getLastName());
		    user.setEmail(userDetails.getEmail());
		    user.setUsername(userDetails.getUsername());
		    user.setPhoneNumber(userDetails.getPhoneNumber());
		    user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
		   // user.setStreetAddress(userDetails.getStreetAddress());
		   // user.setSuburb(userDetails.getSuburb());
		   // user.setCity(userDetails.getCity());
		   // user.setPostalCode(userDetails.getPostalCode());
		   // user.setProvince(userDetails.getProvince());
		    
		    final User userUpdated = userRepository.save(user);
		    return ResponseEntity.ok(userUpdated);
		  }
	    
	    @PostMapping("/login")
	    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        loginRequest.getUsernameOrEmail(),
	                        loginRequest.getPassword()
	                )
	        );

	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        String jwt = tokenProvider.generateToken(authentication);
	        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	    }
	    @PostMapping("/signup")
	    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
	        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
	            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
	                    HttpStatus.BAD_REQUEST);
	        }

	        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
	            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
	                    HttpStatus.BAD_REQUEST);
	        }

	      
            
	        // Creating user's account
	        User user = new User(signUpRequest.getName(),  signUpRequest.getLastName(), 
	        	signUpRequest.getPhoneNumber(), signUpRequest.getUsername(),
	            signUpRequest.getEmail(), signUpRequest.getPassword(), 
	            signUpRequest.getAddresses());
	        
	        
	        // Creating the address object to save user's address information
	        Address userAddress = new Address(signUpRequest.getStreetAddress(), signUpRequest.getSuburb(), signUpRequest.getCity(),
	        		signUpRequest.getPostalCode(), signUpRequest.getProvince(), signUpRequest.getUser());

	        // Creating a HashMap to store user's addresses
	        Set<Address> addressMap = new HashSet();
	        addressMap.add(userAddress);

	        // Adding address to user's address collection
	        user.setAddresses(addressMap);

	        // Encoding the user's password
	        user.setPassword(passwordEncoder.encode(user.getPassword()));

		/*
		 * Role userRole = roleRepository.findByName(RoleName.ROLE_USER) .orElseThrow(()
		 * -> new AppException("User Role not set."));
		 * 
		 * user.setRoles(Collections.singleton(userRole));
		 */
	        User result = userRepository.save(user);

	       

	        return new ResponseEntity<>(new ApiResponse(true, 
	        	"User registered successfully"), HttpStatus.OK);
	    }
	    
	    @DeleteMapping("/remove/{id}")
		  public Map<String, Boolean> deleteUser(@PathVariable(value = "id") 
		  	Long userId) throws Exception {
		    User user =
		        userRepository
		            .findById(userId)
		            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: "
		             + userId));
		    userRepository.delete(user);
		    Map<String, Boolean> response = new HashMap<>();
		    response.put("deleted", Boolean.TRUE);
		    return response;
		  }

}
