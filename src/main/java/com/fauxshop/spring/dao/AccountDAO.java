package com.fauxshop.spring.dao;
 
import java.util.List;
 



import com.fauxshop.spring.model.Account;
import com.fauxshop.spring.model.SessionAccount;
 
public interface AccountDAO {
 
    public void saveAccount(Account a);    
    public List<Account> list();
    public void addAccount(Account a);
    public void addSessionAccount(SessionAccount sa);
    public void updateAccount(Account a);
    public List<Account> listAccounts();
    public Account getAccountById(int id);
    public Account getAccountByName(String name);
    public void removeAccount(int id);
    public void createUserRole(String name);
    public boolean isUserLoginUnique(String name);
    public boolean isSessionAccountAlreadyRegistered(String sessionId);
    public SessionAccount getSessionAccountBySessionId(String sessionId);
}