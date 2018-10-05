package dao;

import model.Transaction;

import java.util.List;

import util.DBaseUtil;

public class TransactionDAO implements DaoInterface<Transaction, String> {

    public void saveOrUpdate(Transaction entity) {
        DBaseUtil.getConnection().create(entity);
    }

    public void update(Transaction entity) {
        DBaseUtil.getConnection().update(entity);
    }

    public Transaction findById(String s) {
        return DBaseUtil.getConnection().find(Transaction.class, s);
    }

    public void delete(Transaction entity) {
        DBaseUtil.getConnection().delete(entity);
    }

    public List findAll() {
        //return DBaseUtil.getConnection().
        return null;
    }
}
