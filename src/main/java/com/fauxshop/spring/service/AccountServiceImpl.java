package com.fauxshop.spring.service;
 
import java.util.List;
 



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 



import com.fauxshop.spring.dao.AccountDAO;
import com.fauxshop.spring.model.Account;
 
@Service
public class AccountServiceImpl implements AccountService {	
     
    private AccountDAO accountDAO;
    
    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }
    
    /*@Override*/
    @Transactional
    public void saveAccount(Account a) {
        this.accountDAO.saveAccount(a);
    }     
 
    /*@Override*/
    @Transactional
    public void addAccount(Account a) {
        this.accountDAO.addAccount(a);
    }     
 
    /*@Override*/
    @Transactional
    public void updateAccount(Account a) {
        this.accountDAO.updateAccount(a);
    }
 
    /*@Override*/
    @Transactional
    public List<Account> listAccounts() {
        return this.accountDAO.listAccounts();
    }
 
    /*@Override*/
    @Transactional
    public Account getAccountById(int id) {
        return this.accountDAO.getAccountById(id);
    }
    
    /*@Override*/
    @Transactional
    public Account getAccountByName(String name) {
        return this.accountDAO.getAccountByName(name);
    }    
 
    /*@Override*/
    @Transactional
    public void removeAccount(int id) {
        this.accountDAO.removeAccount(id);
    }
    
    public boolean createUserRole(String name) {
    	this.accountDAO.createUserRole(name);
    	return true;
    }
    
    public boolean isUserLoginUnique(String name) {
    	return this.accountDAO.isUserLoginUnique(name);
    }
    
 
}