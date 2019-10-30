package com.mongale.ecommercecore.controller;

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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mongale.ecommercecore.exception.ResourceNotFoundException;
import com.mongale.ecommercecore.message.Register;
import com.mongale.ecommercecore.message.reponse.JwtResponse;
import com.mongale.ecommercecore.message.reponse.ResponseMessage;
import com.mongale.ecommercecore.model.Role;
import com.mongale.ecommercecore.model.RoleName;
import com.mongale.ecommercecore.model.User;
import com.mongale.ecommercecore.repository.RoleRepository;
import com.mongale.ecommercecore.repository.UserRepository;
import com.mongale.ecommercecore.security.jwt.JwtProvider;
import com.mongale.ecommercecore.security.service.UserDetailsServiceImpl;

@RestController
@RequestMapping(path="/api/auth")
public class UserAuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;
	
	@GetMapping("/users")
	  public List<User> getAllUsers() {
	    return repo.findAll();
	  }
	
	@GetMapping("/users/{id}")
	  public ResponseEntity<User> getCustomerById(@PathVariable(value = "id") Long customerId)
	      throws ResourceNotFoundException {
	    User customer =
	             repo
	            .findById(customerId)
	            .orElseThrow(() -> new ResourceNotFoundException("Customer not found on :: " + customerId));
	    return ResponseEntity.ok().body(customer);
	  }
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	JwtProvider jwtTokenUtil;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody User authenticationRequest) throws Exception {
	manager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
	final UserDetails userDetails = userDetailsService
	.loadUserByUsername(authenticationRequest.getUsername());
	final String token = jwtTokenUtil.generateJwtToken(userDetails);
	return ResponseEntity.ok(new JwtResponse(token));
	}
	@PostMapping("/create")
	  public ResponseEntity<?> createUser(@Valid @RequestBody User user) {

		
		repo.save(user);

		return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	  }
	
	@DeleteMapping("/remove/{id}")
	  public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws Exception {
	    User user =
	        repo
	            .findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
	    repo.delete(user);
	    Map<String, Boolean> response = new HashMap<>();
	    response.put("deleted", Boolean.TRUE);
	    return response;
	  }
	
	 @PutMapping("/update/{id}")
	  public ResponseEntity<User> updateUser(
	      @PathVariable(value = "id") Long userId, @Valid @RequestBody User userDetails)
	      throws ResourceNotFoundException {
	    User user =
	        repo
	            .findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
	    user.setName(userDetails.getName());
	    
	    user.setEmail(userDetails.getEmail());
	    user.setPassword(userDetails.getPassword());
	    //user.setPhoneNumber(userDetails.getPhoneNumber());
	   // user.setStreetAddress(userDetails.getStreetAddress());
	   // user.setSuburb(userDetails.getSuburb());
	   // user.setCity(userDetails.getCity());
	   // user.setPostalCode(userDetails.getPostalCode());
	   // user.setProvince(userDetails.getProvince());
	    
	    final User userUpdated = repo.save(user);
	    return ResponseEntity.ok(userUpdated);
	  }


}
