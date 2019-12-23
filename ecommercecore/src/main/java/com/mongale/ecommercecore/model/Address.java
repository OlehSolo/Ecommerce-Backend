package com.mongale.ecommercecore.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "address")
public class Address {
	
	@Id
   @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="address_id")
	private long id;
	
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
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

	public Address() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public Address(String streetAddress, String suburb, String city,
			 String postalCode,  String province, User user) {
		super();
		this.streetAddress = streetAddress;
		this.suburb = suburb;
		this.city = city;
		this.postalCode = postalCode;
		this.province = province;
		this.user = user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	

	

}
