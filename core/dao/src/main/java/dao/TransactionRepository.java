package dao;

import model.Transaction;
import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;

import java.util.List;

public class TransactionRepository extends CouchDbRepositorySupport<Transaction> {

    public TransactionRepository(CouchDbConnector db) {
        super(Transaction.class, db);
    }


}
