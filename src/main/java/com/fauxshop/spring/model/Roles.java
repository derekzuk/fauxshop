package com.fauxshop.spring.model;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
 
/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 *
 */
@Entity
@Table(name="roles")
public class Roles {
	
	@Id
    @Column(name="USER_LOGIN")
    private String userLogin;
     
    @Column(name="ROLE")
    private String role;
        
    public String getUserLogin() {
        return userLogin;
    }
 
    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
 
    public String getRole() {
        return role;
    }
 
    public void setRole(String role) {
        this.role = role;
    }
     
    @Override
    public String toString(){
        return "userLogin="+userLogin+
        		", role="+role;
   }
}