package org.project.manager.impl;

import org.project.manager.Manager;
import org.project.model.Command;
import org.project.model.ExpenseCommand;
import org.project.processor.Processor;
import org.project.validate.Validator;

import java.util.Map;
import java.util.HashMap;

public class ExpenseManager implements Manager
{

    Map<String, Processor> processorMap = new HashMap<>();

    Map<String, Validator> validatorMap = new HashMap<>();

    @Override
    public void setProcessor(Map<String, Processor> processorMap)
    {
        this.processorMap = processorMap;
    }

    @Override
    public Processor getProcessor(Command command) {
        ExpenseCommand expenseCommand = (ExpenseCommand)command.getObject();
        return processorMap.get(expenseCommand.getExpenseType());
    }

    @Override
    public void setValidator(Map<String, Validator> validatorMap)
    {
        this.validatorMap = validatorMap;
    }

    @Override
    public Validator getValidator(Command command)
    {
        ExpenseCommand expenseCommand = (ExpenseCommand)command.getObject();
        return validatorMap.get(expenseCommand.getExpenseType());
    }
}


