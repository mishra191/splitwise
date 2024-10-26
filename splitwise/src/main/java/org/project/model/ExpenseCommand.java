package org.project.model;

import java.util.List;

public class ExpenseCommand
{

    private String sender;

    private String numberOfUsers;

    private List<String> listOfUsers;

    private String expenseType;

    private String amount;

    private List<String> expenseList;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getNumberOfUsers() {
        return numberOfUsers;
    }

    public void setNumberOfUsers(String numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }

    public List<String> getListOfUsers() {
        return listOfUsers;
    }

    public void setListOfUsers(List<String> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public List<String> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<String> expenseList) {
        this.expenseList = expenseList;
    }
}
