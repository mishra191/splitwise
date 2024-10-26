package org.project.result;

import org.project.model.ValidateType;
import org.project.transaction.Transaction;

import java.util.List;

public class Result
{
    List<Transaction> transactionList;

    ValidateType type;

    public Result(ValidateType type)
    {
        this.type = type;
    }

    public void setTransactionList(List<Transaction> transactionList)
    {
        this.transactionList = transactionList;
    }

    public List<Transaction> getTransactionList()
    {
        return transactionList;
    }
}
