package com.journaldev.spring.dao;
 
import java.util.List;
 

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
 
import com.journaldev.spring.model.TransactionLog;

@Repository
public class TransactionDAOImpl implements TransactionDAO {
     
    private static final Logger logger = LoggerFactory.getLogger(TransactionDAOImpl.class);
    
    private SessionFactory sessionFactory;
     
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    
    /*    @Override*/
    public void save(TransactionLog t) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(t);
        tx.commit();
        session.close();
    }
 
    @SuppressWarnings("unchecked")
/*    @Override*/
    public List<TransactionLog> list() {
        Session session = this.sessionFactory.openSession();
        List<TransactionLog> transactionList = session.createQuery("from Transaction").list();
        session.close();
        return transactionList;
    }    
 
    /*@Override*/
    public void addTransaction(TransactionLog t) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(t);
        logger.info("Transaction saved successfully, Transaction Details="+t);
    }
 
    /*@Override*/
    public void updateTransaction(TransactionLog t) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(t);
        logger.info("Transaction updated successfully, Transaction Details="+t);
    }
 
    @SuppressWarnings("unchecked")
    /*@Override*/
    public List<TransactionLog> listTransactions() {
        Session session = this.sessionFactory.getCurrentSession();
        List<TransactionLog> transactionsList = session.createQuery("from Transaction").list();
        for(TransactionLog t : transactionsList){
            logger.info("Transaction List::"+t);
        }
        return transactionsList;
    }
 
    /*@Override*/
    public TransactionLog getTransactionById(int id) {
        Session session = this.sessionFactory.getCurrentSession();      
        TransactionLog t = (TransactionLog) session.load(TransactionLog.class, new Integer(id));
        logger.info("Transaction loaded successfully, Transaction details="+t);
        return t;
    }
 
    /*@Override*/
    public void removeTransaction(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        TransactionLog t = (TransactionLog) session.load(TransactionLog.class, new Integer(id));
        if(null != t){
            session.delete(t);
        }
        logger.info("Transaction deleted successfully, transaction details="+t);
    }
 
}