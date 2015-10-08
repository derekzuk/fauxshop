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
@Table(name="session_account")
public class SessionAccount implements Serializable {
	
	private static final long serialVersionUID = 6182390898687671093L;
	
    @Id
    @Column(name="SESSION_ACCOUNT_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int sessionAccountId;
	 
    @Column(name="SESSION_ID")
    private String sessionId;
     
    @Column(name="EMAIL")
    private String email;
        
    @Column(name="SHIP_NAME")
    private String shipName;

    @Column(name="SHIP_CITY")
    private String shipCity;
    
    @Column(name="SHIP_STATE")
    private String shipState;
    
    @Column(name="SHIP_ZIP")
    private String shipZip;
    
    @Column(name="SHIP_PHONE")
    private String shipPhone;
    
    @Column(name="SHIP_COUNTRY")
    private String shipCountry;
    
    @Column(name="SHIP_ADDRESS")
    private String shipAddress;
    
    @Column(name="SHIP_ADDRESS2")
    private String shipAddress2;  
    
    public int getSessionAccountId() {
        return sessionAccountId;
    }
 
    public void setSessionAccountId(int sessionAccountId) {
        this.sessionAccountId = sessionAccountId;
    }    
    
    public String getSessionId() {
        return sessionId;
    }
 
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }        
    
    public String getShipName() {
    	return shipName;
    }
    
    public void setShipName(String shipName) {
    	this.shipName = shipName;
    }
    
    public String getShipCity() {
    	return shipCity;
    }
    
    public void setShipCity(String shipCity) {
    	this.shipCity = shipCity;
    }
    
    public String getShipState() {
    	return shipState;
    }
    
    public void setShipState(String shipState) {
    	this.shipState = shipState;
    }    

    public String getShipZip() {
    	return shipZip;
    }
    
    public void setShipZip(String shipZip) {
    	this.shipZip = shipZip;
    }    
    
    public String getShipPhone() {
    	return shipPhone;
    }
    
    public void setShipPhone(String shipPhone) {
    	this.shipPhone = shipPhone;
    }    
    
    public String getShipCountry() {
    	return shipCountry;
    }
    
    public void setShipCountry(String shipCountry) {
    	this.shipCountry = shipCountry;
    }    
    
    public String getShipAddress() {
    	return shipAddress;
    }
    
    public void setShipAddress(String shipAddress) {
    	this.shipAddress = shipAddress;
    } 

    public String getShipAddress2() {
    	return shipAddress2;
    }
    
    public void setShipAddress2(String shipAddress2) {
    	this.shipAddress2 = shipAddress2;
    }    
    
    
    @Override
    public String toString(){
        return "sessionAccountId="+sessionAccountId+
        		"sessionId="+sessionId+
        		", email="+email+
        		", shipName="+shipName+
        		", shipCity="+shipCity+
        		", shipState="+shipState+
        		", shipZip="+shipZip+
        		", shipPhone="+shipPhone+
        		", shipCountry="+shipCountry+
        		", shipAddress="+shipAddress+
        		", shipAddress2="+shipAddress2;
    }
}