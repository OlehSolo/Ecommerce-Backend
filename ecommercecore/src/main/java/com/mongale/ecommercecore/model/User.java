package com.mongale.ecommercecore.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import com.mongale.ecommercecore.model.audit.DateAudit;


@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "username"
        }),
        @UniqueConstraint(columnNames = {
            "email"
        })
})
public class User extends DateAudit {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "user_id")
	    private Long id;

	    @NotBlank
	    @Size(max = 50)
	    private String firstName;
	    
	    @NotBlank
	    @Size(max = 50)
	    private String lastName;
	    
	    @NotBlank
	    @Size(max = 10)
	    private String phoneNumber;

	    
	    @Size(max = 50)
	    private String username;

	    @NaturalId
	    @NotBlank
	    @Size(max = 50)
	    @Email
	    private String email;

	    @NotBlank
	    private String password;

	    @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(name = "user_roles",
	            joinColumns = @JoinColumn(name = "user_id"),
	            inverseJoinColumns = @JoinColumn(name = "role_id"))
	    private Set<Role> roles = new HashSet<>();
	    
	    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	    @JoinTable(name = "user_addresses",
	            joinColumns = @JoinColumn(name = "user_id"),
	            inverseJoinColumns = @JoinColumn(name = "address_id"))
	    private Set<Address> addresses = new HashSet<>();

		public Set<Address> getAddresses() {
			return addresses;
		}

		public void setAddresses(Set<Address> addresses) {
			this.addresses = addresses;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		

		public User() {

	    }

	    public User(String name, String lastName, String phoneNumber, String username, String email, String password, Set<Address> addresses) {
	        this.firstName = name;
	        this.username = username;
	        this.lastName = lastName;
	        this.phoneNumber = phoneNumber;
	        this.email = email;
	        this.password = password;
	        this.addresses = addresses;
	        
	    }

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getName() {
	        return firstName;
	    }
        
	    public String getLastName()
	    {
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
	    public void setName(String name) {
	        this.firstName = name;
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

	    public Set<Role> getRoles() {
	        return roles;
	    }

	    public void setRoles(Set<Role> roles) {
	        this.roles = roles;
	    }
}
