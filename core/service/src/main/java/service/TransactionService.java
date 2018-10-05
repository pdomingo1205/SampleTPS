package service;

import dao.TransactionRepository;
import model.Transaction;
import util.DBaseUtil;

import java.util.List;

public class TransactionService {

    private TransactionRepository transactionRepository = new TransactionRepository(DBaseUtil.getConnection());

    public List<Transaction> findAll() {
        return transactionRepository.getAll();
    }

    public void createTransaction(Transaction newTransaction) {
        System.out.println(newTransaction.getTransactionId());
        try {
            if (transactionRepository.contains(newTransaction.getTransactionId())) {
                System.out.println(("Transaction already exists with transaction: " + newTransaction));
            }else{
                transactionRepository.add(newTransaction);
            }
        }catch(Exception e){
            System.out.println(("Transaction already exists with transaction: " + newTransaction));
        }
    }

    public Transaction findById(String id) {

        return transactionRepository.get(id);
    }

    public void updateTransaction(Transaction newTransaction) {
        transactionRepository.update(newTransaction);
    }


    public void deleteTransaction(Transaction transaction) {
        transactionRepository.remove(transaction);
    }
}
