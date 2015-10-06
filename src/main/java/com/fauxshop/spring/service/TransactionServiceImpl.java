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
    public List<TransactionLog> listTransactions() {
        return this.transactionDAO.listTransactions();
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
	public void createTransaction(int cartId, String sessionId, long trackingNumber, String message, String cardType, int cardNumber, int cardSecurityCode) {
		this.transactionDAO.createTransaction(cartId, sessionId, trackingNumber, message, cardType, cardNumber, cardSecurityCode);
	}    	
	
	/*@Override*/
	@Transactional
	public void createTransactionsFromCartList(List<Cart> cartList, String sessionId, String message, String cardType, int cardNumber, int cardSecurityCode) {
		this.transactionDAO.createTransactionsFromCartList(cartList, sessionId, message, cardType, cardNumber, cardSecurityCode);
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
    	
}