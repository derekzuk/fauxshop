package com.fauxshop.spring.service;
 
import java.util.List;
 





import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 





import com.fauxshop.spring.dao.TransactionDAO;
import com.fauxshop.spring.model.Cart;
import com.fauxshop.spring.model.TransactionLog;
 
@Service
public class TransactionServiceImpl implements TransactionService {
     
    private TransactionDAO transactionDAO;
    
    public void setTransactionDAO(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }
 
    /*@Override*/
    @Transactional
    public void addTransaction(TransactionLog t) {
        this.transactionDAO.addTransaction(t);
    }
 
    /*@Override*/
    @Transactional
    public void updateTransaction(TransactionLog t) {
        this.transactionDAO.updateTransaction(t);
    }
 
    /*@Override*/
    @Transactional
    public TransactionLog getTransactionById(int id) {
        return this.transactionDAO.getTransactionById(id);
    }
 
    /*@Override*/
    @Transactional
    public void removeTransaction(int id) {
        this.transactionDAO.removeTransaction(id);
    }    

	/*@Override*/
	@Transactional
	public void createTransaction(int cartId, String sessionId, long trackingNumber) {
		this.transactionDAO.createTransaction(cartId, sessionId, trackingNumber);
	}
	
	/*@Override*/
	@Transactional
	public void createTransactionFromSessionId(int cartId, String sessionId, long trackingNumber) {
		this.transactionDAO.createTransactionFromSessionId(cartId, sessionId, trackingNumber);
	}    		
	
	/*@Override*/
	@Transactional
	public void createTransactionsFromCartList(List<Cart> cartList, String sessionId) {
		this.transactionDAO.createTransactionsFromCartList(cartList, sessionId);
	} 	
	
	/*@Override*/
	@Transactional
	public boolean createTransactionsFromSessionCartList(List<Cart> cartList, String sessionId) {
		this.transactionDAO.createTransactionsFromSessionCartList(cartList, sessionId);
		return true;
	} 		
	
	public TransactionLog getLastTransactionByAccountId(int accountId) {
		return this.transactionDAO.getLastTransactionByAccountId(accountId);
	}
	
	public TransactionLog getLastTransactionBySessionId(String sessionId) {
		return this.transactionDAO.getLastTransactionBySessionId(sessionId);
	}	
	
    public void setTransactionToConfirmed(long trackingNumber) {
    	this.transactionDAO.setTransactionToConfirmed(trackingNumber);
    }
    
    public void updateMessage(TransactionLog transaction, String message) {
    	this.transactionDAO.updateMessage(transaction, message);
    }
    	
}