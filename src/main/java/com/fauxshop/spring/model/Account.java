package com.fauxshop.spring.model;
 
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 *
 */
@Entity
@Table(name="account")
public class Account implements Serializable {
	
	private static final long serialVersionUID = 6182390898687671093L;
	 
    @Id
    @Column(name="ACCOUNT_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int accountId;
     
    @Column(name="USER_LOGIN")
    private String userLogin;
    
    @Column(name="EMAIL")
    private String email;
    
    @Column(name="PASSWORD")
    private String password;
    
    @Column(name="FIRST_NAME")
    private String firstName;
    
    @Column(name="LAST_NAME")
    private String lastName;
    
    @Column(name="CITY")
    private String city;
    
    @Column(name="STATE")
    private String state;
    
    @Column(name="ZIP")
    private String zip;
    
    @Column(name="PHONE_NUMBER")
    private String phoneNumber;
    
    @Column(name="COUNTRY")
    private String country;
    
    @Column(name="ADDRESS")
    private String address;
    
    @Column(name="ADDRESS2")
    private String address2;
    
    public int getAccountId() {
        return accountId;
    }
 
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
 
    public String getUserLogin() {
        return userLogin;
    }
 
    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
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
    
    public String getFirstName() {
    	return firstName;
    }
    
    public void setFirstName(String firstName) {
    	this.firstName = firstName;
    }
    
    public String getLastName() {
    	return lastName;
    }
    
    public void setLastName(String lastName) {
    	this.lastName = lastName;
    }
    
    public String getCity() {
    	return city;
    }
    
    public void setCity(String city) {
    	this.city = city;
    }
    
    public String getState() {
    	return state;
    }
    
    public void setState(String state) {
    	this.state = state;
    }    

    public String getZip() {
    	return zip;
    }
    
    public void setZip(String zip) {
    	this.zip = zip;
    }    
    
    public String getPhoneNumber() {
    	return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
    	this.phoneNumber = phoneNumber;
    }    
    
    public String getCountry() {
    	return country;
    }
    
    public void setCountry(String country) {
    	this.country = country;
    }    
    
    public String getAddress() {
    	return address;
    }
    
    public void setAddress(String address) {
    	this.address = address;
    } 

    public String getAddress2() {
    	return address2;
    }
    
    public void setAddress2(String address2) {
    	this.address2 = address2;
    }
    
    @Override
    public String toString(){
        return "accountId="+accountId+
        		", userLogin="+userLogin+
        		", email="+email+
        		", password="+password+
        		", firstName="+firstName+
        		", lastName="+lastName+
        		", city="+city+
        		", state="+state+
        		", zip="+zip+
        		", phoneNumber="+phoneNumber+
        		", country="+country+
        		", address="+address+
        		", address2="+address2;
    }
}