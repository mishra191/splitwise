package org.project.util;

import org.project.model.ExpenseCommand;

import java.util.ArrayList;
import java.util.List;

public class Util
{

    public static ExpenseCommand getExpenseCommand(String[] commandList) throws Exception
    {
        ExpenseCommand expenseCommand = new ExpenseCommand();

        expenseCommand.setSender(commandList[1]);
        expenseCommand.setAmount(commandList[2]);

        int numberOfUsers = Integer.parseInt(commandList[3]);
        expenseCommand.setNumberOfUsers(commandList[3]);

        List<String> usersList = new ArrayList<>();

        for(int count = 4; count < 4+numberOfUsers; count++)
        {
            usersList.add(commandList[count]);
        }

        expenseCommand.setListOfUsers(usersList);

        String expenseType = commandList[4+numberOfUsers];
        expenseCommand.setExpenseType(expenseType);

        List<String> expenseList = new ArrayList<>();

        if(expenseType.equals("EXACT") || expenseType.equals("PERCENT"))
        {
            int start = 5+numberOfUsers;
            for (int count = start; count < start + numberOfUsers; count++)
            {
                expenseList.add(commandList[count]);
            }

            expenseCommand.setExpenseList(expenseList);
        }

        return expenseCommand;
    }

   // EXPENSE u1 1250 2 u2 u3 EXACT 370 880
}
