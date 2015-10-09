package com.fauxshop.spring.service;
 
import java.util.List;
 




import com.fauxshop.spring.model.Cart;
import com.fauxshop.spring.model.TransactionLog;
 
public interface TransactionService {
    
    public void addTransaction(TransactionLog t);
    public void updateTransaction(TransactionLog t);
    public List<TransactionLog> listTransactions();
    public TransactionLog getTransactionById(int id);
    public void removeTransaction(int id);
    public void createTransaction(int cartId, String sessionId, long trackingNumber);
    public void createTransactionFromSessionId(int cartId, String sessionId, long trackingNumber);
    public void createTransactionsFromCartList(List<Cart> cartList, String sessionId);
    public boolean createTransactionsFromSessionCartList(List<Cart> cartList, String sessionId);
    public TransactionLog getLastTransactionByAccountId(int accountId);
    public TransactionLog getLastTransactionBySessionId(String sessionId);
    public void setTransactionToConfirmed(long trackingNumber);
    public void updateMessage(TransactionLog transaction, String message);
     
}