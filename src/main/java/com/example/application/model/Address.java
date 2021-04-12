package com.example.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="address")
public class Address {
	private long addressId;
	private String city;
	private String state;
	private String country;
	private String addressDesc;
	private String pincode;
	private String addressType;
	
	public Address() {
		
	}
	public Address(String city, String state, String country, String addressDesc, String pincode,String addressType) {
		this.city = city;
		this.state = state;
		this.country = country;
		this.addressDesc = addressDesc;
		this.pincode = pincode;
		this.addressType=addressType;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="addressId",nullable = false)
	public long getAddressId() {
		return addressId;
	}
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	@Column(name="city",nullable = true)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(name="state",nullable = true)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Column(name="country",nullable = true)
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Column(name="addressDesc",nullable = true)
	public String getAddressDesc() {
		return addressDesc;
	}
	public void setAddressDesc(String addressDesc) {
		this.addressDesc = addressDesc;
	}
	@Column(name="pincode",nullable = true)
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	@Column(name="address_type",nullable = true)
	public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	}

