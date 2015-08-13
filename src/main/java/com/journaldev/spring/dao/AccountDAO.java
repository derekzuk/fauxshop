package com.journaldev.spring.dao;
 
import java.util.List;
 

import com.journaldev.spring.model.Account;
 
public interface AccountDAO {
 
    public void save(Account a);    
    public List<Account> list();
    public void addAccount(Account a);
    public void updateAccount(Account a);
    public List<Account> listAccounts();
    public Account getAccountById(int id);
    public Account getAccountByName(String name);
    public void removeAccount(int id);
}