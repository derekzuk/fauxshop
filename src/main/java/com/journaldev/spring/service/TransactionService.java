package com.journaldev.spring.service;
 
import java.util.List;
 

import com.journaldev.spring.model.TransactionLog;
 
public interface TransactionService {
    
    public void addTransaction(TransactionLog t);
    public void updateTransaction(TransactionLog t);
    public List<TransactionLog> listTransactions();
    public TransactionLog getTransactionById(int id);
    public void removeTransaction(int id);
     
}