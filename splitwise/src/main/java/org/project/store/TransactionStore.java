package org.project.store;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.project.transaction.Transaction;

public class TransactionStore {

    private Map<Integer, Transaction> transactionMap = new HashMap<>();

    public Map<Integer, Transaction> getTransactionMap() {
        return transactionMap;
    }


    public void setTransactionMap(Map<Integer, Transaction> transactionMap) {
        this.transactionMap = transactionMap;
    }
}
