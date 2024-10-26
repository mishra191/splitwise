package org.project.processor.impl;

import org.project.model.AggregrateCommand;
import org.project.model.Command;
import org.project.model.ExpenseCommand;
import org.project.model.ValidateType;
import org.project.processor.Processor;
import org.project.result.Result;
import org.project.store.ExpenseStore;
import org.project.store.TransactionStore;
import org.project.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class AggregrateProcessor implements Processor
{
    private TransactionStore transactionStore;
    private ExpenseStore expenseStore;

    public AggregrateProcessor(TransactionStore transactionStore, ExpenseStore expenseStore)
    {
        this.expenseStore = expenseStore;
        this.transactionStore = transactionStore;
    }
    @Override
    public Result process(Command command)
    {
        AggregrateCommand aggregrateCommand = (AggregrateCommand)command.getObject();
        String userId = aggregrateCommand.getUserId();
        List<Transaction> transactionList = new ArrayList<>();
        if(userId == null)
        {
            Map< String, List<Transaction>> expenseMap = expenseStore.getExpenseMap();
            for(String key : expenseMap.keySet())
            {
                for(Transaction transaction : expenseMap.get(key))
                {
                    transactionList.add(transaction);
                }
            }
        }
        else
        {
            expenseStore.getExpenseMap().get(userId).forEach(transactionList::add);
        }
        Result result = new Result(ValidateType.SUCCESS);
        result.setTransactionList(transactionList);
        return result;
    }

//    SHOW
//    SHOW u1
}
