package org.project;

import org.junit.Assert;
import org.junit.Test;
import org.project.factory.ExpenseFactory;
import org.project.manager.Manager;
import org.project.manager.impl.AggregrateManager;
import org.project.manager.impl.ExpenseManager;
import org.project.model.*;
import org.project.processor.Processor;
import org.project.processor.impl.AggregrateProcessor;
import org.project.processor.impl.EqualProcessor;
import org.project.processor.impl.ExactProcessor;
import org.project.processor.impl.PercentProcessor;
import org.project.result.Result;
import org.project.store.ExpenseStore;
import org.project.store.TransactionStore;
import org.project.store.UserStore;
import org.project.transaction.Transaction;
import org.project.user.UserManager;
import org.project.util.Util;
import org.project.validate.Validator;
import org.project.validate.impl.AggregrateValidator;
import org.project.validate.impl.EqualValidator;
import org.project.validate.impl.ExactValidator;
import org.project.validate.impl.PercentValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class SplitWise
{
    private UserManager userManager;
    private UserStore userStore;
    private  Map<String, Manager> managerMap;

    public SplitWise()
    {
        userStore = new UserStore();
        userManager = new UserManager(userStore);

        Manager expenseManager = new ExpenseManager();
        Manager aggregateManager = new AggregrateManager();

        TransactionStore transactionStore = new TransactionStore();
        ExpenseStore expenseStore = new ExpenseStore();

        Processor aggregrateProcessor = new AggregrateProcessor(transactionStore, expenseStore);

        Map<String, Processor> aggregrateProcessorMap = new HashMap<>();

        aggregrateProcessorMap.put("SHOW", aggregrateProcessor);

        aggregateManager.setProcessor(aggregrateProcessorMap);

        Processor equalProcessor = new EqualProcessor(transactionStore, expenseStore);
        Processor exactProcessor = new ExactProcessor(transactionStore, expenseStore);
        Processor percentProcessor = new PercentProcessor(transactionStore, expenseStore);

        Map<String, Processor> expenseProcessorMap = new HashMap<>();

        expenseProcessorMap.put("EQUAL", equalProcessor);
        expenseProcessorMap.put("EXACT", exactProcessor);
        expenseProcessorMap.put("PERCENT", percentProcessor);

        expenseManager.setProcessor(expenseProcessorMap);

        Validator exactValidator = new ExactValidator();
        Validator equalValidator = new EqualValidator();
        Validator percentValidator = new PercentValidator();


        Map<String, Validator> validatorMap = new HashMap<>();
        validatorMap.put("EQUAL", equalValidator);
        validatorMap.put("EXACT", exactValidator);
        validatorMap.put("PERCENT", percentValidator);

        expenseManager.setValidator(validatorMap);

        Validator aggregrateValidator = new AggregrateValidator();

        Map<String, Validator> aggregrateValidatorMap = new HashMap<>();
        aggregrateValidatorMap.put("SHOW", aggregrateValidator);
        aggregateManager.setValidator(aggregrateValidatorMap);

        managerMap = new HashMap<>();
        managerMap.put("EXPENSE", expenseManager);
        managerMap.put("SHOW", aggregateManager);

        ExpenseFactory.getInstance().setManagerMap(managerMap);
    }

    // User1 9986877301 mishra191@gmail.com
    // User2 9986877302 mishra192@gmail.com
    // User3 9986877303 mishra193@gmail.com
    // User4 9986877304 mishra194@gmail.com

    public boolean createUser(String string) throws Exception
    {
         String[] userList = string.split(" ");
         User user = new User();
         user.setUserName(userList[0]);
         user.setEmailId(userList[1]);
         user.setPhoneNumber(userList[2]);
         userManager.createUser(user);
         return true;
    }

    public Result issueCommand(Command command) throws Exception
    {
        Manager manager = ExpenseFactory.getInstance().getManager(command);
        Validate validate = manager.getValidator(command).validate(command);
        if(validate.getType() == ValidateType.FAILURE)
        {
            return new Result(validate.getType());
        }
        return manager.getProcessor(command).process(command);
    }

    public Command getCommand(String string) throws Exception
    {
        String[] splitString = string.split(" ");
        Command command = new Command();
        command.setName(splitString[0]);

         if (splitString[0].equals("EXPENSE"))
         {
             command.setObject(Util.getExpenseCommand(splitString));
         }

         else if (splitString[0].equals("SHOW"))
         {
             AggregrateCommand aggregrateCommand = new AggregrateCommand();
             if(splitString.length > 1)
             {
                 aggregrateCommand.setUserId(splitString[1]);
             }
             command.setObject(aggregrateCommand);
         }
        return command;
    }

    @Test
    public void testSplitWise() throws Exception
    {
        SplitWise splitWise = new SplitWise();
        splitWise.createUser("User1 9986877301 mishra191@gmail.com");
        splitWise.createUser("User2 9986877302 mishra192@gmail.com");
        splitWise.createUser("User3 9986877303 mishra193@gmail.com");
        splitWise.createUser("User4 9986877304 mishra194@gmail.com");

        Command command1 = splitWise.getCommand("EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL");
        Result result1 = splitWise.issueCommand(command1);

        Command command2 = splitWise.getCommand("EXPENSE u1 1250 2 u2 u3 EXACT 370 880");
        Result result2 = splitWise.issueCommand(command2);

        Command command3 = splitWise.getCommand("EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20");
        Result result3 = splitWise.issueCommand(command3);


        Command command4 = splitWise.getCommand("SHOW u1");
        Result result4 = splitWise.issueCommand(command4);

//        transactionList = {ArrayList@1165}  size = 6
//        5 = {Transaction@1187} "u1 owes u4: 0.0"
//        4 = {Transaction@1186} "u3 owes u1: 880.0"
//        3 = {Transaction@1185} "u2 owes u1: 370.0"
//        2 = {Transaction@1184} "u4 owes u1: 250.0"
//        1 = {Transaction@1183} "u3 owes u1: 250.0"
//        0 = {Transaction@1182} "u2 owes u1: 250.0"

        Assert.assertEquals(result4.getTransactionList().size(), 6);

        Assert.assertEquals(result4.getTransactionList().get(5).toString(), "u1 owes u4: 0.0");

        Assert.assertEquals(result4.getTransactionList().get(4).toString(), "u3 owes u1: 880.0");

        Assert.assertEquals(result4.getTransactionList().get(3).toString(), "u2 owes u1: 370.0");

        Assert.assertEquals(result4.getTransactionList().get(2).toString(), "u4 owes u1: 250.0");

        Assert.assertEquals(result4.getTransactionList().get(1).toString(), "u3 owes u1: 250.0");

        Assert.assertEquals(result4.getTransactionList().get(0).toString(), "u2 owes u1: 250.0");

        Command command5 = splitWise.getCommand("SHOW u4");
        Result result5 = splitWise.issueCommand(command5);

        Command command6 = splitWise.getCommand("SHOW u2");
        Result result6 = splitWise.issueCommand(command6);

        Command command7 = splitWise.getCommand("SHOW");
        Result result7 = splitWise.issueCommand(command7);

//        transactionList = {ArrayList@1228}  size = 16
//        0 = {Transaction@1234} "u2 owes u1: 250.0"
//        1 = {Transaction@1235} "u3 owes u1: 250.0"
//        2 = {Transaction@1236} "u4 owes u1: 250.0"
//        3 = {Transaction@1237} "u2 owes u1: 370.0"
//        4 = {Transaction@1238} "u3 owes u1: 880.0"
//        5 = {Transaction@1239} "u1 owes u4: 0.0"
//        6 = {Transaction@1234} "u2 owes u1: 250.0"
//        7 = {Transaction@1237} "u2 owes u1: 370.0"
//        8 = {Transaction@1240} "u2 owes u4: 0.0"
//        9 = {Transaction@1235} "u3 owes u1: 250.0"
//        10 = {Transaction@1238} "u3 owes u1: 880.0"
//        11 = {Transaction@1241} "u3 owes u4: 0.0"
//        12 = {Transaction@1236} "u4 owes u1: 250.0"
//        13 = {Transaction@1239} "u1 owes u4: 0.0"
//        14 = {Transaction@1240} "u2 owes u4: 0.0"
//        15 = {Transaction@1241} "u3 owes u4: 0.0"

        Assert.assertEquals(result7.getTransactionList().size(), 16);

        Assert.assertEquals(result7.getTransactionList().get(0).toString(), "u2 owes u1: 250.0");

        Assert.assertEquals(result7.getTransactionList().get(1).toString(), "u3 owes u1: 250.0");

        Assert.assertEquals(result7.getTransactionList().get(2).toString(), "u4 owes u1: 250.0");

        Assert.assertEquals(result7.getTransactionList().get(3).toString(), "u2 owes u1: 370.0");

        Assert.assertEquals(result7.getTransactionList().get(4).toString(), "u3 owes u1: 880.0");

        Assert.assertEquals(result7.getTransactionList().get(5).toString(), "u1 owes u4: 0.0");

        Assert.assertEquals(result7.getTransactionList().get(6).toString(), "u2 owes u1: 250.0");

        Assert.assertEquals(result7.getTransactionList().get(7).toString(), "u2 owes u1: 370.0");

        Assert.assertEquals(result7.getTransactionList().get(8).toString(), "u2 owes u4: 0.0");

        Assert.assertEquals(result7.getTransactionList().get(9).toString(), "u3 owes u1: 250.0");

        Assert.assertEquals(result7.getTransactionList().get(10).toString(), "u3 owes u1: 880.0");

        Assert.assertEquals(result7.getTransactionList().get(11).toString(), "u3 owes u4: 0.0");

        Assert.assertEquals(result7.getTransactionList().get(12).toString(), "u4 owes u1: 250.0");

        Assert.assertEquals(result7.getTransactionList().get(13).toString(), "u1 owes u4: 0.0");

        Assert.assertEquals(result7.getTransactionList().get(14).toString(), "u2 owes u4: 0.0");

        Assert.assertEquals(result7.getTransactionList().get(15).toString(), "u3 owes u4: 0.0");


    }
}
