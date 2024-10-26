package org.project.processor.impl;

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



public class PercentProcessor implements Processor
  {

      private TransactionStore transactionStore;
      private ExpenseStore expenseStore;

    public PercentProcessor(TransactionStore transactionStore, ExpenseStore expenseStore)
    {
        this.expenseStore = expenseStore;
        this.transactionStore = transactionStore;
    }
    @Override
    public Result process(Command command) {
        ExpenseCommand expenseCommand = (ExpenseCommand)command.getObject();
        List<String> expenseList = expenseCommand.getExpenseList();
        List<String> listOfUsers = expenseCommand.getListOfUsers();
        int amount = Integer.parseInt(expenseCommand.getAmount());
        String sender = expenseCommand.getSender();
        Map<String, List<Transaction>> expenseMap = expenseStore.getExpenseMap();
        Map<Integer, Transaction> transactionMap = transactionStore.getTransactionMap();

        for(int count = 0; count < listOfUsers.size(); count++)
         {

            String user = listOfUsers.get(count);
            int expense = Integer.parseInt(expenseList.get(count));

            if(!user.equals(sender))
            {
                Transaction transaction = new Transaction(count, sender, user, (expense/100)*amount);
                transactionMap.put(transaction.getTransactionId(), transaction);

                if(expenseMap.containsKey(user))
                {
                   expenseMap.get(user).add(transaction);
                }
                else
                {
                    List<Transaction> list = new ArrayList<>();
                    list.add(transaction);
                    expenseMap.put(user, list);
                }

                if(expenseMap.containsKey(sender))
                {
                    expenseMap.get(sender).add(transaction);
                }
                else
                {
                    List<Transaction> list = new ArrayList<>();
                    list.add(transaction);
                    expenseMap.put(sender, list);
                }
            }
        }
        return new Result(ValidateType.SUCCESS);
    }

//    EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20
}
