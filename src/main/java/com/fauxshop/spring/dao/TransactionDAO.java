package com.fauxshop.spring.dao;
 
import java.util.List;
 

import com.fauxshop.spring.model.TransactionLog;
 
public interface TransactionDAO {
 
    public void save(TransactionLog t);    
    public List<TransactionLog> list();
    public void addTransaction(TransactionLog t);
    public void updateTransaction(TransactionLog t);
    public List<TransactionLog> listTransactions();
    public TransactionLog getTransactionById(int id);
    public void removeTransaction(int id);
}