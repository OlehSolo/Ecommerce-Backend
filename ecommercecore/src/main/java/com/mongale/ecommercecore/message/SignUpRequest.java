package com.mongale.ecommercecore.message;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.mongale.ecommercecore.model.Address;
import com.mongale.ecommercecore.model.User;

public class SignUpRequest {

	 @NotBlank
	    @Size(min = 4, max = 50)
	    private String firstName;

	    @NotBlank
	    @Size(min = 3, max = 50)
	    private String username;
	    
	    @NotBlank
	    @Size(min = 3, max = 50)
	    private String lastName;
	    
	    @NotBlank
	    @Size(max = 10)
	    private String phoneNumber;
	    
	    @Size(max = 150)
		private String streetAddress;
		
		@Size(max = 50)
		private String suburb;
		
		@Size(max = 50)
		private String city;
		
		@Size(max = 4)
		private String postalCode;
		
		@Size(max = 30)
		private String province;
		
		private Set<String> role;
		
		private User user;
		
		private Set<Address> addresses = new HashSet<>();

	    public String getStreetAddress() {
			return streetAddress;
		}

		public void setStreetAddress(String streetAddress) {
			this.streetAddress = streetAddress;
		}

		public String getSuburb() {
			return suburb;
		}

		public void setSuburb(String suburb) {
			this.suburb = suburb;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getPostalCode() {
			return postalCode;
		}

		public void setPostalCode(String postalCode) {
			this.postalCode = postalCode;
		}

		public String getProvince() {
			return province;
		}

		public void setProvince(String province) {
			this.province = province;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		@NotBlank
	    @Size(max = 40)
	    @Email
	    private String email;

	    @NotBlank
	    @Size(min = 6, max = 20)
	    private String password;


	    public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

		public Set<Address> getAddresses() {
			return addresses;
		}

		public void setAddresses(Set<Address> addresses) {
			this.addresses = addresses;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Set<String> getRole() {
			return role;
		}

		public void setRole(Set<String> role) {
			this.role = role;
		}

		
}
