package org.project.validate.impl;

import org.project.model.Command;
import org.project.model.ExpenseCommand;
import org.project.model.Validate;
import org.project.model.ValidateType;
import org.project.validate.Validator;

import java.util.List;


public class ExactValidator implements Validator {


    @Override
    public Validate validate(Command command) throws Exception
    {
        ExpenseCommand expenseCommand = (ExpenseCommand)command.getObject();
        List<String> sumList = expenseCommand.getExpenseList();
        int amount = Integer.parseInt(expenseCommand.getAmount());

        for(String sum : sumList){
            amount -= Integer.parseInt(sum);
        }

        if(amount != 0){
            return new Validate(ValidateType.FAILURE);
        }
        return new Validate(ValidateType.SUCCESS);
    }


    //   In case of percent, you need to verify if
//     the total sum of percentage shares is 100 or not.
//     In case of exact, you need to
//     verify if the total sum of shares is equal to the total amount or not.
}
