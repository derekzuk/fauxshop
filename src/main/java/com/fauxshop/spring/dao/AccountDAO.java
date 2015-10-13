package com.fauxshop.spring.dao;
 
import java.util.List;
 




import org.hibernate.SessionFactory;

import com.fauxshop.spring.model.Account;
import com.fauxshop.spring.model.SessionAccount;
 
public interface AccountDAO {
 
	public void setSessionFactory(SessionFactory sf);
    public void saveAccount(Account a);
    public void saveSessionAccount(SessionAccount sa);  
    public List<Account> list();
    public void addAccount(Account a);
    public void addSessionAccount(SessionAccount sa);
    public List<Account> listAccounts();
    public Account getAccountByName(String name);
    public void removeAccount(int id);
    public void createUserRole(String name);
    public boolean isUserLoginUnique(String name);
    public boolean isSessionAccountAlreadyRegistered(String sessionId);
    public SessionAccount getSessionAccountBySessionId(String sessionId);
}