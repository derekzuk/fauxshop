package com.fauxshop.spring.service;
 
import java.util.List;
 

import com.fauxshop.spring.model.Account;
 
public interface AccountService {
    
	public void saveAccount(Account a);
    public void addAccount(Account a);
    public void updateAccount(Account a);
    public List<Account> listAccounts();
    public Account getAccountById(int id);
    public Account getAccountByName(String name);
    public void removeAccount(int id);     
    public boolean createUserRole(String name);
    public boolean isUserLoginUnique(String name);
}