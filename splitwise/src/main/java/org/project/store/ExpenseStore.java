package org.project.store;

import org.project.transaction.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseStore
{
    private Map<String, List<Transaction>> expenseMap = new HashMap<>();

    public Map<String, List<Transaction>> getExpenseMap() {
        return expenseMap;
    }

    public void setExpenseMap(Map<String, List<Transaction>> expenseMap) {
        this.expenseMap = expenseMap;
    }
}
