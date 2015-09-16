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
	public void createTransaction(int cartId, long trackingNumber) {
		this.transactionDAO.createTransaction(cartId, trackingNumber);
	}    	
	
	/*@Override*/
	@Transactional
	public void createTransactionsFromCartList(List<Cart> cartList) {
		this.transactionDAO.createTransactionsFromCartList(cartList);
	} 	
    	
}