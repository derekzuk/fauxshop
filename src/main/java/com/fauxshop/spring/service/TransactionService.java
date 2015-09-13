package com.fauxshop.spring.service;
 
import java.util.List;
 

import com.fauxshop.spring.model.TransactionLog;
 
public interface TransactionService {
    
    public void addTransaction(TransactionLog t);
    public void updateTransaction(TransactionLog t);
    public List<TransactionLog> listTransactions();
    public TransactionLog getTransactionById(int id);
    public void removeTransaction(int id);
    public void createTransaction(int cartId);
     
}