package com.fauxshop.spring.dao;
 
import java.util.List;

import com.fauxshop.spring.model.Cart;
import com.fauxshop.spring.model.TransactionLog;
 
public interface TransactionDAO {
 
    public void save(TransactionLog t);    
    public List<TransactionLog> list();
    public void addTransaction(TransactionLog t);
    public void updateTransaction(TransactionLog t);
    public TransactionLog getTransactionById(int id);
    public void removeTransaction(int id);
    public void createTransaction(int cartId, String sessionId, long trackingNumber);
    public void createTransactionFromSessionId(int cartId, String sessionId, long trackingNumber);
    public void createTransactionsFromCartList(List<Cart> cartList, String sessionId);
    public void createTransactionsFromSessionCartList(List<Cart> cartList, String sessionId);
    public TransactionLog getLastTransactionByAccountId(int accountId);
    public TransactionLog getLastTransactionBySessionId(String sessionId);
    public void setTransactionToConfirmed(long trackingNumber);
    public void updateMessage(TransactionLog transaction, String message);
}