package com.fauxshop.spring.service;
 
import java.util.List;
 



import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.fauxshop.spring.model.Account;
import com.fauxshop.spring.model.SessionAccount;
 
public interface AccountService {
    
	public void saveAccount(Account a);
	public void saveSessionAccount(SessionAccount sa);
    public void addAccount(Account a);
    public void addSessionAccount(SessionAccount sa);
    public List<Account> listAccounts();
    public Account getAccountByName(String name);
    public void removeAccount(int id);     
    public boolean createUserRole(String name);
    public boolean isUserLoginUnique(String name);
    public boolean isSessionAccountAlreadyRegistered(String sessionId);
    public boolean isUserLoggedIn(UsernamePasswordAuthenticationToken name);
    public SessionAccount getSessionAccountBySessionId(String sessionId);
}