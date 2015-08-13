package com.journaldev.spring.service;
 
import java.util.List;
 

import com.journaldev.spring.model.Account;
 
public interface AccountService {
    
    public void addAccount(Account a);
    public void updateAccount(Account a);
    public List<Account> listAccounts();
    public Account getAccountById(int id);
    public Account getAccountByName(String name);
    public void removeAccount(int id);
     
}